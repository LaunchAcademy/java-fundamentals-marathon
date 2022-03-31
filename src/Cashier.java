import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Cashier {

  //  public static final String FILE_NAME = "outstandingReceipts.txt";
  public static void main(String[] args) throws IOException {
    // to read a file, we first set up our File object
    File file = new File("outstandingReceipts.txt");
//    File file = new File(FILE_NAME);


    // to read a file, we then create a Scanner for reading and hand it that file object
    Scanner fileScanner = new Scanner(file);
//    System.out.println("What's your starting balance for today?");
//    String balanceAmt = scanner.nextLine();
////    if (balanceAmt.isBlank()) {
////      System.out.println("ERROR: Please enter a value.");
////      return;
////    }
//    while (balanceAmt.isBlank()) {
//      System.out.println("Please enter a value!");
//      balanceAmt = scanner.nextLine();
//    }
//    double totalBalance = 0.0;
//    try {
//      totalBalance = Double.parseDouble(balanceAmt);
//      System.out.println(totalBalance);
//    } catch (NumberFormatException e) {
//      System.out.println("ERROR: Please enter a numeric value.");
//    }
    double totalBalance = getMoneyValue("What's your starting balance for today?");
    System.out.println(totalBalance);

    // to read our file, we go through the file line by line using a while loop
    while (fileScanner.hasNextLine()) {
      String input = fileScanner.nextLine();
      //System.out.println(input);
      totalBalance = totalBalance + Double.parseDouble(input);
    }
    System.out.println("Your total balance after balancing receipts is $" + totalBalance);

//    System.out.println("How much was the customer's order?");
//    String customerTotal = scanner.nextLine();
//    while (customerTotal.isBlank()) {
//      System.out.println("Error: Please enter a value!");
//      customerTotal = scanner.nextLine();
//    }
//    double orderTotal = 0.0;
//    try {
//      orderTotal = Double.parseDouble(customerTotal);
//      System.out.println(orderTotal);
//    } catch (NumberFormatException error) {
//      System.out.println("ERROR: Please enter a numeric value.");
////      error.printStackTrace();
//    }
    double orderTotal = getMoneyValue("How much was the customer's order?");
    System.out.println(orderTotal);

    double cashProvided = getMoneyValue("How much cash did the customer provide?");
    System.out.println(cashProvided);

    // OUR OLD WAY, REFACTORED (LINES 65-84)
    // check if the customer provided enough money
    // if they didn't provide enough money,
//    while(cashProvided < orderTotal) {
//      // keep asking them until they do
//      cashProvided = getMoneyValue("That's not enough money, please provide enough!");
//    }
//
//    // calculate the change by subtracting the order total from the amount paid
//    double changeDue = cashProvided - orderTotal;
//    System.out.println("The change due to the customer is: $" + changeDue);

    // check if we have enough money to provide change
    // if we don't have enough money
//    while(totalBalance < changeDue) {
//    // keep asking them until they pay a low enough amount that we can provide change
//      cashProvided = getMoneyValue("Sorry, we don't have enough money to provide change. Please pay closer to your order total of $" + orderTotal);
//      changeDue = cashProvided - orderTotal;
//    }
//
//    System.out.println("Great, we can provide that change! Thank you for your order. The change due to the customer is: $" + changeDue);

    // NEW WAY, REFACTORED
    double changeDue = cashProvided - orderTotal;
    while(cashProvided < orderTotal || totalBalance < changeDue) {
      // keep asking them until they do
      if(cashProvided < orderTotal) {
        cashProvided = getMoneyValue("That's not enough money, please provide enough!");
      } else {
        cashProvided = getMoneyValue("Sorry, we don't have enough money to provide change. Please pay closer to your order total of $" + orderTotal);
      }
      changeDue = cashProvided - orderTotal;
    }

    System.out.println("Great, we can provide that change! Thank you for your order. The change due to the customer is: $" + changeDue);
  }

  public static double getMoneyValue(String prompt) {
     Scanner scanner = new Scanner(System.in);
     System.out.println(prompt);
     String userInput = scanner.nextLine();
     while (userInput.isBlank()) {
       System.out.println("Error: Please enter a value!");
       userInput = scanner.nextLine();
     }
    double parsedInput = 0.0;
    try {
      parsedInput = Double.parseDouble(userInput);
//      System.out.println(parsedInput);
    } catch (NumberFormatException error) {
      System.out.println("ERROR: Please enter a numeric value.");
    }
    return parsedInput;
  }
}