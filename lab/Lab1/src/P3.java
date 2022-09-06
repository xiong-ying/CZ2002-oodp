/**

* @title: P3.java
* @author: xiongying

* @date: 2021-08-26 
* @Version: 1.0 

* @Description: Currency Conversion between US$ and S$

*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class P3 {

	public static void main(String[] args) {
		
		// Variables
		double rate = 1.82;
		double SGD;
		int start, end, incre;

		Scanner Sc = new Scanner(System.in);
		
		DecimalFormat df = new DecimalFormat("######0.00");
		
				
		// User input
		System.out.print("staring: ");
		start = Sc.nextInt();
		
		System.out.print("ending: ");
		end = Sc.nextInt();
		
		System.out.print("increment: ");
		incre = Sc.nextInt();
		
		
		// Invalid Input Handling
		if (start > end) {
			System.out.println("Error input!");
			System.exit(0);
		}
		
		
		/* 1) First Table - For Loop */
		
		// Title
		System.out.println("US$         S$");
		System.out.println("------------");
		
		// For Loop
		for (int i=start; i <= end; i += incre) {
			SGD = i * rate;
			df.format(SGD);
			
			System.out.format("%-12d" + "%s\n", i, SGD);
		}
		
		// Empty line to separate the tables
		System.out.println(" ");

		
		/* 2) Second Table - While Loop */
		
		// Title
		System.out.println("US$         S$");
		System.out.println("------------");
		
		// While Loop
		int j=start; 
		while (j <= end) {
			SGD = j * rate;
			df.format(SGD);
			
			System.out.format("%-12d" + "%s\n", j, SGD);
			j += incre;
		}
		
		// Empty line to separate the tables
		System.out.println(" ");
		
		
		/* 3) Third Table - Do while Loop */
		
		// Title
		System.out.println("US$         S$");
		System.out.println("------------");
		
		// While Loop
		int k=start; 
		do {
			SGD = k * rate;
			df.format(SGD);
			
			System.out.format("%-12d" + "%s\n", k, SGD);
			k += incre;
		} while (k <= end);
		
		
		// Close the Scanner
		Sc.close();

	}

}
