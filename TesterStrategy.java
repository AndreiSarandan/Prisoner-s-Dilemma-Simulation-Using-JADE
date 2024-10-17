public class TesterStrategy implements Strategy {
    private int round = 0;

    @Override
    public String chooseAction(String opponentLastAction) {
        round++;
        if (round == 1) {
            return "betray";
        } else if (round == 2 || round == 3) {
            return "cooperate";
        } else {
            return "betray".equals(opponentLastAction) ? "betray" : "cooperate";
        }
    }

    @Override
    public String getStrategyName() {
        return "Tester";
    }
}
