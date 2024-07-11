class CalcDice {
    private int[] sumCounts = new int[11];
    private int totalRolls = 0;
    private double[] probabilities = new double[11];

    public CalcDice() {
    	rollDice();
    }

    public void rollDice() {
        int die1 = rollDie();
        int die2 = rollDie();
        int sum = die1 + die2;

        // Update counts
        sumCounts[sum - 2]++;
        totalRolls++;
        calculateProbabilities();
    }

    private int rollDie() {
        return (int) (Math.random() * 6) + 1; // Generate a random number between 1 and 6
    }

    public int getSumCount(int sum) {
        return sumCounts[sum - 2];
    }

    private void calculateProbabilities() {
        for (int i = 2; i <= 12; i++) {
            probabilities[i - 2] = ((double) getSumCount(i) / totalRolls) * 100;
            
            System.out.println(probabilities[i - 2]);
        }
    }
    
    public double getProbability(int sum) {
        if (totalRolls > 0) {
            return probabilities[sum - 2];
        } else {
            return 0;
        }
    }
}