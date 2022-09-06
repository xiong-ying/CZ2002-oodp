package Shape;

import java.util.Scanner;

public class Cone implements Shape{

	// variable
	Scanner scan = new Scanner(System.in);
	private double radius, height;
	
	
	// constructor
	public Cone() {
		System.out.print ("\nCone - Enter the radius: ");
		radius = scan.nextDouble();
		
		System.out.print ("\nCone - Enter the height: ");
		height = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		System.out.println("Cannot calculate area for 3D object.");
		return 0;
	}
	
	public double calSurface() {
		double surface = Math.PI * Math.pow(radius, 2) + Math.PI * radius * Math.sqrt(Math.pow(height, 2) + Math.pow(radius,2));
		return surface;
	}


}
