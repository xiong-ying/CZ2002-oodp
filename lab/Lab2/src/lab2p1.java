import java.util.Scanner;
import java.lang.Math;

public class lab2p1 {
	public static void main(String[] args)
	{
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Perform the following methods:");
			System.out.println("1: miltiplication test");
			System.out.println("2: quotient using division by subtraction");
			System.out.println("3: remainder using division by subtraction");
			System.out.println("4: count the number of digits");
			System.out.println("5: position of a digit");
			System.out.println("6: extract all odd digits");
			System.out.println("7: quit");
			
			choice = sc.nextInt();
			
			switch (choice) {
			
				case 1: /* add mulTest() call */
					
					mulTest();
					
					break;
					
				case 2: /* add divide() call */
					
					int quotient;
					
					System.out.println("m = ");
					int m2 = sc.nextInt();
					System.out.println("n = ");
					int n2 = sc.nextInt();
					
					quotient = divide(m2,n2);
					
					System.out.println(m2+"/"+n2+" = "+quotient);
					
					break;
					
				case 3: /* add modulus() call */
					
					int remainder;
					
					System.out.println("m = ");
					int m3 = sc.nextInt();
					System.out.println("n = ");
					int n3 = sc.nextInt();
					
					remainder = modulus(m3,n3);
					
					System.out.println(m3+" % "+n3+" = "+remainder);
					
					break;
				case 4: /* add countDigits() call */
					
					int count;
					
					System.out.println("n : ");
					int n4 = sc.nextInt();
					
					count = countDigits(n4);
					
					if (count<0) {
						System.out.println("Error input!!");
					}
					else {
						System.out.println("count = "+count);
					}
					
					break;
				case 5: /* add position() call */

					int pos;
					
					System.out.println("n : ");
					int n5 = sc.nextInt();
					System.out.println("digit : ");
					int d = sc.nextInt();
					
					pos = position(n5, d);
					
					System.out.println("position = "+pos);
					
					break;
				case 6: /* add extractOddDigits() call */
					
					long oddDigit;
					
					System.out.println("n : ");
					int n6 = sc.nextInt();
					
					oddDigit = extractOddDigits(n6);
					
					if (oddDigit == 0) {
						System.out.println("Error input!!");
					}
					else {
						System.out.println("oddDigits = "+oddDigit);
					}
					
					
					
					break;
				case 7: System.out.println("Program terminating ….");
			}
		} while (choice < 7);

	}
	
	
	
	/* add method code here */
	
	// 3.2 Multiplication
	
	public static void mulTest() {
		int a, b, correctAns, userAns;
		int score = 0;
		Scanner sc1 = new Scanner(System.in);
		
		for (int i=0; i<5; i++) {
			a = (int)(Math.random() * 9) + 1;
			b = (int)(Math.random() * 9) + 1;
			correctAns = a*b;

			System.out.println("How much is "+a+" times "+b+"?");
			userAns = sc1.nextInt();
			
			if(userAns == correctAns) score++;

		}
		
		System.out.println(score+" answers out of 5 are correct.");
	}
	
	
	// 3.3 divide
	
	public static int divide(int m, int n) {
		
		int q = 0;
		
		do {
			m = m - n;
			if (m>=0) q++;
			
		} while(m >= n);
		
		return q;
		
	}
	
	
	// 3.4 modulus
	
	public static int modulus(int m, int n) {
		
		while(m >= n) {
			m = m - n;
		}
		return m;
		
	}
	
	// 3.5 count Digit
	
	public static int countDigits(int n) {

		int counter = 0;
		double n1 = (double)n;
		
		if (n1 < 0) {
			return -1;
		}
		
		else if (n1 == 0) {
			return 1;
		}
		
		else {
			while (n1 >= 1) {
				n1 = n1/10;
				counter ++;
			}
		}
		
		return counter;
		
	}
	
	// 3.6 position
	
	public static int position(int n, int digit) {

		int pos = 0;
		int lastDigit;

		while (n>0) {
			lastDigit = n%10;
			n = n/10;
			pos ++;
			
			if(lastDigit == digit) return pos;
		}

		return -1;
	}
	
	// 3.7 extract Odd Digits

	public static long extractOddDigits(long n) {

		long result = 0;
		int lastDigit;
		int k=1;
		
		if(n<0) {
			return result;
		}

		while (n>=1) {
			lastDigit = (int) (n%10);
			n = n/10;
			//System.out.println("last digit is "+lastDigit);
			
			if (lastDigit%2 == 1) {
				result = result + lastDigit*k;
				k=k*10;
			}
		}
		
		if (result == 0) result = -1;
		
		return result;
		
	}
	
	
	
	
	
}