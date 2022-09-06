/**

* @title: P1.java
* @author: xiongying

* @date: 2021-08-26 
* @Version: 1.0 

* @Description: Print a String based on the character that user input

*/

import java.util.Scanner;

public class P1 {
	
	//main function
	public static void main(String[] args) {
		
		// Variables
		String n;
		Scanner in = new Scanner(System.in);
		n = in.next();
		
		// Switch case based on user input
		switch (n) {
			
			// 1) Input is "a" or "A", print "Action movie fan"
			case "a":
			case "A":
				System.out.println("Action movie fan\n");
				break;

			// 2) Input is "c" or "C", print "Comedy movie fan"
			case "c":
			case "C":
				System.out.println("Comedy movie fan\n");
				break;
			
			// 3) Input is "d" or "D", print "Drama movie fan"
			case "d":
			case "D":
				System.out.println("Drama movie fan\n");
				break;
			
			// 4) Any other input, print error message
			default:
				System.out.println("Invalid Choice\n");
				
			
		}
		
		// Close the Scanner
		in.close();
		

	}

}
