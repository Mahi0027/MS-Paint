package frame2;
import javax.imageio.ImageIO;

import javax.swing.*;    
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.*;    
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Menu  extends JMenuBar implements ActionListener,MouseMotionListener
{    
	JFrame f;
	JPanel text_area=new JPanel(),status_bar=new JPanel(),button=new JPanel();
	private JMenuBar mb;    
	private JMenu file,edit,help,view;    
	private JMenuItem New,Open,Save,as,Exit,cut,copy,paste,color,selectAll,about,background_color,set,light,dark,theme;  
	private JLabel showx,showy;
	String getx,gety;
	private JButton getxx,getyy,b1,b2,b3,b4;  
	private JTextArea ta; 
	public void menuitem()
	{
		
		try
		{
			f=new JFrame("Paint");
			f.setBounds(50, 50, 1300, 650);  
			
			text_area.setBounds(0,0,f.getWidth(),f.getHeight()-40);
			text_area.setBackground(Color.WHITE);
			status_bar.setBounds(0,f.getHeight()-40, f.getWidth(),30);
			status_bar.setBackground(Color.gray);
			button.setBounds(0,0,70,text_area.getHeight());
			button.setBackground(Color.pink);
			//buttons
			b1=new JButton();
			b1.setText("Pencil");
			b1.setFont(new Font("Arial", Font.BOLD, 7));
			b1.setToolTipText("Pencil");
			b1.setBounds(5, 20, 60, 40);
			f.add(b1);
			
			b2=new JButton();
			b2.setText("brush");
			b2.setFont(new Font("Arial", Font.BOLD, 7));
			b2.setToolTipText("Brush");
			b2.setBounds(5, 100, 60, 40);
			f.add(b2);
			
			b3=new JButton();
			b3.setBounds(5, 180, 60, 40);
			f.add(b3);
			b4=new JButton();
			b4.setBounds(5, 260, 60, 40);
			f.add(b4);
			
			
			
			//Window window;
			
			f.setUndecorated(false);
			f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			f.addMouseMotionListener(this);
			
			mb=new JMenuBar();    
			file=new JMenu("File");    
			edit=new JMenu("Edit");    
			size=new JMenu("Size");
			help=new JMenu("Help"); 
			
			New=new JMenuItem("New");    
			Open=new JMenuItem("Open");    
			Save=new JMenuItem("Save");    
			as=new JMenuItem("Save_as");
			Exit=new JMenuItem("Exit");
			New.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			Open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			Save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			//as.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			Exit.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			
			New.addActionListener(this);    
			Open.addActionListener(this);    
			Save.addActionListener(this);    
			as.addActionListener(this);
			Exit.addActionListener(this);
			
			cut=new JMenuItem("Cut");    
			copy=new JMenuItem("Copy");    
			paste=new JMenuItem("Paste");    
			color=new JMenuItem("Color");
			selectAll=new JMenuItem("selectAll");
			cut.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			copy.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			paste.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			selectAll.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			
			
			cut.addActionListener(this);    
			copy.addActionListener(this);    
			paste.addActionListener(this);    
			color.addActionListener(this);
			selectAll.addActionListener(this);
			
			file.add(New);file.add(Open);file.add(Save);file.add(as);file.add(Exit);
			
			edit.add(color);edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);     
			
			
			
			background_color=new JMenuItem("Background color");    
			set=new JMenuItem("Change resolution");
			view=new JMenuItem("view");
			theme=new JMenuItem("Theme");
			light=new JMenuItem("Light");
			dark=new JMenuItem("Dark");
			theme.add(light);
			theme.add(dark);
			view.add(background_color);
			view.add(set);
			view.add(theme);
			
			
			about=new JMenuItem("About");
			about.addActionListener(this);
			help.add(about);
			
			mb.add(file);
			mb.add(edit);
			mb.add(help);
			mb.add(view);
			
			ta=new JTextArea();
		    
			ta.setBounds(0,0,f.getWidth(),f.getHeight()-50);    
			text_area.add(mb);
			text_area.add(ta);    
			
			//botton label
			
			getx="x-axis :";
			showx=new JLabel("");
			//getx.setBounds(1100, 610,50 , 20);
			showx.setBounds(1150,0,40,20);
			//f.add(getx);
			status_bar.add(showx);
			gety="y-axis :";
			showy=new JLabel("");
			//gety.setBounds(1200, 610,50 , 20);
			showx.setBounds(1250,0,40,20);
			//f.add(gety);
			status_bar.add(showy);
			
			f.add(button);
			f.add(status_bar);
			f.add(text_area);  
			
			f.setJMenuBar(mb);
			//f.setLayout(new GridLayout(10,8)); 
			f.setLayout(null);
			f.setResizable(false);
			f.setVisible(true);
		}catch(Exception e){System.out.println("exfff");}
	}     
	public void actionPerformed(ActionEvent e)
	{   
		if(e.getSource()==New)
		{
			ta.selectAll() ; 
			ta.cut();
			ta.setText(" ");
			ta.selectAll();
			ta.copy();
			ta.paste();
		}
		if(e.getSource()==Open)
		{
			
			if(e.getSource()==Open){    
			    JFileChooser fc=new JFileChooser();  
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files(*.jpg,*.png,*.bmp)", "jpg","jpeg","png","bmp");
			    fc.setFileFilter(filter);
			    int i=fc.showOpenDialog(selectAll);  
			    if(i==JFileChooser.APPROVE_OPTION)
			    {    
			        File f=fc.getSelectedFile();    
			        final String filepath=f.getPath();
			        
				    try
				    {
				    	BufferedImage image=null;
				    	try
				    	{
				    				image=ImageIO.read(new File(filepath));
				    	}
				    	catch(Exception mm)
				    	{
				    		mm.printStackTrace();
				    		System.exit(1);
				    	}
				    	
				    	ImageIcon imageIcon=new ImageIcon(image);
				    	JLabel jLabel=new JLabel();
				    	jLabel.setIcon(imageIcon);
				    	text_area.getRootPane().add(jLabel, BorderLayout.CENTER);
				        //text_area.pack();
				        //f.setLocationRelativeTo(null);
				    	 
				    }
				    catch(Exception ee)
				    {
				        try{  
				        BufferedReader br=new BufferedReader(new FileReader(filepath));    
				        String s1="",s2="";                         
				        while((s1=br.readLine())!=null)
				        {    
				        	s2+=s1+"\n";    
				        }    
				        ta.setText(s2);    
				        br.close();    
				        }
				        catch (Exception ex) {ex.printStackTrace();  }                 
				    }
				}    
			} 
		}
		if(e.getSource()==Save)
		{
			
		}
		if(e.getSource()==as)
		{
			
		}
		if(e.getSource()==Exit)
		{
			f.dispose();
		}
		
		
		if(e.getSource()==cut)    
		ta.cut();    
		if(e.getSource()==paste)    
		ta.paste();    
		if(e.getSource()==copy)    
		ta.copy();    
		if(e.getSource()==color)
		{
			Color initialcolor=Color.RED;    
			Color color=JColorChooser.showDialog(f,"Select a color",initialcolor);    
		}
		if(e.getSource()==selectAll)    
		ta.selectAll() ; 
		
		if(e.getSource()==about)
		{
			JOptionPane o =new JOptionPane();
			//final ImageIcon icon = new ImageIcon(getClass().getResource("/home/mahipal/workspace/paint/src/jai.png"));
			//JOptionPane.showMessageDialog(null, "te", "hello", JOptionPane.INFORMATION_MESSAGE, icon);
			o.showMessageDialog(f,"           MyPaint"+"\n\n\n"+"created by:"+"Naman Paliwal"+"\n"+"                    Shashank Sharma"+"\n"+"                    Mahipal Singh"+"\n"+"Version :    1.0");
		}
	}
	public void mouseMoved(MouseEvent e)
	{
		showx.setText(getx+" "+e.getX());
		showy.setText(gety+" "+e.getY());
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}