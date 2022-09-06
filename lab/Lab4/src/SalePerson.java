public class SalePerson implements Comparable<Object>{

	// variable
	private String firstName;
	private String lastName;
	private int totalSales;
	
	// constructor
	public SalePerson(String FN, String LN, int TS) {
		firstName = FN;
		lastName = LN;
		totalSales = TS;
	}
	
	// method
	public String toString() {
		String finalString = lastName + ", "+firstName+" : "+totalSales;
		return finalString;
	}
	
	public boolean equals(Object o) {
		
		SalePerson i = (SalePerson) o;
		
		if((this.firstName == i.firstName) && (this.lastName == i.lastName)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int compareTo(Object o) {
		
		
		SalePerson i = (SalePerson) o;
		

		if(this.totalSales > i.totalSales) { // if 2Sales > 1Sales
			return 1;
		}
		else if ((this.totalSales == i.totalSales) && ( this.lastName.compareTo(i.lastName)<0 )) {// if tie, 2lName < 1lName, swap
			return 1;
		}
		else {
			return -1;
		}


	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getTotalSales() {
		return totalSales;
	}
	

}
