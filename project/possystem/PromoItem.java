package possystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a promotion in the menu. Each promotion can contain multiple menu
 * items.
 * 
 * @author Chong Hui Yee
 * @version 1.0
 * @since 2021-11-32
 */
public class PromoItem {
	/**
	 * The unique id for each menu item.
	 */
	int id;
	/**
	 * The name for the menu item.
	 */
	String name;
	/**
	 * The description for the menu item.
	 */
	String description;
	/**
	 * The price for the menu item.
	 */
	float price;
	/**
	 * The availability for the menu item. (To indicate if the item is deleted or
	 * not)
	 */
	Boolean isAvailable;
	/**
	 * The list of MenuItems that belong to this PromoItem.
	 */
	List<MenuItem> items;

	/**
	 * Create a class constructor for the PromoItem class
	 * 
	 * @param id          This PromoItem's unique id.
	 * @param name        This PromoItem's name.
	 * @param description This PromoItem's description.
	 * @param price       This PromoItem's price.
	 * @param isAvailable The availability for the PromoItem.
	 */
	public PromoItem(int id, String name, String description, float price, Boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	/**
	 * Take PromoItem object and convert object to cvs string format
	 * 
	 * @return this string representation of the object.
	 */
	public String convertObjectToCsvString() {
		String result = new String(String.valueOf(id) + "," + name.replace(",", "|") + ","
				+ description.replace(",", "|") + "," + String.valueOf(price) + "," + (isAvailable ? "1" : "0") + ",");

		for (MenuItem mItem : items) {
			result = result + mItem.id + "|";
		}

		return result;
	}

	/**
	 * Get menu item list from PromoItem
	 * 
	 * @return this list of menu items.
	 */
	public List<MenuItem> getItems() {
		return items;
	}

	/**
	 * Set menu item list from PromoItem
	 * 
	 * @param items list of menu items.
	 */
	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	/**
	 * Print the PromoItem in format
	 */
	public void printPromoItem() {
		System.out.printf("%03d. ", id);
		System.out.printf(padRight(name.replace("|", ","), 60));
		System.out.printf("  $%.2f", price);
		System.out.println();
		for (MenuItem menuItem : items) {
			System.out.printf("     ");
			System.out.printf("* ");
			System.out.printf("%03d. ", menuItem.id);
			System.out.printf(padRight(menuItem.name.replace("|", ","), 20));
			System.out.println();
		}
		System.out.printf("    ");
		System.out.printf(description.replace("|", ","));
		System.out.println();
		System.out.println();
	}

	/**
	 * Sets the name of this PromoItem.
	 * 
	 * @param name This PromoItem's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description of this PromoItem.
	 * 
	 * @param description This PromoItem's description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the price of this PromoItem.
	 * 
	 * @param price This PromoItem's price.
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * Sets the availability of this PromoItem.
	 * 
	 * @param isAvailable This PromoItem's availability.
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the right.
	 * @return the padded string.
	 */
	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Pad string with spaces on left
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the left.
	 * @return the padded string.
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}
