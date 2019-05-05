package main_package;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.*;
public class Icon extends JPanel
{
	static final long serialVersionUID=0;
	JLabel name ;
	JProgressBar jb;
	int x=100,y=20,increment=20,h=20;
	int length=200;
	int height=100;
	String[] bakwaas={"Panels","Icons","Fonts","Paint...","Paint..."};
	JFrame f=new JFrame();

	
	public void paintComponent(Graphics g) {		
		//System.out.println("paintComponent");
		Toolkit t=Toolkit.getDefaultToolkit();  
        Image img=t.getImage("PaintBanner.png");  
        //ImageIcon icon=new ImageIcon(img);
        g.drawImage(img,0,0,this);
        jb.setVisible(true);
       }  
	public void progressbar(int start)
	{ 
		//System.out.println("Entering progressbar");
		this.setBounds(0, 0, 375, 170);
		
		f.setResizable(false);f.setBounds(500,250,375,240);
		f.setLayout(null);f.setAlwaysOnTop(true);
		f.add(this);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		repaint();
		f.setVisible(true);
		jb=new JProgressBar(0,100);
		jb.setValue(start);
		jb.setFont(new Font("Bookman Old Style",Font.TRUETYPE_FONT,18));
		jb.setString("Loading ");
		jb.setVisible(false);
		//jb.setForeground(new Color(120, 190, 90));
		jb.setForeground(new Color(240,240,240));
		jb.setStringPainted(true);		
		//System.out.println("f.geth = "+f.getHeight());
		//System.out.println("this.geth = "+this.getHeight());
		this.setLayout(null);
		jb.setBounds(0,175,f.getWidth(),(f.getHeight()-this.getHeight())/2);
		f.add(jb);
		iterate();
	}
	public void iterate()
	{
		int i=jb.getValue();
		int k=0;
		while(i<100)
		{    
			  jb.setValue(i);    
			  i++;    
			  jb.setString(jb.getString().substring(0, 8)+bakwaas[k]+"  "+i+"%");			  
			  if(i%20==0)
				  k++;
			  try{Thread.sleep(15);}
			  catch(Exception e){}   
		}
		f.dispose();
	}
}
