import java.util.Random;

public class JossStrategy implements Strategy {
    private Random random = new Random();

    @Override
    public String chooseAction(String opponentLastAction) {
        if (random.nextDouble() < 0.1) {
            return "betray";
        }
        return "cooperate".equals(opponentLastAction) ? "cooperate" : "betray";
    }

    @Override
    public String getStrategyName() {
        return "Joss";
    }
}
