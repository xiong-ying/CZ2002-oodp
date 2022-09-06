package Shape;

import java.util.Scanner;

public class Cylinder implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double radius, height;
	
	
	// constructor
	public Cylinder() {
		System.out.print ("\nCylinder - Enter the radius: ");
		radius = scan.nextDouble();
		
		System.out.print ("\nCylinder - Enter the height: ");
		height = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		System.out.println("Cannot calculate area for 3D object.");
		return 0;
	}
	
	public double calSurface() {
		double surface = 2 * Math.PI * Math.pow(radius, 2) + 2 * Math.PI * radius * height ;
		return surface;
	}



}
