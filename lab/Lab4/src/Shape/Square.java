package Shape;

import java.util.Scanner;

public class Square implements Shape{
	
	// variable
	Scanner scan = new Scanner(System.in);
	private double length;
	
	
	// constructor
	public Square() {
		System.out.print ("\nSquare - Enter the length: ");
		length = scan.nextDouble();
	}
	
	// method
	public double calArea() {
		double area = length * length;
		return area;
	}
	
	public double calSurface() {
		System.out.println("Cannot calculate surface for 2D object.");
		return 0;
	}
	
}
