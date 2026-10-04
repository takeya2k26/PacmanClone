import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private Maze maze;
    private PacMan pacman;
    private ScoreManager scoreManager;
    private SoundManager soundManager;

    private RedGhost redGhost;
    private BrownGhost brownGhost;
    private BlueGhost blueGhost;
    private YellowGhost yellowGhost;
    private Ghost[] ghosts;

    private GameState state = GameState.MENU;
    private int level = 1;
    private static final int MAX_LEVEL = 2;
    private static final double LEVEL2_SPEED_FACTOR = 1.3;

    private static final int FRIGHTENED_DURATION = 480; // 8 seconds at 60 FPS
    private int frightenedTimer = 0;

    public GamePanel() {
        maze = new Maze();
        pacman = new PacMan(maze, 1, 1);
        scoreManager = new ScoreManager();
        soundManager = new SoundManager();
redGhost    = new RedGhost(maze, 1, 8, pacman);
brownGhost  = new BrownGhost(maze, 1, 17, pacman);
blueGhost   = new BlueGhost(maze, 19, 1, pacman, redGhost);
yellowGhost = new YellowGhost(maze, 19, 17, pacman);
        ghosts = new Ghost[] { redGhost, brownGhost, blueGhost, yellowGhost };

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(
            Maze.COLS * Maze.TILE_SIZE,
            Maze.ROWS * Maze.TILE_SIZE + 40));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                if (state == GameState.MENU) {
                    if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                        state = GameState.PLAYING;
                    }
                    return;
                }

                if (state == GameState.GAME_OVER || (state == GameState.WIN && level >= MAX_LEVEL)) {
                    if (code == KeyEvent.VK_ENTER) {
                        restartGame();
                    }
                    return;
                }

                if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
                    togglePause();
                    return;
                }

                if (state != GameState.PLAYING) return;
                switch (code) {
                    case KeyEvent.VK_UP:    pacman.setDirection(0, -1); break;
                    case KeyEvent.VK_DOWN:  pacman.setDirection(0, 1); break;
                    case KeyEvent.VK_LEFT:  pacman.setDirection(-1, 0); break;
                    case KeyEvent.VK_RIGHT: pacman.setDirection(1, 0); break;
                }
            }
        });

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void togglePause() {
        if (state == GameState.PLAYING) {
            state = GameState.PAUSED;
            soundManager.stopSiren();
        } else if (state == GameState.PAUSED) {
            state = GameState.PLAYING;
        }
    }

    private void restartGame() {
        level = 1;
        scoreManager.resetAll();
        maze.resetGrid();
        pacman.resetToSpawn();
        for (Ghost ghost : ghosts) ghost.resetToSpawn();
        frightenedTimer = 0;
        state = GameState.PLAYING;
    }

    private void nextLevel() {
        level++;
        maze.resetGrid();
        pacman.resetToSpawn();
        for (Ghost ghost : ghosts) {
            ghost.resetToSpawn();
            ghost.increaseSpeed(LEVEL2_SPEED_FACTOR);
        }
        frightenedTimer = 0;
        state = GameState.PLAYING;
    }

    @Override
    public void run() {
        while (true) {
            if (state == GameState.PLAYING) {
                pacman.update();
                for (Ghost ghost : ghosts) ghost.update();

                // Siren only plays while frightened mode is active
                if (frightenedTimer > 0) {
                    soundManager.startSiren();
                } else {
                    soundManager.stopSiren();
                }

                int eaten = pacman.eatDot();
                if (eaten == 2) {
                    scoreManager.addDot();
                    soundManager.play("chomp");
                }
                if (eaten == 3) {
                    scoreManager.addPowerPellet();
                    activateFrightenedMode();
                }

                updateFrightenedTimer();
                checkGhostCollisions();

                if (maze.countDots() == 0) {
                    soundManager.stopSiren();
                    if (level < MAX_LEVEL) {
                        nextLevel();
                    } else {
                        state = GameState.WIN;
                    }
                }
            }
            repaint();
            try { Thread.sleep(16); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    private void activateFrightenedMode() {
        frightenedTimer = FRIGHTENED_DURATION;
        for (Ghost ghost : ghosts) ghost.setFrightened(true);
    }

    private void updateFrightenedTimer() {
        if (frightenedTimer > 0) {
            frightenedTimer--;
            if (frightenedTimer == 0) {
                for (Ghost ghost : ghosts) ghost.setFrightened(false);
            }
        }
    }

    private void checkGhostCollisions() {
        for (Ghost ghost : ghosts) {
            if (ghost.getRow() == pacman.getRow() && ghost.getCol() == pacman.getCol()) {
                if (ghost.isFrightened()) {
                    scoreManager.addGhostEaten();
                    soundManager.play("eatGhost");
                    ghost.resetToSpawn();
                } else {
                    soundManager.stopSiren();
                    soundManager.play("death");
                    scoreManager.loseLife();
                    if (scoreManager.isGameOver()) {
                        state = GameState.GAME_OVER;
                    } else {
                        resetPositions();
                    }
                    break;
                }
            }
        }
    }

    private void resetPositions() {
        pacman.resetToSpawn();
        for (Ghost ghost : ghosts) ghost.resetToSpawn();
        frightenedTimer = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (state == GameState.MENU) {
            drawMenu(g2);
            return;
        }

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.setColor(Color.WHITE);
        g2.drawString("SCORE", 10, 18);
        g2.setColor(Color.YELLOW);
        g2.drawString(String.valueOf(scoreManager.getScore()), 10, 34);

        g2.setColor(Color.WHITE);
        g2.drawString("LIVES", 180, 18);
        for (int i = 0; i < scoreManager.getLives(); i++) {
            g2.setColor(Color.YELLOW);
            g2.fillOval(180 + i * 22, 22, 14, 14);
        }

        g2.setColor(Color.WHITE);
        g2.drawString("LEVEL " + level, 320, 18);

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("PAC-MAN", 85, 25);

        Graphics2D g2maze = (Graphics2D) g2.create();
        g2maze.translate(0, 40);
        maze.draw(g2maze);
        pacman.draw(g2maze);
        for (Ghost ghost : ghosts) ghost.draw(g2maze);
        g2maze.dispose();

        if (state == GameState.WIN) drawOverlay(g2, "YOU WIN!", Color.YELLOW);
        else if (state == GameState.GAME_OVER) drawOverlay(g2, "GAME OVER", Color.RED);
        else if (state == GameState.PAUSED) drawOverlay(g2, "PAUSED", Color.WHITE);
    }

    private void drawMenu(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics fmTitle = g2.getFontMetrics();
        String title = "PAC-MAN";
        g2.drawString(title, (getWidth() - fmTitle.stringWidth(title)) / 2, getHeight() / 2 - 60);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        FontMetrics fmSub = g2.getFontMetrics();
        String sub = "Press ENTER or SPACE to start";
        g2.drawString(sub, (getWidth() - fmSub.stringWidth(sub)) / 2, getHeight() / 2);

        String sub2 = "Arrow keys to move  |  P to pause";
        g2.drawString(sub2, (getWidth() - fmSub.stringWidth(sub2)) / 2, getHeight() / 2 + 26);
    }

    private void drawOverlay(Graphics2D g2, String message, Color color) {
        g2.setColor(new Color(0, 0, 0, 160));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.setColor(color);
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g2.drawString(message, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        String hint = null;
        if (message.equals("PAUSED")) hint = "Press P or ESC to resume";
        else if (message.equals("GAME OVER")) hint = "Press ENTER to play again";
        else if (message.equals("YOU WIN!") && level >= MAX_LEVEL) hint = "Press ENTER to play again";

        if (hint != null) {
            int hx = (getWidth() - g2.getFontMetrics().stringWidth(hint)) / 2;
            g2.drawString(hint, hx, y + 30);
        }
    }
}