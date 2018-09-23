/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.app.ubik.building.connectionSpace;

/**
 *
 * @author SA2305
 */
import java.awt.Color;
import java.util.List;
import ocp.service.ContextEntityItems;
import ocp.service.ContextItemInt;
import ocp.service.ContextService;
import sim.app.ubik.Ubik;
import sim.util.Int2D;
import sim.util.MutableInt2D;
import ubik3d.model.HomePieceOfFurniture;
public abstract class Fridge extends ConnectionSpaceInABuilding {

    public Fridge(int floor, String name, Ubik ubik, float angle, float[][] points) {
        super(floor, name, ubik, angle, points);
    }
    
    
    public void createAccessPoints() {
        accessPoints = new Int2D[2];
        accessPoints[0] = getBoundPoint(getAngle());
        accessPoints[1] = getBoundPoint((float)(getAngle()+Math.PI));
    }

}
