public class AllDStrategy implements Strategy {
    @Override
    public String chooseAction(String opponentLastAction) {
        return "betray";
    }

    @Override
    public String getStrategyName() {
        return "AllD";
    }
}
