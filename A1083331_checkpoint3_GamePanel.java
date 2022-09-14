import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class A1083331_checkpoint3_GamePanel extends JPanel {
    //Description : the obstacle location set in GUI index version.
    private ArrayList<Integer[]> obstacle;
    //Description : the obstacle images set. bar_id -> obstacle image
    private HashMap<Integer,Image> obstacleImg = new HashMap<>();
    //Description : the image object of the map.
    private Image mapImg = new ImageIcon("Resource/map.png").getImage();
    //Description : the displaysize of the map
    private int scaler;
    //Description : the normal image size.
    //Hint : while the mapsize is not normal size, you have to think of the displaysize.
    private int originalGridLen = 256;
    //Description : the image displaysize.
    private int gridLen;
    //Description : the map center point x-axis location.
    //Hint : While dragging the map, you may need to set the map location via this.
    private Integer centerX = 0;
    //Description : the map center point y-axis location.
    //Hint : While dragging the map, you may need to set the map location via this.
    private Integer centerY = 0; 
    public A1083331_checkpoint3_GamePanel(ArrayList<Integer[]> obstacle,HashMap<Integer,Image> obstacleImg,int scaler){
        this.obstacle = obstacle;
        this.scaler = scaler;   
        this.obstacleImg = obstacleImg;
        gridLen = originalGridLen/scaler;

        //TODO(1) You need to set the center point location of the map into variable centerX, centerY.
        //Hint: While setting the location, you have to consider about the scaler of the map.
        //Hint2: If jfScaler is 4, then the value of centerX is 512; centerY is 512.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
        int mapSize=0;
        mapSize=4096/scaler;
        setCenterX(mapSize/2);
        setCenterY(mapSize/2);
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }

    //Description : While painting this JPanel, we draw map on the given location and other obstacles.
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //TODO(2) You need to calculate the location where panel should start drawImage, 
        //        and then draw the whole map on the panel.
        //Hint1: To get the location where panel should start drawImage, 
        //       use getWidth and getHeight function and calculate with centerX, centerY.
        //Hint2: If jfScaler is 4, then the value of mapX is -2xx; mapY is -2xx.
        //Hint3: To draw the whole map, you can use g.drawImage(Image img, int x, int y, int Width, int Height) method. 
        //       Also, You should draw twice, one for map image, and one for obstacle image. 
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/   
	    if (mapImg != null) {
	        g.drawImage(mapImg,getWidth()/2-centerX,getHeight()/2-centerY,4096/scaler,4096/scaler,this);
	    }
	    for(int i=0;i<obstacle.size();i++){
		        g.drawImage(obstacleImg.get(obstacle.get(i)[2]),(getWidth()/2-centerX)+obstacle.get(i)[1]*gridLen,(getHeight()/2-centerY)+obstacle.get(i)[0]*gridLen,gridLen,gridLen,this);
	    }	    
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
    
    public Integer getCenterX(){
        return this.centerX;
    }
    public void setCenterX(Integer centerX){
        this.centerX = centerX;
    }
    public Integer getCenterY(){
        return this.centerY;
    }
    public void setCenterY(Integer centerY){
        this.centerY = centerY;
    }

}
