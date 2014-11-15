package jadehomework1;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Student extends Agent {

    public static int GET_UP = 0;
    public static int ACTIVE = 1;
    public static int HUNGRY = 2;
    public static int THURSTY = 3;
    public static int WORK = 4;
    public static int SLEEP = 5;
    public static int GAME = 6;

    public static String SLEEPING = "sleeping";
    public static String WAKE_UP = "wake up";
    public static String AWAKE = "awake";
    public static String EATING = "eating";
    public static String DRINKING = "drinking";
    public static String GAMING = "gaming";
    public static String WORKING = "work";

    public int sleepyness = 20;
    public int hungryness = 25;
    public int thirstyness = 30;

    public int ticksAwake = 0;

    public boolean isTimeToWork = false;

    public boolean isAwake = false;

    @Override
    protected void setup() {
        super.setup(); //To change body of generated methods, choose Tools | Templates.
        
        //We have to have something to tell us, when to leave for work,
        WakerBehaviour wb = new WakerBehaviour(this, 1000) {

            @Override
            public void onWake() {
                System.out.println("i should go to work now...");
                isTimeToWork = true;
            }
        };
        addBehaviour(wb);

        FSMBehaviour fsm = new FSMBehaviour(this);

        SleepBehaviour slb = new SleepBehaviour(this);
        fsm.registerFirstState(slb, SLEEPING);

        BeingAwakeBehaviour bab = new BeingAwakeBehaviour(this);
        fsm.registerState(bab, AWAKE);

        GoEatingBehaviour geb = new GoEatingBehaviour(this);
        fsm.registerState(geb, EATING);

        GoDrinkingBehaviour gdb = new GoDrinkingBehaviour(this);
        fsm.registerState(gdb, DRINKING);

        GamingBehaviour sb = new GamingBehaviour(this);
        fsm.registerState(sb, GAMING);

        GoToWorkBehaviour gtw = new GoToWorkBehaviour(this);
        fsm.registerLastState(gtw, WORKING);

        //Transfer state from sleeping to being awake.
        fsm.registerTransition(SLEEPING, AWAKE, GET_UP);
        
        //Transfer state from being awake to sleeping.
        fsm.registerTransition(AWAKE, SLEEPING, SLEEP);
        
        //Transfer state from being happy home to going to work.
        fsm.registerTransition(AWAKE, WORKING, WORK);
        
        //Transfer state from being awake to getting something to eat.
        fsm.registerTransition(AWAKE, EATING, HUNGRY);
        
        //Transfer state from eating to beaing awake, deciding what to do next.
        fsm.registerDefaultTransition(EATING, AWAKE);
        
        //Transfer state from being awake to getting something to drink.
        fsm.registerTransition(AWAKE, DRINKING, THURSTY);
        
        //Transfer state from drinking to beaing awake, deciding what to do next.
        fsm.registerDefaultTransition(DRINKING, AWAKE);

        //Transfer state from being awake, doing nothing special, to gaming!!
        fsm.registerTransition(AWAKE, GAMING, GAME);
        //Transfer state from gaming to doing other stuff =(.
        fsm.registerDefaultTransition(GAMING, AWAKE);

        addBehaviour(fsm);
    }

    private class GoEatingBehaviour extends SequentialBehaviour {

        private Student student;

        public GoEatingBehaviour(Agent a) {
            super(a);
            this.student = (Student) a;
            addSubBehaviour(new OneShotBehaviour(getAgent()) {

                @Override
                public void action() {
                    System.out.println("i am hungry, i am going to eat!");
                }

            });
            addSubBehaviour(new OneShotBehaviour(a) {

                @Override
                public void action() {
                    System.out.println("i found something to eat, i am eating now");
                    student.sleepyness += 10;
                    student.hungryness = 0;
                }

            });
            addSubBehaviour(new OneShotBehaviour(a) {

                @Override
                public void action() {
                    System.out.println("i am not hungry anymore, i will do something else now");
                }

            });
        }

        @Override
        public int onEnd() {
            reset();
            return super.onEnd();
        }

    }

    private class GoDrinkingBehaviour extends SequentialBehaviour {

        private Student student;

        public GoDrinkingBehaviour(Agent a) {
            super(a);
            this.student = (Student) a;
            addSubBehaviour(new OneShotBehaviour(getAgent()) {

                @Override
                public void action() {
                    System.out.println("i am thirsty, i am going to drink!");
                }

            });
            addSubBehaviour(new OneShotBehaviour(a) {

                @Override
                public void action() {
                    System.out.println("i found something to drink, i am drinking now (coffee)");
                    student.sleepyness -= 3;
                    student.thirstyness = 0;
                }

            });
            addSubBehaviour(new OneShotBehaviour(a) {

                @Override
                public void action() {
                    System.out.println("i am not thirsty anymore, i will do something else now");
                }

            });
        }

        @Override
        public int onEnd() {
            reset();
            return super.onEnd();
        }

    }

    private class BeingAwakeBehaviour extends OneShotBehaviour {

        private final Student student;

        public BeingAwakeBehaviour(Agent a) {
            super(a);
            student = (Student) a;
        }

        @Override
        public void action() {
            //While awake, students are getting hungry, thursty and sleepy.
            student.sleepyness++;
            student.thirstyness++;
            student.hungryness++;
        }

        @Override
        public int onEnd() {

            //A student has to decide, what to do next.
            
            if (student.isTimeToWork) {
                return Student.WORK;
            }

            if (student.sleepyness > 100) {
                System.out.println("time to sleep!");
                return Student.SLEEP;
            }

            if (student.thirstyness > 5) {
                return Student.THURSTY;
            }

            if (student.hungryness > 15) {
                return Student.HUNGRY;
            }

            //Default activity for a student who is not sleeping.
            return Student.GAME;
        }

    }

    private class GoToWorkBehaviour extends OneShotBehaviour {

        public GoToWorkBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            System.out.println("going to work...");
        }
    }

    private class SleepBehaviour extends SimpleBehaviour {

        private final Student student;

        public SleepBehaviour(Agent a) {
            super(a);
            student = (Student) a;
        }

        @Override
        public void action() {
            System.out.println("i am sleeping...");
            student.sleepyness -= 25;
        }

        @Override
        public boolean done() {
            return student.sleepyness < 0;
        }

        @Override
        public int onEnd() {
            return Student.GET_UP;
        }

    }

    private class GamingBehaviour extends SimpleBehaviour {

        private final Student student;
        private int n = 0;

        public GamingBehaviour(Agent a) {
            super(a);
            student = (Student) a;
        }

        @Override
        public void action() {
            System.out.println("i am gaming, DND!");
            n++;
        }

        @Override
        public boolean done() {
            return n > 9;
        }

        @Override
        public int onEnd() {
            student.sleepyness += 10;
            student.thirstyness += 10;
            student.hungryness += 15;
            return Student.ACTIVE;
        }

    }

}
