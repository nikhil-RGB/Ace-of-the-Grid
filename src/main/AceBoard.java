package main;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.event.*;

import util.AceType;
import util.PositionType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
@SuppressWarnings("unused")
public class AceBoard implements java.io.Serializable
{
	public static String frostID="FROST_ACE";
	public static String rageID="RAGE_ACE";
	public static String flameID="FLAME_ACE";
	public static String betrayalID="BETRAYAL_ACE";
	public static int grid_x=10;
	public static int grid_y=10;
	protected AcePlacer ap1;
	private static final long serialVersionUID=2L;
	protected int flame_aces;
	protected int ice_aces;
	protected int rage_aces;
	protected int betrayal_aces;
	private JButton[][] cells;
	private JPanel holder;
    private JFrame window;
    public int player_no;
    public ArrayList<Color> cols;
    public static void main(String[] args)
    {
    	ArrayList<Color> iou=new ArrayList<>(0);
    	iou.add(Color.RED);
    	iou.add(Color.BLUE);
    	iou.add(Color.GREEN);
    	new AceBoard(3,4,5,6,iou);
    }
    public AceBoard(int flame_aces, int ice_aces,int rage_aces,int betrayal_aces,ArrayList<Color> pls)
    {
    window=new JFrame("Ace Of The Grid");
    window.setSize(1000,1000);
    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
    window.setResizable(false);
    this.initBoard(flame_aces,ice_aces,rage_aces,betrayal_aces,pls);
    window.add(holder);
    window.setVisible(true);
    ap1.setAceIcons();
    }
    public void initBoard(int fa,int ia,int ra,int ba,ArrayList<Color> colours)
    {//This method initializes the board
    	cols=colours;
    	AceEventsManager acm=new AceEventsManager();
    	Border ob=BorderFactory.createLineBorder(Color.GREEN,5,Boolean.TRUE);
    	cells=new JButton[grid_x][grid_y];
    	holder=new JPanel(new GridLayout(grid_x,grid_y));
    	for(int i=0;i<cells.length;++i)
    	{
    		for(int j=0;j<cells[0].length;++j)
    		{
    		cells[i][j]=new JButton("");
    		cells[i][j].setBackground(Color.GRAY);
    		cells[i][j].setActionCommand(i+" "+j);
    		cells[i][j].addActionListener(acm);
    		holder.add(cells[i][j]);
    		}
         }
    	holder.setBorder(ob);
    	AcePlacer ap=this.new AcePlacer();
    	ap1=ap;
        ap.placeAces(ia,fa, ra, ba);
    }

    private class AcePlacer
    {
    	protected ArrayList<Point> points; 
        protected ArrayList<Point> generateAcePositions(int fa,int ia,int ra,int ba)
        { 
          Random r=new Random();
          ArrayList<Point> arr=new ArrayList<>(0);
          int total=fa+ia+ra+ba;
          for(int k=0;k<total;)
          {
        	 int x1=r.nextInt(AceBoard.grid_x);
        	 int y1=r.nextInt(AceBoard.grid_y);
        	 if((!arr.contains(new Point(x1,y1)))&&(this.checkAdjacentCells(new Point(x1,y1))))
        	 {
        	  ++k;
        	 // System.out.println("Ace Placement: "+x1+" "+y1);//FOR TESTING
        	  arr.add(new Point(x1,y1));
        	  cells[x1][y1].setActionCommand("ACE "+cells[x1][y1].getActionCommand());
        	 }
          }
        points=arr;
    	return arr;
          }

