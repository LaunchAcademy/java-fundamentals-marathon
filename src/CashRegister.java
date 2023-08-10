package src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CashRegister {

  public static final String RECEIPT_FILE = "outstandingReceipts.txt";

  public static void main(String[] args) throws IOException {
//    1. Start With Money in the Drawer
//    Scanner scanner = new Scanner(System.in);
//    System.out.println("How much is the starting balance?");
//    String userBalance = scanner.nextLine();
//    System.out.println("You provided: " + userBalance);
//
////    2. Require a Value
////    if(userBalance.isBlank()) {
////      System.out.println("Error: Please provide a value");
////    }
//
////    3. Prompt Until a Value is Supplied
//    while(userBalance.isBlank()) {
//      System.out.println("Error: Please provide a value");
//      userBalance = scanner.nextLine();
//      System.out.println("You provided: " + userBalance);
//    }

//    4. Balance Last Night's Receipts!
//    double parsedUserBalance = Double.parseDouble(userBalance);

    // 6. Clean Up Our Code
    double parsedUserBalance = getMoneyValue("How much is the starting balance?");

    File receiptFile = new File(RECEIPT_FILE);
    Scanner fileScanner = new Scanner(receiptFile);

    while(fileScanner.hasNextLine()) {
      String input = fileScanner.nextLine();
      double parsedInput = Double.parseDouble(input);
//      System.out.println("parsed input " + parsedInput);
      parsedUserBalance += parsedInput;
//      userBalance = userBalance + parsedInput;
    }
    System.out.println("Total drawer balance: " + parsedUserBalance);

    //  5. Ask How Much the Order Cost

//    System.out.println("How much is the order?");
//    String customerOrder = scanner.nextLine();
//
//    while(customerOrder.isBlank()) {
//      System.out.println("Error: Please provide a value for the order");
//      customerOrder = scanner.nextLine();
//    }

//    6. Clean Up Our Code
    double customerOrder = getMoneyValue("How much is the order?");

    System.out.println("Customer order of: " + customerOrder);

//    7. Ask How Much Cash the Customer Provided
    double customerCash = getMoneyValue("How much cash do you have for payment?");

//    8. Ensure the Customer Provided Enough Cash
    while(customerCash < customerOrder) {
//      System.out.println("Please provide an amount equal or greater than the cost, " + customerOrder);
      customerCash = getMoneyValue("Please provide an amount equal or greater than the cost");
    }
    System.out.println("You provided payment: " + customerCash);

//    9. Output the Change
    double customerChange = customerCash - customerOrder;
    System.out.println("Your change is: " + customerChange);

//    10. Handle for the Change Due Exceeding Amount in Register
    while(customerChange > parsedUserBalance) {
      customerCash = getMoneyValue("We do not have enough to provide change, please provide an amount closer to your total of: " + customerOrder);
      customerChange = customerCash - customerOrder;
    }

    System.out.println("Here is your change, " + customerChange + ". Thank you!");
  }

//    6. Clean Up Our Code
  private static double getMoneyValue(String prompt) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(prompt);
    String userInput = scanner.nextLine();

    while(userInput.isBlank()) {
      System.out.println("Error: Please provide a value");
      userInput = scanner.nextLine();
    }

    double parsedUserInput = 0.0;
    try {
      parsedUserInput = Double.parseDouble(userInput);
    } catch (NumberFormatException error) {
      System.out.println("Error: You must provide a valid number");
    }

    return parsedUserInput;
  }
}
