/**

* @title: P2.java
* @author: xiongying

* @date: 2021-08-26 
* @Version: 1.0 

* @Description: Calculate grades Based on Salary and Merit

*/

import java.util.Scanner;

public class P2 {

	public static void main(String[] args) {

		// Variables
		int sal, merit;
		Scanner Sc = new Scanner(System.in);
		
		// User Input
		System.out.print("salary: $");
		sal = Sc.nextInt();
		
		System.out.print("Merit: ");
		merit = Sc.nextInt();
		
		
		// Salary Grading Condition
		
		if ( sal >= 500 && sal < 600 ) { 
			// 1) 500 <= salary < 600, Grade C
			System.out.println("Grade C");
		}
		
		else if ( sal >= 600 && sal < 649 ) { 
			// 2) 600 <= salary <= 649, check merit
			
			if ( merit < 10) { 
				// 2-1) merit < 10, Grade C
				System.out.println("Grade C");
			}
			
			else { 
				// 2-2) merit >= 10, Grade B
				System.out.println("Grade B");
			}
		}
		
		else if ( sal>649 && sal<700 ) {
			// 3) 649 < salary < 700, Grade B
			System.out.println("Grade B");
		}
		
		else if ( sal>=700 && sal<=799 ) {
			// 4) 700 <= salary <= 799, check merit
			
			if ( merit<20) {
				// 4-1) merit < 20, Grade B
				System.out.println("Grade B");
			}
			else {
				// 4-2) merit >= 20, Grade A
				System.out.println("Grade A");
			}
		}
		
		else if ( sal>799 && sal<=899) {
			// 5) 799 > salary <= 899, Grade A
			System.out.println("Grade A");
		}
		
		else {
			// 6) If other input, display error message
			System.out.println("Invalid Input");
		}
		
		// Close the Scanner
		Sc.close();

	}

}
