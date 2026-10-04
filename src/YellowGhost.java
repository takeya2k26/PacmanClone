public class YellowGhost extends Ghost {
    private final int scatterRow = Maze.ROWS - 2;
    private final int scatterCol = 1;

    public YellowGhost(Maze maze, int startRow, int startCol, PacMan pacman) {
        super(maze, startRow, startCol, "assets/sprites/YellowGhost.png", pacman);
    }

    @Override
    protected int[] getTargetTile() {
        int dr = row - pacman.getRow();
        int dc = col - pacman.getCol();
        double dist = Math.sqrt(dr * dr + dc * dc);

        if (dist > 8) {
            return new int[] { pacman.getRow(), pacman.getCol() };
        } else {
            return new int[] { scatterRow, scatterCol };
        }
    }
}