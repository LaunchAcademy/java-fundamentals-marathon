import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CashRegister {

  private static final String OUTSTANDING_PATH = "./outstandingReceipts.txt";

  public static void main(String[] args) throws FileNotFoundException {
    File outstandingReceiptsFile = new File(OUTSTANDING_PATH);

    double cashRegisterBalance = getMoneyValue("How much money is in the register?");

    Scanner outstandingReceiptsScanner = new Scanner(outstandingReceiptsFile);
    while (outstandingReceiptsScanner.hasNextLine()) {
      double number = outstandingReceiptsScanner.nextDouble();
      cashRegisterBalance += number;
    }
    System.out.println("the total is " + cashRegisterBalance);

    double customerOrder = getMoneyValue("How much was the customer's order");
    System.out.println("Customer Order: " + customerOrder);

    double cashReceived = getMoneyValue("How much money did you receive from the customer?");
    System.out.println("I received: $ " + cashReceived );

    while(cashReceived < customerOrder){
      cashReceived = getMoneyValue("Customer did not provide enough cash.  Please enter more");
    }

    double changeForCustomer = cashReceived-customerOrder;

    while(changeForCustomer > cashRegisterBalance || changeForCustomer < 0) {
      cashReceived = getMoneyValue("We do not have enough to provide change. Please enter an amount closer to amount due.");
      changeForCustomer = cashReceived - customerOrder;
    }
    System.out.println("Change due to customer:" + changeForCustomer);
  }

  public static double getMoneyValue(String prompt) {
    Scanner scanner = new Scanner(System.in);

    System.out.println(prompt);
    String userInput = scanner.nextLine();
    while (userInput.isBlank()) {
      System.out.println("Error: input cannot be blank");
      userInput = scanner.nextLine();
    }
    double number = Double.parseDouble(userInput);
    return number;
  }
}
