
package possystem;

/**
 * Represents a item in the menu. Menu item can be part of promotion item.
 * 
 * @author Chong Hui Yee
 * @version 1.0
 * @since 2021-11-32
 */
public class MenuItem {
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
	 * The category id for the category the item belongs to.
	 */
	int categoryId;

	/**
	 * Create a class constructor for the MenuItem class
	 * 
	 * @param id          This MenuItem's unique id.
	 * @param name        This MenuItem's name.
	 * @param description This MenuItem's description.
	 * @param price       This MenuItem's price.
	 * @param isAvailable The availability for the menu item.
	 * @param categoryId  This MenuItem's categoryId.
	 */
	public MenuItem(int id, String name, String description, float price, Boolean isAvailable, int categoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.categoryId = categoryId;
	}

	/**
	 * Take menu item and convert object to cvs string format
	 * 
	 * @return this string representation of the object.
	 */
	public String convertObjectToCsvString() {
		String result = new String(
				String.valueOf(id) + "," + name.replace(",", "|") + "," + description.replace(",", "|") + ","
						+ String.valueOf(price) + "," + (isAvailable ? "1" : "0") + "," + String.valueOf(categoryId));
		return result;
	}

	/**
	 * Print menu item
	 */
	public void printMenuItem() {
		System.out.printf("%03d. ", id);
		System.out.printf(padRight(name.replace("|", ","), 50));
		System.out.printf("  $%.2f", price);
		System.out.println();
		System.out.printf("     ");
		System.out.printf(description.replace("|", ","));
		System.out.println();
		System.out.println();
	}

	/**
	 * Gets the id of this MenuItem.
	 * 
	 * @return this MenuItem's id.
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Sets the name of this MenuItem.
	 * 
	 * @param name This MenuItem's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description of this MenuItem.
	 * 
	 * @param description This MenuItem's description.
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * Sets the price of this MenuItem.
	 * 
	 * @param price This MenuItem's price.
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * Sets the availability of this MenuItem.
	 * 
	 * @param isAvailable This MenuItem's availability.
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Sets the categoryId of this MenuItem.
	 * 
	 * @param categoryId This MenuItem's categoryId.
	 */
	public void setCategory(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the right.
	 * @return paddedString
	 */
	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * Pad string with spaces on left
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the left.
	 * 
	 * @return paddedString
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}
