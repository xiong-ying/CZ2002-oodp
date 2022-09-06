/**

* @title: P4.java
* @author: xiongying

* @date: 2021-08-27 
* @Version: 1.0 

* @Description: Display a pyramid of "AA" & "BB" based on user input number

*/

import java.util.Scanner;


public class P4 {

	public static void main(String[] args) {
		
		//  Variables
		String str = new String();
		
		Scanner Sc = new Scanner(System.in);
		
		
		// User input
		System.out.print("height = ");
		int h = Sc.nextInt();
		
		
		// Invalid input handling
		if ( h <= 0) {
			System.out.println("Error input!!");
			System.exit(0);
		}
		
		
		// Print the pyramid
		for ( int i = 1; i <= h; i++) {
			
			// If odd number, add "AA" in front
			if ( i%2 == 1 ) {
				str = "AA" + str;
			}
			
			// If even number, add "BB" in front
			else {
				str = "BB" + str;
			}
			
			System.out.println(str);
		}
		
		
		// Close the Scanner
		Sc.close();

	}

}
