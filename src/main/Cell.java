//This code is part of the development branch.
//This 
package main;
import java.awt.*;
import javax.swing.*;
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
	public int calculateCritMass()
	{
		return 0;
	}
	public Point determinePosition()
	{
	int x=this.point.x;
	int y=this.point.y;
	int xmax=this.parent.grid.length;
	int ymax=this.parent.grid[0].length;
	if(x==this.parent.grid.length)
	{}
	return null;
	}
	

}
