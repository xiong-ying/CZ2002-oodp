import java.util.Arrays;

public class Plane {
	
	// Variable
	private PlaneSeat[] seat = new PlaneSeat[12];
	private int numEmptySeat;
	
	// Constructor
	public Plane() {
		numEmptySeat = 12;
		for (int i=0;i<12;i++) {
			seat[i] = new PlaneSeat(i);
		}
	}
	
	// method	
	private PlaneSeat[ ] sortSeat() {
		
		int[] custArr = new int[12];
		
		for (int i=0;i<12;i++) {
			if (seat[i].isOccupied()) {
				custArr[i]=seat[i].getCustomerID();
			}
			else {
				custArr[i]=0;
			}
		}	
		
		Arrays.sort(custArr);
			
		PlaneSeat[] seatcopy = new PlaneSeat[12];
			
		for (int i=0;i<12;i++) { //for every custID in sorted array
			
			if (custArr[i]!=0) {
				for (int j=0;j<12;j++) { //compare with custID in original array
				
					if ( custArr[i]==seat[j].getCustomerID() ) {
						seatcopy[i] = new PlaneSeat(j); //assign seatID
						seatcopy[i].assign(custArr[i]); //assign custID
					}// end if 
				}// end for j
			}// end if
			else {
				seatcopy[i] = new PlaneSeat(0);
			}// end else
		}// end for i
			
		return seatcopy;
	}
	
	
	public void showNumEmptySeats() {
		System.out.println("There are " + numEmptySeat + " empty seats");
	}
	
	public void showEmptySeats() {
		System.out.println("The following seats are empty:");
		for (int i=0; i<12; i++) {
			if (seat[i].isOccupied() == false) {
				System.out.println("SeatID "+(i+1));
			}
		}
	}
	
	public void showAssignedSeats(boolean bySeatId) {
		if (bySeatId == true) {
			for (int i=0; i<12; i++) {
				if(seat[i].isOccupied() == true) {
					System.out.println("SeatID "+(seat[i].getSeatID()+1)+" assigned to CustomerID "+seat[i].getCustomerID()+".");
				} // end if
			} // end for
		} // end if
		else {
			
			PlaneSeat[] seatSorted = sortSeat();
			for (int i=0; i<12; i++) {
				if(seatSorted[i].isOccupied() == true) {
					System.out.println("SeatID "+(seatSorted[i].getSeatID()+1)+" assigned to CustomerID "+seatSorted[i].getCustomerID()+".");
				} //end if
			} //end for
		} // end else
	} // end method
	
	public void assignSeat(int seatId, int cust_id) {
		if (seat[seatId].isOccupied()==false) {
			seat[seatId].assign(cust_id);
			numEmptySeat --;
			System.out.println("Seat assigned!");
		}
		else {
			System.out.println("Seat already assigned to a customer.");
		}
	}
	
	public void unAssignSeat(int seatId) {
		if (seat[seatId].isOccupied()==true) {
			seat[seatId].unAssign();
			numEmptySeat ++;
			System.out.println("Seat unassigned!");
		}
		else {
			System.out.println("Cannot unassign empty seat.");
		}
	}

}
