package possystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An invoice printer is used to print invoice whenever customer makes payment.
 * Invoice printer prints out details of InvoiceID, invoiceTimeStamp, Server ID,
 * Table Number, Pax, Membership status, payment status. Invoice printer also
 * prints out detailed breakdown of items being ordered and the corresponding
 * price, subtotalPrice, discount, GST, serviceCharge and totalPrice.
 * 
 * @author sylvia
 * @version 1.0
 * @since 2021-11-08
 */
public class InvoicePrinterMessage {

	public static List<String> invoiceList = new ArrayList<>();
	/**
	 * The list of all the invoices =.
	 */
	public static List<Invoice> invoiceObjList = new ArrayList<Invoice>();

	/**
	 * The constant values for InvoiceList.cvs file path.
	 * Read Data of Orders From File Name: InvoiceList.csv
	 * Column 1: InvoiceID
	 * Column 2: TimeStamp
	 * Column 3: Sub total Price
	 * Column 4: DiscountOfSubtotal
	 * Column 5: GSTOfSubtotal
	 * Column 6: Service ChargeOfSubtotal
	 * */
	final String INVOICE_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/InvoiceList.csv");
	
	/**
	 * Create a class constructor for InvoicePrinterMessage Class.
	 */
	public InvoicePrinterMessage() {
		// Parsing a InvoiceList.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			invoiceList.clear();
			getInvoiceObjList().clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(INVOICE_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);
				// Convert String array to Invoice object & add to invoiceList
				Invoice invoiceItem = new Invoice(item[0], item[1], Float.parseFloat(item[2]), Float.parseFloat(item[3]), Float.parseFloat(item[4]),Float.parseFloat(item[5]), Float.parseFloat(item[6]),Integer.parseInt(item[7]));
				getInvoiceObjList().add(invoiceItem);
				invoiceList.add(line);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * PrintInvoiceMethod Invoice printer prints out details of InvoiceID,
	 * invoiceTimeStamp, Server ID, Table Number, Pax, Membership status, payment
	 * status. Invoice printer also prints out detailed breakdown of items being
	 * ordered and the corresponding price, subtotalPrice, discount, GST,
	 * serviceCharge and totalPrice.
	 */
	public void printInvoice(String invoiceTimeStamp, Order obj_order) {
		System.out.println("*************************************************************************");
		System.out.println("INVOICE																	");
		System.out.println("*************************************************************************");

		String invoiceIDTimeStamp = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		String invoiceID = invoiceIDTimeStamp + String.valueOf(obj_order.getID());

		System.out.printf("InvoiceID 	: " + invoiceID);
		System.out.printf("\n");
		System.out.println();
		System.out.println("Time		: " + invoiceTimeStamp);

		System.out.println("Server Name	: " + obj_order.getServerID());
		int serverId = obj_order.getServerID();

		System.out.println("Table Number	: " + obj_order.getTableNum());
		int tableNumber = obj_order.getTableNum();

		System.out.println("No. of cust	: " + obj_order.getPax());
		int pax = obj_order.getPax();

		String member = obj_order.getIsMember() ? "Member" : "Non-Member";
		System.out.println("Membership	: " + member);

		String payment = obj_order.getIsPaid() ? "Paid" : "Unpaid";
		System.out.println("Payment		: " + payment);

		System.out.println();
		System.out.println();
		for (MenuItem menuItem : obj_order.getMenuList()) {
			System.out.println(menuItem.getID() + " " + menuItem.getName() + "		: $ " + menuItem.getPrice());
		}
		for (PromoItem promoItem : obj_order.getPromoList()) {
			System.out.println(promoItem.getId() + " " + promoItem.getName() + "		: $ " + promoItem.getPrice());
		}

		System.out.println();
		System.out.println("=========================================================================");

		System.out.printf("Subtotal    		: $%.2f ", obj_order.getInvoiceObj().getSubtotalPrice());
		System.out.printf("\n");
		// System.out.println("Subtotal : $ "+
		// obj_order.getInvoiceObj().getSubtotalPrice());

		System.out.printf("Discount    		:-$%.2f ", obj_order.getInvoiceObj().getDiscountOfSubtotal());
		System.out.printf("\n");
		// System.out.println("Discount :-$ "+
		// obj_order.getInvoiceObj().getDiscountOfSubtotal());

		System.out.printf("GST       		: $%.2f ", obj_order.getInvoiceObj().getGSTOfSubtotal());
		System.out.printf("\n");
		// System.out.println("GST : $ "+ obj_order.getInvoiceObj().getGSTOfSubtotal());

		System.out.printf("Service Tax 		: $%.2f ", obj_order.getInvoiceObj().getServiceChargeOfSubtotal());
		System.out.printf("\n");
		// System.out.println("Service Tax : $ "+
		// obj_order.getInvoiceObj().getServiceChargeOfSubtotal());

		System.out.println("*************************************************************************");

		System.out.printf("TOTAL       		: $%.2f ", obj_order.getInvoiceObj().getTotalPrice());
		System.out.printf("\n");
		// System.out.println("TOTAL : $ "+ obj_order.getInvoiceObj().getTotalPrice());
		System.out.println("*************************************************************************");

		/**
		 * Take Invoice object and convert object to csv string format results Add
		 * invoice object to invoiceList
		 */

		String result = new String(
				invoiceID + "," + invoiceTimeStamp + "," + String.valueOf(obj_order.getInvoiceObj().getSubtotalPrice())
						+ "," + String.valueOf(obj_order.getInvoiceObj().getDiscountOfSubtotal()) + ","
						+ String.valueOf(obj_order.getInvoiceObj().getGSTOfSubtotal()) + ","
						+ String.valueOf(obj_order.getInvoiceObj().getServiceChargeOfSubtotal()) + ","
						+ String.valueOf(obj_order.getInvoiceObj().getTotalPrice())) + "," + String.valueOf(obj_order.getID());

		invoiceList.add(result);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(INVOICE_CSV_FILE_PATH))) {
			for (String line : invoiceList) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Invoice> getInvoiceObjList() {
		return invoiceObjList;
	}

}
