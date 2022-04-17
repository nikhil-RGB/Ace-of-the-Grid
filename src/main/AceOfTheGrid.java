//Required changes: selection frame size and access methods.
//External dependancies:None
//Copied and modified Helpers: Arcade4: utilities.PlayerColorSelector
//Look modified by changing background to black for each ColorChooser,tab background is of 
//currently selected color
package main;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import util.AceType;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import util.HoverResponse;
import util.PlayerColorSelector;
@SuppressWarnings("unused")
public final class AceOfTheGrid {
public static String fire_icon1="img_res//flame1.png";
public static String fire_icon="img_res//flame2.png";
public static String main_icon_dep="img_res//MAIN_GAME_ICON.png";
public static String ace_icon_dep="img_res//AceCard.png";
public static String frost_icon="img_res//ace_of_frost.png";
public static String ice_icon="img_res//frost.png";
public static String rage_icon="img_res//rage.png";
public static String betray_icon="img_res//betrayal.png";
public static String bet2_icon="img_res//betrayed.png";
public static Border softborder;
public static Border titled_b;
public static Border compnd;
JTabbedPane intro_pane;
JPanel startGamePanel;
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(()->{
		AceOfTheGrid.initializeDefaultBorders();
		AceOfTheGrid obj=new AceOfTheGrid();
        obj.createIntroductionGUI();
		});
	}
	public void createIntroductionGUI()
	{
		
		JFrame frame=new JFrame("Welcome to ACE OF THE GRID");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK.brighter());
		frame.setSize(500,500);
		intro_pane=new JTabbedPane(SwingConstants.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		intro_pane.add(this.createLoadScreen(),"Start Playing!");
		frame.add(intro_pane);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(AceOfTheGrid.main_icon_dep).getImage());
		frame.setVisible(true);
	}
	public JPanel createLoadScreen()
	{
		ActionListener onCreate=(ev)->{
			SwingUtilities.invokeLater(()->{
				CreateAGamePanel gp=new CreateAGamePanel(null,"Create a new game",false);
			    gp.setSize(660,700);
			    gp.pack();
			    gp.setVisible(true);
			    gp.readyIcons();
			});
		};
		ActionListener onLoad=(ev)->{
			JOptionPane.showMessageDialog(null,"Facility not implemented yet");
		};
		JPanel panel=new DarkAcePicturePanel(true);
		panel.setBackground(Color.BLACK);
		Font f=new Font("algerian",Font.BOLD,18);
		JButton create=new JButton("Create A New Game ");
		create.setBackground(Color.BLACK);
		create.setForeground(Color.WHITE);
		create.setFont(f);
		create.setOpaque(false);
		create.addActionListener(onCreate);
		HoverResponse forCreate;
		create.addMouseListener(forCreate=new HoverResponse(Color.WHITE,Color.GREEN));
		create.addFocusListener(forCreate);
		HoverResponse forLoad;
		JButton load=new JButton("Load a game");
		load.setBackground(Color.BLACK);
		load.setForeground(Color.WHITE);
		load.setFont(f);
		load.setOpaque(false);
		load.addActionListener(onLoad);
		load.addMouseListener(forLoad=new HoverResponse(Color.WHITE,Color.GREEN));
		load.addFocusListener(forLoad);
		create.setBorder(compnd);
	    load.setBorder(compnd);
	    Box holder=Box.createVerticalBox();
	    holder.add(Box.createRigidArea(new Dimension(300,200)));
	    Box other_h=Box.createHorizontalBox();
	    other_h.add(create);
	    other_h.add(Box.createRigidArea(new Dimension(25,10)));
	    other_h.add(load);
	    holder.add(other_h);
	    panel.add(holder);
	    this.startGamePanel=panel;
	    return panel;
	}
	public static void initializeDefaultBorders()
	{
		softborder=BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED, Color.CYAN.darker(),Color.BLACK);
		titled_b=BorderFactory.createTitledBorder("Let's go!");
		compnd=BorderFactory.createCompoundBorder(titled_b, softborder);
	}
	private static class AceCardPicturePanel extends JPanel
	{
		Image s;
		private static final long serialVersionUID = 3816057888298957780L;
		public AceCardPicturePanel()
		{
			super();
			s= new ImageIcon(AceOfTheGrid.ace_icon_dep).getImage();
		}
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		    g.drawImage(s,0,0,this.getWidth()+1,this.getHeight()+1,null);
        }
	}
	private static class PictureLabel extends JLabel
	{
		private static final long serialVersionUID=2234431225666719L;
		 private Image s;
			public PictureLabel(String ss,String path)
			{
			 super(ss);
			 s=new ImageIcon(path).getImage();
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g); 
	            g.drawImage(s,0,0,this.getWidth()+1,this.getHeight()+1,null);
			}
	}
	private static class PicturePanel extends JPanel
	{
	 private static final long serialVersionUID=22344555666719L;
	 private Image s;
		public PicturePanel(String path)
		{
		 super();
		 s=new ImageIcon(path).getImage();
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
            g.drawImage(s,0,0,this.getWidth()+1,this.getHeight()+1,null);
		}
	}
	private static class DarkAcePicturePanel extends JPanel
	{   
		boolean isForTitle;
		Image s=new ImageIcon(AceOfTheGrid.main_icon_dep).getImage();
		public DarkAcePicturePanel(boolean value)
		{
			isForTitle=value;
		}
		private static final long serialVersionUID = -3750024390464025103L;
        public void paintComponent(Graphics g)
		{
        	super.paintComponent(g); 
            g.drawImage(s,0,0,this.getWidth()+1,this.getHeight()+1,null);
            if(!this.isForTitle)
            {return;}
        	g.setColor(Color.RED);
        	g.setFont(new Font("algerian",Font.BOLD,32));
        	g.drawString("Ace Of The Grid",5,30);
		}
	}
	protected class CreateAGamePanel extends JDialog
	{
		private static final long serialVersionUID = -7530758250719312287L;
		final Font headers=new Font("algerian",Font.BOLD,24);
		final Font info=new Font(Font.SERIF,Font.BOLD,16);
		public JTabbedPane requirements;
		public JPanel number_colors;
		      public PlayerColorSelector pcs;
		      int pl_no;
		      public JTabbedPane aiopps;
		      public ArrayList<JCheckBox> jcbb;
		public JPanel aces;
		  public int f_no;
	      public int r_no;
	      public int fl_no;
	      public int b_no;
		      public JLabel frost1;
		      public JLabel main_lab1;
		      public JLabel number1;
		      public JLabel flame1;
		      public JLabel number2;
		      public JLabel main_lab2;
		      public JLabel main_lab3;
		      public JLabel wrath1;
		      public JLabel number3;
		      public JLabel betray1;
		      public JLabel main_lab4;
		      public JLabel number4;
		public JPanel ai_opps_names;
		{
			pl_no=2;
			f_no=3;
			r_no=3;
			fl_no=3;
			b_no=3;
			this.jcbb=new ArrayList<>(0);
		}
		public CreateAGamePanel(Frame parent,String s,boolean mod)
		{
			super(parent,s,mod);
			requirements=new JTabbedPane(SwingConstants.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
			requirements.addTab("Preferences",this.createNumberAndColorTab());
			requirements.add("Aces",this.createAcePanel());
		    this.add(requirements);
		}
	    protected JPanel createNumberAndColorTab()
	    {
		    ItemListener il=(ie)->
		    {
		    	JRadioButton jrad=(JRadioButton)ie.getSource();
		        pl_no=Integer.parseInt(jrad.getText());
		        if(pcs==null||this.aiopps==null)
		        {
		        	return;
		        }
		        if(pcs.getTabbedPane().getTabCount()>pl_no)
		        {
		        	pcs.removeColorTabsTill(pl_no);
		            removePlayerTabTill(pl_no);
		        }
		        else if(pcs.getTabbedPane().getTabCount()<pl_no)
		        {
		        	pcs.addColorTabs(pl_no);
		        	addPlayerTabTill(pl_no);
		        }
		    };
	    number_colors=new AceCardPicturePanel();
	    number_colors.setBackground(Color.BLACK);
	    number_colors.setLayout(new BorderLayout());
	    Box box=Box.createVerticalBox();
	    JLabel l=new JLabel("Select number of players");
	    l.setAlignmentX(Component.CENTER_ALIGNMENT);
	    l.setForeground(Color.blue.darker());
	    l.setFont(this.headers);
	    box.add(l);
	    box.add(Box.createRigidArea(new Dimension(50,50)));
	    JPanel options_holder=new JPanel(new FlowLayout());
	    ButtonGroup bg=new ButtonGroup();
	    JRadioButton btns[]=new JRadioButton[6];
	    for(int k=1;k<btns.length;++k)
	    {   
	    	if(k==1)
	    	{
	    		btns[k]=new JRadioButton(k+1+"",true);
	    	}
	    	else
	    	{
	    	btns[k]=new JRadioButton(k+1+"",false);
	    	}
	    	btns[k].setFont(info);
	    	options_holder.add(btns[k]);
	    	bg.add(btns[k]);
	    	btns[k].addItemListener(il);
	    }
	    box.add(options_holder);
	    box.add(Box.createRigidArea(new Dimension(40,40)));
	    JLabel lab=new JLabel("Select Color Of Players");
	    lab.setAlignmentX(Component.CENTER_ALIGNMENT);
	    lab.setFont(headers);
	    lab.setForeground(Color.blue.darker());
	    box.add(lab);
	    box.add(Box.createVerticalStrut(30));
	    PlayerColorSelector ps=new PlayerColorSelector(2, "",null,false);
	    this.pcs=ps;
	    JTabbedPane req=ps.getTabbedPane();
        ps.dispose();
	    box.add(req);
	    box.add(this.createNumberOppiPane());
	    number_colors.add(box);
	    return number_colors;
	    }
	    protected JTabbedPane createAcePanel()
	    {
	        JTabbedPane acem=new JTabbedPane(JTabbedPane.BOTTOM,JTabbedPane.SCROLL_TAB_LAYOUT);
            JPanel frost=this.createFrostPanel();
            JPanel fire=this.createFlamesPanel();
            JPanel rage=this.createRagePanel();
            JPanel betrayal=this.createBetrayalPanel();
            //other panels here
            acem.add("Ace Of Frost",frost);
            acem.setIconAt(0,new ImageIcon(new ImageIcon(AceOfTheGrid.frost_icon).getImage().getScaledInstance(20, 20,Image.SCALE_FAST)));
            acem.setBackgroundAt(0,Color.CYAN);
            acem.add("Ace Of Fire",fire);
            acem.setIconAt(1,new ImageIcon(new ImageIcon(AceOfTheGrid.fire_icon).getImage().getScaledInstance(20, 20,Image.SCALE_FAST)));
            acem.setBackgroundAt(1,Color.ORANGE.darker());
            acem.add("Ace of Rage",rage);
            acem.setIconAt(2,new ImageIcon(new ImageIcon(AceOfTheGrid.rage_icon).getImage().getScaledInstance(20, 20,Image.SCALE_FAST)));
            acem.setBackgroundAt(2,Color.RED.darker());
            acem.add("Ace of Betrayal",betrayal);
            acem.setBackgroundAt(3, Color.GRAY);
            acem.setIconAt(3,new ImageIcon(new ImageIcon(AceOfTheGrid.bet2_icon).getImage().getScaledInstance(20, 20,Image.SCALE_FAST)));
            return acem;
	    }
	    protected JPanel createFlamesPanel()
	    {
	    	JPanel fire=new AceCardPicturePanel();
	    	Box holder=Box.createVerticalBox();
	    	JLabel main_lab=new JLabel("THE ACE OF FLAMES");
	    	main_lab2=main_lab;
	    	main_lab.setForeground(Color.WHITE);
	    	main_lab.setFont(new Font("algerian",Font.BOLD,25));
	    	main_lab.setBorder(softborder);
	    	holder.add(main_lab);
	    	holder.add(Box.createVerticalStrut(30));
	    	String flame_desc="<html>The Ace of Flames is a Destructive High Power Ace:<br>"
	    			+ "Representative of destruction by flames and heat, the Ace of flames destroys<br>all "
	    			+ "cells within it's hit radius(all surrounding cells).<br>"
	    			+ "Activation of an Ace of Flames occurs when a nearby cell explodes due to<br>"
	    			+ "a chain reaction and hits it.<br>"
	    			+ "TYPE: DESTRUCTIVE ACE<br>"
	    			+ "ALLEGIANCE: NEUTRAL<br>"
	    			+ "ACTIVATION: NEARBY CELL EXPLOSION<br>"
	    			+ "TARGETS: ALL CELLS";
	    	JLabel flame=new JLabel(flame_desc);
	    	flame1=flame;
	    	flame.setBorder(softborder);
	    	holder.add(flame);
	    	holder.add(Box.createVerticalStrut(20));
	    	JLabel number=new JLabel("<html>Enter Desired number of Aces of Flames<br>to put"
	    			+ "into the board");
	    	this.number2=number;
	    	number.setBorder(compnd);
	    	number.setForeground(Color.blue.brighter());
	    	number.setFont(new Font("algerian",Font.BOLD,20));
	    	holder.add(number);
	    	flame.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
	    	flame.setForeground(Color.CYAN);
	    	Box jftf=Box.createHorizontalBox();
	        JRadioButton[] re=this.getNumberPanel(AceType.FLAME_ACE);
	        for(JRadioButton jb:re)
	        {
	        	jftf.add(jb);
	        	jftf.add(Box.createHorizontalStrut(10));
	        }
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	fire.add(holder);
	    	main_lab.setAlignmentX(CENTER_ALIGNMENT);
	    	flame.setAlignmentX(CENTER_ALIGNMENT);
	    	number.setAlignmentX(CENTER_ALIGNMENT);
	    	jftf.setAlignmentX(CENTER_ALIGNMENT);
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	fire.add(holder);
	    	return fire;	
	    }
	    protected JPanel createRagePanel()
	    {
	    	JPanel rage=new AceCardPicturePanel();
	    	Box holder=Box.createVerticalBox();
	    	JLabel main_lab=new JLabel("THE ACE OF RAGE");
	    	main_lab3=main_lab;
	    	main_lab.setForeground(Color.WHITE);
	    	main_lab.setFont(new Font("algerian",Font.BOLD,25));
	    	main_lab.setBorder(softborder);
	    	holder.add(main_lab);
	    	holder.add(Box.createVerticalStrut(30));
	    	String rage_desc="<html>The Ace of Rage is a Constructuve Medium Power Ace:<br>"
	    			+ "Representative of increase in speed and Strength<br> due to rage, the Ace of rage continues a chain<br>reaction by adding +3 elements"
	    			+ "to all cells within it's hit radius<br>(all surrounding cells)."
	    			+ "Activation of an Ace of Rage occurs when a<br> nearby cell explodes due to"
	    			+ " a chain reaction and hits it.<br>"
	    			+ "TYPE: CONSTRUCTIVE ACE<br>"
	    			+ "ALLEGIANCE: PRO-ACTIVATOR<br>"
	    			+ "ACTIVATION: NEARBY CELL EXPLOSION<br>"
	    			+ "TARGETS: ALL CELLS";
	    	JLabel wrath=new JLabel(rage_desc);
	    	wrath1=wrath;
	    	wrath.setBorder(softborder);
	    	holder.add(wrath);
	    	holder.add(Box.createVerticalStrut(20));
	    	JLabel number=new JLabel("<html>Enter Desired number of Aces of Rage<br>to put"
	    			+ "into the board");
	    	this.number3=number;
	    	number.setBorder(compnd);
	    	number.setForeground(Color.WHITE);
	    	number.setFont(new Font("algerian",Font.BOLD,20));
	    	holder.add(number);
	    	wrath.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
	    	wrath.setForeground(Color.WHITE);
	    	Box jftf=Box.createHorizontalBox();
	        JRadioButton[] re=this.getNumberPanel(AceType.RAGE_ACE);
	        for(JRadioButton jb:re)
	        {
	        	jftf.add(jb);
	        	jftf.add(Box.createHorizontalStrut(10));
	        }
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	rage.add(holder);
	    	main_lab.setAlignmentX(CENTER_ALIGNMENT);
	    	wrath.setAlignmentX(CENTER_ALIGNMENT);
	    	number.setAlignmentX(CENTER_ALIGNMENT);
	    	jftf.setAlignmentX(CENTER_ALIGNMENT);
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	rage.add(holder);
	    	return rage;
	    
	    }
	    protected JPanel createFrostPanel()
	    {   
	    	JPanel ice=new AceCardPicturePanel();
	    	Box holder=Box.createVerticalBox();
	    	JLabel main_lab=new JLabel("THE ACE OF FROST");
	    	main_lab1=main_lab;
	    	main_lab.setForeground(Color.black);
	    	main_lab.setFont(new Font("algerian",Font.BOLD,25));
	    	main_lab.setBorder(softborder);
	    	holder.add(main_lab);
	    	holder.add(Box.createVerticalStrut(30));
	    	String frost_desc="<html>The Ace of Frost is a Destructive Medium Power Ace:<br>"
	    			+ "Representative of destruction by Ice and Frost, the Ace of Frost destroys<br>any critical mass "
	    			+ "cell within it's hit radius(all surrounding cells).<br>"
	    			+ "Activation of an Ace of Frost occurs when a nearby cell explodes due to<br>"
	    			+ "a chain reaction and hits it.<br>"
	    			+ "TYPE: DESTRUCTIVE ACE<br>"
	    			+ "ALLEGIANCE: NEUTRAL<br>"
	    			+ "ACTIVATION: NEARBY CELL EXPLOSION<br>"
	    			+ "TARGETS: CRITICAL MASS CELLS";
	    	JLabel frost=new JLabel(frost_desc);
	    	frost1=frost;
	    	frost.setBorder(softborder);
	    	holder.add(frost);
	    	holder.add(Box.createVerticalStrut(20));
	    	JLabel number=new JLabel("<html>Enter Desired number of Aces of Frost<br>to put"
	    			+ "into the board");
	    	this.number1=number;
	    	number.setBorder(compnd);
	    	number.setForeground(Color.blue.brighter());
	    	number.setFont(new Font("algerian",Font.BOLD,20));
	    	holder.add(number);
	    	frost.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
	    	frost.setForeground(Color.BLACK);
	        Box jftf=Box.createHorizontalBox();
	        JRadioButton[] re=this.getNumberPanel(AceType.FROST_ACE);
	        for(JRadioButton jb:re)
	        {
	        	jftf.add(jb);
	        	jftf.add(Box.createHorizontalStrut(10));
	        }
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	ice.add(holder);
	    	main_lab.setAlignmentX(CENTER_ALIGNMENT);
	    	frost.setAlignmentX(CENTER_ALIGNMENT);
	    	number.setAlignmentX(CENTER_ALIGNMENT);
	    	jftf.setAlignmentX(CENTER_ALIGNMENT);
	    	return ice;
	    }
	    protected JPanel createBetrayalPanel()
	    {
	    	JPanel betrayal=new AceCardPicturePanel();
	    	Box holder=Box.createVerticalBox();
	    	JLabel main_lab=new JLabel("THE ACE OF BETRAYAL");
	    	main_lab4=main_lab;
	    	main_lab.setForeground(Color.WHITE);
	    	main_lab.setFont(new Font("algerian",Font.BOLD,25));
	    	main_lab.setBorder(softborder);
	    	holder.add(main_lab);
	    	holder.add(Box.createVerticalStrut(30));
	    	String b_desc="<html>The Ace of Betrayal is a Constructive High Power Ace:<br>"
	    			+ "Representative of loss and grief by betrayal, the Ace of Betrayal acts<br>"
	    			+ " as a critical mass cell, but on exploding continues the reaction<br> in an opponents color.<br> "
	    			+ "The color so chosen is constant, i.e. if the player's number is 1<br>"
	    			+ "then player 2's color will be chosen to continue the reaction<br>"
	    			+ "Activation of an Ace of Betrayal occurs when a nearby cell explodes due to<br>"
	    			+ "a chain reaction and hits it.<br>"
	    			+ "TYPE: CONSTRUCTIVE ACE<br>"
	    			+ "ALLEGIANCE: ANTI-ACTIVATOR<br>"
	    			+ "ACTIVATION: NEARBY CELL EXPLOSION<br>"
	    			+ "TARGETS: ALL CELLS";
	    	JLabel bb=new JLabel(b_desc);
	    	betray1=bb;
	    	bb.setBorder(softborder);
	    	holder.add(bb);
	    	holder.add(Box.createVerticalStrut(20));
	    	JLabel number=new JLabel("<html>Enter Desired number of Aces of Betrayal<br>to put"
	    			+ "into the board");
	    	this.number4=number;
	    	number.setBorder(compnd);
	    	number.setForeground(Color.WHITE);
	    	number.setFont(new Font("algerian",Font.BOLD,20));
	    	holder.add(number);
	    	bb.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
	    	bb.setForeground(Color.WHITE);
	    	Box jftf=Box.createHorizontalBox();
	        JRadioButton[] re=this.getNumberPanel(AceType.BETRAYAL_ACE);
	        for(JRadioButton jb:re)
	        {
	        	jftf.add(jb);
	        	jftf.add(Box.createHorizontalStrut(10));
	        }
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	betrayal.add(holder);
	    	main_lab.setAlignmentX(CENTER_ALIGNMENT);
	    	bb.setAlignmentX(CENTER_ALIGNMENT);
	    	number.setAlignmentX(CENTER_ALIGNMENT);
	    	jftf.setAlignmentX(CENTER_ALIGNMENT);
	    	holder.add(Box.createVerticalStrut(20));
	    	holder.add(jftf);
	    	betrayal.add(holder);
	    	return betrayal;
	    }
	    public void readyIcons()
	    {
	    	frost1.setIcon(AceOfTheGrid.cropImage(frost1,new ImageIcon(AceOfTheGrid.frost_icon)));
	    	frost1.setHorizontalTextPosition(JLabel.CENTER);
	    	main_lab1.setIcon(AceOfTheGrid.cropImage(main_lab1,new ImageIcon(AceOfTheGrid.ice_icon)));
	    	main_lab1.setHorizontalTextPosition(JLabel.CENTER);
	        number1.setIcon(AceOfTheGrid.cropImage(number1,new ImageIcon(AceOfTheGrid.frost_icon)));
	        number1.setHorizontalTextPosition(JLabel.CENTER);
	        flame1.setIcon(AceOfTheGrid.cropImage(flame1,new ImageIcon(AceOfTheGrid.fire_icon)));
	        flame1.setHorizontalTextPosition(JLabel.CENTER);
	        main_lab2.setIcon(AceOfTheGrid.cropImage(main_lab2,new ImageIcon(AceOfTheGrid.fire_icon1)));
	        main_lab2.setHorizontalTextPosition(JLabel.CENTER);
	        number2.setIcon(AceOfTheGrid.cropImage(number2,new ImageIcon(AceOfTheGrid.fire_icon)));
	        number2.setHorizontalTextPosition(JLabel.CENTER);  
	        wrath1.setIcon(AceOfTheGrid.cropImage(wrath1,new ImageIcon(AceOfTheGrid.rage_icon)));
	        wrath1.setHorizontalTextPosition(JLabel.CENTER);
	        main_lab3.setIcon(AceOfTheGrid.cropImage(main_lab3,new ImageIcon(AceOfTheGrid.rage_icon)));
	        main_lab3.setHorizontalTextPosition(JLabel.CENTER);
	        number3.setIcon(AceOfTheGrid.cropImage(number3,new ImageIcon(AceOfTheGrid.rage_icon)));
	        number3.setHorizontalTextPosition(JLabel.CENTER);
	        betray1.setIcon(AceOfTheGrid.cropImage(betray1,new ImageIcon(AceOfTheGrid.betray_icon)));
	        betray1.setHorizontalTextPosition(JLabel.CENTER);
	        main_lab4.setIcon(AceOfTheGrid.cropImage(main_lab4,new ImageIcon(AceOfTheGrid.bet2_icon)));
	        main_lab4.setHorizontalTextPosition(JLabel.CENTER);
	        number4.setIcon(AceOfTheGrid.cropImage(number4,new ImageIcon(AceOfTheGrid.bet2_icon)));
	        number4.setHorizontalTextPosition(JLabel.CENTER);
	    }
	    protected JRadioButton[] getNumberPanel(AceType at)
	    {   
	    	ItemListener il=(ie)->{
	    		if(ie.getStateChange()==ItemEvent.SELECTED)
	    		{
	    			int x=Integer.parseInt(((JRadioButton)(ie.getSource())).getText());
	    		    switch(at)
	    		    {
	    		    case FROST_ACE:
	    		    	f_no=x;
	    		    	break;
					case BETRAYAL_ACE:
						b_no=x;
						break;
					case FLAME_ACE:
						fl_no=x;
						break;
					case RAGE_ACE:
						r_no=x;
						break;
					default:
						break;
	    		    }
	    		}
	    	};
	    	ButtonGroup bg=new ButtonGroup();
	    	JRadioButton[] btt=new JRadioButton[6];
	    	for(int k=0;k<6;++k)
	    	{
	    		btt[k]=new JRadioButton(""+k);
	    		btt[k].setForeground(Color.WHITE);
	    		btt[k].setBackground(Color.BLACK);
	    		if(k==3)
	    		{btt[k].setSelected(true);}
	    		btt[k].addItemListener(il);
	    		bg.add(btt[k]);
	    	}
	    	return btt;
	    }
	    public JTabbedPane createNumberOppiPane()
	    {
	    	this.aiopps=new JTabbedPane(JTabbedPane.BOTTOM,JTabbedPane.SCROLL_TAB_LAYOUT);
	        this.aiopps.add("Player 1",this.getAiOppsNameBox(0));
	        this.aiopps.add("Player 2",this.getAiOppsNameBox(1));
	        return this.aiopps;
	    }
	    protected Box getAiOppsNameBox(int x)
	    {
	    	Box cb=Box.createVerticalBox();
	    	JCheckBox jcb=new JCheckBox("Computer-Controlled",false);
	    	this.jcbb.add(jcb);
	    	JTextField jtf=new JTextField("Enter Player name here");
	    	CaretListener al=(ev)->{
	    		JTextField src=(JTextField)(ev.getSource());
	    		String s=src.getText();
	    		pcs.getTabbedPane().setTitleAt(x,s);
	             };
	             jtf.addCaretListener(al);
	             cb.add(jtf);
	             cb.add(jcb);
	           	 return cb;
	    }
	    protected void addPlayerTab()
	    {   
	    	int x=aiopps.getTabCount();
	    	aiopps.add("Player "+(x+1),getAiOppsNameBox(x));
	    }
	    protected void removePlayerTabTill(int n)
	    {
	    	int x=aiopps.getTabCount();
	    	while(x>n)
	    	{
	    		aiopps.remove(aiopps.getTabCount()-1);
	    		--x;
	    	}
	    }
	    protected void addPlayerTabTill(int x)
	    {   
	    	++x;
	    	int n=this.aiopps.getTabCount();
	    	++n;
	    	while(n<x)
	    	{   
	    		aiopps.add("Player "+n,(this.getAiOppsNameBox(n-1)));
	    		++n;
	    	}
	    }
	    protected void removePlayerTab()
	    {   
	    	aiopps.remove(aiopps.getTabCount()-1);
	    	this.jcbb.remove(aiopps.getTabCount()-1);
	    }
	}
 public static ImageIcon cropImage(JComponent comp,ImageIcon icon)
  {
	 Image s=icon.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(),Image.SCALE_SMOOTH);
     return new ImageIcon(s);
  }
 
}
