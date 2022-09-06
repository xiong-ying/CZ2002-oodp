public class Sorting
{
	//-----------------------------------------------------------------
	// Sorts the specified array of objects using the selection
	// sort algorithm.
	//-----------------------------------------------------------------
	public static void selectionSort (Comparable<Object>[] list)
	{//ascending
		int min;
		Comparable<Object> temp;
		for (int index = 0; index < list.length-1; index++)
		{
			min = index;
			for (int scan = index+1; scan < list.length; scan++)
				if (list[scan].compareTo(list[min]) < 0)
					min = scan;
			// Swap the values
			temp = list[min];
			list[min] = list[index];
			list[index] = temp;
		}
	}
	//-----------------------------------------------------------------
	// Sorts the specified array of objects using the insertion
	// sort algorithm.
	//-----------------------------------------------------------------
	public static void insertionSort (Comparable<Object>[] list)
	{//descending
		for (int index = 1; index < list.length; index++)
		{
			Comparable<Object> key = list[index];
			int position = index;
			// < is Shift larger values to the right, ascending
			// > is shift smaller value to right, descending
			while (position > 0 && key.compareTo(list[position-1]) > 0)
			{
				list[position] = list[position-1];
				position--;
			}
				list[position] = key;
		}
	}
}
//