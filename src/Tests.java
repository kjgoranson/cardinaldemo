import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class Tests {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void RentalAgreementDiscountException() {
        try {
            RentalAgreement.checkout("JAKR", 5, 101, "9/3/15");
            fail("No exception was thrown");
        }
        catch(Exception e){
            assertEquals("Discount percent must be between 0 and 100, inclusive",e.getMessage());
        }
    }

    @Test
    public void RentalAgreementSuccess1() {
        try {
            RentalAgreement rentalAgreement = RentalAgreement.checkout("LADW", 3, 10, "7/2/20");
            RentalAgreement expected = new RentalAgreement("LADW", "Ladder", "Werner", 3,
                    LocalDate.parse("7/2/20",DateTimeFormatter.ofPattern("M/d/yy")),
                    LocalDate.parse("7/5/20",DateTimeFormatter.ofPattern("M/d/yy")),
                    199, 2, 398, 10, 40, 358);
            assertEquals(expected,rentalAgreement);
        }
        catch (Exception e){
            fail("No exception should be thrown");
        }
    }

    @Test
    public void RentalAgreementSuccess2() {
        try {
            RentalAgreement rentalAgreement = RentalAgreement.checkout("CHNS", 5, 25, "7/2/15");
            RentalAgreement expected = new RentalAgreement("CHNS", "Chainsaw", "Stihl", 5,
                    LocalDate.parse("7/2/15",DateTimeFormatter.ofPattern("M/d/yy")),
                    LocalDate.parse("7/7/15",DateTimeFormatter.ofPattern("M/d/yy")),
                    149, 3, 447, 25, 112, 335);
            assertEquals(expected,rentalAgreement);
        }
        catch (Exception e){
            fail("No exception should be thrown");
        }
    }

    @Test
    public void RentalAgreementSuccess3() {
        try {
            RentalAgreement rentalAgreement = RentalAgreement.checkout("JAKD", 6, 0, "9/3/15");
            RentalAgreement expected = new RentalAgreement("JAKD", "Jackhammer", "DeWalt", 6,
                    LocalDate.parse("9/3/15",DateTimeFormatter.ofPattern("M/d/yy")),
                    LocalDate.parse("9/9/15",DateTimeFormatter.ofPattern("M/d/yy")),
                    299, 3, 897, 0, 0, 897);
            assertEquals(expected,rentalAgreement);
        }
        catch (Exception e){
            fail("No exception should be thrown");
        }
    }

    @Test
    public void RentalAgreementSuccess4() {
        try {
            RentalAgreement rentalAgreement = RentalAgreement.checkout("JAKR", 9, 0, "7/2/15");
            RentalAgreement expected = new RentalAgreement("JAKR", "Jackhammer", "Ridgid", 9,
                    LocalDate.parse("7/2/15",DateTimeFormatter.ofPattern("M/d/yy")),
                    LocalDate.parse("7/11/15",DateTimeFormatter.ofPattern("M/d/yy")),
                    299, 5, 1495, 0, 0, 1495);
            assertEquals(expected,rentalAgreement);
        }
        catch (Exception e){
            fail("No exception should be thrown");
        }
    }

    @Test
    public void RentalAgreementSuccess5() {
        try {
            RentalAgreement rentalAgreement = RentalAgreement.checkout("JAKR", 4, 50, "7/2/20");
            RentalAgreement expected = new RentalAgreement("JAKR", "Jackhammer", "Ridgid", 4,
                    LocalDate.parse("7/2/20",DateTimeFormatter.ofPattern("M/d/yy")),
                    LocalDate.parse("7/6/20",DateTimeFormatter.ofPattern("M/d/yy")),
                    299, 1, 299, 50, 150, 149);
            assertEquals(expected,rentalAgreement);
        }
        catch (Exception e){
            fail("No exception should be thrown");
        }
    }

}
