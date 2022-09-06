package possystem;

/**
 * A staff is a person who works in the restaurant. A staff can create an order.
 * 
 * @author xiongying
 * @version 1.0
 * @since 2021-11-08
 */
public class Staff {

	/**
	 * Private Variables
	 */

	/**
	 * The ID of this Staff
	 */
	private int id;

	/**
	 * The full name of this Staff, includes first and last name
	 */
	private String name;

	/**
	 * Gender of this Staff, either "male" or "female"
	 */
	private String gender;

	/**
	 * Job title of this Staff
	 */
	private String jobTitle;

	/**
	 * The working status of this Staff, whether he/she is at work or not true: at
	 * work; false: not at work
	 */
	private boolean isWorking;

	/**
	 * Constructor Creates a new Staff with it's ID, name, gender, job title.
	 * 
	 * @param i  Staff ID
	 * @param n  Staff name
	 * @param g  Staff gender
	 * @param jt Staff job title
	 */
	Staff(int i, String n, String g, String jt, boolean w) {
		this.id = i;
		this.name = n;
		this.gender = g;
		this.jobTitle = jt;
		this.isWorking = w;
	}

	/**
	 * Methods
	 */

	/**
	 * Convert staff object to csv string format
	 * 
	 * @return csv format string of staff object
	 */
	public String convertObjectToCsvString() {
		String result = new String(String.valueOf(id) + "," + name.replace(",", "|") + "," + gender.replace(",", "|")
				+ "," + (isWorking ? "1" : "0"));
		return result;
	}

	/**
	 * print a staff's detail, including ID, name, gender, job title, working
	 * status.
	 */
	public void printDetail() {

		System.out.printf("Staff ID: %03d", id);
		System.out.printf("\n");
		System.out.println("Name: " + name.replace("|", ","));
		System.out.println("Gender: " + gender.replace("|", ","));
		System.out.println("Title :" + jobTitle);
		String workStatus = isWorking ? "yes" : "no";
		System.out.println("At Work:" + workStatus);
		System.out.println(" ");
	}

	/**
	 * Get the ID of this Staff
	 * 
	 * @return this Staff's ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get the full name of this Staff
	 * 
	 * @return this Staff's full name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get staff's gender
	 * 
	 * @return this Staff's gender
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * Get the job title of this Staff
	 * 
	 * @return this Staff's job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * Check the working status of this Staff
	 * 
	 * @return this Staff's working status
	 */
	public boolean atWork() {
		return this.isWorking;
	}

	/**
	 * Set the ID of this Staff
	 * 
	 * @param i Change ID for this Staff
	 */
	public void SetId(int i) {
		this.id = i;
	}

	/**
	 * Set the name of this Staff
	 * 
	 * @param n Change name for this Staff
	 */
	public void SetName(String n) {
		this.name = n;
	}

	/**
	 * Set staff's gender
	 * 
	 * @param g Change gender for this Staff
	 */
	public void SetGender(String g) {
		this.gender = g;
	}

	/**
	 * Set the job title of this Staff
	 * 
	 * @param jt Change job title for this Staff
	 */
	public void SetJobTitle(String jt) {
		this.jobTitle = jt;
	}

	/**
	 * Set the working status of this Staff to on duty
	 */
	public void OnDuty() {
		this.isWorking = true;
	}

	/**
	 * Set the working status of this Staff to off duty
	 */
	public void OffDuty() {
		this.isWorking = false;
	}

}
