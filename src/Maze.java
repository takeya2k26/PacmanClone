import java.awt.*;

public class Maze {
    public static final int TILE_SIZE = 24;
    public static final int ROWS = 21;
    public static final int COLS = 19;

    // 0 = empty path, 1 = wall, 2 = dot, 3 = power pellet
    private int[][] grid = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,2,1},
        {1,3,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,3,1},
        {1,2,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
        {1,2,2,2,2,2,2,1,1,0,1,1,2,2,2,2,2,2,1},
        {1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1},
        {0,0,0,0,1,2,1,0,0,0,0,0,1,2,1,0,0,0,0},
        {1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1},
        {1,2,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,2,1},
        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,2,1},
        {1,3,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,3,1},
        {1,2,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private int[][] originalGrid;

    public Maze() {
        // Deep copy for level resets (Week 10)
        originalGrid = new int[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                originalGrid[r][c] = grid[r][c];
            }
        }
    }

    public int getTile(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return 1;
        return grid[row][col];
    }

    public void setTile(int row, int col, int value) { grid[row][col] = value; }
    public boolean isWall(int row, int col) { return getTile(row, col) == 1; }

    public int countDots() {
        int count = 0;
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                if (grid[r][c] == 2 || grid[r][c] == 3) count++;
        return count;
    }

    // ---- Level reset (Week 10) ----
    public void resetGrid() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = originalGrid[r][c];
            }
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int tile = grid[row][col];
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                if (tile == 1) {
                    g2.setColor(new Color(20, 20, 90));
                    g2.fillRoundRect(x+1, y+1, TILE_SIZE-2, TILE_SIZE-2, 6, 6);
                    g2.setColor(new Color(33, 99, 255));
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRoundRect(x+1, y+1, TILE_SIZE-2, TILE_SIZE-2, 6, 6);
                } else if (tile == 2) {
                    g2.setColor(new Color(255, 204, 153));
                    g2.fillOval(x+TILE_SIZE/2 - 2, y + TILE_SIZE/2 - 2, 4, 4);
                } else if (tile == 3) {
                    g2.setColor(new Color(255, 204, 153));
                    g2.fillOval(x+TILE_SIZE/2 - 5, y + TILE_SIZE/2 - 5, 10, 10);
                }
            }
        }
    }
}