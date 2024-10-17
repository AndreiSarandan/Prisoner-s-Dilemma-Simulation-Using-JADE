import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random random = new Random();

    @Override
    public String chooseAction(String opponentLastAction) {
        // Randomly choose to cooperate or betray
        if (random.nextBoolean()) {
            return "cooperate";
        } else {
            return "betray";
        }
    }

    @Override
    public String getStrategyName() {
        return "Random";
    }
}