        private boolean checkAdjacentCells(Point xx)
        {
        int x=xx.x;
        int y=xx.y;
        ArrayList<Point> arr=new ArrayList<>(0);
        arr.add(new Point(x+1,y+1));
        arr.add(new Point(x-1,y-1));
        arr.add(new Point(x+1,y));
        arr.add(new Point(x-1,y));
        arr.add(new Point(x,y+1));
        arr.add(new Point(x,y-1));
        arr.add(new Point(x-1,y+1));
        arr.add(new Point(x+1,y-1));
        ArrayList<Point> indices=new ArrayList<>(0);
        for(int kk=0;kk<arr.size();++kk)
        {
         Point p=arr.get(kk);	
         if((p.x<0)||(p.y<0))
         {indices.add(p);}
         else if((p.x>=grid_x)||(p.y>=grid_y))
         {
        	 indices.add(p);
         }
        }
        arr.removeAll(indices);
        for(Point p:arr)
        {
        if(cells[p.x][p.y].getActionCommand().contains("ACE"))
        {return false;}
        
        }
        return true;
        
        }
    	public void placeAces(int fa,int ia,int ra,int ba)
    	{
    	ArrayList<Point> positions=this.generateAcePositions(fa, ia, ra, ba);
    	int[] lengths=new int[] {fa,ia,ra,ba};
    	String ids[]= new String[] {frostID,flameID,rageID,betrayalID};
    	int x=0;
    	for(int i=0;i<4;++i)
    	{   
    	    int currentl=lengths[i];
    	    String id=ids[i];
    	    for(int j=0;j<currentl;++j,++x)
    		{
    	    Point point=positions.get(x);
    		JButton unit=cells[point.x][point.y];
    		unit.setActionCommand(unit.getActionCommand()+" "+id);
    		}
    	}
    	
    	}
    	public ImageIcon[] prepareImageIcons()
    	{
    		ImageIcon[] icons= {new ImageIcon(AceOfTheGrid.fire_icon1),new ImageIcon(AceOfTheGrid.ice_icon),new ImageIcon(AceOfTheGrid.rage_icon),new ImageIcon(AceOfTheGrid.betray_icon)};
    		ImageIcon[] iconsn=new ImageIcon[icons.length];
    		Dimension d=cells[0][0].getSize();
    		int x=0;
    		for(ImageIcon p:icons)
    		{
    		Image p1=p.getImage().getScaledInstance(d.width,d.height,Image.SCALE_SMOOTH);
    		iconsn[x]=new ImageIcon(p1);
    		++x;
    		}
    		return iconsn;
    	}
    	public void setAceIcons()
    	{
    		ImageIcon[] ffs=this.prepareImageIcons();
    	 for(Point coord:this.points)
    	 {
    		 ImageIcon toBP=null;
    		 JButton unit=cells[coord.x][coord.y];
    		 String acmd=unit.getActionCommand();
    		 if(acmd.contains(AceBoard.frostID))
    		 {toBP=ffs[1];}
    		 else if(acmd.contains(AceBoard.flameID))
    		 {toBP=ffs[0];}
    		 else if(acmd.contains(AceBoard.rageID))
    		 {toBP=ffs[2];}
    		 else
    		 {toBP=ffs[3];}
    		 unit.setIcon(toBP);
    	 }
    	}
    	
    }
    public class CellManager
    {   //check here!
    	public Color currentCol;
    	{
    	 currentCol=cols.get(player_no);
    	}
    	public int calcCriticalMass(JButton cell)
    	{
    	 if(cell.getActionCommand().contains("ACE")) 
    	 {return 0;}
    	 PositionType pt=this.calculatePosition(cell);
    	 int req=0;
    	 switch(pt)
    	 {
    	 case CORNER:
    	 req=1;
    	 break;
    	 case EDGE:
    	 req= 2;
    	 break;
    	 case CENTER:
         req= 3;
    	 break;
    	 }
    	 return req;
    	}
    	public boolean destroyCell(JButton cell)
    	{
    		if(cell.getActionCommand().contains("ACE"))
    		{return false;}
    		cell.setBackground(Color.GRAY);
    		cell.setText("");
    		cell.setForeground(Color.BLACK);
    		return true;
    	}
    	public void activateAce(JButton unit)
    	{
    		AceType ac=this.recognizeAce(unit);
    		if(ac==AceType.NONE)
    		{return;}
    		switch(ac)
    		{
    		case FROST_ACE:
    		//from here
    	    ArrayList<JButton> surr=this.returnAdjacentCells(unit);
    	    for(JButton jj:surr)
    	    {
    	      if(this.isCritical(jj))
    	      {
    	    	  destroyCell(jj);
    	      }
    	    }
    		break;
    		case FLAME_ACE:
    		ArrayList<JButton> surr1=this.returnAdjacentCells(unit);
    		for(JButton jj:surr1)
    	         {
    	      destroyCell(jj);
    	    	  }
    		break;
    		case RAGE_ACE:
    		ArrayList<JButton> bro=this.returnAdjacentCells(unit);
    		for(JButton btn:bro)
    		{
    			this.addUnit(btn);
    			btn.setBackground(currentCol);
    		}
    		this.startExplosionSequenceFrom(null);
    		break;
    		case BETRAYAL_ACE:
    		this.currentCol=cols.get(Math.abs(player_no-1));	
    		break;
    		case NONE:
    		}
    	}
    	public AceType recognizeAce(JButton unit)
    	{
    	  String acmd=unit.getActionCommand();
    	  if(!acmd.contains("ACE"))
    	  {return AceType.NONE;}
    	  Scanner reader=new Scanner(acmd);
    	  reader.next();
    	  reader.next();
    	  reader.next();
    	  String id=reader.next();
    	  reader.close();
    	  return AceType.valueOf(id); 
    	}
    	public void addUnit(JButton cell)
    	{   
    		if(cell.getActionCommand().contains("ACE"))
    		{return;}	
    		String text=cell.getText();
    		if(text.isEmpty())
    		{text="0";}
    		int num=Integer.parseInt(text);
    		++num;
    		cell.setText(""+num);
    		cell.setBackground(currentCol);
    	}
    	public PositionType calculatePosition(JButton unit)
    	{
    		
    		String acmd=unit.getActionCommand();
    		Scanner reader=new Scanner(acmd);
    		if(acmd.contains("ACE"))
    		{
    			reader.next();
    		}
    		int x1=reader.nextInt();
    		int y1=reader.nextInt();
    		reader.close();
    		int[][] corners= {{0,0},{0,grid_y-1},{grid_x-1,0},{grid_x-1,grid_y-1}};
    		for(int[] pos:corners)
    		{
    			if((x1==pos[0])&&(y1==pos[1]))
    			{return PositionType.CORNER;}
    		}
    		if((x1==0)||(y1==0))
    		{
    			return PositionType.EDGE;
    		}
    		return PositionType.CENTER;
    	}
    	
