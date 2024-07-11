package testscore;

public class calc {
    private double totalScore;
    private double highestScore;
    private double averageScore;
    private int numberOfTests;

    public calc() {
        this.totalScore = 0;
        this.highestScore = Double.MIN_VALUE; // Initialize highestScore with the smallest possible value
        this.averageScore = 0;
        this.numberOfTests = 0;
    }

    public void calculateScores(double testScore) {
        // Update totalScore
        totalScore += testScore;

        // Update highestScore if the current test score is higher
        if (testScore > highestScore) {
            highestScore = testScore;
        }

        // Increment the number of tests
        numberOfTests++;

        // Calculate the average score
        if (numberOfTests > 0) {
            averageScore = totalScore / numberOfTests;
        } else {
            averageScore = 0;
        }
    }

    public double getTotalScore() {
        return totalScore;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public char calculateLetterGrade(double score) {
        int scoreInt = (int) score;
        char letterGrade;

        switch (scoreInt / 10) {
            case 9:
            case 10:
                letterGrade = 'A';
                break;
            case 8:
                letterGrade = 'B';
                break;
            case 7:
                letterGrade = 'C';
                break;
            case 6:
                letterGrade = 'D';
                break;
            default:
                letterGrade = 'F';
                break;
        }

        return letterGrade;
    }
}
