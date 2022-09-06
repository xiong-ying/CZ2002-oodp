package Shape;

import java.util.Scanner;

public class Rectangle implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double length, breadth;
	
	
	// constructor
	public Rectangle() {
		System.out.print ("\nRectangle - Enter the length: ");
		length = scan.nextDouble();
		System.out.print ("\nRectangle - Enter the breadth: ");
		breadth = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		double area = length * breadth;
		return area;
	}
	
	public double calSurface() {
		System.out.println("Cannot calculate surface for 2D object.");
		return 0;
	}

}
