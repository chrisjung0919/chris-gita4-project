package crapsgame;



class Calculation {

    private int point;

    private boolean gameWon;

    private boolean pointPhase = false;



    public void play(int sum) {

        if (!pointPhase) {

            if (sum == 7 || sum == 11) {

                gameWon = true;

            } else if (sum == 2 || sum == 3 || sum == 12) {

                gameWon = false;

            } else {

                point = sum;

                pointPhase = true;

            }

        } else {

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



    public void reset() {

        point = 0;

        gameWon = false;

        pointPhase = false;

    }

}