package main;
import java.awt.*;
public final class Cell 
{
	private String type="";//type of Ace
	private int num;//Currently filled cells
	private int crit_mass;//Maximum mass of a cell
	private Point point;//Point at which a cell exists
	private CellGrid parent;//Parent cells.
	public Cell(int init,Point position,CellGrid parent)
	{
		this.num=init;
		this.point=position;
		this.parent=parent;
	}
	public int getCount()
	{
		return this.num;
	}
	public int getCritMass()
	{
		return this.crit_mass;
	}
	
	

}
