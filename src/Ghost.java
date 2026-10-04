
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.util.Random;

public class Ghost {

    protected int row, col;
    protected int startRow, startCol;      // remembered for resetToSpawn()

    protected double x, y;

    protected int dx, dy;

    protected double normalSpeed = 1.5;
    protected double frightenedSpeed = 0.75;
    protected double speed = normalSpeed;

    protected BufferedImage sprite;

    protected Maze maze;
    protected PacMan pacman;

    protected Random random = new Random();

    protected boolean frightened = false;

    public Ghost(
            Maze maze,
            int startRow,
            int startCol,
            String spritePath,
            PacMan pacman) {

        this.maze = maze;

        this.row = startRow;
        this.col = startCol;

        this.startRow = startRow;
        this.startCol = startCol;

        this.x = col * Maze.TILE_SIZE;
        this.y = row * Maze.TILE_SIZE;

        this.dx = 0;
        this.dy = 0;

        this.pacman = pacman;

        // Load ghost image from inside the JAR
        try {

            InputStream imageStream =
                    Ghost.class.getResourceAsStream(
                            spritePath
                    );

            if (imageStream != null) {

                sprite = ImageIO.read(imageStream);
                imageStream.close();

            } else {

                System.out.println(
                        "Could not find sprite inside JAR: "
                        + spritePath
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Could not load sprite: "
                    + spritePath
            );

            e.printStackTrace();
        }
    }

    public void update() {

        if (isAligned()) {
            chooseDirection();
        }

        if (canMove(dx, dy)) {

            x += dx * speed;
            y += dy * speed;

        } else {

            x = col * Maze.TILE_SIZE;
            y = row * Maze.TILE_SIZE;

            dx = 0;
            dy = 0;
        }

        row = (int) Math.round(y / Maze.TILE_SIZE);
        col = (int) Math.round(x / Maze.TILE_SIZE);

        // Tunnel wrap-around
        if (x < -Maze.TILE_SIZE) {
            x = Maze.COLS * Maze.TILE_SIZE;
        }

        if (x > Maze.COLS * Maze.TILE_SIZE) {
            x = -Maze.TILE_SIZE;
        }
    }

    // Default target: chase Pac-Man directly.
    // Subclasses override this to give each ghost its own personality.
    protected int[] getTargetTile() {

        return new int[] {
                pacman.getRow(),
                pacman.getCol()
        };
    }

    protected void chooseDirection() {

        int[][] directions = {
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0}
        };

        // While frightened, wander randomly instead of chasing
        if (frightened) {

            java.util.List<int[]> options =
                    new java.util.ArrayList<>();

            for (int[] d : directions) {

                if (d[0] == -dx && d[1] == -dy) {
                    continue;
                }

                if (canMove(d[0], d[1])) {
                    options.add(d);
                }
            }

            if (options.isEmpty()) {

                for (int[] d : directions) {

                    if (canMove(d[0], d[1])) {
                        options.add(d);
                    }
                }
            }

            if (!options.isEmpty()) {

                int[] choice =
                        options.get(
                                random.nextInt(options.size())
                        );

                dx = choice[0];
                dy = choice[1];
            }

            return;
        }

        // Normal mode: greedy chase toward target
        int[] target = getTargetTile();

        int bestDx = 0;
        int bestDy = 0;

        double bestDist = Double.MAX_VALUE;

        boolean found = false;

        for (int[] d : directions) {

            if (d[0] == -dx && d[1] == -dy) {
                continue;
            }

            if (!canMove(d[0], d[1])) {
                continue;
            }

            int nextRow = row + d[1];
            int nextCol = col + d[0];

            double dist =
                    distance(
                            nextRow,
                            nextCol,
                            target[0],
                            target[1]
                    );

            if (dist < bestDist) {

                bestDist = dist;
                bestDx = d[0];
                bestDy = d[1];

                found = true;
            }
        }

        if (!found) {

            for (int[] d : directions) {

                if (!canMove(d[0], d[1])) {
                    continue;
                }

                int nextRow = row + d[1];
                int nextCol = col + d[0];

                double dist =
                        distance(
                                nextRow,
                                nextCol,
                                target[0],
                                target[1]
                        );

                if (dist < bestDist) {

                    bestDist = dist;
                    bestDx = d[0];
                    bestDy = d[1];

                    found = true;
                }
            }
        }

        if (!found) {

            for (int[] d : directions) {

                if (canMove(d[0], d[1])) {

                    bestDx = d[0];
                    bestDy = d[1];

                    found = true;

                    break;
                }
            }
        }

        if (found) {

            dx = bestDx;
            dy = bestDy;

        } else {

            dx = 0;
            dy = 0;
        }
    }

    protected double distance(
            int r1,
            int c1,
            int r2,
            int c2) {

        int dr = r1 - r2;
        int dc = c1 - c2;

        return Math.sqrt(dr * dr + dc * dc);
    }

    protected boolean canMove(
            int testDx,
            int testDy) {

        if (testDx == 0 && testDy == 0) {
            return false;
        }

        double nextX =
                x + testDx * speed;

        double nextY =
                y + testDy * speed;

        int checkRow =
                (int) Math.round(
                        nextY / Maze.TILE_SIZE
                );

        int checkCol =
                (int) Math.round(
                        nextX / Maze.TILE_SIZE
                );

        return !maze.isWall(
                checkRow,
                checkCol
        );
    }

    protected boolean isAligned() {

        double diffX =
                x - col * Maze.TILE_SIZE;

        double diffY =
                y - row * Maze.TILE_SIZE;

        return Math.abs(diffX) < speed
                && Math.abs(diffY) < speed;
    }

    // Frightened mode
    public void setFrightened(boolean f) {

        frightened = f;

        speed = f
                ? frightenedSpeed
                : normalSpeed;
    }

    public boolean isFrightened() {
        return frightened;
    }

    public void resetToSpawn() {

        row = startRow;
        col = startCol;

        x = col * Maze.TILE_SIZE;
        y = row * Maze.TILE_SIZE;

        dx = 0;
        dy = 0;

        setFrightened(false);
    }

    // Level speed scaling
    public void increaseSpeed(double factor) {

        normalSpeed *= factor;

        if (!frightened) {
            speed = normalSpeed;
        }
    }

    public void draw(Graphics g) {

        if (frightened) {

            g.setColor(Color.BLUE);

            g.fillOval(
                    (int) x,
                    (int) y,
                    Maze.TILE_SIZE,
                    Maze.TILE_SIZE
            );

            g.setColor(Color.WHITE);

            g.drawOval(
                    (int) x,
                    (int) y,
                    Maze.TILE_SIZE,
                    Maze.TILE_SIZE
            );

        } else if (sprite != null) {

            g.drawImage(
                    sprite,
                    (int) x,
                    (int) y,
                    Maze.TILE_SIZE,
                    Maze.TILE_SIZE,
                    null
            );

        } else {

            // Fallback if image cannot be loaded
            g.setColor(Color.RED);

            g.fillOval(
                    (int) x,
                    (int) y,
                    Maze.TILE_SIZE,
                    Maze.TILE_SIZE
            );
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

