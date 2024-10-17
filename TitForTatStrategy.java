public class TitForTatStrategy implements Strategy {
    @Override
    public String chooseAction(String opponentLastAction) {
        // If it's the first round and there's no opponent's last action, cooperate
        if (opponentLastAction == null) {
            return "cooperate";
        } else {
            // Mimic the opponent's last action
            return opponentLastAction;
        }
    }

    @Override
    public String getStrategyName() {
        return "TitForTat";
    }
}
