package jadehomework1;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class NameAgent extends Agent{

    @Override
    protected void setup() {
        super.setup(); //To change body of generated methods, choose Tools | Templates.
        addBehaviour(new OneShotBehaviour(this) {
            
            @Override
            public void action() {
                System.out.println("i was born...yehaaa!");
            }
        });
    }

    
    
}