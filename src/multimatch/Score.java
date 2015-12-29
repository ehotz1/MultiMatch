package multimatch;

/**
 *
 * @author Ethan
 */
public class Score {
    private int totalScore;
    private int currentScore;
    private int totalErrors;
    private int currentErrors;
    private int numberOfRounds;
    private int round;
    
    public Score(int number) {
        this.totalScore = 0;
        this.currentScore = 0;
        this.currentErrors = 0;
        this.totalErrors = 0;
        this.numberOfRounds = number;
        this.round = 1;
    }
    
    public void setTotalScore(int score) {
        this.totalScore = score;
    }
    
    public void setCurrentScore(int score) {
        this.currentScore = score;
    }
    
    public int getTotalScore() {
        return this.totalScore;
    }
    
    public int getCurrentScore() {
        return this.currentScore;
    }
    
    public void setCurrentErrors(int errors) {
        this.currentErrors = errors;
    }
    
    public void setTotalErrors(int errors) {
        this.totalErrors = errors;
    }
    
    public int getTotalErrors() {
        return this.totalErrors;
    }
    
    public int getCurrentErrors() {
        return this.currentErrors;
    }
    
    public int calculateAverageScore() {
        return this.totalScore/this.round;
    }
    
    public int calculateAverageErrors() {
        return this.totalErrors/this.round;
    }
    
    public void addScore() {
        this.currentScore++;
    }
    
    public void addError() {
        this.currentErrors++;
    }
    
    public void nextRound() {
        this.round++;
        this.totalScore += this.currentScore;
        this.totalErrors += this.currentErrors;
        this.currentErrors = 0;
        this.currentScore = 0;
    }
    
    
    
    
    
}
