package awt;

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

/*
 * Holding left-click draws, and
 * right-clicking ends the drawing.
 */
public class PaintAnyTime implements ActionListener{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaintAnyTime();
            }
        });
    }
    
    Button pencil;
    Button eraser;
    Button rect;
    int flag=4;
    int flag2=0,flag3=1;

    Color currentcolor=Color.blue;
    BufferedImage img = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
    Graphics2D  imgG2 = img.createGraphics();
   

    JFrame frame = new JFrame("Paint Any Time");
    JLabel label = new JLabel(new ImageIcon(img));

  
    MouseAdapter drawer= new MouseAdapter() {
        boolean rButtonDown,lbutton;
        Point prev,curr;
        Point init;
        @Override
        public void mousePressed(MouseEvent e) {
       
        		if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount()>=2&&flag==5)
        		{
        			System.out.println("Creating ending line between "+prev+"and"+init);
        			imgG2.drawLine(prev.x, prev.y, init.x, init.y);
        			label.repaint();
        			//flag=5; not necessary
        			flag2=0;
        			return;
        		}
        		if(SwingUtilities.isLeftMouseButton(e) && flag!=5 && flag2==0)
        			prev=e.getPoint();
        		
        		else if(SwingUtilities.isRightMouseButton(e) && flag==4)
        		{
        			//prev.x=curr.x;prev.y=curr.y;
        			flag=0;
        	    }
        		if(flag==6)
        		    currentcolor=imgG2.getColor();
        		if(flag==7 )
                	{if(flag2!=0){
                		curr=e.getPoint();
                		imgG2.drawLine(prev.x,prev.y,curr.x,curr.y);
                		flag2=0;
                		label.repaint();}
            		else
            			flag2++;
                	}
        	if(SwingUtilities.isLeftMouseButton(e) && flag==5)
    		{
        		if(flag2==0){
        			flag2++;  prev=e.getPoint(); 
        			init=prev;
        			System.out.println(init);
        			}
        		else{
        			curr=e.getPoint();
        			imgG2.setColor(Color.GREEN);
        			imgG2.drawLine(prev.x,prev.y,curr.x,curr.y);
        			label.repaint();
        			prev=curr;
        			System.out.println(prev);
        		}
        		
    		}
        	if(SwingUtilities.isRightMouseButton(e) && flag==5){
        		//flag=5;
        		flag2=0;      
        		}
    
        }
        

        @Override
        public void mouseDragged(MouseEvent e) {
        	if(flag==2)
        	{   
        		if(e.getPoint().x<0 || e.getPoint().y<0 ||e.getPoint().x>500||e.getPoint().y>500)
        		{ 
        			prev=e.getPoint();
        			//System.out.println("nanan");
        			if(e.getPoint().x<0){
        				imgG2.fillRoundRect(1,e.getPoint().y,5,25,2,2);}
        			else if(e.getPoint().y<0){
        				imgG2.fillRoundRect(e.getPoint().x,1,25,5,2,2);}
        			else if(e.getPoint().x>500){
        				imgG2.fillRoundRect(495,e.getPoint().y,5,25,2,2);}
        			else{
        				imgG2.fillRoundRect(e.getPoint().x,495,25,5,2,2);}
        			label.repaint();
        		}
        	    else{
        	    	Point  next = e.getPoint();
                    imgG2.drawLine(prev.x, prev.y, next.x, next.y);
                    label.repaint();
                    prev = next;
                }
        	}	
        	else if(flag==3)
        	{
        		if(SwingUtilities.isLeftMouseButton(e))
        		{
        			curr=e.getPoint();
        		}
        	}
        	else if(flag==6)
        	{
        		curr=e.getPoint();
            	imgG2.fillRect(curr.x,curr.y,50,50);
            	prev=curr;
            	label.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        	
        	
        	if(flag==2){
        		if (SwingUtilities.isLeftMouseButton(e)) {
                lbutton=false;
            }
        		/*if (SwingUtilities.isRightMouseButton(e)) {
            	Point curr=e.getPoint();
            	int ra=Math.abs( prev.x-curr.x);            	
            	label.repaint();
            	rButtonDown = false;
            }*/
        		// Useless
        }
        	else if(flag==3 || flag==4 ||flag==5 ||flag==6 || flag==8)
        	{
        		if(SwingUtilities.isLeftMouseButton(e))
        		{
        			curr=e.getPoint();
                	int minx=(curr.x>prev.x?prev.x:curr.x)   ;
                	int miny=(curr.y>prev.y?prev.y:curr.y)   ;
                	int rax=Math.abs( prev.x-curr.x);
                	int ray=Math.abs( prev.y-curr.y);
                	if(flag==3)
                		if(flag3==0)
                			imgG2.drawRect(minx, miny,rax, ray);
                		else
                			imgG2.fillRect(minx, miny,rax, ray);
                	if(flag==8){
                		if(e.getPoint().x>500)
                		{
                			System.out.println("naman paliwal");
                		}
                		
                			
                		
                		if(flag3==0){
                			imgG2.drawRoundRect(minx, miny,rax, ray,50,50);}
                		else {
                			imgG2.fillRoundRect(minx, miny,rax, ray,50,50);}
                	}
                	else if(flag==4)
                		if(flag3==0)
                			imgG2.drawOval(minx, miny,rax, ray);
                		else
                			imgG2.fillOval(minx, miny,rax, ray);
                	
                	else if(flag==6){
                		curr=e.getPoint();
                         minx=(curr.x>prev.x?prev.x:curr.x)   ;
                    	 miny=(curr.y>prev.y?prev.y:curr.y)   ;
                    	 rax=Math.abs( prev.x-curr.x);
                    	 ray=Math.abs( prev.y-curr.y);
                    	imgG2.fillRect(curr.x,curr.y,50,50);
                    	prev=curr;
                    	label.repaint();
                		imgG2.setColor(currentcolor);
                		//imgG2.drawRect(100,100,50,50);
                	}
                	label.repaint();
                }
        		}
        	
        	
        }
    };
       
    

    PaintAnyTime() {
        // RenderingHints let you specify
        // options such as anti aliasing.
    	// Smoothen the Texture.
        imgG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
        imgG2.setStroke(new BasicStroke(3));
        //
        pencil=new Button("pencil");
        pencil.setBounds(0,0,20,20);
        //frame.add(pencil);
         label.setPreferredSize(new Dimension(1100,1000));
        label.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        label.setBackground(currentcolor);
        label.setOpaque(true);
        label.addMouseListener(drawer);
        label.addMouseMotionListener(drawer);
        Cursor cursor =
            Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        label.setCursor(cursor);
        frame.add(label, BorderLayout.CENTER);
        frame.pack();
        imgG2.fillRect(50,50,50,50);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pencil)
		{
		
		}
	}
 }