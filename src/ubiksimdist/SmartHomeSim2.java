package ubiksimdist;


import sim.app.ubik.Ubik;
import sim.app.ubik.behaviors.Automaton;
import sim.app.ubik.people.PersonHandler;
import sim.app.ubik.people.Worker;
import sim.app.ubik.utils.Configuration;


/**
 * @author Emilio Serrano, Ph.d.; eserrano@gsi.dit.upm.es
 */
public class SmartHomeSim2 extends Ubik {
//    
static int maxTimeForExecution=1500;
   
     //private int globalTargetDemo1sm=1;//fix global exit for 
     private int smarthometest=2;

    //public void setGlobalTargetDemo1(int val2) {
     public void setSmarthometest(int val2) {
        //this.globalTargetDemo1 = val2;
        this.smarthometest = val2;
       // String[]  s= {"Exit1", "Exit2", "Exit3", "Exit4"};
       String[] s = {"room2"};
        //Worker.setGlobalGoal(s[val]);
        Worker.setGlobalGoal(null);
    }
//
//    public int getSmarthometest() {
//        return smarthometest;
//    }
//    
//     //public Object smarthometest() { return new String[]  {"room1", "room2", "room3", "room4"}; }
//
//
//
//     /**    
//    * Object with information about execution and, if needed,
//      * to finish the execution
//      */     
//
//  
//    
//    /**
//     * Passing a random seed
//     * @param seed 
 //    */
    public SmartHomeSim2(long seed)   {
//       Configuration configuration = new Configuration ();
//       configuration.setPathScenario("./environments/draft3");
//       configuration.setSeed(786);
       
     //   super(seed);
     setSeed(123);
        
    }
    
      /**
     * Passing a random seed and time to make EscapeMonitorAgent to finish simulation
     * This time must be less than maxTimeForExecution
     * @param seed 
     * @param timeForSim 
     */
    public SmartHomeSim2(long seed, int timeForSim)   {
        super(seed);

        
    }
    

    /**
     * Using seed from config.pros file
     */
     public SmartHomeSim2() {         
           super();
           setSeed(getSeedFromFile());         
    }
     
     /**
      * 
     * Adding things before running simulation.   
     * Method called after pressing pause (the building variables are instantiated) but before executing simulation.
 
      */
     @Override
   public void start() {               
        super.start(); 
        Configuration configuration = new Configuration ();
        configuration.setPathScenario("./environments/draft3");
        configuration.setSeed(786);
//        Ubik ubik = new Ubik(configuration);
//        UbikSimWithUI vid= UbikSimWithUi(ubik);


        
        

   }
    
   
   /**
 * Default execution without GUI. It executed the simulation for maxTimeForExecution steps.
 * @param args 
 */
    public static void main(String []args) {
       
       
       SmartHomeSim2 state = new SmartHomeSim2(System.currentTimeMillis());
      
       state.start();
        do{
                if (!state.schedule.step(state)) break;
        }while(state.schedule.getSteps() < maxTimeForExecution);//
        state.finish();     
      
     
    }
    
 
 


}
