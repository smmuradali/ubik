/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.app.ubik.people;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//This class especially use for Smart Home user.
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sim.app.ubik.Ubik;
import sim.app.ubik.behaviors.Automaton;
import sim.app.ubik.behaviors.AutomatonWorker;
import sim.app.ubik.behaviors.PositionTools;
import sim.app.ubik.behaviors.pathfinderDemos.Pathfinder;
import sim.app.ubik.behaviors.pathfinderDemos.PathfinderThread;
import sim.app.ubik.building.rooms.Room;
import sim.engine.SimState;
import sim.util.Int2D;
import ubik3d.model.HomePieceOfFurniture;
import sim.app.ubik.building.Location;
import sim.app.ubik.building.SpaceArea;
import sim.app.ubik.building.connectionSpace.Door;
import sim.app.ubik.building.connectionSpace.ConnectionSpaceInABuilding;
import sim.app.ubik.domoticDevices.PresenceSensor;
import sim.app.ubik.domoticDevices.DoorSensor;
import sim.util.Int2D;
import sim.app.ubik.domoticDevices.PresenceActuator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import sim.app.ubik.furniture.Furniture;
import sim.app.ubik.mySQLconnection.*;



/**
 *
 * @author SA2305
 */
public class SmartUser extends Person{
    private static final Logger LOG = Logger.getLogger(SmartUser.class.getName());
    private static String globalGoal=null; //goal for all agents     
   //rivate static String exits[] = {"Exit1", "Exit2", "Exit3", "Exit4"}; //list of poosible goals
//    private static String rooms[] = {"room1", "room2", "room3", "room4","room5","room6","room7","room8"};
//    private static String rooms1[] = {"room2",};
    private DBConnection database;
    
           
   
    private Pathfinder pf;
    private List<Int2D> goals;
    private Int2D currentGoal=null;
    private Object initialPosition;
    private boolean activity;
    private PresenceSensor ps;
    private PresenceActuator PA;
    private boolean pa;
    private String actualActivity="";
    
