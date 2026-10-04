public class RedGhost extends Ghost {
    public RedGhost(Maze maze, int startRow, int startCol, PacMan pacman) {
        super(maze, startRow, startCol, "assets/sprites/RedGhost.png", pacman);
    }

    @Override
    protected int[] getTargetTile() {
        return new int[] { pacman.getRow(), pacman.getCol() };
    }
}