
package possystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handle all the menu related objects, AKA Menu Manager Add/Edit/Delete
 * Contains MenuItems, PromoItems and Category
 * 
 * @author Chong Hui Yee
 * @version 1.0
 * @since 2021-11-10
 */
public class Menu {

	/**
	 * The constant values for MenuItems.cvs file path.
	 */
	public static final String MENU_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/MenuItems.csv");
	/**
	 * The constant values for Categories.cvs file path.
	 */
	public static final String CATEGORY_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/Categories.csv");
	/**
	 * The constant values for PromoItems.cvs file path.
	 */
	public static final String PROMO_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/PromoItems.csv");

	/**
	 * The list of all the menu items.
	 */
	static List<MenuItem> menuList = new ArrayList<MenuItem>();
	/**
	 * The list of all the category.
	 */
	static List<Category> categoryList = new ArrayList<Category>();
	/**
	 * The list of all the promotion items.
	 */
	static List<PromoItem> promoList = new ArrayList<PromoItem>();

	/**
	 * getter: get Menu List
	 * 
	 * @return menuList
	 */
	public List<MenuItem> getMenuList() {
		return Menu.menuList;
	}

	/**
	 * getter: get Promo List
	 * 
	 * @return promoList
	 */
	public List<PromoItem> getPromoList() {
		return Menu.promoList;
	}

	/**
	 * Create a class constructor for the Menu class Initialisation include fetching
	 * item from csv to variable.
	 */
	public Menu() {
		// Convert category item from data to object
		parseCategoryCSVtoObject();
		// Convert menu item from data to object
		parseMenuItemCSVtoObject();
		// Convert promo item from data to object
		parsePromoItemCSVtoObject();
	}

	// *******************************************************************************************
	// *******************************************************************************************
	// MENU
	// *******************************************************************************************
	// *******************************************************************************************

