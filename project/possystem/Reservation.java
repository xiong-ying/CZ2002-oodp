package possystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Salutation that can be used
 */
enum Salutation {
	Miss, 
	Mdm,
	Mr,
	Dr;
}

/**
 * Status of the reservation
 * Reserved  - Table is reserved, customer has yet to arrived.
 * Seated - The customer has arrived and seated.
 * Completed - The customer has completed dining and left.
 * Cancelled - The reservation is cancelled by customer.
 * NoShow - The customer did not appeared for the reservation
 */
enum Status {
	Reserved, Seated, Cancelled, NoShow
}

/**
 * The class represent a reservation made in the Restaurant Reservation Point of
 * Sale System. It contains the details of the reservation - date, time,
 * customer information and reservation status.
 * 
 * @version 1.0
 * @since 2021-11-12
 * @author Chong Hui Yee
 *
 */
public class Reservation {

	/**
	 * The unique id for each reservation.
	 */
	int id;
	/**
	 * The date & time of the reservation.
	 */
	LocalDateTime dateTime;
	/**
	 * The customer's Salutation of this reservation.
	 */
	Salutation custSalutation;
	/**
	 * The customer's name of this reservation.
	 */
	String custName;
	/**
	 * The customer's contact of this reservation. Only Singapore number allowed.
	 * (+65)
	 */
	int custContact;
	/**
	 * The number of seat needed for this reservation.
	 */
	int pax;
	/**
	 * The status of this reservation.
	 */
	Status status;
	/**
	 * The table number reserved for this reservation.
	 */
	int tableNumber;

	/**
	 * Create a class constructor for the Reservation class
	 * 
	 * @param id             This Reservation's unique id.
	 * @param dateTime       This Reservation's name.
	 * @param custSalutation This Reservation's customer salutation.
	 * @param custName       This Reservation's customer name.
	 * @param custContact    This Reservation's customer contact.
	 * @param pax            The number of seat needed for this reservation.
	 * @param status         The status of this reservation.
	 * @param tableNumber    The table number reserved for this reservation.
	 */
	public Reservation(int id, LocalDateTime dateTime, Salutation custSalutation, String custName, int custContact,
			int pax, Status status, int tableNumber) {
		this.id = id;
		this.dateTime = dateTime;
		this.custSalutation = custSalutation;
		this.custName = custName;
		this.custContact = custContact;
		this.pax = pax;
		this.status = status;
		this.tableNumber = tableNumber;
	}

	/**
	 * @return the id of the Reservation
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the date and time of reservation
	 * 
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	/**
	 * Get the date in string format (dd-MM-yyyy)
	 * 
	 * @return the date string
	 */
	public String getDateString() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	    String formattedDate = dateTime.format(myFormatObj);
		
		return formattedDate;
	}

	/**
	 * Set the date and time of reservation
	 * 
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Get the salutation of the customer
	 * 
	 * @return the custSalutation
	 */
	public Salutation getCustSalutation() {
		return custSalutation;
	}

	/**
	 * Set the salutation of the customer
	 * 
	 * @param custSalutation the custSalutation to set
	 */
	public void setCustSalutation(Salutation custSalutation) {
		this.custSalutation = custSalutation;
	}

	/**
	 * Get the name of the customer
	 * 
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * Set the name of the customer
	 * 
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * Get the contact number of the customer
	 * 
	 * @return the custContact
	 */
	public int getCustContact() {
		return custContact;
	}

	/**
	 * Set the contact number of the customer
	 * 
	 * @param custContact the custContact to set
	 */
	public void setCustContact(int custContact) {
		this.custContact = custContact;
	}

	/**
	 * Get the number of guests for the reservation
	 * 
	 * @return the pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Set the number of guests for the reservation
	 * 
	 * @param pax the pax to set
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * Get the status of the reservation
	 * 
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status of the reservation
	 * 
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Get the table number of the reservation
	 * 
	 * @return the tableNumber
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * Set the table number of the reservation
	 * 
	 * @param tableNumber the tableNumber to set
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	/**
	 * Check is reservation is lunch session.
	 * 
	 * @return isLunchReservation Between 11am - 4pm
	 */
	public Boolean isLunchReservation() {
		if (dateTime.getHour() >= 18){
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Print the details of this reservation
	 * 
	 */
	public void printReservation() {
		System.out.printf("%03d. ", id);
		System.out.printf(padRight(custSalutation.toString(), 4) + " ");
		System.out.printf(padRight(custName.replace("|", ","), 20));
		System.out.printf(padRight(String.valueOf(pax) + " pax" , 10));
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm a");
	    String formattedDate = dateTime.format(myFormatObj);
		System.out.printf(" " + padRight(formattedDate, 15));
		System.out.printf(padRight("Table " + String.valueOf(tableNumber).toString(), 15) + " ");
		System.out.printf(padRight(status.toString(), 15) + " ");
		System.out.println();
		System.out.printf(padLeft("+65 ", 9) + String.valueOf(custContact));
		System.out.println();
		System.out.println();
		System.out.println();
	}

	/**
	 * Take Reservation object and convert object to cvs string format
	 * 
	 * @return this string representation of the object.
	 */
	public String convertObjectToCsvString() {

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formattedDate = dateTime.format(myFormatObj);

		String result = new String(String.valueOf(id) + "," + formattedDate + "," + custSalutation + "," + custName
				+ "," + String.valueOf(custContact) + "," + String.valueOf(pax) + "," + status + ","
				+ String.valueOf(tableNumber));

		return result;
	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the right.
	 */
	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * Pad string with spaces on left
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the left.
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}
