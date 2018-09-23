/*
 * UbikSim2 has been developed by:
 * 
 * Juan A. Botía , juanbot[at] um.es
 * Pablo Campillo, pablocampillo[at] um.es
 * Francisco Campuzano, fjcampuzano[at] um.es
 * Emilio Serrano, emilioserra [at] dit.upm.es
 * 
 * This file is part of UbikSimIDE.
 * 
 *     UbikSimIDE is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     UbikSimIDE is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with UbikSimIDE.  If not, see <http://www.gnu.org/licenses/>
 */

package sim.app.ubik.behaviors;

import java.util.ArrayList;
import sim.app.ubik.building.rooms.Room;
import sim.app.ubik.people.Person;
import sim.engine.SimState;

public class AutomatonTestPerson extends Automaton {
     Room room1;
     Room room2;
     Room room3;
     Room room4;
     Room room5;
     Room room6;
     Room room7;
     Room room8;
     Person p;
     public AutomatonTestPerson(Person p){
         super(p); 
//         room1= PositionTools.getRoom(p, "room1"); 
//         room2= PositionTools.getRoom(p, "room2");
         room1= PositionTools.getRoom(p, "FS01");
         room2= PositionTools.getRoom(p, "FS02");
         room3= PositionTools.getRoom(p, "FS03");
         room4= PositionTools.getRoom(p, "FS04");
         room5= PositionTools.getRoom(p, "FS05");
         room6= PositionTools.getRoom(p, "FS06");
         room7= PositionTools.getRoom(p, "FS07");
         room8= PositionTools.getRoom(p, "FS08");
         this.p=personImplementingAutomaton;
         
     }

    @Override
    public Automaton getDefaultState(SimState simState) {
        //nothing by default
        return new DoNothing(p,0,1,"wait one step");
       // return new Move(p,1,3,"move", room2.getCenter().x,room2.getCenter().y);
        //return new SimpleMove(p,"SimpleMove",1551,230);
        
    }

    @Override
    public ArrayList<Automaton> createNewTransitions(SimState simState) {
        //change room if person is not moving and with a probability of 0.01
        if(!this.isTransitionPlanned("move") &&  p.getUbik().random.nextFloat()<0.01){            
            ArrayList<Automaton> r= new ArrayList<>();
            if(PositionTools.isInRoom(p, room2))
                r.add(new Move(p,0,-1,"move",room3.getCenter().x, room3.getCenter().y));
            else
                r.add(new Move(p,0,-1,"move",room1.getCenter().x, room1.getCenter().y));
            return r;
        }
        return null;        
    }
}
