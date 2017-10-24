import java.util.Scanner;

/**
 * Created by iyasuwatts on 10/17/17.
 */
public class Main {

    public static void main(String[] args){
        CLI_Interface.welcomeMessage();

        Scanner scanner = new Scanner(System.in);
        CLI_Interface.printIntroMenu(scanner.nextLine());

        if ("Yes".equalsIgnoreCase(scanner.nextLine())) {
            System.out.println("Please enter account type:");
            String type = scanner.nextLine();
            System.out.println("Please enter a balance");
            double balance = scanner.nextDouble();
            Account account = new Account(type, balance);
            System.out.println(account.toString());

        }



    }
}

