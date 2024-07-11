package crapsgame;

class Calculation {
    private int point;
    private boolean gameWon;
    private boolean pointPhase = false; // Start with pointPhase set to false

    public void play(int sum) {
        if (!pointPhase) {
            // First throw
            if (sum == 7 || sum == 11) {
                gameWon = true;
            } else if (sum == 2 || sum == 3 || sum == 12) {
                gameWon = false;
            } else {
                point = sum;
                pointPhase = true; // Transition to point phase
            }
        } else {
            // Subsequent throws (point phase)
            if (sum == point) {
                gameWon = true;
            } else if (sum == 7) {
                gameWon = false;
            }
        }
    }

    public boolean hasWon() {
        return gameWon;
    }

    public int getPoint() {
        return point;
    }

    public boolean isPointPhase() {
        return pointPhase;
    }
}