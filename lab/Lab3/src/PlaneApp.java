import java.util.Scanner;

public class PlaneApp {
	
	public static void main(String[] args) {
		
		int choice, seatID, custID;
		Scanner sc = new Scanner(System.in);
		
		Plane newPlane = new Plane();
		
		
		do {
			System.out.println(" ");
			System.out.println("(1)Show number of empty seats");
			System.out.println("(2)Show the list of empty seats");
			System.out.println("(3)Show the list of seat assignment by seat ID");
			System.out.println("(4)Show the list of seat assignment by customer ID");
			System.out.println("(5)Assign a customer to a seat");
			System.out.println("(6)Remove a seat assignment");
			System.out.println("(7)Exit");
			System.out.println("Enter the number of your choice:");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					
					newPlane.showNumEmptySeats();
					break;
					
				case 2:
					
					newPlane.showEmptySeats();
					break;
					
				case 3://show assigned seat by seatID
					
					System.out.println("The seat assignments are as follow:");
					newPlane.showAssignedSeats(true);
					break;
					
				case 4://show assigned seat by custID
					
					System.out.println("The seat assignments are as follow:");
					newPlane.showAssignedSeats(false);
					break;
					
				case 5://assign seat
					
					System.out.println("Assigning seat ..");
					System.out.println("Please enter SeatID:");
					seatID = sc.nextInt();
					System.out.println("Please enter customerID:");
					custID = sc.nextInt();
					
					newPlane.assignSeat(seatID-1, custID);
					
					break;
					
				case 6://remove seat assignment
					
					System.out.println("Enter SeatID to unassign customer from:");
					seatID = sc.nextInt();
					
					newPlane.unAssignSeat(seatID-1);
					
			}
		}while (choice<7);
	}
	

}
