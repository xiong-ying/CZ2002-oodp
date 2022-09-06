package Shape;

import java.util.Scanner;

public class Shape3DApp {

	// 3D shape application class
	public static void main(String[] args) {
		
		// variable
		Scanner scan = new Scanner(System.in);
		int number, shapeSelect, calSelect;
		double totalSurface = 0;
		
		// 1. Ask for total number of shapes
		System.out.println("Enter the total number of shapes: ");
		number = scan.nextInt();
		Shape shape[] = new Shape[number];
		
		
		// 2. Enter dimension for all shapes
		for (int i = 0; i < number; i++) {
			
			System.out.println("\nShape " + (i+1) + " - > ");
			System.out.println("1.Sphere; 2.Square-based Pyramid; 3.Cubiod;");
			System.out.println("4.Cone; 5.Cylinder");
			System.out.println("Select the shape:");
			shapeSelect = scan.nextInt();
			
			switch (shapeSelect){
				case 1: // Sphere
					Sphere sphere = new Sphere();
					shape[i] = sphere;

					break;
				case 2: // Square-based Pyramid
					Pyramid pyramid = new Pyramid();
					shape[i] = pyramid;
					break;
					
				case 3: // Cube
					Cubiod cube = new Cubiod();
					shape[i] = cube;
					break;
					
				case 4: // Cone
					Cone cone = new Cone();
					shape[i] = cone;
					break;
					
				case 5: // Cylinder
					Cylinder cylinder = new Cylinder();
					shape[i] = cylinder;

			} // end switch
		} // end for
		
		// 3. Calculation
		System.out.println("\nCalculation  - > ");
		System.out.println("1.Calculate surface \n...");
		System.out.println("\nChoose type of Calculation:");
		calSelect = scan.nextInt();
		
		switch (calSelect) {
			case 1: 
				for (int i = 0; i < number; i++ ) {
					totalSurface = totalSurface + shape[i].calSurface();
				}
				System.out.println("\nTotal surface of all shapes are " + totalSurface + ".");
				break;
			default:
				System.out.println("\nNo other selection.");
		}
		
		scan.close();

	}
}
