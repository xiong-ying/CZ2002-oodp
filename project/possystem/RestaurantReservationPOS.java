package possystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main entry point of the Restaurant Reservation Point of Sale System.
 * 
 * Restaurant operation time: Lunch: 11am - 4pm Dinner: 6pm - 11pm
 * 
 * @author Team members of Group 3
 * @version 1.0
 * @since 2021-11-08
 */
public class RestaurantReservationPOS {

	/**
	 * The system main method. Display and capturing the user flow.
	 * 
	 * @param args The command line arguments.
	 * @throws IOException exception
	 */
	public static void main(String[] args) throws IOException {

		// Setting up scanner for user input choice for POS system
		Scanner scanner = new Scanner(System.in);
		int userInput = -1;

		do {
			// DISPLAYING MAIN SERVICE MENU
			System.out.println();
			System.out.println("~~~~~~~~Restaurant Reservation Point of Sale System~~~~~~~~");
			System.out.println("1. Menu");
			System.out.println("2. Order");
			System.out.println("3. Print Invoice");
			System.out.println("4. Reservation");
			System.out.println("5. Check Table Availability");
			System.out.println("6. Sales Report");
			System.out.println("7. EXIT");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			// Getting user input choice for main service with invalid input handling
			do {
				try {
					System.out.println();
					System.out.println("Please select the service: ");
					userInput = scanner.nextInt();
				} catch (Exception e) {
					userInput = -1;
					scanner = new Scanner(System.in);
				}

				if (userInput < 1 || userInput > 7) {
					System.out.println();
					System.out.println("**Invalid input, please enter valid choice. 1 - 6**");
				}
			} while (!(userInput > 0 && userInput < 8));

			// Navigate program to user input choice
			switch (userInput) {
			case 1:
				// 1. Menu
				openMenuService();
				break;
			case 2:
				// 2. Order
				openOrderService();
				break;
			case 3:
				// 3. Print Invoice
				openPrintInvoiceService();
				break;
			case 4:
				// 4. Reservation
				openReservationService();
				break;
			case 5:
				// 5. Check Table Availability
				displayAvailableTablesNow();
				break;
			case 6:
				// 6. Sales Report
				openSaleReportService();
				break;
			case 7:
				// 7. EXIT Restaurant Reservation Point of Sale System
				System.out.println();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~GOOD BYE~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println();
				break;
			default:
				break;
			}
		} while (userInput != 7);
	}