    //constructor
    public SmartUser(int floor, HomePieceOfFurniture person3DModel, Ubik ubik) {
        super(floor, person3DModel, ubik);
        database = new DBConnection();
        database.connect();
    }
    
@Override
    public void step(SimState state) {
        super.step(state);        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateTime = sdf.format(cal.getTime());
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        System.out.println("Furnitures: SmartUser");
        List<Furniture> furnitures = (List<Furniture>) ubik.getBuilding().getFloor(floor).getFurnitureHandler().getFurnitures();
        for(int i=0;i<furnitures.size();i++){
            System.out.println(furnitures.get(i).getName() + " + " + "(" + furnitures.get(i).getCenter().x + "," + furnitures.get(i).getCenter().y +")");
        }
        
        Room room = (Room) ubik.getBuilding().getFloor(floor).getSpaceAreaHandler().getSpaceArea(x, y, Room.class);
        if(room!=null) {
            ConnectionSpaceInABuilding cs = room.getConnectionSpaceNearerTo(x, y);
            if (cs != null) {
                if (cs instanceof Door) {
                    Door door = (Door) cs;
                    if (cs.getCenter().distance(new Int2D(x,y))*ubik.getCellSize() < 75) {
                        if (door.isOpened()) {
                            door.close();
//                            String query = "insert into measure values(default,2," + door.getName() + ",1,0,'" + dateTime + "');";
//                            System.out.println(query);
//                            database.executeUpdate(query);                  
                        } else {
                            door.open();
                            //Int2D point =door.getAccessPoints()[0];
//                            String query = "insert into measure values(default,2," + door.getName()+ ",0,1,'" + dateTime + "');";
//                            System.out.println(query);
//                            database.executeUpdate(query);                            
                        }
                    }
                }else{
                    System.out.println("A door has not been found!");}
                
            }
        }
        
        DoorSensor ds = (DoorSensor) ubik.getBuilding().getFloor(floor).getDeviceHandler().getDeviceByName("7");
        System.out.println(ds.getName());
        if(ds!=null){
            if(ds.getStatusChange()){
                if(ds.getStatus().equals("opened")){
                    String query = "insert into measure values(default,2," + ds.getName()+ ",0,1,'" + dateTime + "');";
                    System.out.println(query);
                    database.executeUpdate(query);
                }else{
                    String query = "insert into measure values(default,2," + ds.getName()+ ",1,0,'" + dateTime + "');";
                    System.out.println(query);
                    database.executeUpdate(query);
                }
            }
        }
        
        

        //This has to be checked!!!
        ps = (PresenceSensor) ubik.getBuilding().getFloor(floor).getDeviceHandler().getNearestDevice(new Int2D(x,y),PresenceSensor.class);
        if(ps.getSpaceArea().getCenter().distance(new Int2D(x,y))*ubik.getCellSize() < 80){ 
            if(ps.getCurrentState()){
                ps.off();
                String query = "insert into measure values(default,2," + ps.getName() + ",0,1,'" + dateTime + "');";
                System.out.println(query);
                database.executeUpdate(query);
            }else{
                ps.on();
                String query = "insert into measure values(default,2," + ps.getName() + ",1,0,'" + dateTime + "');";
                System.out.println(query);
                database.executeUpdate(query);
            }
            System.out.println("Presence Sensor: " + ps.getName() + ps.getCurrentState());
                
        }
        

           

                       
//            ps = (PresenceSensor) ubik.getBuilding().getFloor(floor).getDeviceHandler().getDeviceByName("36");
//            if(ps.thereAnyActivity()){
//                System.out.println(ps.getName()+ " " + ps.activity);
//                if(actualActivity.equals("")){
//                    actualActivity = String.valueOf(ps.activity);                     
//                }
//                System.out.println(actualActivity + " - " + String.valueOf(ps.activity));
//                if(!actualActivity.equals(String.valueOf(ps.activity))){
//                    Calendar cal = Calendar.getInstance();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                    String dateTime = sdf.format(cal.getTime());
//                    try{
//                        try{
//                            Class.forName("com.mysql.jdbc.Driver");
//                        }catch(ClassNotFoundException e){
//                            System.err.println(e);
//                        }
//                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_datafromsimulation","root","1234");
//                        Statement stmt = conn.createStatement();
//                        String query = "insert into measure values(default,2," + ps.getName() + ",";
//                        actualActivity = String.valueOf(ps.activity);
//                            if(String.valueOf(ps.activity).equals("true")){
//                                query += "0,1,";
//                            }else{
//                                query += "1,0,";
//                            }
//                        query += "'" + dateTime + "');";
//                        System.out.println(query);
//    //                    stmt.executeUpdate(query);
//                    }catch(SQLException se){
//                        System.err.println(se);
//                    } 
//                }                
//            }
            
            
//        
     
        
        if (pf == null) {//generate pathfidner and goals in first step
            pf = new Pathfinder(this);
            //pf = new PathfinderThread(this);
            generateGoals();
        }
 
        if (goals.isEmpty()) {//if no remaining goals, put in red and do not follow      
            return;
        }
       
        
        if (currentGoal == null || pf.isInGoal()) {//if no current goal or it is in a goal, remove current goal and replace it
            goals.remove(currentGoal);
            currentGoal=null;
            if (!goals.isEmpty()) {
                currentGoal = goals.get(0);
                pf.setGoalAndGeneratePath(currentGoal);
            }
            else{this.setColor(Color.BLUE);}//blue to say that agent has accomplished all goals
        }
        else{
            pf.step(state);//take steps to the goal (step can regenerate paths)
        }
       
        
 
    }
    
    
     private void generateGoals() {
        goals = new ArrayList();
//        goals.add(new Int2D(480,900));        
//        goals.add(PositionTools.getRoom(this,rooms[1]).getCenter());
//        goals.add(PositionTools.getRoom(this,rooms[2]).getCenter());
//        goals.add(PositionTools.getRoom(this,rooms[3]).getCenter());
//        goals.add(PositionTools.getRoom(this,rooms[4]).getCenter());
//        goals.add(PositionTools.getRoom(this,rooms[5]).getCenter());
//        goals.add(PositionTools.getRoom(this,rooms[6]).getCenter());
//        goals.add(PositionTools.getRoom(this,rooms[7]).getCenter());
        
        
        
     //   goals.add(PositionTools.getRoom(this,rooms[5]).getCenter()); 
     goals.add(new Int2D(9,9));
       
        
    }

    

    
    
    
    public static void setGlobalGoal(String globalGoal) {
        SmartUser.globalGoal= globalGoal;
        
    }
}
    
    


