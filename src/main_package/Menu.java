package main_package;

import javax.imageio.ImageIO;
import javax.swing.*;    
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;    
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Menu  extends JPanel implements ActionListener
{    
	public static final long serialVersionUID=0;
	public JMenuBar mb;    
	public JMenu file,edit,help,view,theme;    
	public JMenuItem New,Open,Save,as,Exit,cut,copy,paste,color,selectAll,about;
	public JMenuItem background_color,light,dark,set;
	public JFrame f;
	public File filename=null;
	public JPanel p;
	public static String s1,s2,filepath=null,fileext="jpg";
	public String aboutauthors="                   Paint\n\n< Design & Coding >\nNaman Paliwal"+
			"\nMahipal Singh\nVersion : 1.0";
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files (*.jpg,*.png,*.bmp)",
																 "jpg","jpeg","png","bmp");   
	JLabel jLabel=new JLabel();
	public static boolean saved=true;
	RenderPaint okay;
	public Menu(JFrame f,JPanel pop)
	{
		this.f=f;
		this.p=pop;
		try
		{	
			mb=new JMenuBar();    
			file=new JMenu("File ");    
			edit=new JMenu(" Edit ");    
			help=new JMenu(" Help  "); 
			
			New=new JMenuItem("New");    
			Open=new JMenuItem("Open");    
			Save=new JMenuItem("Save");    
			as=new JMenuItem("Save_as");
			Exit=new JMenuItem("Exit");
			New.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			Open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			Save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			//as.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			//Exit.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			
			New.addActionListener(this);    
			Open.addActionListener(this);    
			Save.addActionListener(this);    
			as.addActionListener(this);
			Exit.addActionListener(this);
			
			color=new JMenuItem("Color");
			
			cut=new JMenuItem("Cut");
			cut.setEnabled(false);    
			copy=new JMenuItem("Copy");    
			copy.setEnabled(false);
			paste=new JMenuItem("Paste");    
			paste.setEnabled(false);
			selectAll=new JMenuItem("selectAll");
			selectAll.setEnabled(false);
			cut.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			copy.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			paste.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			selectAll.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			
			color.addActionListener(this);
			
			cut.addActionListener(this);    
			copy.addActionListener(this);    
			paste.addActionListener(this);    
			selectAll.addActionListener(this);
			
			file.add(New);file.add(Open);file.add(Save);file.add(as);
			file.add(new JSeparator());
			file.add(Exit);
			
			edit.add(color);
			edit.add(new JSeparator());
			edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);     
			//about.setAccelerator(KeyStroke.getKeyStroke());
			
			
			view=new JMenu(" View ");
			background_color=new JMenuItem("Change Background");    
			set=new JMenuItem("Change resolution");
			theme=new JMenu("Theme");
			
			light=new JMenuItem("Light");
			light.setForeground(new Color(120,120,190));
			light.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			dark=new JMenuItem("Dark");
			dark.setForeground(Color.BLACK);
			dark.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			theme.add(light);
			theme.add(dark);
			view.add(background_color);
			view.add(set);
			view.add(theme);
			light.addActionListener(this);
			dark.addActionListener(this);
			set.addActionListener(this);
			background_color.addActionListener(this);
			
			about=new JMenuItem("About");
			about.addActionListener(this);
			help.add(about);
			mb.add(file);
			mb.add(edit);
			mb.add(view);
			mb.add(help);
			f.setJMenuBar(mb);	
			}
			catch(Exception e)
			{
				//System.out.println("Exception Occured in Menu.java");
			}
		
	}     
	public void actionPerformed(ActionEvent e)
	{   
		if(e.getSource()==New)
		{
			newScreen();
		}
		if(e.getSource()==Open)
		{
			
			if(e.getSource()==Open){    
			    JFileChooser fc=new JFileChooser();  
			    fc.setFileFilter(filter);
			    int i=fc.showOpenDialog(p);  
			    if(i==JFileChooser.APPROVE_OPTION)
			    {    
			        filename=fc.getSelectedFile();    
			        filepath=filename.getPath();
			        //System.out.println("File="+file);
			        //System.out.println("FilePath="+filepath);
			        fileext=filepath.split("\\.")[filepath.split("\\.").length-1];
			        fileext=fileext.toLowerCase();
			        Mytoolbar.filename=filepath;
			        f.setTitle(Mytoolbar.filename+" - Paint");
				    if(fileext.equalsIgnoreCase("jpg")||fileext.equalsIgnoreCase("png")||fileext.equalsIgnoreCase("bmp"))
				    {
				    	//System.out.println("Image File");
				    	BufferedImage image=null;
				    	try
				    	{
				    		image=ImageIO.read(new File(filepath));

					    	ImageIcon imageIcon=new ImageIcon(image);
					    	newScreen();
					    	//okay.label.setIcon(imageIcon);
					    	//okay.label.setOpaque(false);
					    	int scaledw=imageIcon.getIconWidth(),scaledh=imageIcon.getIconHeight();
					    	if(imageIcon.getIconHeight()>Mytoolbar.standardheight)
					    		scaledh=Mytoolbar.standardheight;
					    	
					    	if(imageIcon.getIconWidth()>Mytoolbar.standardwidth-5)
					    		scaledw=Mytoolbar.standardwidth-5;
					    	
					    	p.setSize(scaledw, scaledh);
					    	okay.label.setBounds(0, 0, imageIcon.getIconWidth(),imageIcon.getIconHeight());
					    	okay.paintimage(image);
					    	Mytoolbar.resolutionLabel.setText("Resolution : "+imageIcon.getIconWidth()+" X "+imageIcon.getIconHeight());
					    	//okay.label.setOpaque(false);
					    	p.add(okay.label);
					    	//p.add(RenderPaint.label);
				    	}
				    	catch(Exception mm)
				    	{ 	JOptionPane.showMessageDialog(f, "Error in Loading Image");	}
				    }
				    else
				    {
				        try{  
				        BufferedReader br=new BufferedReader(new FileReader(filepath));    
				        s1="";
				        s2="";                         
				        while((s1=br.readLine())!=null)
				        {    
				        	s2+=s1+"\n"; 
				        }    
				        JOptionPane.showMessageDialog(p,"Not an Image File.\nBut You Can Snapshot this Text file into an Image");
				        JTextPane ta=new JTextPane();
				        ta.setBounds(1,1,p.getWidth()-20,200);
				        newScreen();
				        ta.setAutoscrolls(true);
				        ta.setText(s2);
				        p.add(ta);
				    	Mytoolbar.resolutionLabel.setText("Not an Image File");
				        fileext="jpg";
				    	br.close();    
				        }
				        catch(Exception ex) 
				        {ex.printStackTrace();}                 
				    }
				}    
			} 
		}
		if(e.getSource()==Save)
		{
			if(filename==null)
			saveAsFile();
			
			else
			saveFile();
		
		}
		if(e.getSource()==as)
		{			
			saveAsFile();
		}
		if(e.getSource()==Exit)
		{
		if(saved==false)
			if(Mytoolbar.asktosave(f))
			{			}
			else
				f.dispose();
		else
			f.dispose();
		}
		if(e.getSource()==color)
		{
			Color initialcolor=Color.RED;    
			Color tempcolor=JColorChooser.showDialog(f,"Select a color",initialcolor);    
			if(tempcolor!=null)
				Mytoolbar.setcurrentcolor(tempcolor);
					
		}
		
		/*
		if(e.getSource()==cut)    		if(e.getSource()==paste)    if(e.getSource()==copy)    		if(e.getSource()==selectAll)    
		*/		
		
		if(e.getSource()==set)
		{
			setresolution(p);
		}
		if(e.getSource()==background_color)
		{ 
			Color tempcolor=JColorChooser.showDialog(f,"Fill Background Color",Mytoolbar.getcurrentcolor());    
			if(tempcolor!=null)
				p.setBackground(tempcolor);
		}
		if(e.getSource()==light)
		{	
			light.setForeground(new Color(120,120,190));
			dark.setForeground(Color.BLACK);
			Mytoolbar.settheme(0);
		}
		
		if(e.getSource()==dark)
		{

			dark.setForeground(new Color(120,120,190));
			light.setForeground(Color.BLACK);
			Mytoolbar.settheme(-200);
		}
		
		
		if(e.getSource()==about)
		{
			JPanel authorpanel=new JPanel();
			JLabel authorimage=new JLabel(new ImageIcon("PaintBanner.png"));
			JLabel authorname=new JLabel("Paint Version 1.0\nCreators:\nMahipal\nNaman");
			authorpanel.setSize(700, 700);
			authorpanel.setLayout(null);
			authorimage.setBounds(30, 30, 300, 300);
			authorname.setBounds(600, 600, 500, 20);
			//authorpanel.add(authorimage);
			authorpanel.add(authorname);
			JOptionPane.showMessageDialog(null,authorimage,"ABOUT",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/*public void saveImage(String name,String type,JPanel obj) {
		BufferedImage image= null;
		//BufferedImage image = new BufferedImage(p.getWidth(),p.getHeight(), BufferedImage.TYPE_INT_RGB);
		//Graphics2D g2 = image.createGraphics();
		//paint(g2);
		//try
		//{
		//ImageIO.write(image, type, new File(name+"."+type));
		//}
		//catch (Exception e) { e.printStackTrace(); }
		
		try {
			Robot r=new Robot();
			
			int x=p.getX();
			int y=p.getY();
			
			image= r.createScreenCapture(new Rectangle(x,y,p.getWidth(),p.getHeight()));//p.getWidth(),p.getHeight()
			try
			{
			ImageIO.write(image, type, new File(name+"."+type));
			}
			catch (Exception e) { e.printStackTrace(); }
					
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}*/
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint();
	}
	/*
	public void paint(Graphics g)
	{
	}
	*/
	
	public void newScreen()
	{
		p.removeAll();
		Mytoolbar.resolutionLabel.setText("Resolution : "+Mytoolbar.standardwidth+" X "+Mytoolbar.standardheight);
		p.setBounds(130,0,Mytoolbar.standardwidth-5,Mytoolbar.standardheight-5);
		p.setBackground(Color.WHITE);
		p.setForeground(Color.WHITE);
		//p.add(RenderPaint.label);
		//RenderPaint.clearscreen();
		okay=new RenderPaint(p);
		p.add(okay.label);
	}
	
	public void saveFile()
	{
		saved=true;
		Mytoolbar.saveImage(filepath,fileext,p);
	}
	
	public void saveAsFile()
	{
		JFileChooser fsaver=new JFileChooser();  
	    fsaver.setFileFilter(filter);
	    int i=fsaver.showSaveDialog(p); 
	    if(i==JFileChooser.APPROVE_OPTION)
	    {
	    	  filename=fsaver.getSelectedFile();  
	    	  filepath=filename.getPath();
	    	  int reply;
	    	  if(filepath.contains(".png")||filepath.contains(".jpg"))
		    	  {
	    		  System.out.println("Already contains an Extension\nCurrent filepath = "+filepath);
	    		  fileext=filepath.split("\\.")[filepath.split("\\.").length-1];			  
		    	  filepath=filepath.substring(0, filepath.length()-4);
		    	  System.out.println("Setting filepath to"+filepath);
		    	  }
	    	  else
	    	  {
		      System.out.println("filename  = "+filename);
		      filename=new File(filename+"."+fileext);
		      }
	    	  System.out.println("Final Filename is "+filename);
	    	  
	    	  if(filename.exists())
		      {
	    		  System.out.println("\nfile already exists!!!!!!!!\n");
		    	  reply=JOptionPane.showConfirmDialog(p,"File Already Exists ! Do you want to replace it ?");
			      if(reply==JOptionPane.YES_OPTION)
			      {
			    	  System.out.println("Replacing File");
			    	  saveconfirm("");
			      }
		      }
	    	  else{
	    		  System.out.println("\nFile doesn't exists already.\n");
	    		  saveconfirm(fileext);
	    	  }
	      }
	}
	private void saveconfirm(String extension) {
     	  System.out.println("\nIN SAVECONFIRM\nFilePath="+filepath);    
	      Mytoolbar.filename=filename.toString();
	      f.setTitle(Mytoolbar.filename+" - Paint");  
	      System.out.println("Saving  File as :: "+filename+"."+extension);
	      System.out.println("FileExtension = >"+extension+"<");
	      Mytoolbar.saveImage(filepath,extension,p);		
	}
	public static void setresolution(JPanel p)
	{
		JPanel temppanel=new JPanel();
		JTextField Width=new JTextField(5);
		JTextField Height=new JTextField(5);
		temppanel.add(new JLabel("Width : "));
		temppanel.add(Width);
		//temppanel.add(Box.createHorizontalStrut(10));
		temppanel.add(new JLabel("Height : "));
		temppanel.add(Height);
		int choice=JOptionPane.showConfirmDialog(null, temppanel, "Enter New Resolution", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,Mytoolbar.icons[Mytoolbar.b.length-1]);
		
		if(choice==JOptionPane.OK_OPTION) 
		{
			try{
			Integer tempwidth=Integer.parseInt(Width.getText());
			Integer tempheight=Integer.parseInt(Height.getText());
			if(tempwidth>Mytoolbar.standardwidth)
				tempwidth=Mytoolbar.standardwidth;
			if(tempheight>Mytoolbar.standardheight)
				tempheight=Mytoolbar.standardheight;
			p.setSize(tempwidth, tempheight);
			Mytoolbar.resolutionLabel.setText("Resolution : "+tempwidth+" X "+tempheight);
			}
			catch(Exception except)
			{
				JOptionPane.showConfirmDialog(null, "Invalid Values Entered. Try Again.","ERROR",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			}
			
		}
	
	}
}