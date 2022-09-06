package Shape;

import java.util.Scanner;

public class Sphere implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double radius;
	
	
	// constructor
	public Sphere() {
		System.out.print ("\nSphere - Enter the radius: ");
		radius = scan.nextDouble();

	}
	
	// method
	public double calArea() {
		System.out.println("Cannot calculate area for 3D object.");
		return 0;
		
	}
	
	public double calSurface() {
		double surface = radius * radius * Math.PI * 4;
		return surface;
	}

}
