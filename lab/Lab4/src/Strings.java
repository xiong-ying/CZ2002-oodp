import java.util.Scanner;
public class Strings
{
// --------------------------------------------
// Reads in an array of integers, sorts them,
// then prints them in sorted order.
// --------------------------------------------
public static void main (String[] args)
{
	Comparable[] strList;
	int size;
	Scanner scan = new Scanner(System.in);
	System.out.print ("\nHow many strings do you want to sort? ");
	size = scan.nextInt()+1;
	strList = new Comparable[size];
	System.out.println ("\nEnter the strings...");
	for (int i = 0; i < size; i++)
		strList[i] = scan.nextLine();
		// change the sorting method here
		Sorting.insertionSort(strList);
		System.out.println ("\nYour strings in descending sorted order...");
	for (int i = 0; i < size; i++)
		System.out.print(strList[i] + " \n");
	System.out.println ();
	
	scan.close();
	}
}