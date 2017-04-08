/**
 * 
 */
package pro.budthapa.utility;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * @author budthapa
 * Mar 22, 2017
 * 
 */

public class Months {
	
	public static Map<Integer,String> months(){
		Map<Integer,String> months=new HashMap<>();
		months.put(1, Month.JANUARY.toString());
		months.put(2, Month.FEBRUARY.toString());
		months.put(3, Month.MARCH.toString());
		months.put(4, Month.APRIL.toString());
		months.put(5, Month.MAY.toString());
		months.put(6, Month.JUNE.toString());
		months.put(7, Month.JULY.toString());
		months.put(8, Month.AUGUST.toString());
		months.put(9, Month.SEPTEMBER.toString());
		months.put(10, Month.OCTOBER.toString());
		months.put(11, Month.NOVEMBER.toString());
		months.put(12, Month.DECEMBER.toString());		
		return months;
	}
}