	/**
	 * Read menu item from .csv file and convert line to MenuItem object and save to
	 * array.
	 */
	public static void parseMenuItemCSVtoObject() {
		// Parsing a MenuItems.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			menuList.clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(MENU_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);
				// Convert String array to MenuItem object & add to MenuList
				MenuItem menuItem = new MenuItem(Integer.parseInt(item[0]), item[1].replace("|", ","),
						item[2].replace("|", ","), Float.parseFloat(item[3]), item[4].equals("1") ? true : false,
						Integer.parseInt(item[5]));
				menuList.add(menuItem);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print all MenuItem in menuList
	 */
	public void printAllMenuItems() {

		// Print Menu Header
		System.out.println();
		System.out.println("__________________________MENU LIST___________________________");
		System.out.println();

		for (Category cat : categoryList) {
			if (cat.isAvailable) {
				// Print Category Header
				System.out.printf(padLeft("*", Math.round(60 - cat.name.length()) / 2));
				System.out.printf(cat.name);
				System.out.printf(padRight("*", Math.round(60 - cat.name.length()) / 2));
				System.out.println();
				// Print Menu Items
				for (MenuItem item : menuList) {
					// Print menu item only if it is in the category
					if (cat.id == item.categoryId && item.isAvailable) {
						item.printMenuItem();
					}
				}
				System.out.println();
			}
		}
		// Print Menu Footer
		System.out.println("______________________________________________________________");

	}

	/**
	 * Add a new MenuItem into menuList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void addNewMenuItem() throws IOException {
		// Temp Variables
		String name;
		String description;
		float price;
		int catId;

		// Get all inputs from user
		Scanner newScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Adding New Menu Item... ");
		System.out.println("Please enter item's name: ");
		name = newScanner.nextLine();
		System.out.println("Please enter item's description: ");
		description = newScanner.nextLine();
		System.out.println("Please enter item's price: ");
		price = newScanner.nextFloat();
		System.out.println();
		// Print Category List
		printCategoryList();
		System.out.println("Please enter item's category no: ");
		catId = newScanner.nextInt();

		// Change "," to "|" for save in Csv
		// Add new menu item to menuList
		MenuItem menuItem = new MenuItem(menuList.size() + 1, name.replace(",", "|"), description.replace(",", "|"),
				price, true, catId);
		menuList.add(menuItem);

		// Save new menu item to csv
		List<String> mlist = new ArrayList<>();

		for (MenuItem item : menuList) {
			mlist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_CSV_FILE_PATH))) {
			for (String line : mlist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the updated item
		System.out.println();
		System.out.println("******************************************************************");
		if (menuItem != null)
			menuItem.printMenuItem();
		System.out.println("Item sucessfully added!");
		System.out.println("******************************************************************");

	}

	/**
	 * Edit a existing MenuItem in menuList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void editMenuItem() throws IOException {
		// Temp Variables
		int itemId;
		Boolean didFind = false;
		MenuItem updatedItem = null;
		
		if (menuList.size() == 0) {
			System.out.println();
			System.out.println("No menu items to be edited...");
			return;
		}

		// Print menu item for user selection
		printAllMenuItems();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			// Get menu item id input from user
			System.out.println();
			System.out.println("Enter the item no. to be edited...");
			System.out.printf("Please enter item's id: ");
			itemId = updateScanner.nextInt();
			// Filter out the menu item based on user input
			for (MenuItem item : menuList) {
				if (item.id == itemId && item.isAvailable) {
					// Display the item to be edited
					System.out.println();
					System.out.println("Item to be edited...");
					item.printMenuItem();

					// Request update name
					System.out.printf("Please enter updated item's name: ");
					item.setName(scanner.nextLine().replace(",", "|"));
					System.out.println();
					// Request update description
					System.out.printf("Please enter updated item's description: ");
					item.setDescription(scanner.nextLine().replace(",", "|"));
					System.out.println();
					// Request update price
					System.out.printf("Please enter updated item's price: ");
					item.setPrice(scanner.nextFloat());
					System.out.println();
					// Print Category List
					printCategoryList();
					// Request update category id
					System.out.printf("Please enter updated item's category no: ");
					item.setCategory(scanner.nextInt());
					// Update variable indicating that item did exist in MenuList
					didFind = true;
					updatedItem = item;
					break;
				}
			}
			// If unable to find item in menu list, ask user to key in valid item id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid menu item, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save updated menu item to csv
		List<String> mlist = new ArrayList<>();

		for (MenuItem item : menuList) {
			mlist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_CSV_FILE_PATH))) {
			for (String line : mlist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the updated item
		System.out.println();
		System.out.println("******************************************************************");
		if (updatedItem != null)
			updatedItem.printMenuItem();
		System.out.println("Item sucessfully updated!");
		System.out.println("******************************************************************");
	}

	/**
	 * Delete a existing MenuItem in menuList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void deleteMenuItem() throws IOException {
		// Temp Variables
		int itemId;
		Boolean didFind = false;
		MenuItem deletedItem = null;
		
		if (menuList.size() == 0) {
			System.out.println();
			System.out.println("No menu items to be deleted...");
			return;
		}

		// Print menu item for user selection
		printAllMenuItems();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			// Get menu item id input from user
			System.out.println();
			System.out.println("Enter the item no. to be deleted...");
			System.out.printf("Please enter item's id: ");
			itemId = updateScanner.nextInt();
			// Filter out the menu item based on user input
			for (MenuItem item : menuList) {
				if (item.id == itemId && item.isAvailable) {
					// Display the item to be edited
					System.out.println();
					System.out.println("Item to be deleted...");
					item.printMenuItem();

					// Request confirmation
					System.out.println();
					System.out.printf("Are you sure to delete item? (Y/N): ");
					String confirmation = scanner.nextLine();
					System.out.println(confirmation);
					if (confirmation.equals("N")) {
						// Return to previous menu
						updateScanner.close();
						scanner.close();
						return;
					} else if (confirmation.equals("Y")) {
						didFind = true;
						deletedItem = item;
						// Update variable indicating that item did exist in MenuList
						item.setIsAvailable(false);
					}
					break;
				}
			}
			// If unable to find item in menu list, ask user to key in valid item id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid menu item, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save updated menu item to csv
		List<String> mlist = new ArrayList<>();

		for (MenuItem item : menuList) {
			mlist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_CSV_FILE_PATH))) {
			for (String line : mlist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the deleted item
		System.out.println();
		System.out.println("******************************************************************");
		if (deletedItem != null)
			deletedItem.printMenuItem();
		System.out.println("Item sucessfully deleted!");
		System.out.println("******************************************************************");

	}

	// *******************************************************************************************
	// *******************************************************************************************
	// Promotion Items
	// *******************************************************************************************
	// *******************************************************************************************

	/**
	 * Read promo item from .csv file and convert line to PromoItem object and save
	 * to array
	 * 
	 */
	public static void parsePromoItemCSVtoObject() {
		// Parsing a PromoItems.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			promoList.clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(PROMO_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);
				// Get MenuItem array from string
				List<MenuItem> mList = new ArrayList<MenuItem>();
				String[] mStringList = item[5].split("\\|");
				// Search in menuList for matching id and add into mList
				for (MenuItem menuItem : menuList) {
					for (String menuString : mStringList) {
						if (!menuString.equals("") && menuItem.id == Integer.parseInt(menuString)) {
							mList.add(menuItem);
						}
					}
				}

				// Convert String array to PromoItem object & add to PromoList
				PromoItem promoItem = new PromoItem(Integer.parseInt(item[0]), item[1].replace("|", ","),
						item[2].replace("|", ","), Float.parseFloat(item[3]), item[4].equals("1") ? true : false);
				promoItem.setItems(mList);
				promoList.add(promoItem);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print all MenuItem in menuList
	 */
	public void printAllPromoItems() {

		// Print Promo Header
		System.out.println();
		System.out.println("_______________________________PROMOTION LIST_______________________________");
		System.out.println();

		// Print Promotion Items
		for (PromoItem item : promoList) {
			// Print menu item only if it is in the category
			if (item.isAvailable) {
				item.printPromoItem();
			}
		}

		// Print Promo Footer
		System.out.println("____________________________________________________________________________");

	}

	/**
	 * Add a new PromoItem into promoList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void addNewPromoItem() throws IOException {
		// Temp Variables
		String name;
		String description;
		float price;
		Boolean isCompleted = false;
		List<MenuItem> mList = new ArrayList<MenuItem>();

		// Get all inputs from user
		Scanner newScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Adding New promotion item... ");
		System.out.println("Please enter promotion's name: ");
		name = newScanner.nextLine();
		System.out.println("Please enter promotion's description: ");
		description = newScanner.nextLine();
		System.out.println("Please enter promotion's price: ");
		price = newScanner.nextFloat();
		System.out.println();
		// Print existing menu to user
		printAllMenuItems();
		System.out.println("Add menu item for promotion... ");
		// Ask user input menu items id until user is satisfied
		while (!isCompleted) {
			Boolean didFind = false;
			// Request for menu item id
			System.out.println("Please enter menu item's id no: ");
			int itemID = newScanner.nextInt();

			// Check if itemID is valid in menuList
			for (MenuItem menuItem : menuList) {
				// If menu item is valid, add to mList
				if (menuItem.id == itemID && menuItem.isAvailable) {
					// Update didFind variable, this show that itemID is valid
					didFind = true;
					mList.add(menuItem);
					System.out.println(menuItem.name + " is added to Promotion - " + name);
				}
			}

			if (!didFind) {
				// if itemID cannot be found, request user to try again
				System.out.println("** Invalid menu item's id no, please try again... ");
			} else {
				Boolean isValidConfirmation = false;

				while (!isValidConfirmation) {
					// else if itemID was found, request user if need to add another item
					System.out.println("Do you want to add another menu item? (Y/N)");
					Scanner conScanner = new Scanner(System.in);
					String confirmation = conScanner.nextLine();
					if (confirmation.equals("N")) {
						// Completed adding of menu items
						isValidConfirmation = true;
						isCompleted = true;
					} else if (confirmation.equals("Y")) {
						isValidConfirmation = true;
					} else {
						System.out.println("** Invalid input please try again... ");
					}
				}
			}
		}

		// Change "," to "|" for save in Csv
		// Add new promo item to promoList
		PromoItem promoItem = new PromoItem(promoList.size() + 1001, name.replace(",", "|"),
				description.replace(",", "|"), price, true);
		promoItem.setItems(mList);
		promoList.add(promoItem);

		// Save new promo item to csv
		List<String> plist = new ArrayList<>();

		for (PromoItem item : promoList) {
			plist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(PROMO_CSV_FILE_PATH))) {
			for (String line : plist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the new added promotion item
		System.out.println();
		System.out.println("******************************************************************");
		if (promoItem != null)
			promoItem.printPromoItem();
		System.out.println("Promotion sucessfully added!");
		System.out.println("******************************************************************");

	}

	/**
	 * Edit a existing PromoItem in promoList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void editPromoItem() throws IOException {
		// Temp Variables
		int itemId;
		Boolean didFind = false;
		PromoItem updatedItem = null;
		Boolean isCompleted = false;
		List<MenuItem> mList = new ArrayList<MenuItem>();
		
		if (promoList.size() == 0) {
			System.out.println();
			System.out.println("No promotion items to be edited...");
			return;
		}

		// Print promo item for user selection
		printAllPromoItems();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			// Get promo item id input from user
			System.out.println();
			System.out.println("Enter the promotion no. to be edited...");
			System.out.printf("Please enter promotion's id: ");
			itemId = updateScanner.nextInt();
			// Filter out the promo item based on user input
			for (PromoItem item : promoList) {
				if (item.id == itemId && item.isAvailable) {
					// Display the promotion to be edited
					System.out.println();
					System.out.println("Promotion to be edited...");
					item.printPromoItem();

					// Request update name
					System.out.printf("Please enter updated promotion's name: ");
					item.setName(scanner.nextLine().replace(",", "|"));
					System.out.println();
					// Request update description
					System.out.printf("Please enter updated promotion's description: ");
					item.setDescription(scanner.nextLine().replace(",", "|"));
					System.out.println();
					// Request update price
					System.out.printf("Please enter updated promotion's price: ");
					item.setPrice(scanner.nextFloat());
					System.out.println();

					// Print existing menu to user
					printAllMenuItems();
					System.out.println("Add menu item for promotion... ");
					// Ask user input menu items id until user is satisfied
					while (!isCompleted) {
						Boolean didFindMenuItem = false;
						// Request for menu item id
						System.out.println("Please enter menu item's id no: ");
						int itemID = scanner.nextInt();

						// Check if itemID is valid in menuList
						for (MenuItem menuItem : menuList) {
							// If menu item is valid, add to mList
							if (menuItem.id == itemID && menuItem.isAvailable) {
								// Update didFind variable, this show that itemID is valid
								didFind = true;
								mList.add(menuItem);
								System.out.println(menuItem.name + " is added to Promotion - " + item.name);
							}
						}

						if (!didFind) {
							// if itemID cannot be found, request user to try again
							System.out.println("** Invalid menu item's id no, please try again... ");
						} else {
							Boolean isValidConfirmation = false;

							while (!isValidConfirmation) {
								// else if itemID was found, request user if need to add another item
								System.out.println("Do you want to add another menu item? (Y/N)");
								Scanner conScanner = new Scanner(System.in);
								String confirmation = conScanner.nextLine();
								if (confirmation.equals("N")) {
									// Completed adding of menu items
									isValidConfirmation = true;
									isCompleted = true;
								} else if (confirmation.equals("Y")) {
									isValidConfirmation = true;
								} else {
									System.out.println("** Invalid input please try again... ");
								}
							}
						}
					}

					// Update variable indicating that item did exist in MenuList
					didFind = true;
					updatedItem = item;
					break;
				}
			}
			// If unable to find item in promo list, ask user to key in valid item id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid promotion item, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save new promo item to csv
		List<String> plist = new ArrayList<>();

		for (PromoItem item : promoList) {
			plist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(PROMO_CSV_FILE_PATH))) {
			for (String line : plist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the new added promotion item
		System.out.println();
		System.out.println("******************************************************************");
		if (updatedItem != null)
			updatedItem.printPromoItem();
		System.out.println("Promotion sucessfully updated!");
		System.out.println("******************************************************************");
	}

	/**
	 * Delete a existing PromoItem in promoList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void deletePromoItem() throws IOException {
		// Temp Variables
		int itemId;
		Boolean didFind = false;
		PromoItem deletedItem = null;
		
		if (promoList.size() == 0) {
			System.out.println();
			System.out.println("No promotion items to be deleted...");
			return;
		}

		// Print Promo item for user selection
		printAllPromoItems();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			// Get promo item id input from user
			System.out.println();
			System.out.println("Enter the promotion no. to be deleted...");
			System.out.printf("Please enter promotion id: ");
			itemId = updateScanner.nextInt();
			// Filter out the promo item based on user input
			for (PromoItem item : promoList) {
				if (item.id == itemId && item.isAvailable) {
					// Display the item to be edited
					System.out.println();
					System.out.println("Promotion to be deleted...");
					item.printPromoItem();

					// Request confirmation
					System.out.println();
					System.out.printf("Are you sure to delete promotion? (Y/N): ");
					String confirmation = scanner.nextLine();
					System.out.println(confirmation);
					if (confirmation.equals("N")) {
						// Return to previous menu
						updateScanner.close();
						scanner.close();
						return;
					} else if (confirmation.equals("Y")) {
						didFind = true;
						deletedItem = item;
						// Update variable indicating that promotion did exist in promoList
						item.setIsAvailable(false);
					}
					break;
				}
			}
			// If unable to find promotion in promplist, ask user to key in valid promotion
			// id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid promotion, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save updated promo list to csv
		List<String> plist = new ArrayList<>();

		for (PromoItem item : promoList) {
			plist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(PROMO_CSV_FILE_PATH))) {
			for (String line : plist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the new added promotion item
		System.out.println();
		System.out.println("******************************************************************");
		if (deletedItem != null)
			deletedItem.printPromoItem();
		System.out.println("Promotion deleted successfully!");
		System.out.println("******************************************************************");

	}

	// *******************************************************************************************
	// *******************************************************************************************
	// CATEGORY
	// *******************************************************************************************
	// *******************************************************************************************

	/**
	 * Print all category item from categoryList
	 */
	public void printCategoryList() {
		System.out.println("__________________________CATEGORY LIST___________________________");
		System.out.println();
		for (Category cat1 : categoryList) {
			cat1.printCategory();
		}
		System.out.println("___________________________________________________________________");
	}

	/**
	 * Read category item from .csv file and convert line to Category object and
	 * save to array
	 */
	public static void parseCategoryCSVtoObject() {
		// Parsing a Categories.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			categoryList.clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(CATEGORY_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.split(splitBy);
				// Convert String array to MenuItem object & add to MenuList
				Category cat = new Category(Integer.parseInt(item[0]), item[1], item[2].equals("1") ? true : false);
				categoryList.add(cat);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a new category into categoryList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void addNewCategory() throws IOException {
		// Temp Variables
		String name;

		// Get all inputs from user
		Scanner newScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Adding New Category... ");
		System.out.println("Please enter category's name: ");
		name = newScanner.nextLine();

		// Change "," to "|" for save in Csv
		// Add new category to categoryList
		Category category = new Category(categoryList.size() + 1, name.replace(",", "|"), true);
		categoryList.add(category);

		// Save new category to csv
		List<String> clist = new ArrayList<>();

		for (Category cat : categoryList) {
			clist.add(cat.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CATEGORY_CSV_FILE_PATH))) {
			for (String line : clist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the added category
		System.out.println();
		System.out.println("******************************************************************");
		if (category != null)
			category.printCategory();
		System.out.println("Category sucessfully added!");
		System.out.println("******************************************************************");

	}

	/**
	 * Edit a existing category in categoryList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void editCategory() throws IOException {
		// Temp Variables
		int catId;
		Boolean didFind = false;
		Category updatedCat = null;
		
		if (categoryList.size() == 0) {
			System.out.println();
			System.out.println("No category to be deleted...");
			return;
		}

		// Print categories for user selection
		printCategoryList();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			// Get category id input from user
			System.out.println();
			System.out.println("Enter the category no. to be edited...");
			System.out.printf("Please enter category's id: ");
			catId = updateScanner.nextInt();
			// Filter out the menu item based on user input
			for (Category cat : categoryList) {
				if (cat.id == catId) {
					// Display the category to be edited
					System.out.println();
					System.out.println("Category to be edited...");
					cat.printCategory();

					// Request update name
					System.out.printf("Please enter updated category's name: ");
					cat.setName(scanner.nextLine().replace(",", "|"));
					// Update variable indicating that item did exist in categoryList
					didFind = true;
					updatedCat = cat;
					break;
				}
			}
			// If unable to find category in category list, ask user to key in valid
			// category id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid category id, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save new updated category to csv
		List<String> clist = new ArrayList<>();

		for (Category cat : categoryList) {
			clist.add(cat.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CATEGORY_CSV_FILE_PATH))) {
			for (String line : clist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the updated category
		System.out.println();
		System.out.println("******************************************************************");
		if (updatedCat != null)
			updatedCat.printCategory();
		System.out.println("Category sucessfully updated!");
		System.out.println("******************************************************************");
	}

	/**
	 * Delete a existing category in categoryList and save in csv
	 * 
	 * @throws IOException exception
	 */
	public void deleteCategory() throws IOException {
		// Temp Variables
		int catId;
		Boolean didFind = false;
		Category deletedCat = null;
		
		if (promoList.size() == 0) {
			System.out.println();
			System.out.println("No promotion items to be edited...");
			return;
		}

		// Print categories for user selection
		printCategoryList();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			// Get category id input from user
			System.out.println();
			System.out.println("Enter the category no. to be deleted...");
			System.out.printf("Please enter category's id: ");
			catId = updateScanner.nextInt();
			// Filter out the category based on user input
			for (Category cat : categoryList) {
				if (cat.id == catId) {
					// Display the category to be edited
					System.out.println();
					System.out.println("Category to be deleted...");
					cat.printCategory();

					// Request confirmation
					System.out.println();
					System.out.printf("Are you sure to delete category and all menu item in category? (Y/N): ");
					String confirmation = scanner.nextLine();
					System.out.println(confirmation);
					if (confirmation.equals("N")) {
						// Return to previous menu
						updateScanner.close();
						scanner.close();
						return;
					} else if (confirmation.equals("Y")) {
						didFind = true;
						deletedCat = cat;
						// Update variable indicating that category did exist in categoryList
						cat.setIsAvailable(false);

						// Delete menu item in category too
						for (MenuItem item : menuList) {
							// Delete menu item only if it is in the category
							if (cat.id == item.categoryId && item.isAvailable) {
								item.setIsAvailable(false);
							}
						}
						System.out.println();

					}
					break;
				}
			}
			// If unable to find category in category list, ask user to key in valid
			// category id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid category, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save new updated category to csv
		List<String> clist = new ArrayList<>();

		for (Category cat : categoryList) {
			clist.add(cat.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CATEGORY_CSV_FILE_PATH))) {
			for (String line : clist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Save updated menu item to csv
		List<String> mlist = new ArrayList<>();

		for (MenuItem item : menuList) {
			mlist.add(item.convertObjectToCsvString());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_CSV_FILE_PATH))) {
			for (String line : mlist) {
				bw.write(line);
				bw.newLine();
			}
		}

		// Show user the deleted item
		System.out.println();
		System.out.println("******************************************************************");
		if (deletedCat != null)
			deletedCat.printCategory();
		System.out.println("Category sucessfully deleted!");
		System.out.println("******************************************************************");

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
