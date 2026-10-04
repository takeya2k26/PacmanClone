import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class PacMan {
    private int row, col;              // current tile position
    private int startRow, startCol;    // remembered for resetToSpawn()
    private double x, y;               // pixel position (smooth movement)
    private int dx, dy;                // current direction
    private int nextDx, nextDy;        // queued direction (from key press)
    private double speed = 2.0;
    private BufferedImage sprite;
    private Maze maze;

    private int animFrame = 0;         // 0 = mouth open, 1 = mouth closed
    private int animCounter = 0;
    private static final int ANIM_SPEED = 6; // lower = faster chomp

    public PacMan(Maze maze, int startRow, int startCol) {
        this.maze = maze;
        this.row = startRow;
        this.col = startCol;
        this.startRow = startRow;
        this.startCol = startCol;
        this.x = col * Maze.TILE_SIZE;
        this.y = row * Maze.TILE_SIZE;
        this.dx = 0;
        this.dy = 0;
        this.nextDx = 0;
        this.nextDy = 0;

        try {
            sprite = ImageIO.read(new File("assets/sprites/PacMan.png"));
        } catch (Exception e) {
            System.out.println("Could not load PacMan.png: " + e.getMessage());
        }
    }

    public void setDirection(int newDx, int newDy) {
        nextDx = newDx;
        nextDy = newDy;
    }

    // Returns tile type eaten: 2=dot, 3=power pellet, 0=nothing
    public int eatDot() {
        int tile = maze.getTile(row, col);
        if (tile == 2 || tile == 3) {
            maze.setTile(row, col, 0);
            return tile;
        }
        return 0;
    }

    public void update() {
        boolean centered = Math.abs(x - col * Maze.TILE_SIZE) < speed
                         && Math.abs(y - row * Maze.TILE_SIZE) < speed;

        if (centered && canMove(nextDx, nextDy)) {
            dx = nextDx;
            dy = nextDy;
        }

        if (canMove(dx, dy)) {
            x += dx * speed;
            y += dy * speed;
            animCounter++;
            if (animCounter >= ANIM_SPEED) {
                animCounter = 0;
                animFrame = 1 - animFrame; // toggle 0/1
            }
        } else {
            // Snap to grid to avoid jitter against walls
            x = col * Maze.TILE_SIZE;
            y = row * Maze.TILE_SIZE;
            dx = 0;
            dy = 0;
        }

        // Update row/col based on pixel position
        row = (int) Math.round(y / Maze.TILE_SIZE);
        col = (int) Math.round(x / Maze.TILE_SIZE);

        // Tunnel wrap-around (left/right edges)
        if (x < -Maze.TILE_SIZE) x = Maze.COLS * Maze.TILE_SIZE;
        if (x > Maze.COLS * Maze.TILE_SIZE) x = -Maze.TILE_SIZE;
    }

    private boolean canMove(int testDx, int testDy) {
        if (testDx == 0 && testDy == 0) return false;
        double nextX = x + testDx * speed;
        double nextY = y + testDy * speed;
        int checkRow = (int) Math.round(nextY / Maze.TILE_SIZE);
        int checkCol = (int) Math.round(nextX / Maze.TILE_SIZE);
        return !maze.isWall(checkRow, checkCol);
    }

    // ---- Respawn after losing a life (Week 8) ----
    public void resetToSpawn() {
        row = startRow;
        col = startCol;
        x = col * Maze.TILE_SIZE;
        y = row * Maze.TILE_SIZE;
        dx = 0; dy = 0;
        nextDx = 0; nextDy = 0;
    }

    // ---- Rendering with rotation / chomp animation (Week 9) ----
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int size = Maze.TILE_SIZE;

        double angle = 0; // default: facing right
        if (dx == -1) angle = Math.PI;
        else if (dy == -1) angle = -Math.PI / 2;
        else if (dy == 1) angle = Math.PI / 2;

        if (sprite != null) {
            g2.rotate(angle, x + size / 2.0, y + size / 2.0);
            g2.drawImage(sprite, (int) x, (int) y, size, size, null);
        } else {
            g2.setColor(Color.YELLOW);
            if (animFrame == 0) {
                // Mouth open: draw as a pac-man arc facing movement direction
                int startAngle = (int) Math.toDegrees(-angle) + 30;
                g2.fillArc((int) x, (int) y, size, size, startAngle, 300);
            } else {
                // Mouth closed: full circle
                g2.fillOval((int) x, (int) y, size, size);
            }
        }
        g2.dispose();
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public double getX() { return x; }
    public double getY() { return y; }
    public int getDx() { return dx; }
    public int getDy() { return dy; }
    public int getAnimFrame() { return animFrame; }
}