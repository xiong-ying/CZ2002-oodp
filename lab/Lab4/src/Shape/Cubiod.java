package Shape;

import java.util.Scanner;

public class Cubiod implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double length, width, height;
	
	
	// constructor
	public Cubiod() {
		System.out.print ("\nCubiod - Enter the length: ");
		length = scan.nextDouble();
		System.out.print ("\nCubiod - Enter the width: ");
		width = scan.nextDouble();
		System.out.print ("\nCubiod - Enter the height: ");
		height = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		System.out.println("Cannot calculate area for 3D object.");
		return 0;
	}
	
	public double calSurface() {
		double surface = 2 * ( length * width + width * height + height * length);
		return surface;
	}


}
