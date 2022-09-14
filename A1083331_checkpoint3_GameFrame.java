import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class A1083331_checkpoint3_GameFrame extends JFrame {
    //Description : Width of Frame
    private int FWidth;
    //Description : Height of Frame
    private int FHeight;
    //Description : the displaysize of the map
    private int jfScaler = 2;
    //Description : the obstacle images set. bar_id -> obstacle image
    private HashMap<Integer,Image> obstacleImg = new HashMap<>();
    //Description : the filenames  of the obstacle image set.  bar_id -> filename 
    private HashMap<Integer, String> typeChar = new HashMap<Integer, String>();
    //Description : the obstacle location set queryed from database
    private ArrayList<Integer[]> obstacleDataStructure; 
    //Description : the obstacle location set in GUI index version.
    private ArrayList<Integer[]> obstacle;
    //Description : the object to query data.
    private A1083331_checkpoint3_QueryDB querydb;
    private static int PressedX = 0;
    private static int PressedY = 0;
    private static int ReleasedX = 0;
    private static int ReleasedY = 0;

    public A1083331_checkpoint3_GameFrame(int FWidth, int FHeight,String mapID,int jfScaler) throws HeadlessException {
        this.FWidth = FWidth;
        this.FHeight = FHeight;
        this.setTitle("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FWidth, FHeight);
        this.jfScaler = jfScaler;
        this.obstacle = new ArrayList<Integer[]>();
        this.obstacleDataStructure = new ArrayList<Integer[]>();
        this.querydb = new A1083331_checkpoint3_QueryDB();
        this.querydb.setMapID(mapID);

        //TODO(1): You need to get the obstacle from database and transform it into GUI index version
        //       and set your map(panel) on the frame.
        //Hint:  In order to build Hashmap obstacleImg, key means the obstacle_type from database 
        //       and value equals the Image class that load from the corresponding filepath.
        //Hint2: To get the obstacle set from database, we need you to realize the queryData() 
        //       in the object QueryDB and get the result.
        //Hint3: obstacle is transformed by obstacleDataStructure via toGUIIdx() in order to let 
        //       the location transformed from database to panel location.(GUI index version)
        //Hint4: ObstacleDataStructure is a Integer array ([row, column, obstacle_type]) like ArrayList.
        //       Obstacle is a Integer array ([x_coordinate, y_coordinate, obstacle_type]) like ArrayList.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/   

         querydb.queryData(querydb.getObstacle(),querydb.getObstacleImg());
         typeChar=querydb.getObstacleImg();
         obstacleDataStructure=querydb.getObstacle();
         toGUIIdx(obstacleDataStructure,obstacle);         
         for(int i=0;i<typeChar.size();i++){
         	Image mapImg = new ImageIcon("Resource/"+typeChar.get(i)).getImage();
         	obstacleImg.put(i,mapImg);        	
         }
         A1083331_checkpoint3_GamePanel panel=new A1083331_checkpoint3_GamePanel(obstacle,obstacleImg,jfScaler);      
         add(panel,BorderLayout.CENTER);
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

        //TODO(2): For mouse event here, you should implement map drag here. 
        // Hint: For example, if you click on the top and release in the bottom, 
        //       the map should be dragged from up to down.
        // Hint: You should got both pressed location and release location 
        //       and than calculate the moving.
        panel.addMouseListener(new MouseAdapter() {
            //Description : the event happenned while mouse be pressed.
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // TODO2.1.1. Get the location of mousePressed.
                /********************************************************************************************
                 START OF YOUR CODE
                 ********************************************************************************************/                	
             	PressedX=e.getX();
             	PressedY=e.getY();
                /********************************************************************************************
                 END OF YOUR CODE
                 ********************************************************************************************/
            }
            //Description : the event happenned while mouse be released
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                // TODO2.2.1. Get the location of mouseReleased.
                // TODO2.2.2. The map displacement will be calculated by Released location minus Pressed location 
                // TODO2.2.3. And then make the map moving by controlling it's location variable 
                //            and repaint the map via repaint() in object JPanel.
                /********************************************************************************************
                 START OF YOUR CODE
                 ********************************************************************************************/
              	ReleasedX=e.getX();
             	ReleasedY=e.getY();
             	int currentX=panel.getCenterX();
             	int currentY=panel.getCenterY();
             	panel.setCenterX(currentX-(ReleasedX-PressedX));
             	panel.setCenterY(currentY-(ReleasedY-PressedY)); 
             	panel.repaint();
                 /********************************************************************************************
                 END OF YOUR CODE
                 ********************************************************************************************/
            }
        });
        panel.addMouseMotionListener(new MouseAdapter(){
            //Description : the event happenned while mouse be dragged.
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                //TODO(optional) we hope you can drag the map smoothly, you can override this function
                // instead of mousePressed 
                /********************************************************************************************
                 START OF YOUR CODE
                 ********************************************************************************************/	
             	ReleasedX=e.getX();
             	ReleasedY=e.getY();
             	int currentX=panel.getCenterX();
             	int currentY=panel.getCenterY();
             	panel.setCenterX(currentX-(ReleasedX-PressedX));
             	panel.setCenterY(currentY-(ReleasedY-PressedY)); 
             	panel.repaint();
                PressedX=ReleasedX;
             	PressedY=ReleasedY;          
                /********************************************************************************************
                 END OF YOUR CODE
                 ********************************************************************************************/

            }
        });

    }
    
    //Description : transform the obstacle location from database version to GUI index version
    //              data is the database one, and the other.
    public static void toGUIIdx(ArrayList<Integer[]> data,ArrayList<Integer[]> dataGui){
        for(Integer[] x : data){
            dataGui.add( new Integer[]{x[1]-1, x[0]-1, x[2]} );
        }
    }
}
