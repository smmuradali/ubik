/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.app.ubik.behaviors;

import java.util.ArrayList;
import sim.app.ubik.people.Person;
import sim.engine.SimState;
import sim.app.ubik.building.rooms.Room;


/**
 *
 * @author SA2305
 */
public class AutomatonWorker extends Automaton{

    public AutomatonWorker(Person personImplementingAutomaton) {
        super(personImplementingAutomaton);        
    }

    @Override
    public Automaton getDefaultState(SimState simState) {
        return new DoNothing(personImplementingAutomaton,0,1,"doNothing");
    }

    @Override
    public ArrayList<Automaton> createNewTransitions(SimState simState) {
        ArrayList<Automaton> r = new ArrayList<Automaton>();
//        Room room2 = PositionTools.getRoom(personImplementingAutomaton,"room1");
//        r.add(new Move(personImplementingAutomaton,0,-1,"firstMove","room1"));
        r.add(new Move(personImplementingAutomaton,0,-1,"firstMove",16,10));

//        Room room4 = PositionTools.getRoom(personImplementingAutomaton,"room4");
//        r.add(new Move(personImplementingAutomaton,0,-1,"firstMove","room4"));
//      r.add(new Disappear(p, " escaped ", p.getName() + " escaped using " + exit. getName()));
        
//        if(!p.hasBeenCloseToFire()){
//            
//            if (sim.getFire().tauchingFire(personImplementingAutomaton)){
//                p.setCloseToFire(true);
//                sim.getMonitorAgent().peopleCloseToFire++;
//            }
//        }
        return r;
    }

    
}
