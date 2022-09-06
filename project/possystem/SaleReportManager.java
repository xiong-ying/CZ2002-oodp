package possystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The class handles all sales report in the restaurant.
 * 
 * @version 1.0
 * @since 2021-11-14
 * @author Chong Hui Yee
 *
 */
public class SaleReportManager {
	
	/**
	 * The list of invoices 
	 */
	static List<Invoice> invoiceList = new ArrayList<Invoice>();
	
	/**
	 * Create a class constructor for the SaleReportManager class Initialisation include fetching
	 * item from csv to variable.
	 */
	public SaleReportManager() {
		// Load all invoice item to SaleReportManager
		InvoicePrinterMessage ipm = new InvoicePrinterMessage();
		invoiceList = ipm.getInvoiceObjList();
	}
	
	
	/**
	 * Generate daily report
	 */
	public void generateDailyReport() {

		Scanner dateScanner = new Scanner(System.in);
		int selectedIndex = -1;
		OrderList orderList = new OrderList();

		// Get date list from invoices
		List<String> dateList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		DateTimeFormatter invFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Sort reservation list
		invoiceList.sort((o1, o2) -> LocalDateTime.parse(o1.getInvoiceTimeStamp(), invFormatObj).compareTo(LocalDateTime.parse(o2.getInvoiceTimeStamp(), invFormatObj)));
		
		for (Invoice item : invoiceList) {
			LocalDateTime date = LocalDateTime.parse(item.getInvoiceTimeStamp(), invFormatObj);
			dateList.add(date.format(formatter));
		}
		List<String> dayList = dateList.stream().distinct().collect(Collectors.toList());

		// Print header
		System.out.println();
		System.out.println("------------Available Day Report-------------");
		System.out.println();
		for (int i = 0; i < dayList.size(); i++) {
			System.out.println(String.valueOf(i+1) + ". " + dayList.get(i));
		}
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println();
		
		//Get selected date
		while(selectedIndex == -1) {
			System.out.println();
			System.out.println("Please enter no. to view report: ");
			int index = dateScanner.nextInt();
			
			if (index - 1 < dayList.size()) {
				selectedIndex = index - 1;
			}else {
				System.out.println();
				System.out.println("Invaild selection, please try again...");
				System.out.println();
			}

		}
		
		//Filter Invoices based on date
		List<Invoice> filterInvoiceList = new ArrayList<Invoice>();
		List<Order> ordList = orderList.getOrderList();
		float totalDiscountLost = 0;
		float totalRevenue = 0;
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		List<PromoItem> promoItemsList = new ArrayList<PromoItem>();
		
		for (Invoice item : invoiceList) {
			LocalDateTime date = LocalDateTime.parse(item.getInvoiceTimeStamp(), invFormatObj);
			
			if (date.format(formatter).equals(dayList.get(selectedIndex) )) {
				filterInvoiceList.add(item);
				totalDiscountLost += item.getDiscountOfSubtotal();
				totalRevenue += item.getTotalPrice();
				
				Order order = ordList.stream().filter(a -> a.getID() == item.getOrderID()).collect(Collectors.toList()).get(0);
				for(MenuItem mitem : order.getMenuList()) {
					menuItemsList.add(mitem);
				}
				for(PromoItem pitem : order.getPromoList()) {
					promoItemsList.add(pitem);
				}
				
			}
		}
		
		
		
		//Print Report
		Menu menu = new Menu();
		System.out.println();
		System.out.println("--------------" + dayList.get(selectedIndex)  + " Report---------------");
		
		System.out.printf("Total Revenue: ");
		System.out.printf(padLeft(" ",20));
		System.out.printf("  $%.2f", totalRevenue);
		System.out.println();
		
		System.out.printf("Total lost from discount: ");
		System.out.printf(padLeft("-",12));
		System.out.printf("$%.2f", totalDiscountLost);
		System.out.println();
		
		System.out.println();
		System.out.println("Total number of items sold: ");
		System.out.println();
		for ( MenuItem mItem : menu.getMenuList()) {
			int count = 0;
			for ( MenuItem soldItem : menuItemsList) {
				
				if (mItem.getID() == soldItem.getID()) {
					count +=1;
				}
				
			}
			if (count != 0) {
				
				System.out.printf(" - ");
				System.out.printf("%03d. ", mItem.getID());
				System.out.printf(padRight(mItem.getName(),20));
				System.out.printf(padLeft( String.valueOf(count),5));
				System.out.printf(" unit(s)");
				System.out.println();
				
			}
			
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Total number of promotion items sold: ");
		System.out.println();
		for ( PromoItem pItem : menu.getPromoList()) {
			int count = 0;
			for ( PromoItem soldItem : promoItemsList) {
				
				if (pItem.getId() == soldItem.getId()) {
					count +=1;
				}
				
			}
			if (count != 0) {
				
				System.out.printf(" - ");
				System.out.printf("%03d. ", pItem.getId());
				System.out.printf(padRight(pItem.getName(),20));
				System.out.printf(padLeft( String.valueOf(count),5));
				System.out.printf(" unit(s)");
				System.out.println();
				
			}
		}
		
		System.out.println("-----------------------------------------------");
		System.out.println();
		

	}
	
	/**
	 * Generate Monthly report
	 */
	public void generateMonthlyReport() {

		Scanner dateScanner = new Scanner(System.in);
		int selectedIndex = -1;
		OrderList orderList = new OrderList();

		// Get date list from invoices
		List<String> dateList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
		DateTimeFormatter invFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Sort reservation list
		invoiceList.sort((o1, o2) -> LocalDateTime.parse(o1.getInvoiceTimeStamp(), invFormatObj)
				.compareTo(LocalDateTime.parse(o2.getInvoiceTimeStamp(), invFormatObj)));

		for (Invoice item : invoiceList) {
			LocalDateTime date = LocalDateTime.parse(item.getInvoiceTimeStamp(), invFormatObj);
			dateList.add(date.format(formatter));
		}
		List<String> dayList = dateList.stream().distinct().collect(Collectors.toList());

		// Print header
		System.out.println();
		System.out.println("------------Available Day Report-------------");
		System.out.println();
		for (int i = 0; i < dayList.size(); i++) {
			System.out.println(String.valueOf(i+1) + ". " + dayList.get(i));
		}
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println();
		
		//Get selected date
		while(selectedIndex == -1) {
			System.out.println();
			System.out.println("Please enter no. to view report: ");
			int index = dateScanner.nextInt();
			
			if (index - 1 < dayList.size()) {
				selectedIndex = index - 1;
			}else {
				System.out.println();
				System.out.println("Invaild selection, please try again...");
				System.out.println();
			}

		}
		
		//Filter Invoices based on date
		List<Invoice> filterInvoiceList = new ArrayList<Invoice>();
		List<Order> ordList = orderList.getOrderList();
		float totalDiscountLost = 0;
		float totalRevenue = 0;
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		List<PromoItem> promoItemsList = new ArrayList<PromoItem>();
		
		for (Invoice item : invoiceList) {
			LocalDateTime date = LocalDateTime.parse(item.getInvoiceTimeStamp(), invFormatObj);
			
			if (date.format(formatter).equals(dayList.get(selectedIndex) )) {
				filterInvoiceList.add(item);
				totalDiscountLost += item.getDiscountOfSubtotal();
				totalRevenue += item.getTotalPrice();
				
				Order order = ordList.stream().filter(a -> a.getID() == item.getOrderID()).collect(Collectors.toList()).get(0);
				for(MenuItem mitem : order.getMenuList()) {
					menuItemsList.add(mitem);
				}
				for(PromoItem pitem : order.getPromoList()) {
					promoItemsList.add(pitem);
				}
				
			}
		}
		
		
		
		//Print Report
		Menu menu = new Menu();
		System.out.println();
		System.out.println("--------------" + dayList.get(selectedIndex)  + " Report---------------");
		
		System.out.printf("Total Revenue: ");
		System.out.printf(padLeft(" ",20));
		System.out.printf("  $%.2f", totalRevenue);
		System.out.println();
		
		System.out.printf("Total lost from discount: ");
		System.out.printf(padLeft("-",12));
		System.out.printf("$%.2f", totalDiscountLost);
		System.out.println();
		
		System.out.println();
		System.out.println("Total number of items sold: ");
		System.out.println();
		for ( MenuItem mItem : menu.getMenuList()) {
			int count = 0;
			for ( MenuItem soldItem : menuItemsList) {
				
				if (mItem.getID() == soldItem.getID()) {
					count +=1;
				}
				
			}
			if (count != 0) {
				
				System.out.printf(" - ");
				System.out.printf("%03d. ", mItem.getID());
				System.out.printf(padRight(mItem.getName(),20));
				System.out.printf(padLeft( String.valueOf(count),5));
				System.out.printf(" unit(s)");
				System.out.println();
				
			}
			
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Total number of promotion items sold: ");
		System.out.println();
		for ( PromoItem pItem : menu.getPromoList()) {
			int count = 0;
			for ( PromoItem soldItem : promoItemsList) {
				
				if (pItem.getId() == soldItem.getId()) {
					count +=1;
				}
				
			}
			if (count != 0) {
				
				System.out.printf(" - ");
				System.out.printf("%03d. ", pItem.getId());
				System.out.printf(padRight(pItem.getName(),20));
				System.out.printf(padLeft( String.valueOf(count),5));
				System.out.printf(" unit(s)");
				System.out.println();
				
			}
		}
		
		System.out.println("-----------------------------------------------");
		System.out.println();
		

	}
	
	/**
	 * Generate Yearly report
	 */
	public void generateYearlyReport() {

		Scanner dateScanner = new Scanner(System.in);
		int selectedIndex = -1;
		OrderList orderList = new OrderList();

		// Get date list from invoices
		List<String> dateList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter invFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Sort reservation list
		invoiceList.sort((o1, o2) -> LocalDateTime.parse(o1.getInvoiceTimeStamp(), invFormatObj)
				.compareTo(LocalDateTime.parse(o2.getInvoiceTimeStamp(), invFormatObj)));

		for (Invoice item : invoiceList) {
			LocalDateTime date = LocalDateTime.parse(item.getInvoiceTimeStamp(), invFormatObj);
			dateList.add(date.format(formatter));
		}
		List<String> dayList = dateList.stream().distinct().collect(Collectors.toList());

		// Print header
		System.out.println();
		System.out.println("------------Available Day Report-------------");
		System.out.println();
		for (int i = 0; i < dayList.size(); i++) {
			System.out.println(String.valueOf(i+1) + ". " + dayList.get(i));
		}
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println();
		
		//Get selected date
		while(selectedIndex == -1) {
			System.out.println();
			System.out.println("Please enter no. to view report: ");
			int index = dateScanner.nextInt();
			
			if (index - 1 < dayList.size()) {
				selectedIndex = index - 1;
			}else {
				System.out.println();
				System.out.println("Invaild selection, please try again...");
				System.out.println();
			}

		}
		
		//Filter Invoices based on date
		List<Invoice> filterInvoiceList = new ArrayList<Invoice>();
		List<Order> ordList = orderList.getOrderList();
		float totalDiscountLost = 0;
		float totalRevenue = 0;
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		List<PromoItem> promoItemsList = new ArrayList<PromoItem>();
		
		for (Invoice item : invoiceList) {
			LocalDateTime date = LocalDateTime.parse(item.getInvoiceTimeStamp(), invFormatObj);
			
			if (date.format(formatter).equals(dayList.get(selectedIndex) )) {
				filterInvoiceList.add(item);
				totalDiscountLost += item.getDiscountOfSubtotal();
				totalRevenue += item.getTotalPrice();
				
				Order order = ordList.stream().filter(a -> a.getID() == item.getOrderID()).collect(Collectors.toList()).get(0);
				for(MenuItem mitem : order.getMenuList()) {
					menuItemsList.add(mitem);
				}
				for(PromoItem pitem : order.getPromoList()) {
					promoItemsList.add(pitem);
				}
				
			}
		}
		
		
		
		//Print Report
		Menu menu = new Menu();
		System.out.println();
		System.out.println("--------------" + dayList.get(selectedIndex)  + " Report---------------");
		
		System.out.printf("Total Revenue: ");
		System.out.printf(padLeft(" ",20));
		System.out.printf("  $%.2f", totalRevenue);
		System.out.println();
		
		System.out.printf("Total lost from discount: ");
		System.out.printf(padLeft("-",12));
		System.out.printf("$%.2f", totalDiscountLost);
		System.out.println();
		
		System.out.println();
		System.out.println("Total number of items sold: ");
		System.out.println();
		for ( MenuItem mItem : menu.getMenuList()) {
			int count = 0;
			for ( MenuItem soldItem : menuItemsList) {
				
				if (mItem.getID() == soldItem.getID()) {
					count +=1;
				}
				
			}
			if (count != 0) {
				
				System.out.printf(" - ");
				System.out.printf("%03d. ", mItem.getID());
				System.out.printf(padRight(mItem.getName(),20));
				System.out.printf(padLeft( String.valueOf(count),5));
				System.out.printf(" unit(s)");
				System.out.println();
				
			}
			
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Total number of promotion items sold: ");
		System.out.println();
		for ( PromoItem pItem : menu.getPromoList()) {
			int count = 0;
			for ( PromoItem soldItem : promoItemsList) {
				
				if (pItem.getId() == soldItem.getId()) {
					count +=1;
				}
				
			}
			if (count != 0) {
				
				System.out.printf(" - ");
				System.out.printf("%03d. ", pItem.getId());
				System.out.printf(padRight(pItem.getName(),20));
				System.out.printf(padLeft( String.valueOf(count),5));
				System.out.printf(" unit(s)");
				System.out.println();
				
			}
		}
		
		System.out.println("-----------------------------------------------");
		System.out.println();
		

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
