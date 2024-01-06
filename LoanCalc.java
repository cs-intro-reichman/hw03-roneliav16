/**
* Computes the periodical payment necessary to re-pay a given loan.
*/
public class LoanCalc {
	
	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter;    // Monitors the efficiency of the calculation

    /** 
     * Gets the loan data and computes the periodical payment.
     * Expects to get three command-line arguments: sum of the loan (double),
     * interest rate (double, as a percentage), and number of payments (int).  
     */
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
	
	/**
	* Uses a sequential search method  ("brute force") to compute an approximation
	* of the periodical payment that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
		double periodicalPayment = loan / n;
		double balance = LoanCalc.endBalance(loan, rate, n, periodicalPayment); // This statement calls to another endBalance function to 
		iterationCounter = 1;													// culculate the the remain balance for this periodical payment

		while((Math.abs(balance)) >= epsilon && (balance >= 0)) { // The loop stops when the balance stops on a number that very close to 0.
			periodicalPayment += epsilon / 10;  // this statement increase the annual payment by very tiny steps to get a the accuarate result
			balance = LoanCalc.endBalance(loan, rate, n, periodicalPayment); 
			iterationCounter++;  // Add 1 to the counter of iteration
		}

    	return periodicalPayment;
    }
    
    /**
	* Uses bisection search to compute an approximation of the periodical payment 
	* that will bring the ending balance of a loan close to 0.
	* Given: the sum of theloan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		double L = (loan / n), H = loan; // L - lower payment, H - higher payment
		double g = (H + L) / 2; // g - the midlle of H and L
		double balance = LoanCalc.endBalance(loan, rate, n, g); // This statement calls to another endBalance function to culculate the the remain balance for this periodical payment
		iterationCounter = 1;	// Reset the variable to the other search	 

		while((Math.abs(balance)) >= epsilon) { // The loop stops when the balance stops on a number that very close to 0.
			if(balance > 0) {
				L = g;
			} else {
				H = g;
			}
			g = (L + H) / 2;
			balance = LoanCalc.endBalance(loan, rate, n, g); 
			iterationCounter++;  // Add 1 to the counter of iteration
		}
		return g;
    }
	
	/**
	* Computes the ending balance of a loan, given the sum of the loan, the periodical
	* interest rate (as a percentage), the number of periods (n), and the periodical payment.
	*/
	private static double endBalance(double loan, double rate, int n, double payment) {
		double balance = loan;
		for(int i = 0; i < n; i++) { // This loop return the remain balance for given payment.
			balance = (balance - payment) * ((rate / 100) + 1);
		}
		return balance;
	}
}