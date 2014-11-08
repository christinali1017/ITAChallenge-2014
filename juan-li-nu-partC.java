import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PointsTest2 {

    private static final String FILE = "C:\\wish\\juan-li-nu-partA.txt";
    public static ArrayList<Point> points = new ArrayList<Point>();
    public static ArrayList<float[]> pointData = new ArrayList<float[]>();
    public static ArrayList<int[]> faces = new ArrayList<int[]>();
    
    
    public static void main(final String[] args){
    		getPoints();
    		partC_C();
    }
    
    
    //get point data and face data
    public static void getPoints(){
    	try{
            final BufferedReader br = new BufferedReader(new FileReader(new File(FILE)));
            int i = 0;
            int j = 0;
            int k= 0;
            while(br.ready()){
               final String[] split = br.readLine().split(" ");
               if(split[0].equals("point")){
	               final float x = Float.parseFloat(split[1]); 
	               final float y = Float.parseFloat(split[2]); 
	               final float z = Float.parseFloat(split[3]); 
	               float[] coor = new float[2];
	               coor = perspective3D(x, y, z);
	               coor = coordinateMapping(coor[0], coor[1]);
	               points.add(i++, new Point((int)coor[0], (int)coor[1]));
	               float[] co = {x, y, z};
	               pointData.add(k++,co);
               }
               else{
            	   final int x = Integer.parseInt(split[1]);
                   final int y = Integer.parseInt(split[2]);
                   final int z = Integer.parseInt(split[3]);
                   int[] co = {x, y, z};
                   faces.add(j++, co);
               }
            }
            
    } catch (Exception e){
        e.printStackTrace();
    }
    }
    
    public Point rotatePoint(Point pt, Point center, double angleDeg)
    {
        double angleRad = (angleDeg/180)*Math.PI; 
        double cosAngle = Math.cos(angleRad );
        double sinAngle = Math.sin(angleRad );
        double dx = (pt.x-center.x);
        double dy = (pt.y-center.y);
        pt.x = center.x + (int) (dx*cosAngle-dy*sinAngle);
        pt.y = center.y + (int) (dx*sinAngle+dy*cosAngle);
        return pt;
    }
    
    public static void rotatePointX(double angleDeg)
    {
    	double angleRad = (angleDeg/180)*Math.PI; 
    	double cosAngle = Math.cos(angleRad );
    	double sinAngle = Math.sin(angleRad );
    	double px;
    	double py;
    	double pz;
    	int i = 0;
    	for(float[] arr : pointData){
    		arr[0] = arr[0];
    		arr[1]  = (float) (arr[1] * cosAngle - arr[2]*sinAngle);
    		arr[2]  = (float) (arr[1]* sinAngle + arr[2]*cosAngle);
    	}

    }
    
    public static void rotatePointY(double angleDeg)
    {
    	double angleRad = (angleDeg/180)*Math.PI; 
    	double cosAngle = Math.cos(angleRad );
    	double sinAngle = Math.sin(angleRad );
    	double px;
    	double py;
    	double pz;
    	int i = 0;
    	for(float[] arr : pointData){
    		arr[0] =(float) (arr[0] * cosAngle - arr[2]*sinAngle);
    		arr[1] = arr[1];
    		arr[2] = (float) (arr[0]* sinAngle + arr[2]*cosAngle);
    	}

    }
    
    public static void rotatePointZ(double angleDeg)
    {
    	double angleRad = (angleDeg/180)*Math.PI; 
    	double cosAngle = Math.cos(angleRad );
    	double sinAngle = Math.sin(angleRad );
    	double px;
    	double py;
    	double pz;
    	int i = 0;
    	for(float[] arr : pointData){
    		arr[0] =(float) (arr[0] * cosAngle - arr[1]*sinAngle);
    	    arr[1] = (float) (arr[0]* sinAngle + arr[1]*cosAngle);
    		arr[2] = arr[2];
    	}

    }
    
    //partC_A
    public static void partC_A(){
    	int xMax = 0;
		int yMax = 0;
        final JFrame frame = new JFrame("Point Data Rendering");
        final Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(xMax + 1024, yMax + 1024));
        panel.setBackground(Color.black);
        frame.setContentPane(panel);
        frame.setBackground(Color.black);
        frame.pack();
        frame.setVisible(true);
        saveImage(frame);
        frame.repaint();
    }
    
    
    //partC_B
    public static void partC_B(){
    	rotatePointX(15);
    	rotatePointY(-30);
    	int i = 0;
    	points.clear();
    	for(float[] arr : pointData){
    		 float[] coor = new float[2];
             coor = perspective3D(arr[0], arr[1], arr[2]);
             coor = coordinateMapping(coor[0], coor[1]);
             points.add(i++, new Point((int)coor[0], (int)coor[1]));
    	}
    	
    	//System.out.println(points);
    	int xMax = 0;
		int yMax = 0;
        final JFrame frame = new JFrame("Point Data Rendering");
        final Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(xMax + 1024, yMax + 1024));
        panel.setBackground(Color.black);
        frame.setContentPane(panel);
        frame.setBackground(Color.black);
        frame.pack();
        frame.setVisible(true);
        saveImage(frame);
        frame.repaint();
    	
    }
    
    //partC_C
    public static void partC_C(){
     		int xMax = 0;
    		int yMax = 0;
            final JFrame frame = new JFrame("Point Data Rendering");
            final Panel panel = new Panel();
            panel.setPreferredSize(new Dimension(xMax + 1024, yMax + 1024));
            panel.setBackground(Color.black);
            frame.setContentPane(panel);
            frame.setBackground(Color.black);
            frame.pack();
            frame.setVisible(true);
            saveImage(frame);
            frame.repaint();
    	
    }
    
    public static float[] coordinateMapping(float x, float y){
    	float[] coor = new float[2];
    	coor[0] = 512 * (x + 1);
    	coor[1] = 512 * (1 - y);
    	return coor;
    }
    
    public static float[] perspective3D(float x, float y, float z){
    	float[] coor = new float[3];
    	coor[0] = 5*x/(1/(z-2));
    	coor[1] =  5*y/(1/(z-2));
    	return coor;
    }
    
    private static void saveImage(JFrame panel) {
    	 Dimension size = panel.getSize();
         BufferedImage image = new BufferedImage(
                     size.width, size.height 
                               , BufferedImage.TYPE_INT_RGB);
         Graphics2D g2 = image.createGraphics();
         panel.paint(g2);
         try
         {
             ImageIO.write(image, "png", new File("C:/wish/juan-li-nu-partC-C.png"));
             System.out.println("Panel saved as Image.");
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
    }

    public static class Panel extends JPanel {
        @Override
        public void paintComponent(final Graphics g){
            g.setColor(Color.blue);
            for(Point p : points){
                g.drawLine((int) p.getX(),(int)  p.getY(), (int) p.getX(), (int) p.getY());
            }
            g.setColor(Color.pink);
            for(int[] arr : faces){
            	Point p1 = points.get(arr[0]);
            	Point p2 = points.get(arr[1]);
            	Point p3 = points.get(arr[2]);
            	 g.drawLine((int) p1.getX(),(int)  p1.getY(), (int) p2.getX(), (int) p2.getY());
            	 g.drawLine((int) p2.getX(),(int)  p2.getY(), (int) p3.getX(), (int) p3.getY());
            	 g.drawLine((int) p1.getX(),(int)  p1.getY(), (int) p3.getX(), (int) p3.getY());       	
            }
            
        }

    }
    
    

}