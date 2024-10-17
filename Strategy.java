public interface Strategy {
    String chooseAction(String opponentLastAction);
    String getStrategyName();
}