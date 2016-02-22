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
    private int currentRound;
    private final int totalRounds;
    
    public Score(int number) {
        this.totalScore = 0;
        this.currentScore = 0;
        this.currentErrors = 0;
        this.totalErrors = 0;
        this.currentRound = 0;
        this.totalRounds = number;
    }
    
    public int getCurrentRound() {
        return this.currentRound;
    }
    
    public int getTotalRounds() {
        return this.totalRounds;
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
    
    public double getTotalErrors() {
        return this.totalErrors;
    }
    
    public int getCurrentErrors() {
        return this.currentErrors;
    }
    
    public double calculateAverageScore() {
        double score = (double)totalScore;
        double rounds = (double)totalRounds;
        return score/rounds;
    }
    
    public double calculateAverageErrors() {
        double errors = (double)totalErrors;
        double rounds = (double)totalRounds;
        return errors/rounds;
    }
    
    public void addScore() {
        this.currentScore++;
    }
    
    public void addError() {
        this.currentErrors++;
    }
    
    public void nextRound() {
        this.currentRound++;
        this.totalScore += this.currentScore;
        this.totalErrors += this.currentErrors;
        this.currentErrors = 0;
        this.currentScore = 0;
    }
    
    public void saveData() {
        String results;
        results = "TotalScore:"+totalScore+";TotalErrors:"+totalErrors+";AvgScore:"+calculateAverageScore()+";"
                + "AvgErrors:"+calculateAverageErrors()+";TotalRounds:"+totalRounds;
        Mailer mailer = new Mailer(results);
    }
    
    
    
}
