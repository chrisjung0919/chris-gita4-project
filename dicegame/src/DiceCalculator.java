import java.util.Random;

public class DiceCalculator {
    private int[] sumCounts;
    private int roll1;
    private int roll2;
    private int sum;

    public DiceCalculator() {
        sumCounts = new int[11]; // Initialize the array to store the count of each sum (2-12)
    }

    public void rollDice() {
        Random random = new Random();
        roll1 = random.nextInt(6) + 1; // Generating random number between 1 and 6 (inclusive)
        roll2 = random.nextInt(6) + 1;

        sum = roll1 + roll2;
        sumCounts[sum - 2]++; // Incrementing the count for the appropriate sum
    }

    public int[] getSumCounts() {
        return sumCounts;
    }

    public int getRoll1() {
        return roll1;
    }

    public int getRoll2() {
        return roll2;
    }
    public int getSum() {
    	return sum;
    }
}
