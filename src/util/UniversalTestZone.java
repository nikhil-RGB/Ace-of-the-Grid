package util;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import main.AceOfTheGrid;
@SuppressWarnings("unused")
public final class UniversalTestZone 
{

	public static void main(String[] args)
	{
	PlayerColorSelector pc=new PlayerColorSelector(4,"Choose Colors", null, true);
	pc.setIconImage(new ImageIcon(AceOfTheGrid.main_icon_dep).getImage());
	pc.setSize(600, 600);
	pc.setVisible(true);
	}

}
