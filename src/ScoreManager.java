public class ScoreManager {
    private int score;
    private int lives;
    private int ghostEatMultiplier;

    public ScoreManager() {
        this.score = 0;
        this.lives = 3;
        this.ghostEatMultiplier = 0;
    }

    public void addDot() { score += 10; }

    public void addPowerPellet() {
        score += 50;
        ghostEatMultiplier = 0;   // reset combo each time a new pellet is eaten
    }

    // Eating ghosts back-to-back scores more: 200, 400, 800, 1600...
    public void addGhostEaten() {
        ghostEatMultiplier++;
        score += 200 * ghostEatMultiplier;
    }

    public void loseLife() { lives--; }

    // ---- Full restart (Week 10) ----
    public void resetAll() {
        score = 0;
        lives = 3;
        ghostEatMultiplier = 0;
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public boolean isGameOver() { return lives <= 0; }
}