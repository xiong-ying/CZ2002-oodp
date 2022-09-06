package Shape;

import java.util.Scanner;

public class Shape2DApp {

	// 2D shape application class
	public static void main(String[] args) {
		
		// variable
		Scanner scan = new Scanner(System.in);
		int number, shapeSelect, calSelect;
		double totalArea = 0;
		
		// 1. Ask for total number of shapes
		System.out.println("Enter the total number of shapes: ");
		number = scan.nextInt();
		Shape shape[] = new Shape[number];
		
		
		// 2. Enter dimension for all shapes
		for (int i = 0; i < number; i++) {
			
			System.out.println("\nShape " + (i+1) + " - > ");
			System.out.println("1.Square; 2.Rectangle; 3.Circle; 4.Triangle");
			System.out.println("Select the shape:");
			shapeSelect = scan.nextInt();
			
			switch (shapeSelect){
				case 1: // Square
					Square s = new Square();
					shape[i] = s;

					break;
				case 2: // Rectangle
					Rectangle r = new Rectangle();
					shape[i] = r;
					break;
					
				case 3: // Circle
					Circle c = new Circle();
					shape[i] = c;
					break;
					
				case 4: // Triangle
					Triangle t = new Triangle();
					shape[i] = t;

			} // end switch
		} // end for
		
		// 3. Calculation
		System.out.println("\nCalculation  - > ");
		System.out.println("1.Calculate area \n...");
		System.out.println("\nChoose type of Calculation:");
		calSelect = scan.nextInt();
		
		switch (calSelect) {
			case 1: 
				for (int i = 0; i < number; i++ ) {
					totalArea = totalArea + shape[i].calArea();
				}
				System.out.println("\nTotal area of all shapes are " + totalArea + ".");
				break;
			default:
				System.out.println("\nNo other selection.");
		}
		
		scan.close();

	}

}
