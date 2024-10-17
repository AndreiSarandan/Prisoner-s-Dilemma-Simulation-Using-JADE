import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ArbitratorAgent extends Agent {
    private AID prisoner1;
    private AID prisoner2;
    private int rounds;
    private int currentRound = 0;

    @Override
    protected void setup() {

    	Object[] args = getArguments();
        if (args != null && args.length > 0) {
            try {
                rounds = Integer.parseInt((String) args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                doDelete(); 
            }
        }

        prisoner1 = new AID("prisoner1", AID.ISLOCALNAME);
        prisoner2 = new AID("prisoner2", AID.ISLOCALNAME);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                if (currentRound < rounds) {
                	// ask strategy to start the round
                	ACLMessage request1 = new ACLMessage(ACLMessage.REQUEST);
                    request1.addReceiver(prisoner1);
                    send(request1);

                    ACLMessage request2 = new ACLMessage(ACLMessage.REQUEST);
                    request2.addReceiver(prisoner2);
                    send(request2);

                    ACLMessage reply1 = blockingReceive();
                    ACLMessage reply2 = blockingReceive();

                    //parse response to get the action
                    String[] response1 = reply1.getContent().split(":");
                    String decision1 = response1[0];
                    String strategyName1 = response1[1];

                    String[] response2 = reply2.getContent().split(":");
                    String decision2 = response2[0];
                    String strategyName2 = response2[1];
                    
                    //display
                    System.out.println("Round " + (currentRound + 1) + ":");
                    System.out.println("Prisoner 1 used " + strategyName1 + " and chose to " + decision1);
                    System.out.println("Prisoner 2 used " + strategyName2 + " and chose to " + decision2);

                    //outcome
                    determineOutcome(decision1, decision2);

                    
                    //inform prisoners of opponents actions
                    ACLMessage inform1 = new ACLMessage(ACLMessage.INFORM);
                    inform1.addReceiver(prisoner1);
                    inform1.setContent(decision2); 
                    send(inform1);

                    ACLMessage inform2 = new ACLMessage(ACLMessage.INFORM);
                    inform2.addReceiver(prisoner2);
                    inform2.setContent(decision1); 
                    send(inform2);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    currentRound++;
                } else {
                    System.out.println("Game over after " + rounds + " rounds.");
                    block();
                }
            }
        });
    }

    private void determineOutcome(String decision1, String decision2) {
        if ("cooperate".equals(decision1) && "cooperate".equals(decision2)) {
            System.out.println("Both cooperated: Each gets 1 year in prison.");
        } else if ("cooperate".equals(decision1) && "betray".equals(decision2)) {
            System.out.println("Prisoner 1 cooperated, Prisoner 2 betrayed: Prisoner 1 gets 3 years, Prisoner 2 is released.");
        } else if ("betray".equals(decision1) && "cooperate".equals(decision2)) {
            System.out.println("Prisoner 1 betrayed, Prisoner 2 cooperated: Prisoner 1 is released, Prisoner 2 gets 3 years.");
        } else if ("betray".equals(decision1) && "betray".equals(decision2)) {
            System.out.println("Both betrayed: Each gets 2 years in prison.");
        }
    }
}
