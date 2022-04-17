package util;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.JComponent;
public class HoverResponse extends MouseAdapter implements FocusListener
{
private Color r;
private Color pre;
public HoverResponse(Color og,Color pref)
{
	r=og;
    pre=pref;
}
@Override
public void mouseEntered(MouseEvent me)
{
JComponent src=(JComponent)(me.getSource());
src.setForeground(pre);
}
public void mouseExited(MouseEvent me)
{
	JComponent src=(JComponent)(me.getSource());
	src.setForeground(r);
}
@Override
public void focusGained(FocusEvent gain)
{
	JComponent src=(JComponent)(gain.getSource());
	src.setBackground(pre);
}
@Override
public void focusLost(FocusEvent lost)
{
	JComponent src=(JComponent)(lost.getSource());
	src.setBackground(r);
}
}
