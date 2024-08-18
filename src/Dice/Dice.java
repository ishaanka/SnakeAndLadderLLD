package Dice;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    public int diceCount;
    public int diceRange;

    public Dice(int diceCount, int diceRange) {
        this.diceCount = diceCount;
        this.diceRange = diceRange;
    }

    public int rollDice() {
        int dc = 0;
        int totalSum=0;
        while(dc < this.diceCount) {
            totalSum += ThreadLocalRandom.current().nextInt(1,this.diceRange+1);
            dc++;
        }
        return totalSum;
    }
}
