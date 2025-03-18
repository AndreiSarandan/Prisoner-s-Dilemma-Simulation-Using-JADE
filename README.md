Developed a simulation of the Prisoner's Dilemma using the JADE (Java Agent Development Framework), which allows the creation of multi-agent systems. The project consists of two main types of agents: ArbitratorAgent and PrisonerAgent, each responsible for different roles within the simulation.

Key Components:
ArbitratorAgent:

Role: Manages the overall game mechanics and mediates communication between the two prisoner agents.
Functionality:
1. Initializes the game with a specified number of rounds.
2. Sends requests to both prisoners at each round to make decisions (either "cooperate" or "betray").
3. Receives responses from the prisoners, parses their actions, and determines the outcome based on their decisions.
4. Notifies both prisoners of each other's actions after each round.
5. Displays the results of each round, indicating the decisions made and the resulting consequences (e.g., prison sentences).


PrisonerAgent:
Role: Represents each prisoner and decides whether to cooperate or betray based on a selected strategy.
Functionality:
1. Chooses a strategy at random from several predefined strategies.
2. Updates its knowledge of the opponent's last action when informed.


Strategies:
  1. AllDStrategy: Always betrays.
  2. RandomStrategy: Randomly chooses between cooperation and betrayal.
  3. TitForTatStrategy: Mimics the opponent's last action.
  4. TesterStrategy: This strategy introduces a mix of cooperation and betrayal based on the number of rounds played and the opponent's last action.
  5. JossStrategy: Generally cooperates but there is a 10% chance of betraying the opponent at any given turn.
  6. Receives requests from the ArbitratorAgent and responds with its decision and the name of the strategy used.

   
Technical Features:
JADE Framework: Utilizes JADE to facilitate communication between agents using ACL (Agent Communication Language) messages, enabling asynchronous message passing and interaction.
Cyclic Behaviours: Implements cyclic behaviors for both agents to allow continuous communication and decision-making during the game rounds.
Random Strategy Selection: Employs a random selection mechanism to choose strategies, adding an element of unpredictability to the simulation.
Outcomes:
The application successfully simulates a series of interactions between two agents representing prisoners, showcasing the complexities of decision-making under conditions of uncertainty and competition.
It highlights the different outcomes based on cooperation and betrayal, effectively illustrating the dynamics of the Prisoner's Dilemma through agent-based programming.
