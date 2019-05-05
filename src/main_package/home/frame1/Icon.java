package frame1;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.*;

public class Icon extends Canvas
{
	JFrame w;
	JFrame iconframe = new JFrame();
	JProgressBar jb;
	Label name;
	int x=100,y=20,increment=20,h=20;
	int length=200;
	int height=100;
	/*public Icon()
	{
		setVisible(true);
	}*/
	public void paint(Graphics g)
	{  
		iconframe.setSize(300,300);
		iconframe.setLayout(null);
		iconframe.setAlwaysOnTop(true);
		iconframe.setVisible(true);
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("/home/mahipal/workspace/paint/src/images.jpg");  
        g.drawImage(i, 0,0,this);      
    }  
	public void progressbar()
	{ 
		int i=0;
		//w=new Window(iconframe);
		w=new JFrame();
		
		name=new Label("Paint");
		name.setFont(new Font("Courier New", Font.ITALIC, 60));
		name.setBackground(Color.getHSBColor(50, 30, 50));
		name.setForeground(Color.getHSBColor(140, 120, 220));
		name.setBounds(700,500,100,100);
		name.setAlignment(name.CENTER);
		w.add(name);
		jb=new JProgressBar(0,100);
		jb.setValue(0);
		jb.setStringPainted(true);
		jb.setSize(100, 50);
		//f.setAlwaysOnTop(true);
		w.setBounds(500, 200, length,height);
		//f.setLayout(new GridLayout(20,1));
		w.add(jb,BorderLayout.SOUTH);
		w.add(name,BorderLayout.CENTER);
		w.setResizable(false);
		w.setVisible(true);
		iterate();
	}
	public void iterate()
	{
		int i=0;
		while(i<=100)
		{    
			  jb.setValue(i);    
			  i++;    
			  try{Thread.sleep(25);}catch(Exception e){}   
		}
		if(i==101)
		{
			w.dispose();
			w=null;
			//w.setVisible(false);
			iconframe.dispose();
			iconframe=null;
		}
	}
}
