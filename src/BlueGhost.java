public class BlueGhost extends Ghost {
    private RedGhost redGhost;

    public BlueGhost(Maze maze, int startRow, int startCol, PacMan pacman, RedGhost redGhost) {
        super(maze, startRow, startCol, "assets/sprites/BlueGhost.png", pacman);
        this.redGhost = redGhost;
    }

    @Override
    protected int[] getTargetTile() {
        int aheadRow = pacman.getRow() + pacman.getDy() * 2;
        int aheadCol = pacman.getCol() + pacman.getDx() * 2;

        int targetRow = aheadRow + (aheadRow - redGhost.getRow());
        int targetCol = aheadCol + (aheadCol - redGhost.getCol());

        return new int[] { targetRow, targetCol };
    }
}