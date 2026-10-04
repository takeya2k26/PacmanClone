public class BrownGhost extends Ghost {
    public BrownGhost(Maze maze, int startRow, int startCol, PacMan pacman) {
        super(maze, startRow, startCol, "assets/sprites/BrownGhost.png", pacman);
    }

    @Override
    protected int[] getTargetTile() {
        int targetRow = pacman.getRow() + pacman.getDy() * 4;
        int targetCol = pacman.getCol() + pacman.getDx() * 4;
        return new int[] { targetRow, targetCol };
    }
}