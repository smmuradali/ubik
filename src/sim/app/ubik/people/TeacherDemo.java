/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.app.ubik.people;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sim.app.ubik.Ubik;
import sim.app.ubik.behaviors.PositionTools;
import sim.app.ubik.behaviors.pathfinderDemos.Pathfinder;
import sim.app.ubik.behaviors.pathfinderDemos.PathfinderThread;
import sim.app.ubik.building.rooms.Room;
import sim.engine.SimState;
import sim.util.Int2D;
import ubik3d.model.HomePieceOfFurniture;

public class TeacherDemo extends Person {
  private static final Logger LOG = Logger.getLogger(Worker.class.getName());
    private static String globalGoal=null; //goal for all agents     
   //rivate static String exits[] = {"Exit1", "Exit2", "Exit3", "Exit4"}; //list of poosible goals
   // private static String rooms[] = {"room1", "room2", "room3", "room4"};
    private static String rooms1[] = {"room2"};
    private static String rooms2[] = {"room3"};
    //private static String rooms2[] = {"room2"};
    
           
    private String firstLocalGoal=null;//local goal if no global is fixed
    private Pathfinder pf;

    public TeacherDemo(int floor, HomePieceOfFurniture person3DModel, Ubik ubik) {
        super(floor, person3DModel, ubik);
    }
    //constructor
//    public Worker(int floor, HomePieceOfFurniture person3DModel, Ubik ubik) {
//        super(floor, person3DModel, ubik);
//    }
//    
    @Override
    public void step(SimState state){
            super.step(state);
            
            
    
            if (pf == null) {//generate pathfidner and goals in first step                       
            pf = new PathfinderThread(this);
           
            //this.localGoal=rooms[state.random.nextInt(rooms.length)];
            this.firstLocalGoal=rooms1[state.random.nextInt(rooms1.length)];
         // this.localGoal=exits[state.random.nextInt(exits.length)];//random exit 
            //pf.setGoalAndGeneratePath(PositionTools.getRoom(this,localGoal).getCenter());
            pf.setGoalAndGeneratePath(PositionTools.getRoom(this,firstLocalGoal).getCenter());
            
           } 
            
            
//            
//            if (pf == null) {//generate pathfidner and goals in first step                       
//            pf = new PathfinderThread(this);
//            this.localGoal=exits[state.random.nextInt(exits.length)];//random exit            
//            pf.setGoalAndGeneratePath(PositionTools.getRoom(this,localGoal).getCenter());
//            
//        }  
//             
//           if (pf != null) {
//               //generate pathfidner and goals in first step
//               //pf = new PathfinderThread(this);
//               pf = new PathfinderThread(this);
//               this.firstLocalGoal=rooms2[state.random.nextInt(rooms2.length)];//random exit
//               pf.setGoalAndGeneratePath(PositionTools.getRoom(this,firstLocalGoal).getCenter());
//           } 
//           
          
            
            
            
            
            if(globalGoal!=null && !globalGoal.equals(firstLocalGoal) ){//if globalGoal fixed and it does not match the current goal
            pf.setGoalAndGeneratePath(PositionTools.getRoom(this,globalGoal).getCenter());
            firstLocalGoal= globalGoal;//this allow not entering this condition again if global goal is not rechanged
             }
            
            
//           if(pf.isInGoal()){
//           this.stop();//stop agent and make it get out of the simulation       
//           PositionTools.getOutOfSpace(this);
//           LOG.info(name + " has leave the building using " + localGoal);
//           return;
//               }
           
            pf.step(state);//take steps to the goal (step can regenerate paths)
           
       
     }
    
//    public static void setGlobalGoal(String globalGoal) {
//        Worker.globalGoal= globalGoal;
//        
//    }
//    
    
}
