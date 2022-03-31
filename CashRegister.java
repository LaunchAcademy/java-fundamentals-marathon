import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CashRegister {

  public static final String RECEIPT_FILE_PATH = "./outstandingReceipts.txt";
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws FileNotFoundException {

    double starterCash = getMoneyValue("How much money do you have?");
    System.out.println("You gave me this much cash. $" + starterCash);

    File receipts = new File(RECEIPT_FILE_PATH);
    Scanner fileScanner = new Scanner(receipts).useDelimiter("\\n");

    double registerBalance = starterCash;

    while (fileScanner.hasNextLine()) {
      registerBalance += Double.parseDouble(fileScanner.nextLine());
    }
    System.out.println("Our current register balance is $" + registerBalance);


    double orderCost = getMoneyValue("How much was the order?");
    System.out.println("The cost was $" + orderCost);

    double providedCash = getMoneyValue("How much cash did the customer provide?");
    System.out.println("Customer provided $" + providedCash);

    while(orderCost > providedCash || providedCash - orderCost > registerBalance){
      if (providedCash - orderCost > registerBalance) {
        providedCash = getMoneyValue("Please provide a lesser amount.");
      } else {
        providedCash = getMoneyValue("That wasn't enough money please provide more");
      }
      System.out.println("Customer provided $" + providedCash);
    }

    double change = providedCash - orderCost;

    if (change > 0) {
      System.out.println("Your change is $" + change);
    } else {
      System.out.println("Thank you, come again!");
    }

  }

  public static double getMoneyValue(String prompt) {
    String userInput = "";
    double moneyValue = 0.0;

    while (userInput.isBlank()) {
      System.out.println(prompt);
      userInput = scanner.nextLine();

      if (!userInput.isBlank()) {
        try {
          moneyValue = Double.parseDouble(userInput);
        } catch (NumberFormatException e) {
          System.out.println("That wasn't a proper number.");
          userInput = "";
        }
      }

    }
    return moneyValue;
  }
}
