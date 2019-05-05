package main_package;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Mytoolbar extends JPanel implements MouseListener,MouseMotionListener,ActionListener
{
	static final long serialVersionUID=0;
	JFrame frame;
	static JPanel status;
	JLabel statusLabel=new JLabel("Move the Mouse");
	static JLabel resolutionLabel=new JLabel();
	static int width=1135,height=673;
	int buttonID=0;
	Border raisedbevel,loweredbevel,loweredetched,raisedetched,sizeborder,raisedsize,compound;
	static TitledBorder titled,colorsss;
	
	JPanel sizepane,strokepane;
	public static JPanel bar,colorpane,colorb[]=new JPanel[13];
	public static JPanel small,medium,large;
	public static JButton empty,fill;
	public static JButton b[]=new JButton[8];
	JButton dark,light;
	JLabel themelabel=new JLabel("Select Theme");
	static ImageIcon icons[]=new ImageIcon[8];
	String iconpath[]={ "pencil1.png",
			"rectangle.png",
			"oval.png",
			"polygon1.png",
			"eraser.png",
			"line1.png",
			"roundrect.png",
			"dimension.png"};
	String iconname[]={"Pencil","Rectangle","Oval","Polygon","Eraser","Line","Round Rectangle","Change Resolution"};
	static String filename="Untitled";
	Menu mainmenu;
	RenderPaint draw;
	int x=20,y=40;
	public static final int standardwidth=1000,standardheight=500;
	public Color colors[]={Color.BLACK,Color.DARK_GRAY,Color.gray,Color.BLUE,Color.CYAN,new Color(80,170,80),
			Color.WHITE,Color.YELLOW,Color.ORANGE,Color.MAGENTA,
			Color.pink,Color.RED};
	public static Color currentcolor=Color.WHITE;
	public static Color linecol;
	static int theme=0,iota=250;
	
	public static int strokesize=2;

						/*************** CONSTRUCTOR **************/
	
	Mytoolbar()
	{
		Icon icon=new Icon();
		icon.progressbar(0);
		
		// INITIALISINGS
		
		frame=new JFrame();
		bar=new JPanel();
		status=new JPanel();
		sizepane=new JPanel();
        strokepane=new JPanel();
		colorpane=new JPanel();
		small=new JPanel();
		medium=new JPanel();
		large=new JPanel();
        empty=new JButton(new ImageIcon("empty.png"));
        fill=new JButton(new ImageIcon("fill.png"));
        
        
        // BOUNDS
        
        frame.setBounds(100,20,width, height);
		this.setBounds(130, 0, width-135, height-83-80-10);
		bar.setBounds(0,0,129,frame.getHeight()-83-80-10);
		statusLabel.setBounds(5,5,100,20);
		resolutionLabel.setBounds(width-150, 5, 150, 20);
		colorpane.setBounds(0,frame.getHeight()-171,width-3,90);
		sizepane.setBounds(x, 300, 2*y+4, 80);
		strokepane.setBounds(x,400,2*y+4,80);
		status.setBounds(0,height-80,width, 30);
		
		colorpane.setLayout(null);
		sizepane.setLayout(null);
		strokepane.setLayout(null);
		
		small.setBounds(10,16,65,4);
		medium.setBounds(10,32,65,8);
		large.setBounds(10,52,65,13);
		empty.setBounds(15, 5, 55, 30);
		fill.setBounds(15,40, 55, 30);
		
		// MAHIPAL'S CLASS
		
		mainmenu=new Menu(frame,this); 
		draw=new RenderPaint(this);
		
		
		// SETTING COLORS
		
		frame.setBackground(Color.BLUE);
		this.setBackground(Color.WHITE);
		status.setBackground(new Color(180,180,100));
		resolutionLabel.setText("Resolution : "+(width-135)+" X "+(height-163-10));
		linecol=new Color(55-theme, 55-theme, 55-theme);
		small.setBackground(linecol);
		medium.setBackground(linecol);
		large.setBackground(linecol);
		bar.setBackground(new Color(230+theme, 230+theme, 230+theme));
		colorpane.setBackground(new Color(220+theme,220+theme,210+theme));
		
		
		// intermediate additions
		
		status.add(statusLabel);
		status.add(resolutionLabel);
		
		sizepane.add(small);
		sizepane.add(medium);
		sizepane.add(large);
		bar.add(sizepane);
		
		strokepane.add(fill);
		strokepane.add(empty);
		bar.add(strokepane);
		
		/*************************/
		
		// PANEL LISTENERS FOR PLOTTING MOUSE COORDINATES
		
		this.addMouseMotionListener(this);
		
		// other action listeners
		
		small.addMouseListener(this);
		medium.addMouseListener(this);
		large.addMouseListener(this);
		
		
		// SETTING ALL BUTTON AND COLOR BORDERS
		
		
		loweredetched=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        sizeborder = BorderFactory.createLoweredBevelBorder();        
		raisedetched=BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(180,180,100),new Color(180,180,100));
		compound= BorderFactory.createCompoundBorder(
                raisedetched,raisedetched);

		raisedsize=BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(10,10,200),new Color(100,100,200));
		
        if(theme==0)
        	iota=0;
        else
        	iota=240;
        titled = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(new Color(150,150,200),2), "Tools", 0, 2,new Font(Font.DIALOG, Font.BOLD, 13),new Color(iota,iota,iota));
        colorsss = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(new Color(150,150,200),2), "Colors", 0, 2,new Font(Font.DIALOG, Font.BOLD, 13), new Color(iota,iota,iota));
        titled.setTitleJustification(TitledBorder.CENTER);
        colorpane.setBorder(colorsss);
        bar.setBorder(titled);
        
        sizepane.setBorder(sizeborder);
        small.setBorder(raisedsize);
		
        
        // ADDING BUTTONS AND ASSIGNING IMAGES,BORDERS,LISTENERS,TOOLTIPS
        
        for(int i=0;i<b.length/2;i++)
		{
			//System.out.println("Setting icon\nIconpath = "+iconpath[i]+"\nNumber = "+i);
			icons[i]=new ImageIcon(iconpath[i]);
        	b[i]=new JButton(icons[i]);
			b[i].setBounds(x, y, 34, 34);
			y+=60;
			b[i].setName(Integer.toString(i));
			b[i].setToolTipText(iconname[i]);
			b[i].setBorder(loweredetched);
			//b[i].setBorder(raisedbevel/*new LineBorder(Color.BLACK,1)*/);
			b[i].addMouseListener(this);
			b[i].addActionListener(this);
			bar.add(b[i]);
		}
		
        
        y=40;
		
		fill.setName("1");
		empty.setName("0");
		fill.setToolTipText("Fill Shapes");
		empty.setToolTipText("Outline Only");
		fill.setBorder(loweredetched);
		empty.setBorder(loweredbevel);
		fill.addActionListener(this);
		empty.addActionListener(this);
	
		
		
		
		for(int i=b.length/2;i<b.length;i++)
		{
			//System.out.println("Setting icon\nIconpath = "+iconpath[i]+"\nNumber = "+i);
			icons[i]=new ImageIcon(iconpath[i]);
        	b[i]=new JButton(icons[i]);
			b[i].setBounds(x+50, y, 34, 34);
			y+=60;
			b[i].setName(Integer.toString(i));
			b[i].setToolTipText(iconname[i]);
			b[i].setBorder(loweredetched);
			//b[i].setBorder(raisedbevel/*new LineBorder(Color.BLACK,1)*/);
			b[i].addMouseListener(this);
			b[i].addActionListener(this);
			bar.add(b[i]);	
		}
		
		/************************************************************************/
		
		
		
		
		// ADDING COLOR PALLETES TO MAIN_COLOR_PANEL
		
		int colorx=85,colory=20;
		colorb[0]=new JPanel();
		colorb[0].setBounds(20, 20, 40, 60);
		setcurrentcolor(Color.BLACK);
		colorpane.add(colorb[0]);
		colorb[0].addMouseListener(this);
		colorb[0].setBorder(compound);//BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(180,180,100),new Color(100,100,0)));
		for(int i=1;i<13;i++)
		{
			if(i%7==0)
				{
				colorx=85;
				colory=43+10;
				}
			colorb[i]=new JPanel();
			colorb[i].setBounds(colorx,colory,22,27);
			colorx+=30;
			colorb[i].setBackground(colors[i-1]);
			colorb[i].setName(Integer.toString(i));
			colorb[i].addMouseListener(this);
			colorb[i].setBorder(raisedetched);
			colorpane.add(colorb[i]);
		}
		
		
		/**************************************************************************/
		
		
		
		// SETTING FINAL LAYOUTS
		bar.setLayout(null);
		status.setLayout(null);
		frame.setLayout(null);
		
		// changing color of raised border for strokepane
		
		raisedetched=BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(100,100,255),new Color(100,100,255));		
		
		// ADDING ALL COMPONENTS TO FRAME
		frame.add(status);
		frame.add(colorpane);
		frame.add(bar);
		frame.add(this);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle(filename+" - Paint");
		Toolkit t=Toolkit.getDefaultToolkit();
		frame.setIconImage(t.getImage("PaintIcon.png"));	
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent arg0) {
			if(Menu.saved==false)
				{
				if(asktosave(frame))
				{}
				else
				frame.dispose();
				}
			else
				frame.dispose();
			}
			/*public void windowDeactivated(WindowEvent e)
			{
				frame.setVisible(true);
				if(asktosave("Disposing"))
					return;
				else
					frame.dispose();
			}*/
		});
	}

	
	/******************************* end of constructor *****************************/
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/********************************************************************************/
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	/******************************** MAIN METHOD *******************************/
	
	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		new Mytoolbar();
	}
	
	
	/****************************** ACTION LISTENERS ****************************/

	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) 
	{
		statusLabel.setText("X : " + arg0.getX() + " | Y :" + arg0.getY() );
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) 
	{
		statusLabel.setText("Mouse Outside");
	}
	public void mousePressed(MouseEvent e) 
	{
		if(e.getSource()==small)
		{
			medium.setBorder(null);
			large.setBorder(null);
			small.setBorder(raisedsize);
			strokesize=2;
		}
		else if(e.getSource()==medium)
		{
			small.setBorder(null);
			large.setBorder(null);
			medium.setBorder(raisedsize);
			strokesize=4;
		}
		else if(e.getSource()==large)
		{
			medium.setBorder(null);
			small.setBorder(null);
			large.setBorder(raisedsize);
			strokesize=6;
		}
		else if(e.getSource()==colorb[0])
		{
			if(e.getClickCount()>=2)
			{
				Color tempcolor=JColorChooser.showDialog(frame,"Select a color",getcurrentcolor());    
				if(tempcolor!=null)
					Mytoolbar.setcurrentcolor(tempcolor);
			}
			
			
		}
		else if(e.getSource() instanceof JPanel)
		{
			JPanel panel=((JPanel)(e.getSource()));
			String name=panel.getName();
			if(!(name.isEmpty()))
			{
				setcurrentcolor(panel.getBackground());
			}
		}
	}
	
	public void mouseReleased(MouseEvent arg0) {}
	
	/****************************** ACTION LISTENERS FOR BUTTONS ***************************/
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton)
		{
			if(e.getSource()==fill)
			{
				empty.setBorder(loweredetched);
				fill.setBorder(raisedetched);
				RenderPaint.flag3=1;
			}
			else if(e.getSource()==empty)
			{
				fill.setBorder(loweredetched);
				empty.setBorder(raisedetched);
				RenderPaint.flag3=0;				
			}
			else
			{
			//System.out.println("is a button");
			String temp=e.getSource().toString().split("\\[")[1].split(",")[0];
			//b[buttonID].setBorder(/*new LineBorder(Color.BLACK,1)*/);
			b[buttonID].setBorder(loweredetched);
			buttonID=Integer.parseInt(temp);
		
			RenderPaint.flag=buttonID+2;
			
			b[buttonID].setOpaque(false);
			b[buttonID].setBorder(loweredbevel/*new LineBorder(Color.GRAY,3)*/); // GREEN - /*new Color(80,160,80)*/
			if(e.getSource()==b[b.length-1])
			{
					Menu.setresolution(this);
					b[buttonID].setBorder(loweredetched);
			}
			}
		}
	}

	/************************************ IMAGE SAVER FUNCTION ***************************/
	
	public static void saveImage(String name,String type,JPanel thisobj) {
		if(type.length()==0)
		type=Menu.fileext;
		String withoutdot=type;
		type="."+type;
		
		BufferedImage image = new BufferedImage(thisobj.getWidth(),thisobj.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		thisobj.paint(g2);
		try
		{
		//System.out.println("Saving imageio.write as "+name+type);
		//System.out.println("FileExtension = "+withoutdot);
		ImageIO.write(image, withoutdot, new File(name+type));
		Menu.saved=true;
		}
		catch (Exception e) { e.printStackTrace(); }
	}
		
	/******************************* PAINT COMPONENT METHOD *****************************/
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*g.drawLine(130, 30, 232, 400);
		g.drawOval(92, 67, 237, 20);
		g.setFont(new Font("tahoma",Font.BOLD,20));*/
		//g.drawString("Paint (Ver 1.0)",100, this.getHeight()/2);
		//g.drawString("< Design & Coding > ", 105, this.getHeight()/2+30);
		//g.drawString(" Naman Paliwal  Shashank Sharma Mahipal Singh", 110, this.getHeight()/2+60);
		repaint();
	}
	
	/*********************************** COLOR BEANS ************************************/
	
	public static void setcurrentcolor(Color incoming)
	{
		currentcolor=incoming;
		colorb[0].setBackground(incoming);
	}

	public static Color getcurrentcolor()
	{
		return currentcolor;
	}

	public static int getstrokesize()
	{
		return strokesize;
	}

	public static void settheme(int temp)
	{
		theme=temp;
		linecol=new Color(55-theme, 55-theme, 55-theme);
		small.setBackground(linecol);
		medium.setBackground(linecol);
		large.setBackground(linecol);
		bar.setBackground(new Color(230+theme, 230+theme, 230+theme));
		colorpane.setBackground(new Color(220+theme,220+theme,210+theme));
		Mytoolbar.titled.setTitleColor(new Color(-temp,-temp,-temp));
		Mytoolbar.colorsss.setTitleColor(new Color(-temp,-temp,-temp));
		
	}
	
	public static boolean asktosave(JFrame frame)
	{
		int reply=JOptionPane.showConfirmDialog(frame, "Do you want to save the file before closing ?","Exiting",JOptionPane.YES_NO_CANCEL_OPTION);
		if(reply==JOptionPane.NO_OPTION)
		return false;
		else	
		return true;
	}
}




