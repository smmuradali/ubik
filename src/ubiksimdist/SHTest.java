/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiksimdist;
import sim.app.ubik.Ubik;
import sim.app.ubik.UbikSimWithUI;
import sim.app.ubik.utils.Configuration;
import sim.app.ubik.behaviors.Automaton;
import sim.app.ubik.people.*;
import sim.app.ubik.behaviors.PositionTools;
import sim.display.Console;
import static ubiksimdist.SimExampleWithGUI.demoCode;

/**
 *
 * @author SA2305
 */
public class SHTest extends Ubik {
    //numberOfAgets is the number of persons that will be in the SmartHome
    int numberOfAgents = 1;    
    
    public SHTest(long seed) {
        super(seed);
    }
    
    //We still don't know how to use this constructor
    public SHTest(Configuration configuration) {
        super(configuration);
    }
    
    public void start(){
        super.start();
        
        Automaton.setEcho(false);
        
        //Assigns? the person whose name is "Agent" to the variable pattern
        Person pattern = PositionTools.getPerson(this,"Agent");
        
        PersonHandler ph = getBuilding().getFloor(0).getPersonHandler();
        ph.addPersons(getNumberOfAgents(), true, pattern);
        ph.changeNameOfAgents("Agent");
    }

    public int getNumberOfAgents() {
        return numberOfAgents;
    }

    public void setNumberOfAgents(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
    }   
    
    public static void main(String[] args){
        Ubik sh = new SHTest(0);
//        switch (demoCode) {//scenario depeding on demo code
//            case 1:
//                sh.setPathScenario("./environments/mapExample.ubiksim");
//                break;
//            case 2:
//                //
                sh.setPathScenario("./environments/draft2.ubiksim");
//                break;
//                
//            case 3:
//                sh.setPathScenario("./environments/twoRooms.ubiksim");
//                break;
//            default:
//                //the file in config.pros will be used
//        }
        
        UbikSimWithUI vid = new UbikSimWithUI(sh);
        Console c = new Console(vid);
        c.setIncrementSeedOnStop(false);
        c.setVisible(true);
       
    }
    
}