	/**
	 * Display and capturing the user flow for menu services.
	 */
	static void openMenuService() {
		// Setting up scanner for user input choice for Menu
		Scanner menuScanner = new Scanner(System.in);
		Scanner closeScanner = new Scanner(System.in);
		String x;
		int menuInput = -1;

		// Initialize Menu Class
		Menu menu = new Menu();

		do {
			// DISPLAYING MENU SERVICES
			System.out.println();
			System.out.println("~~~~~~~~~~~~~MENU~~~~~~~~~~~~~");
			System.out.println("1. View Menu");
			System.out.println("2. Add New Menu Item");
			System.out.println("3. Edit Menu Item");
			System.out.println("4. Delete Menu Item");
			System.out.println("~~~~~~~~~PROMOTION MENU~~~~~~~~");
			System.out.println("5. View Promotion Menu");
			System.out.println("6. Add New Promotion Item");
			System.out.println("7. Edit Promotion Item");
			System.out.println("8. Delete Promotion Item");
			System.out.println("~~~~~~~~~~Categories~~~~~~~~~~");
			System.out.println("9. View Categories");
			System.out.println("10. Add New Category");
			System.out.println("11. Edit Category");
			System.out.println("12. Delete Category");
			System.out.println("~~~~~~~~~~~~~BACK~~~~~~~~~~~~~");
			System.out.println("13. Go back to main menu");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			// Getting user input choice for menu option with invalid input handling
			do {
				try {
					System.out.println();
					System.out.println("Please select the option: ");
					menuInput = menuScanner.nextInt();
				} catch (Exception e) {
					menuInput = -1;
					menuScanner = new Scanner(System.in);
				}

				if (menuInput < 1 || menuInput > 13) {
					System.out.println();
					System.out.println("**Invalid input, please enter valid choice. 1 - 13**");
				}
			} while (!(menuInput > 0 && menuInput < 14));

			// Navigate program to user input choice
			switch (menuInput) {
			case 1:
				// 1. View Menu
				// Print the menu item from the list
				menu.printAllMenuItems();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;
			case 2:
				// 2. Add New Menu Item
				try {
					menu.addNewMenuItem();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				// 3. Edit Menu Item
				try {
					menu.editMenuItem();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				// 4. Delete Menu Item
				try {
					menu.deleteMenuItem();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				// 5. View Promotion Menu
				menu.printAllPromoItems();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;
			case 6:
				// 6. Add New Promotion Item
				try {
					menu.addNewPromoItem();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				// 7. Edit Promotion Item
				try {
					menu.editPromoItem();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 8:
				// 8. Delete Promotion Item
				try {
					menu.deletePromoItem();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 9:
				// 9. View Categories
				menu.printCategoryList();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;
			case 10:
				// 10. Add New Category
				try {
					menu.addNewCategory();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 11:
				// 11. Edit Category
				try {
					menu.editCategory();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 12:
				// 12. Delete Category
				try {
					menu.deleteCategory();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 13:
				// 13. Go back to main menu
				break;
			default:
				break;
			}
		} while (menuInput != 13);
	}

	/**
	 * Display and capturing the user flow for order services.
	 */
	static void openOrderService() {

		// Setting up scanner for user input choice for Order
		Scanner menuScanner = new Scanner(System.in);
		Scanner closeScanner = new Scanner(System.in);
		String x;
		int menuInput = -1;

		// Length of Menu Selection
		int menuLength = 6;

		// Initialize StaffList Class
		StaffList staffs = new StaffList();
		// Initialize Order Class
		OrderList orders = new OrderList();

		do {
			// DISPLAYING ORDER SERVICES
			System.out.println();
			System.out.println("~~~~~~~~~~~~~ORDER~~~~~~~~~~~~");
			System.out.println("1. Create New Order");
			System.out.println("2. View Active Order");
			System.out.println("3. View All Orders (including past orders)");
			System.out.println("~~~~~~~~ADD/REMOVE ITEM~~~~~~~~");
			System.out.println("4. Add Item to Order");
			System.out.println("5. Remove Item from Order");
			System.out.println("~~~~~~~~~~~~~BACK~~~~~~~~~~~~~");
			System.out.println("6. Go back to main menu");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			// Getting user input choice for order option with invalid input handling
			do {
				try {
					System.out.println();
					System.out.println("Please select the option: ");
					menuInput = menuScanner.nextInt();
				} catch (Exception e) {
					menuInput = -1;
					menuScanner = new Scanner(System.in);
				}

				if (menuInput < 1 || menuInput > menuLength) {
					System.out.println();
					System.out.println("**Invalid input, please enter valid choice. 1 - " + menuLength + " **");
				}
			} while (!(menuInput > 0 && menuInput <= menuLength));

			// Navigate program to user input choice
			switch (menuInput) {
			case 1:
				// 1. Create New Order
				// Create an empty new order with new order ID
				try {
					orders.addNewOrder();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				// 2. View all active order
				// View the current active order information
				orders.printActiveOrder();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;

			case 3:
				// 3. View All Orders (includes paid)
				// Display a list info of all orders that are currently active(no paid yet)
				orders.printAllOrder();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;

			case 4:
				// 4. Add Item to Order
				// Add item from menu/promo item list to order

				try {
					orders.addItemToOrder();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 5:
				// 5. Remove Item from Order
				// Remove an item from menu/promo item list from order

				try {
					orders.removeItemFromOrder();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 6:
				// 6. Go back to main menu
				break;
			default:
				break;
			}
		} while (menuInput != 6);
	}

	/**
	 * Display and capturing the user flow for Reservation services.
	 */
	static void openReservationService() {
		// Setting up scanner for user input choice for Reservation
		Scanner reservationScanner = new Scanner(System.in);
		Scanner closeScanner = new Scanner(System.in);
		String x;
		int reservationInput = -1;

		// Initialise ReservationManger Class
		ReservationManager reservationManager = new ReservationManager();

		do {
			// DISPLAYING RESERVATION SERVICES
			System.out.println();
			System.out.println("~~~~~~~~~~~~~RESERVATION~~~~~~~~~~~~~~");
			System.out.println("1. View Reservation");
			System.out.println("2. Add New Reservation");
			System.out.println("3. Edit Reservation");
			System.out.println("4. Cancel Reservation");
			System.out.println("~~~~~~~~~~~~~~~~~BACK~~~~~~~~~~~~~~~~~");
			System.out.println("5. Go back to main menu");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			// Getting user input choice for reservation option with invalid input handling
			do {
				try {
					System.out.println();
					System.out.println("Please select the option: ");
					reservationInput = reservationScanner.nextInt();
				} catch (Exception e) {
					reservationInput = -1;
					reservationScanner = new Scanner(System.in);
				}

				if (reservationInput < 1 || reservationInput > 5) {
					System.out.println();
					System.out.println("**Invalid input, please enter valid choice. 1 - 5**");
				}
			} while (!(reservationInput > 0 && reservationInput < 6));

			// Navigate program to user input choice
			switch (reservationInput) {
			case 1:
				// 1. View Reservation
				reservationManager.printAllReservation();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;
			case 2:
				// 2. Add New Reservation
				try {
					reservationManager.addNewReservation();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				// 3. Edit Reservation
				try {
					reservationManager.editReservation();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				// 4. Cancel Reservation
				try {
					reservationManager.cancelReservation();
					System.out.println();
					System.out.println("Enter any key to continue...");
					x = closeScanner.nextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				// 5. Go back to main menu
				break;
			default:
				break;
			}
		} while (reservationInput != 5);
	}

	/**
	 * Displaying print invoice service
	 * @throws IOException exception
	 */
	private static void openPrintInvoiceService() throws IOException {

		// Setting up scanner for user input choice for POS system
		Scanner scanner = new Scanner(System.in);

		// Initialise Order Class
		OrderList orders = new OrderList();
		
		//Check if Empty
		int activeOrderListCount = 0;
		for (Order order : orders.getOrderList()) {
			//Check if valid order number and if order is paid already
			if (!order.getIsPaid()) {
				activeOrderListCount++;
			}
		}
		if (activeOrderListCount == 0) {
			System.out.println();
			System.out.println("No active order available for payment...");
			scanner = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter any key to continue...");
			String x = scanner.nextLine();
			return;
		}
		
		//Select Order for printing
		int orderNo  = -1;
		Order selectedOrder = null;
		
		//Print all the order available for invoicing
		orders.printActiveOrder();
		
		while(orderNo == -1) {
			System.out.println("Please enter order no. : ");
			orderNo = scanner.nextInt();
			
			for (Order order : orders.getOrderList()) {
				//Check if valid order number and if order is paid already
				if (order.getID() == orderNo && !order.getIsPaid()) {
					selectedOrder = order;
				}
			}
			
			//If no valid order is found
			if (selectedOrder == null) {
				System.out.println();
				System.out.println("Invalid order number entered, please try again...");
				System.out.println();
				orderNo = -1;
			}
		}
		

		for (Order order : orders.getOrderList()) {
			if (order.getID() == orderNo) {
				//print order
				order.viewOrder();
				
				System.out.println("Do you want to make a payment? (Y/N): ");
				String paymentCheck = scanner.next();
				if (paymentCheck.equals("Y")) {
					order.setIsPaid(true);
					order.getInvoiceObj().generateInvoice(order);
				} else {
					System.out.println("Please make a payment to generate the invoice");
				}
			}
		}
		
		orders.saveOrderToCSV();
		
		Scanner cscanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter any key to continue...");
		String x = cscanner.nextLine();
	}
	
	/**
	 * Displaying open table now
	 */
	private static void displayAvailableTablesNow() {

		//Table Manager
		TableManager tableManager = new TableManager();
		
		tableManager.printAvailableTableNowForWalkIn();
		
		Scanner cscanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter any key to continue...");
		String x = cscanner.nextLine();
	}

	/**
	 * Display and capturing the user flow for sale report services.
	 */
	static void openSaleReportService() {

		// Setting up scanner for user input choice for Order
		Scanner menuScanner = new Scanner(System.in);
		Scanner closeScanner = new Scanner(System.in);
		String x;
		int menuInput = -1;

		// Length of Menu Selection
		int menuLength = 4;

		// Initialise Sale Report Manager
		SaleReportManager saleManager = new SaleReportManager();

		do {
			// DISPLAYING SALE REPORT SERVICES
			System.out.println();
			System.out.println("~~~~~~~~~~~~~SALES  REPORT~~~~~~~~~~~~");
			System.out.println("1. Generate Daily Report");
			System.out.println("2. Generate Monthly Report");
			System.out.println("3. Generate Yearly Report");
			System.out.println("~~~~~~~~~~~~~~~~~BACK~~~~~~~~~~~~~~~~~");
			System.out.println("4. Go back to main menu");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			// Getting user input choice for order option with invalid input handling
			do {
				try {
					System.out.println();
					System.out.println("Please select the option: ");
					menuInput = menuScanner.nextInt();
				} catch (Exception e) {
					menuInput = -1;
					menuScanner = new Scanner(System.in);
				}

				if (menuInput < 1 || menuInput > menuLength) {
					System.out.println();
					System.out.println("**Invalid input, please enter valid choice. 1 - " + menuLength + " **");
				}
			} while (!(menuInput > 0 && menuInput <= menuLength));

			// Navigate program to user input choice
			switch (menuInput) {
			case 1:
				// 1. Generate Daily Report
				saleManager.generateDailyReport();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();

				break;
			case 2:
				// 2. Generate Monthly Report
				saleManager.generateMonthlyReport();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;

			case 3:
				// 2. Generate Yearly Report
				saleManager.generateYearlyReport();
				System.out.println();
				System.out.println("Enter any key to continue...");
				x = closeScanner.nextLine();
				break;

			case 4:
				// 4. Go back to main menu
				break;
			default:
				break;
			}
		} while (menuInput != 4);
	}
}
