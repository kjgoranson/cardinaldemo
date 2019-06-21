import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the rental agreement checkout!");
        Scanner in = new Scanner(System.in);

        while(true){
            try {
                System.out.println("Please enter tool code:");
                String code = in.next();
                System.out.println("Please enter checkout date in the format MM/dd/yy:");
                String date = in.next();
                System.out.println("Please enter number of days for this rental:");
                int days = in.nextInt();
                System.out.println("Please enter discount percent as a whole number (0-100):");
                int discount = in.nextInt();
                RentalAgreement agreement = RentalAgreement.checkout(code, days, discount, date);
                agreement.print();
            }
            catch(Exception e){
                System.out.println("ERROR - "+e.getMessage());
            }
        }
    }
}
