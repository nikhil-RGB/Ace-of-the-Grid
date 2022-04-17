package util;
import java.awt.Point;
import java.util.*;
public final class TestingSpace {
	public static void main(String[] args)
	{

	testArrayListPoint();	
	}
	public static void testArrayListPoint()
	{
		ArrayList<Point> ob=new ArrayList<>(0);
		ob.add(new Point(2,3));
		ob.add(new Point(4,5));
		System.out.println("Test for Point arraylist\n");
		System.out.println("should be true: "+ob.contains(new Point(2,3)));
		System.out.println("should be true: "+ob.contains(new Point(4,5)));
		System.out.println("should be false: "+ob.contains(new Point(2,0)));
	}
    public static void testArrayListInt()
    {
    	ArrayList<int[]> arr=new ArrayList<>(0);
    	arr.add(new int[] {2,3});
    	arr.add(new int[] {2,3});
    	arr.add(new int[] {4,5});
    	System.out.println("Test for int[] with contains method\n");
    	System.out.println("Should give true: "+arr.contains(new int[] {2,3}));
    	System.out.println("Should give false: "+arr.contains(new int[] {5,4}));
    }
}
