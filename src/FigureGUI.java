
/**
 * This class creates GUI that contains the buttons, the text area and the panel 
 * that we are going to draw the rectangle and circle objects
 * @author Max Zelalem
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class FigureGUI extends JFrame implements ActionListener {
	private static enum Action {RECTANGLE, CIRCLE, DATE};
	private JButton redButton = new JButton("Red");
	private JButton greenButton = new JButton("Green");
	private JButton blueButton = new JButton("Blue");
	private JButton rectangleButton = new JButton("Rectangle");
	private JButton circleButton = new JButton("Circle");
	private JButton exitButton = new JButton("Exit");
	private JTextArea listArea = new JTextArea(10, 20);
	private GregorianCalendar currentDate;
	private FiguresPanel figuresPanel = new FiguresPanel();
	private Action action = Action.RECTANGLE; 
	private String formattedDate;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int x;
	private int y;
	protected ArrayList listFigures = new ArrayList<Figure>();
	private String file ="FiguresSeretseab";
	File fileName=new File(file);
	private Rectangle rectangle1;
	private Circle circle1;
	private Point point;
	private ArrayList<Point> arrayOfPoints = new ArrayList<Point>(); 
	private Color colors;
	private int r;
	/**
	 * This class creates the custom panel that we are going to use to draw the circle and rectangle objects 
	 * @max zlelalem
	*/
	
		public class FiguresPanel extends JPanel implements MouseListener {
			
/*** This method draws the objects into the panel
*/
		@Override
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			g.setColor(figuresPanel.getBackground());
			//get all the objects in the listfigures array list and draw them into the panel
			for(int i=0;i<listFigures.size();i++){
				
				//check to see if that object is a rectangle
				if(listFigures.get(i).getClass().getName().equalsIgnoreCase("Rectangle")){
				
					
					Rectangle rect= listFigures.get(i);
					g.setColor(rect.getColor());
					g.drawRect(rect.getX(),rect.getY(), rect.getWidth(), rect.getHeight());
					g.fillRect(rect.getX(),rect.getY(), rect.getWidth(), rect.getHeight());
					
					
				}
				//check to see if that object is a circle
				else if(listFigures.get(i).getClass().getName().equalsIgnoreCase("Circle")){
						Circle cir=(Circle) listFigures.get(i);
						g.setColor(cir.getColor());
						g.drawOval(cir.getX(),cir.getY(), Integer.valueOf((int) (2*cir.getRadius())),Integer.valueOf((int) ((int)2*cir.getRadius())));
						g.fillOval(cir.getX(),cir.getY(), Integer.valueOf((int) (2*cir.getRadius())),Integer.valueOf((int) ((int)2*cir.getRadius())));
					
				}
					
			}
			//draw the date
			 if (action==Action.DATE);{
				g.setColor(Color.BLACK);
				g.drawString(formattedDate, 10, figuresPanel.getHeight()-figuresPanel.getHeight()/20);
				
			}
		}
		/**-
		 * Waits for the mouse click and creates the appropriate figures.
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			switch(action) {
			case RECTANGLE:
				//check to see if arrayOfPoints is empty.If it is empty put that point where the mouse is clicked into the array
				if(arrayOfPoints.isEmpty()){
						x=event.getX();
						y=event.getY();
						
						point=new Point(x,y);
						arrayOfPoints.add(point);
						
				}
				//if arrayOfPoints is not empty then add the second point into the array		
				else if(!arrayOfPoints.isEmpty()){
				
					x=event.getX();
					y=event.getY();
				
					point=new Point(x,y);
					arrayOfPoints.add(point);
				
						x1=arrayOfPoints.get(0).x;
						y1=arrayOfPoints.get(0).y;
						x2=arrayOfPoints.get(1).x;
						y2=arrayOfPoints.get(1).y;
						
						//create a new rectangle object using the points and the selected color
					rectangle1=new Rectangle(x1, y1, x2-x1, y2-y1, colors);
					listFigures.add(rectangle1);
					listArea.append(rectangle1.toString() + "\n");
					
					
					this.repaint();

					//clear the array
					arrayOfPoints.clear();
			
								
				}	
					
			
				break;
		
			case CIRCLE:
				 //process the mouse click
				
				//check to see if arrayOfPoints is empty.If it is empty put that point where the mouse is clicked into the array
				if(arrayOfPoints.isEmpty()){
					x=event.getX();
					y=event.getY();
					
					//System.out.println(x + "ops" + y);
					point=new Point(x,y);
					arrayOfPoints.add(point);
					
		}
				//if arrayOfPoints is not empty then add the second point into the array				
		else if(!arrayOfPoints.isEmpty()){
			
			x=event.getX();
			y=event.getY();
			
			point=new Point(x,y);
			arrayOfPoints.add(point);
			
					x1=arrayOfPoints.get(0).x;
					y1=arrayOfPoints.get(0).y;
					x2=arrayOfPoints.get(1).x;
					y2=arrayOfPoints.get(1).y;
					
				r=(int)Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
				circle1=new Circle(x1, y1,r, colors);
				listFigures.add(circle1);
				listArea.append(circle1.toString() + "\n");
			
				this.repaint();

			
				arrayOfPoints.clear();
		
							
				
				break;
			}
			}
		}
		/**
		 * Not used
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		/**
		 * Not used
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		/**
		 * Not used
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		/**
		 * Not used
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}
	/**
	 * Sets up the entire interface.
	 * @throws ClassNotFoundException 
	 */
	public FigureGUI() {
		super("Figures GUI");
		
		JPanel backgroundPanel=new JPanel(new GridLayout(1, 3));;
		JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
		JPanel listPanel = new JPanel(new GridLayout(1, 1));
		JPanel mainPanel = new JPanel(new GridLayout(1, 4));
		
		//add the buttons to the button panel
		buttonPanel.add(redButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(exitButton);
		
		//add the jscrollpane to the text area and put it on a jpanel
		listPanel.add(new JScrollPane(listArea));
		mainPanel.add(figuresPanel);
		
		//add listeners to all buttons and clicks on the figresPanel
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		blueButton.addActionListener(this);
		rectangleButton.addActionListener(this);
		circleButton.addActionListener(this);
		exitButton.addActionListener(this);
		figuresPanel.addMouseListener(figuresPanel);
		
		//check to see if the file exists
			if(fileName.exists()){
		
				
		//read from file and put the objects that we read into listFigures arraylist and re draw  it
			try {
				
				ObjectInputStream inputStream = null;
				
				 inputStream = new ObjectInputStream(new FileInputStream(file));
				
				
				 Object obj = null;
		            
		            try {
						while ((obj = inputStream.readObject()) != null) {
						    //check to see if the object that we read is a rectangle
						    if (obj instanceof Rectangle) {
						    	listFigures.add(obj);
						       
						        repaint();
						        listArea.append(obj.toString() + "\n");
						    }
						    //check to see the object that we read is a circle
						    if (obj instanceof Circle) {
						    	listFigures.add(obj);
						       
						        listArea.append(obj.toString() + "\n");
						        repaint();
						    }
						    
						 
						    
						}
						  inputStream.close();
					} catch (ClassNotFoundException e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            catch (FileNotFoundException e) {
						System.out.println("The specified file hasn't been created yet");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
		          
			 } catch (EOFException ex) {  //This exception will be caught when EOF is reached
		            System.out.println("End of file reached.");        
			} catch (FileNotFoundException e) {
				System.out.println("The specified file hasn't been created yet");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	  //create the date object
		currentDate = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		formattedDate = dateFormat.format(currentDate.getTime());
		
		//add all panels to the jFrame
		backgroundPanel.add(figuresPanel, BorderLayout.WEST);
		backgroundPanel.add(buttonPanel);
		backgroundPanel.add(listPanel,BorderLayout.EAST);
		add(backgroundPanel);
	}
	/**
	 * Listener for all buttons. 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource()==rectangleButton){
		
			 action=Action.RECTANGLE;
			
		}
		else if(event.getSource()==circleButton){
			 action=Action.CIRCLE;
			//new Circle(x1, y1, (int)Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)), colors);
		}
		else if(event.getSource()==redButton){
			colors=Color.RED;
		}
		else if(event.getSource()==greenButton){
			colors=Color.GREEN;
		}
		else if(event.getSource()==blueButton){
			 colors=Color.BLUE;
		}
		else if (event.getSource()==exitButton){
			writeObject(file);
			System.exit(0);
			
		}
		
		
	}
	/**
	 * writes the objects that we created to a file
	 * 
	 */
		public void writeObject(String file){
		
			
				try {
					ObjectOutputStream output = new ObjectOutputStream(
					        new FileOutputStream(fileName));
					
					for(int i=0;i<listFigures.size();i++){
					output.writeObject(listFigures.get(i));
					}
		
					output.close();
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}
		
	/**
	 * The method creates an FigureGUI object
	 * 
	 * @param args not used
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		FigureGUI figureGUI=new FigureGUI();
		figureGUI.setSize(900,250);
		figureGUI.setTitle("FigureGUI");
		figureGUI.setVisible(true);
		figureGUI.setLocationRelativeTo(null);
		
		//for(int i=0;i<listFigures.size();i++){
			//System.out.println(listFigures.get(i).toString());
		//}
		
	}
}

