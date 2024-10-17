import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

public class PrisonerAgent extends Agent {
    private Strategy strategy;
    private String opponentLastAction;
    private Random random = new Random();

    @Override
    protected void setup() {

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getPerformative() == ACLMessage.REQUEST) {
                    	strategy = chooseRandomStrategy();
                        String action = strategy.chooseAction(opponentLastAction);
                        ACLMessage reply = msg.createReply();
                        reply.setContent(action + ":" + strategy.getStrategyName());
                        send(reply);
                    } else if (msg.getPerformative() == ACLMessage.INFORM) {
                        opponentLastAction = msg.getContent();
                    } 
                } else {
                    block();
                }
            }
        });
    }

    private Strategy chooseRandomStrategy() {
        int strategyIndex = random.nextInt(5); 
        switch (strategyIndex) {
            case 0:
                return new AllDStrategy();
            case 1:
                return new RandomStrategy();
            case 2:
                return new TitForTatStrategy();
            case 3:
                return new TesterStrategy();
            case 4:
                return new JossStrategy();
            default:
                return new TitForTatStrategy(); 
        }
    }
}
