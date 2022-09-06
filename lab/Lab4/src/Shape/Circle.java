package Shape;

import java.util.Scanner;

public class Circle implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double radius;
	
	
	// constructor
	public Circle() {
		System.out.print ("\nCircle - Enter the radius: ");
		radius = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		double area = radius * Math.PI * 2;
		return area;
	}
	
	public double calSurface() {
		System.out.println("Cannot calculate surface for 2D object.");
		return 0;
	}


}
