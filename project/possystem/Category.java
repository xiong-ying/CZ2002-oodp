package possystem;

/**
 * Represents the categories in the menu. Category object which is use to group
 * menu items.
 * 
 * @author Chong Hui Yee
 * @version 1.0
 * @since 2021-11-32
 */
public class Category {
	/**
	 * The unique id for each Category.
	 */
	int id;
	/**
	 * The name for the Category.
	 */
	String name;
	/**
	 * The availability for the Category. (To indicate if the item is deleted or
	 * not)
	 */
	Boolean isAvailable;

	/**
	 * Create a class constructor for the Category class
	 * 
	 * @param id          This Category's unique id.
	 * @param name        This Category's name.
	 * @param isAvailable The availability for the Category'.
	 */
	public Category(int id, String name, Boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.isAvailable = isAvailable;
	}

	/**
	 * Take category object and convert object to cvs string format.
	 * 
	 * @return this string representation of the object.
	 */
	public String convertObjectToCsvString() {
		String[] record = { String.valueOf(id), name, isAvailable ? "1" : "0" };
		String result = new String(String.valueOf(id) + "," + name.replace(",", "|") + "," + (isAvailable ? "1" : "0"));
		return result;
	}

	/**
	 * Print the category item in format.
	 */
	public void printCategory() {
		System.out.printf("%03d. ", id);
		System.out.printf(padRight(name, 50));
		System.out.println();
		System.out.println();
	}

	/**
	 * Sets the name of this Category.
	 * 
	 * @param name This Category's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the availability of this MenuItem.
	 * 
	 * @param isAvailable This Category's availability.
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the right.
	 * 
	 * @return the padded string.
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
	 * @return the padded string.
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}