package Shape;

import java.util.Scanner;

public class Pyramid implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double length, height;
	
	
	// constructor
	public Pyramid() {
		System.out.print ("\nPyramid - Enter the length: ");
		length = scan.nextDouble();
		System.out.print ("\nPyramid - Enter the height: ");
		height = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		System.out.println("Cannot calculate area for 3D object.");
		return 0;
	}
	
	public double calSurface() {
		double surface = Math.pow(length, 2) + 4 * 0.5 * length * Math.sqrt(Math.pow(height, 2) + Math.pow((length/2),2));
		return surface;
	}

}
