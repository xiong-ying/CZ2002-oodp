package Shape;

import java.util.Scanner;

public class Triangle implements Shape{

	// variable
	Scanner scan = new Scanner(System.in);
	private double height, base;
	
	
	// constructor
	public Triangle() {
		System.out.print ("\nTriangle - Enter the height: ");
		height = scan.nextDouble();
		System.out.print ("\nTriangle - Enter the base: ");
		base = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		double area = height * base * 0.5;
		return area;
	}
	
	public double calSurface() {
		System.out.println("Cannot calculate surface for 2D object.");
		return 0;
	}

}
