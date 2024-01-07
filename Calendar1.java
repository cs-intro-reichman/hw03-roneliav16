/** 
 * Prints the calendars of all the years in the 20th century.
 */
public class Calendar1 {	
    // Starting the calendar on 1/1/1900
	static int dayOfMonth = 1;   
	static int month = 1;
	static int year = 1900;
	static int dayOfWeek = 2;     // 1.1.1900 was a Monday
	static int nDaysInMonth = 31; // Number of days in January
	
	/** 
	 * Prints the calendars of all the years in the 20th century. Also prints the  
	 * number of Sundays that occured on the first day of the month during this period.
	 */
	public static void main(String args[]) {
		// Advances the date and the day-of-the-week from 1/1/1900 till 31/12/1999, inclusive.
	    // Prints each date dd/mm/yyyy in a separate line. If the day is a Sunday, prints "Sunday".
	    // The following variable, used for debugging purposes, counts how many days were advanced so far.
	    int debugDaysCounter = 0; 
	    //// Write the necessary initialization code, and replace the condition
	    //// of the while loop with the necessary condition 
		int sundays = 0;
	 	while (year < 2000) {
			System.out.print(dayOfMonth + "/" + month + "/" + year);
			if(dayOfWeek == 1 &&  dayOfMonth == 1) {
				System.out.println(" Sunday");
				sundays++;
			} else {
				System.out.println();
			}
			advance();
	 		debugDaysCounter++;
	 		//// If you want to stop the loop after n days, replace the condition of the
	 		//// if statement with the condition (debugDaysCounter == n)
	 		if (debugDaysCounter == 365001) { 
	 			break;
	 		}
        }
			System.out.println("During the 20th century, " +  sundays + " fell on the first day of the month");
	 }
	
	 // Advances the date (day, month, year) and the day-of-the-week.
	 // If the month changes, sets the number of days in this month.
	 // Side effects: changes the static variables dayOfMonth, month, year, dayOfWeek, nDaysInMonth.
	private static void advance() {
	if(dayOfWeek < 7) {
		dayOfWeek++;
	} else {
		dayOfWeek = 1;
	}

	if(dayOfMonth < nDaysInMonth) {
		dayOfMonth++;
	} else { if (month == 12) {
				year++;
				dayOfMonth = 1;
				month = 1;
				nDaysInMonth = nDaysInMonth(month, year);
			} else {
				month++;
				dayOfMonth = 1;
				nDaysInMonth = nDaysInMonth(month, year);

			}
		}
	 } 
		 
    // Returns true if the given year is a leap year, false otherwise.
	private static boolean isLeapYear(int year) {
		boolean ifLeap = ((year % 400) == 0) || ((year % 4) == 0) && ((year % 100) != 0);
		return ifLeap;	
	}
	 
	// Returns the number of days in the given month and year.
	// April, June, September, and November have 30 days each.
	// February has 28 days in a common year, and 29 days in a leap year.
	// All the other months have 31 days.
	private static int nDaysInMonth(int month, int year) {
		int febDays = (isLeapYear(year)) ? 29 : 28;
		switch(month) {
			case 1: return 31;
			case 2: return febDays; // 28 common year, 29 years leap year.
			case 3: return 31;
			case 4: return 30;
			case 5: return 31;
			case 6: return 30;
			case 7: return 31;
			case 8: return 31;
			case 9: return 30;
			case 10: return 31;
			case 11: return 30;
			case 12: return 31;
		}
		return 0;
	}
}