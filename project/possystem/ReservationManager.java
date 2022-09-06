/**
 * 
 */
package possystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class handles all reservations. Including making new reservation, editing
 * reservation and deleting reservations.
 * 
 * Reservations can only be made ahead of time.
 * For lunch reservation (11am - 4pm), it can only be made the day before. (Cut-off time: the previous day) 
 * For dinner reservation (6pm - 9pm), it can only be made before 4pm on the day itself. (Cut-off time: before 4pm of the actual date of reservation)
 * 
 * Reservation will be automatically cancelled after 15 mins.
 * 
 * Reservation will be closed after 50% of the tables are reserved based on lunch or dinner session.
 * So that there will still be tables for walk in guests.
 * 
 * @version 1.0
 * @since 2021-11-12
 * @author Chong Hui Yee
 *
 */
public class ReservationManager {

	/**
	 * The constant values for Categories.cvs file path.
	 */
	public static final String RESERVATION_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/Reservations.csv");

	/**
	 * The list of all the reservation.
	 */
	static List<Reservation> reservationList = new ArrayList<Reservation>();

	/**
	 * Create a class constructor for the ReservationManager class Initialisation include fetching
	 * item from csv to variable.
	 */
	public ReservationManager() {
		parseReservationCSVtoObject();
		try {
			checkAllReservation();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the reservationList
	 */
	public List<Reservation> getReservationList() {
		return reservationList;
	}
	
	/**
	 * Read reservation from .csv file and convert line to Reservation object and save to
	 * array.
	 */
	public static void parseReservationCSVtoObject() {
		// Parsing a Reservations.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			reservationList.clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(RESERVATION_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);
				if (item.length < 7) {
					return;
				}
				//Parse date time from string to LocalDateTime
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				LocalDateTime reservationTime = LocalDateTime.parse(item[1], formatter);
				//Parse salutation from string to Salutation
				Salutation custSal = null;
				for (Salutation sal : Salutation.values()) {
					  if (item[2].equals(sal.name()) ) {
						  custSal = sal;
					  }
				}
				//Parse status from string to Status
				Status status = null;
				for (Status stat : Status.values()) {
					  if (item[6].equals(stat.name())) {
						  status = stat;
					  }
				}
				// Convert String array to Reservation object & add to reservationList
				Reservation reservation = new Reservation(Integer.parseInt(item[0]), reservationTime, custSal, item[3].replace("|", ","), Integer.parseInt(item[4]),  Integer.parseInt(item[5]), status,  Integer.parseInt(item[7]));
				reservationList.add(reservation);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print all the reservations.
	 * Arrange in dates.
	 */
	public void printAllReservation() {

		// Print Header
		System.out.println();
		System.out.println("__________________________________RESERVATION LIST___________________________________");
		System.out.println();
		System.out.println();

		//Sort reservation list
		reservationList.sort((o1,o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
		
		//Get date list from reservation
		List<String> dateList = new ArrayList<String>();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, dd MMM yyyy");		
		for (Reservation item : reservationList) {
			dateList.add(item.dateTime.format(myFormatObj));
		}
		List<String> reservationDateList = dateList.stream().distinct().collect(Collectors.toList());
		
		//Print reservations according to dates
		for (String item : reservationDateList) {
			// Print Dates Header
			System.out.printf(padLeft("_____", Math.round(80 - item.length()) / 2));
			System.out.printf(item);
			System.out.printf(padRight("_____", Math.round(80 - item.length()) / 2));
			System.out.println();
			System.out.println();
			for (Reservation reservation : reservationList) {
				if (item.equals(reservation.dateTime.format(myFormatObj))) {
					reservation.printReservation();
				}
			}
		}
		// Print Footer
		System.out.println("_______________________________________________________________________________________");

	}

	/**
	 * Print all the UPCOMING reservations.
	 * Arrange in dates.
	 */
	public void printUpcomingReservation() {

		// Print Header
		System.out.println();
		System.out.println("__________________________________RESERVATION LIST___________________________________");
		System.out.println();
		System.out.println();

		//Sort reservation list
		reservationList.sort((o1,o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
		
		//Get date list from reservation
		List<String> dateList = new ArrayList<String>();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, dd MMM yyyy");		
		for (Reservation item : reservationList) {
			dateList.add(item.dateTime.format(myFormatObj));
		}
		List<String> reservationDateList = dateList.stream().distinct().collect(Collectors.toList());
		
		//Print reservations according to dates
		for (String item : reservationDateList) {
			// Print Dates Header
			System.out.printf(padLeft("_____", Math.round(80 - item.length()) / 2));
			System.out.printf(item);
			System.out.printf(padRight("_____", Math.round(80 - item.length()) / 2));
			System.out.println();
			System.out.println();
			for (Reservation reservation : reservationList) {
				if (reservation.dateTime.isAfter(LocalDateTime.now()) && item.equals(reservation.dateTime.format(myFormatObj)) ) {
					reservation.printReservation();
				}
			}
		}
		// Print Footer
		System.out.println("_______________________________________________________________________________________");

	}
	
	/**
	 * Print and return all the reservations on the current day.
	 * 
	 * @return the current reservation of the day
	 */
	public List<Reservation> printAndGetAllReservationForTheDay() {
		
		List<Reservation> reservationforTheDayList = new ArrayList<Reservation>();

		// Print Header
		System.out.println();
		System.out.println("__________________________________RESERVATION LIST___________________________________");
		System.out.println();
		System.out.println();

		// Sort reservation list
		reservationList.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, dd MMM yyyy");

		for (Reservation reservation : reservationList) {
			// Check if reservation is on current day
			if (reservation.dateTime.format(myFormatObj).equals(reservation.dateTime.format(myFormatObj))) {
				// Checkout for lunch or dinner
				if (currentDateTime.getHour() < 17) {
					if (reservation.isLunchReservation()) {
						reservationforTheDayList.add(reservation);
						reservation.printReservation();
					}

				} else {
					if (!reservation.isLunchReservation()) {
						reservationforTheDayList.add(reservation);
						reservation.printReservation();
					}
				}
			}
		}

		// Print Footer
		System.out.println("_______________________________________________________________________________________");

		return reservationforTheDayList;
	}
	
	/**
	 * Add new reservations and save to csv. Reservations can only be made ahead of
	 * time. For lunch reservation (11am - 4pm), it can only be made the day before
	 * (Cut-off time: the previous day). For dinner reservation (6pm - 11pm), it can
	 * only be made before 4pm on the day itself. (Cut-off time: before 4pm of the
	 * actual date of reservation). Reservation will be closed after 50% of the
	 * tables are reserved based on lunch or dinner session.
	 * @throws IOException 
	 */
	public void addNewReservation() throws IOException {
		// Temp Variables
		LocalDateTime dateTime = null;
		Salutation salutation = null;
		String custName;
		int custContact;
		int tableNo = -1;
		int pax = 0;
		Boolean isValidDate = false;
		
		// Get all inputs from user
		Scanner newScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Adding New Reservation... ");
		
		//Getting Date & Time in valid format & Table
		while (!isValidDate) {
			System.out.println();
			System.out.println("Please enter reservation's date in DD-MM-YYYY HH:MM format: ");
			String dateString = newScanner.nextLine();
			//Parse dateString from string to LocalDateTime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			try {
				dateTime = LocalDateTime.parse(dateString, formatter);
			} catch (Exception e) {
				System.out.println("Invalid date format, please try again... ");
				continue;
			}
			
			//Check date is in future
			if (dateTime.isBefore(LocalDateTime.now())) {
				System.out.println("Reservation can only be made in advance, please try again... ");
				
			}else if (dateTime.getHour()<11 || dateTime.getHour()>21 || dateTime.getHour() == 17){
				// Check if reservation is available for select date
				System.out.println("Invalid Timing: For lunch reservation (11am - 4pm), For dinner reservation (6pm - 9pm), please try again... ");
				
			}else {
				TableManager tblManager = new TableManager();
				
				List<Table> availableTableList = tblManager.getAvailableTableList(dateTime);
				
				// Reservation will be closed after 50% of the tables are reserved based on lunch or dinner session.
				if (availableTableList.size() < 11){
					System.out.println("Reservation full! Please enter another timing...");
				}else {
					//Request number of people
					System.out.println("Please enter number of pax: ");
					pax = newScanner.nextInt();
					
					//Checking if pax is in valid range
					if (pax<1 || pax > 10) {
						System.out.println("Restuarant only accept reservation for 1-10 pax, please try again...");
					}else if(pax > 0 && pax <= 2 ) {
						//Check for table up to two pax
						for (Table cTbl : availableTableList) {
							if (cTbl.seats == 2) {
								tableNo = cTbl.getTableNo();
								isValidDate = true;
								break;
							}
						}
						//If no table for 2, assign table for 4
						if (tableNo == -1) {
							for (Table cTbl : availableTableList) {
								if (cTbl.seats == 4) {
									tableNo = cTbl.getTableNo();
									isValidDate = true;
									break;
								}
							}
						}
						//If still no table, reservation cannot be made.
						if (tableNo == -1) {
							System.out.println("Restuarant is full for the selected date and time, please enter another date...");
						}
					}else if(pax > 2 && pax <= 4 ) {
						//Check for table up to four pax, assign table for 4
						if (tableNo == -1) {
							for (Table cTbl : availableTableList) {
								if (cTbl.seats == 4) {
									tableNo = cTbl.getTableNo();
									isValidDate = true;
									break;
								}
							}
						}
						//If still no table, reservation cannot be made.
						if (tableNo == -1) {
							System.out.println("Restuarant is full for the selected date and time, please enter another date...");
						}
					}else if(pax > 4 && pax <= 6 ) {
						//Check for table up to six pax, assign table for 6
						if (tableNo == -1) {
							for (Table cTbl : availableTableList) {
								if (cTbl.seats == 6) {
									tableNo = cTbl.getTableNo();
									isValidDate = true;
									break;
								}
							}
						}
						//If still no table, reservation cannot be made.
						if (tableNo == -1) {
							System.out.println("Restuarant is full for the selected date and time, please enter another date...");
							newScanner = new Scanner(System.in);
						}
					}else if(pax > 6 && pax <= 10 ) {
						//Check for table up to ten pax, assign table for 10
						if (tableNo == -1) {
							for (Table cTbl : availableTableList) {
								if (cTbl.seats == 10) {
									tableNo = cTbl.getTableNo();
									isValidDate = true;
									break;
								}
							}
						}
						//If still no table, reservation cannot be made.
						if (tableNo == -1) {
							System.out.println("Restuarant is full for the selected date and time, please enter another date...");
						}
					}
					
					if (tableNo == -1) {
						newScanner = new Scanner(System.in);
					}
				}
				
			}
		}
		
		//After confirming date and table, proceed to get customer information
		//Fetching salutation
		System.out.println("Please select salutation:");
		System.out.println(padRight("1. Miss", 5));
		System.out.println(padRight("2. Mdm", 5));
		System.out.println(padRight("3. Mr", 5));
		System.out.println(padRight("4. Dr", 5));
		System.out.println("Please select customer's salutation (1 - 4) : ");
		int choice = newScanner.nextInt();
		if (choice == 1) {
			salutation = Salutation.Miss;
		}else if (choice == 2){
			salutation = Salutation.Mdm;
		}else if (choice == 3) {
			salutation = Salutation.Mr;
		}else if (choice == 4) {
			salutation = Salutation.Dr;
		}
		
		Scanner custScanner = new Scanner(System.in);
		//Fetching customer name
		System.out.println("Please enter customer's name: ");
		custName = custScanner.nextLine();
		
		//Fetching customer phone number
		System.out.println("Please enter phone number : ");
		System.out.printf("+65 ");
		custContact = custScanner.nextInt();

		// Create new Reservation
		// Change "," to "|" for save in Csv
		// Add new reservation item to reservationList
		Reservation reserve = new Reservation(reservationList.size() + 1, dateTime, salutation, custName.replace(",", "|"), custContact, pax, Status.Reserved, tableNo);
		reservationList.add(reserve);

		// Save new reservation to csv
		saveAndUpdateAllReservation();
		
		// Show user the new reservation
		System.out.println();
		System.out.println("******************************************************************");
		if (reserve != null)
			reserve.printReservation();
		System.out.println("Reservation sucessfully added!");
		System.out.println("******************************************************************");

	}

	/**
	 * Cancel existing Reservation
	 * @throws IOException 
	 */
	public void cancelReservation() throws IOException {
		// Temp Variables
		int reservationid = 0;
		Boolean didFind = false;
		Reservation cancelledReservation = null;
		
		if (reservationList.size() == 0) {
			System.out.println();
			System.out.println("No reservation to be cancelled...");
			return;
		}
		
		// Get all inputs from user
		Scanner updateScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Cancelling Existing Reservation... ");
		
		//Print upcoming reservation
		printUpcomingReservation();
		
		while (!didFind) {
			// Get reservation id input from user
			System.out.println();
			System.out.println("Enter the reservation no. to be cancelled...");
			System.out.printf("Please enter reservation no. : ");
			reservationid = updateScanner.nextInt();
			// Filter out the reservation based on user input
			for (Reservation item : reservationList) {
				if (item.id == reservationid && item.dateTime.isAfter(LocalDateTime.now())) {
					
					cancelledReservation = item;
					//Set status
					item.setStatus(Status.Cancelled);
					didFind = true;
				}
			}
			
			if (!didFind) {
				System.out.println("Invalid reservation ID, please try again");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save updated reservation to csv
		saveAndUpdateAllReservation();
		
		// Show user the updated reservation
		System.out.println();
		System.out.println("******************************************************************");
		if (cancelledReservation != null)
			cancelledReservation.printReservation();
		System.out.println("Reservation sucessfully cancelled!");
		System.out.println("******************************************************************");

	}
	
	/**
	 * Edit existing Reservation
	 * @throws IOException 
	 */
	public void editReservation() throws IOException {
		// Temp Variables
		LocalDateTime dateTime = null;
		Salutation salutation = null;
		int reservationid = 0;
		String custName = null;
		int custContact = 0;
		int tableNo = -1;
		int pax = 0;
		Boolean isValidDate = false;
		Boolean didFind = false;
		Reservation updatedReservation = null;
		
		if (reservationList.size() == 0) {
			System.out.println();
			System.out.println("No reservation to be edited...");
			return;
		}
		
		
		// Get all inputs from user
		Scanner updateScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Editing Existing Reservation... ");
		
		//Print upcoming reservation
		printUpcomingReservation();
		
		while (!didFind) {
			// Get reservation id input from user
			System.out.println();
			System.out.println("Enter the reservation no. to be edited...");
			System.out.printf("Please enter reservation no. : ");
			reservationid = updateScanner.nextInt();
			// Filter out the reservation based on user input
			for (Reservation item : reservationList) {
				if (item.id == reservationid && item.dateTime.isAfter(LocalDateTime.now())) {
					
					updatedReservation = item;
					
					//Getting Date & Time in valid format & Table
					while (!isValidDate) {
						System.out.println();
						updateScanner = new Scanner(System.in);
						System.out.println("Please enter updated reservation's date in DD-MM-YYYY HH:MM format: ");
						String dateString = updateScanner.nextLine();
						//Parse dateString from string to LocalDateTime
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
						try {
							dateTime = LocalDateTime.parse(dateString, formatter);
						} catch (Exception e) {
							System.out.println("Invalid date format, please try again... ");
							continue;
						}
						
						//Check date is in future
						if (dateTime.isBefore(LocalDateTime.now())) {
							System.out.println("Reservation can only be made in advance, please try again... ");
							
						}else if (dateTime.getHour()<11 || dateTime.getHour()>21 || dateTime.getHour() == 17){
							// Check if reservation is available for select date
							System.out.println("Invalid Timing: For lunch reservation (11am - 4pm), For dinner reservation (6pm - 9pm), please try again... ");
							
						}else {
							TableManager tblManager = new TableManager();
							
							List<Table> availableTableList = tblManager.getAvailableTableList(dateTime);
							
							// Reservation will be closed after 50% of the tables are reserved based on lunch or dinner session.
							if (availableTableList.size() < 11){
								System.out.println("Reservation full! Please enter another timing...");
							}else {
								//Request number of people
								System.out.println("Please enter updated number of pax: ");
								pax = updateScanner.nextInt();
								System.out.println(item.tableNumber / 10);
								//Checking if pax is in valid range
								if (pax<1 || pax > 10) {
									System.out.println("Restuarant only accept reservation for 1-10 pax, please try again...");
								}else if(pax > 0 && pax <= 2 ) {
									//Check for table up to two pax
									for (Table cTbl : availableTableList) {
										if (cTbl.seats == 2) {
											tableNo = cTbl.getTableNo();
											isValidDate = true;
											break;
										}
									}
									//If no table for 2, assign table for 4
									if (tableNo == -1) {
										for (Table cTbl : availableTableList) {
											if (cTbl.seats == 4) {
												tableNo = cTbl.getTableNo();
												isValidDate = true;
												break;
											}
										}
									}
									
									if ( (item.tableNumber / 10) == 2) {
										tableNo = item.tableNumber;
										isValidDate = true;
									}
									
									//If still no table, reservation cannot be made.
									if (tableNo == -1) {
										System.out.println("Restuarant is full for the selected date and time, please enter another date...");
									}
								}else if(pax > 2 && pax <= 4 ) {
									//Check for table up to four pax, assign table for 4
									if (tableNo == -1) {
										for (Table cTbl : availableTableList) {
											if (cTbl.seats == 4) {
												tableNo = cTbl.getTableNo();
												isValidDate = true;
												break;
											}
										}
									}
									
									if ( (item.tableNumber / 10) == 4) {
										tableNo = item.tableNumber;
										isValidDate = true;
									}
									
									//If still no table, reservation cannot be made.
									if (tableNo == -1) {
										System.out.println("Restuarant is full for the selected date and time, please enter another date...");
									}
								}else if(pax > 4 && pax <= 6 ) {
									//Check for table up to six pax, assign table for 6
									if (tableNo == -1) {
										for (Table cTbl : availableTableList) {
											if (cTbl.seats == 6) {
												tableNo = cTbl.getTableNo();
												isValidDate = true;
												break;
											}
										}
									}
									
									if ( (item.tableNumber / 10) == 6) {
										tableNo = item.tableNumber;
										isValidDate = true;
									}
									
									//If still no table, reservation cannot be made.
									if (tableNo == -1) {
										System.out.println("Restuarant is full for the selected date and time, please enter another date...");
										updateScanner = new Scanner(System.in);
									}
								}else if(pax > 6 && pax <= 10 ) {
									//Check for table up to ten pax, assign table for 10
									if (tableNo == -1) {
										for (Table cTbl : availableTableList) {
											if (cTbl.seats == 10) {
												tableNo = cTbl.getTableNo();
												isValidDate = true;
												break;
											}
										}
									}
									
									if ( (item.tableNumber / 10) == 10) {
										tableNo = item.tableNumber;
										isValidDate = true;
									}
									
									//If still no table, reservation cannot be made.
									if (tableNo == -1) {
										System.out.println("Restuarant is full for the selected date and time, please enter another date...");
									}
								}
								
								if (tableNo == -1) {
									updateScanner = new Scanner(System.in);
								}
							}
							
						}
					}
					
					//After confirming date and table, proceed to get customer information
					//Fetching salutation
					System.out.println("Please select salutation:");
					System.out.println(padRight("1. Miss", 5));
					System.out.println(padRight("2. Mdm", 5));
					System.out.println(padRight("3. Mr", 5));
					System.out.println(padRight("4. Dr", 5));
					System.out.println("Please select customer's salutation (1 - 4) : ");
					int choice = updateScanner.nextInt();
					if (choice == 1) {
						salutation = Salutation.Miss;
					}else if (choice == 2){
						salutation = Salutation.Mdm;
					}else if (choice == 3) {
						salutation = Salutation.Mr;
					}else if (choice == 4) {
						salutation = Salutation.Dr;
					}
					
					Scanner custScanner = new Scanner(System.in);
					//Fetching customer name
					System.out.println("Please enter customer's name: ");
					custName = custScanner.nextLine();
					
					//Fetching customer phone number
					System.out.println("Please enter phone number : ");
					System.out.printf("+65 ");
					custContact = custScanner.nextInt();
					
					didFind = true;
					
				}
			}
			
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid reservation ID, please try again");
				updateScanner = new Scanner(System.in);
			}

		}
		final Integer id = reservationid;
		reservationList.removeIf(i -> (i.id == id));
		Reservation reserve = new Reservation(reservationid, dateTime, salutation, custName.replace(",", "|"), custContact, pax, Status.Reserved, tableNo);
		reservationList.add(reserve);

		// Save updated reservation to csv
		saveAndUpdateAllReservation();
		
		// Show user the updated reservation
		System.out.println();
		System.out.println("******************************************************************");
		if (reserve != null)
			reserve.printReservation();
		System.out.println("Reservation sucessfully updated!");
		System.out.println("******************************************************************");

	}
	
	/**
	 * Check for no show and updated csv.
	 * 
	 * @throws IOException exception
	 */
	public void checkAllReservation() throws IOException {

		for (Reservation item : reservationList) {
			if (LocalDateTime.now().isAfter(item.getDateTime().plusMinutes(15)) && item.status == Status.Reserved) {
				item.setStatus(Status.NoShow);
			}
		}

		saveAndUpdateAllReservation();
	}
	
	/**
	 * Save and updated csv.
	 * 
	 * @throws IOException exception
	 */
	public void saveAndUpdateAllReservation() throws IOException {

		// Save updated reservation to csv
		List<String> mlist = new ArrayList<>();

		for (Reservation item : reservationList) {
			mlist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESERVATION_CSV_FILE_PATH))) {
			for (String line : mlist) {
				bw.write(line);
				bw.newLine();
			}
		}
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
