/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiksimdist;
import sim.app.ubik.*;
import sim.display.Console;

/**
 *
 * @author SA2305
 */
public class Main {
    public static void main(String[] args){
        Ubik sh = new SHTest(0);
        UbikSimWithUI vid = new UbikSimWithUI(sh);
        Console c = new Console(vid);
        c.setIncrementSeedOnStop(false);
        c.setVisible(true);
       
    }
}