       public boolean isCritical(JButton butt)
       {
    	   String acmd=butt.getActionCommand();
    	   String text=butt.getText();
    	   if(acmd.contains("ACE"))
    	   {
    		   return true;//ACES ACT AS CRITICAL CELLS WITH ANANMOLOUS PROPERTIES
    	   }
    	   	int req=this.calcCriticalMass(butt);
    	   	if(text.isEmpty())
    	   	{text="0";}
    	   boolean flag=(Integer.parseInt(text)==req)?true:false;
           return flag;   	 
       }
       public boolean isStable(JButton butt)
       {
    	if(butt.getActionCommand().contains("ACE"))
    	{return true;}
    	int req=this.calcCriticalMass(butt);
    	String text=butt.getText();
    	if(text.isEmpty()) {return true;}
    	if(Integer.parseInt(text)>req)
    	{return false;}
    	return true;
       }
       public ArrayList<JButton> returnAdjacentCells(JButton cell)
       {
    	String acmd=cell.getActionCommand();
    	acmd=acmd.replace("ACE ", "");
    	Scanner reader=new Scanner(acmd);
    	int x=reader.nextInt();
    	int y=reader.nextInt();
    	reader.close();
    	ArrayList<Point> arr=new ArrayList<>(0);
        arr.add(new Point(x+1,y+1));
        arr.add(new Point(x-1,y-1));
        arr.add(new Point(x+1,y));
        arr.add(new Point(x-1,y));
        arr.add(new Point(x,y+1));
        arr.add(new Point(x,y-1));
        arr.add(new Point(x-1,y+1));
        arr.add(new Point(x+1,y-1));
        ArrayList<JButton> re=new ArrayList<>(0);
        for(Point loc:arr)
        {
        	int x1=loc.x;
        	int y1=loc.y;
        	if(!((x1<0||y1<0)||((x1>=grid_x)||(y1>=grid_y)))) 
        	{
        		re.add(cells[x1][y1]);
        	}
        }
        return re;
       }
       public ArrayList<JButton> returnThreatenedCells(JButton cell)
       {
       	String acmd=cell.getActionCommand();
       	acmd=acmd.replace("ACE", "");
       	Scanner reader=new Scanner(acmd);
       	int x=reader.nextInt();
       	int y=reader.nextInt();
       	reader.close();
       	ArrayList<Point> arr=new ArrayList<>(0);
           arr.add(new Point(x+1,y));
           arr.add(new Point(x-1,y));
           arr.add(new Point(x,y+1));
           arr.add(new Point(x,y-1));
           ArrayList<JButton> re=new ArrayList<>(0);
           for(Point loc:arr)
           {
           	int x1=loc.x;
           	int y1=loc.y;
           	if(!((x1<0||y1<0)||((x1>=grid_x)||(y1>=grid_y)))) 
           	{
           		re.add(cells[x1][y1]);
           	}
           }
           return re;
       }
       public void explodeAt(JButton unit)
       {
       	//This method handles the "explosion" phase of chain reaction
    	int num=Integer.parseInt(unit.getText());
    	ArrayList<JButton> jb=new ArrayList<>(0); 
        Color pl=cols.get(player_no);
        ArrayList<JButton> units=this.returnThreatenedCells(unit);
        num=num-units.size();
        for(JButton tb:units)
        {
        	String ac=tb.getActionCommand();
        	if(ac.contains("ACE"))
        	{
        		jb.add(tb);
        	}
        	else
        	{
        		this.addUnit(tb);
        		tb.setBackground(pl);
        	}
        }
        if(num<=0)
       {this.destroyCell(unit);}
        else
        {unit.setText(num+"");}
        for(JButton jojo:jb)
        {this.activateAce(jojo);}
       }
       public void startExplosionSequenceFrom(JButton butt)
       {
    	   if(butt!=null)
    	 {explodeAt(butt);}
    	 
    	 do 
    	 {
    		 for(int i=0;i<grid_x;++i)
    		 {
    			for(int j=0;j<grid_y;++j) 
    			{
    				JButton on=cells[i][j];
    				if(!this.isStable(on))
    				{this.explodeAt(on);}
    			}
    		 }
    	
    	 }
    	 while(!this.isBoardStable());
       }
       public boolean isBoardStable()
       {
    	   for(int i=0;i<grid_x;++i)
    	   {
    		   for(int j=0;j<grid_y;++j)
    		   {
    			   JButton butt=cells[i][j];
    			   if(!this.isStable(butt))
    			   {return false;}
    		   }
    	   }
    	   return true;
       }
       public void updateCurrentCol()
       {
    	this.currentCol=cols.get(player_no) ;  
       }
    }
    public class AceEventsManager implements java.awt.event.ActionListener
    {
	@Override
	public void actionPerformed(ActionEvent ev)
	{   
		JButton bc=(JButton)(ev.getSource());
		String acmd=bc.getActionCommand();
		String text=bc.getText();
		if(acmd.contains("ACE"))
		{return;}
		else if((!bc.getText().isEmpty())&&(!bc.getBackground().equals(cols.get(player_no))))
		{return;}
		CellManager cm=new CellManager();
	    if(!cm.isCritical(bc))
	    {cm.addUnit(bc);}
	    else
	    {cm.startExplosionSequenceFrom(bc);}
	    ++player_no;
	    if(player_no>=cols.size())
	    {player_no=0;}
	    cm.updateCurrentCol();
	}
    }
	
}
