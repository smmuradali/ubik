/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiksimdist;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sim.app.ubik.Ubik;
import sim.app.ubik.UbikSimWithUI;
import static sim.app.ubik.UbikSimWithUI.getLocoIcon;
import sim.app.ubik.behaviors.Automaton;
import sim.app.ubik.people.PersonHandler;
import sim.display.Console;
import sim.display.Controller;

/**
 *
 * @author SA2305
 */
public class SmartHomeSimForTestPersonGUI extends UbikSimWithUI {
    protected static SmartHomeSim smartHomeSim;
    protected static int demoCode = 1; //1 for escape in MapExample, 2 for pathfinding in primeraPlantaUMU_DIIC, 3 for UbikSim 1.0 example

    public static void setDemoCode(int demoCode) {
        SmartHomeSimWithGUI.demoCode = demoCode;
    }

    public SmartHomeSimForTestPersonGUI(Ubik ubik) {
        super(ubik);
    }

    /**
     * Method called after pressing pause (the building variables are
     * instantiated) but before executing simulation. Any JFrame can be
     * registered to be shown in the display menu
     */
    @Override
    public void start() {
        super.start();
  
        if (demoCode == 1) {
            Automaton.setEcho(true);
            //add more people
//            PersonHandler ph = simExample.getBuilding().getFloor(0).getPersonHandler();
//            ph.addPersons(100, true, ph.getPersons().get(0));
//            //change their name
//            ph.changeNameOfAgents("a");
        }

    }

    /**
     * Method to finish the simulation
     */
    @Override
    public void finish() {
        super.finish();
  
    }

    @Override
    public void init(final Controller c) {
        super.init(c);

    }

    /**
     * Executing simulation with GUI, it delegates to SimExample, simulation
     * without GUI
     *
     * @param args
     */
    public static void main(String[] args) {
        //simExample = new SimExample(System.currentTimeMillis());
        smartHomeSim = new SmartHomeSim();
        
        switch (demoCode) {//scenario depeding on demo code
            case 1:
               smartHomeSim.setPathScenario("./environments/TestPersonSimulation.ubiksim");
                //smartHomeSim.setPathScenario("./environments/draftTeacher.ubiksim");
                break;
            case 2:
                //
                smartHomeSim.setPathScenario("./environments/primeraPlantaUM.ubiksim");
                break;
                
            case 3:
                smartHomeSim.setPathScenario("./environments/twoRooms.ubiksim");
                break;
            default:
                //the file in config.pros will be used
        }
        
        
        SmartHomeSimForTestPersonGUI vid = new SmartHomeSimForTestPersonGUI(smartHomeSim);
        Console c = new Console(vid);
        c.setIncrementSeedOnStop(true);
        c.setVisible(true);
        c.setSize(1000, 650);
        c.setIconImage(getLocoIcon().getImage());
       
        
        

    }

}

    

