/**
 An invoice calculator interface
 @author 	sylvia
 @version 	1.0
 @since		2021-11-08
 */

package possystem;

import java.util.Scanner;

public interface InvoiceCalculator {

	public static final float GSTRate = (float) 0.07;
	public static final float serviceChargeRate = (float) 0.10;
	public float calculatePrice(Order obj_order);
	public float calculateDiscountOfSubtotal(float discountRatePer, float subtotalPrice);
	public float calculateGSTOfSubtotal(float GSTRate, float subtotalPrice);
	public float calculateServiceChargeOfSubtotal(float serviceChargeRate, float subtotalPrice);
	public float calculateTotalPrice(float subtotalPrice, float discountOfSubtotal, float GSTOfSubtotal, float serviceChargeOfSubtotal);	

	
	
}


