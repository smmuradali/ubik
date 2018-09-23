/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiksimdist;

import sim.app.ubik.Ubik;
import sim.app.ubik.UbikSimWithUI;
import sim.app.ubik.behaviors.Automaton;
import sim.app.ubik.people.PersonHandler;
import sim.display.Console;
import sim.display.Controller;
import static ubiksimdist.SimExampleWithGUI.simExample;

/**
 *
 * @author SA2305
 */
public class SHTestWithUI extends UbikSimWithUI {
    private MyDisplay myDisplay;

    public SHTestWithUI(Ubik ubik) {
        super(ubik);
    }

    @Override
    public void start() {
        super.start();
        Automaton.setEcho(false);
        //add more people
        PersonHandler ph = simExample.getBuilding().getFloor(0).getPersonHandler();
        ph.addPersons(100, true, ph.getPersons().get(0));
        //change their name
        ph.changeNameOfAgents("Agents");       
        
    }

    @Override
    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(Controller c) {
        super.init(c); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args){
        Ubik u = new SHTest(0);
        SHTestWithUI s = new SHTestWithUI(u);
        
        u.setPathScenario("./environments/draft2.ubiksim");
        
        Console c = new Console(s);
        c.setIncrementSeedOnStop(true);
        c.setVisible(true);
        c.setSize(500, 650);
        c.setIconImage(getLocoIcon().getImage());
       
    }
    
}
