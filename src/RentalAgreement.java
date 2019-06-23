import exceptions.InvalidDiscountRangeException;
import exceptions.InvalidRentalLengthException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int dailyRentalCharge; //Using int to avoid any error caused by floating point arithmetic. Price is in pennies.
    private int chargeDays;
    private int preDiscountCharge; //Using int to avoid any error caused by floating point arithmetic. Price is in pennies.
    private int discountPercent;
    private int discountAmount; //Using int to avoid any error caused by floating point arithmetic. Price is in pennies.
    private int finalCharge; //Using int to avoid any error caused by floating point arithmetic. Price is in pennies.

    /**
     * Generates a rental agreement based on tool code, days rented, discount percent, and checkout date
     *
     * @param toolCode
     * @param rentalDays
     * @param discountPercent
     * @param checkoutDate
     * @throws Exception
     */
    public static RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, String checkoutDate) throws Exception{
        if(rentalDays<=0){
            throw new InvalidRentalLengthException();
        }
        if(discountPercent<0 || discountPercent>100){
            throw new InvalidDiscountRangeException();
        }


        RentalTool tool = new RentalTool(toolCode);
        String toolType = tool.getToolType();
        String toolBrand = tool.getToolBrand();
        LocalDate checkoutLocalDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("M/d/yy"));
        LocalDate dueDate = checkoutLocalDate.plusDays(rentalDays);
        int dailyRentalCharge = tool.getDailyCharge();
        int chargeDays = getChargeDays(checkoutLocalDate,rentalDays,tool.isWeekendCharge(),tool.isHolidayCharge());
        int preDiscountCharge = chargeDays*dailyRentalCharge;
        int discountAmount = (discountPercent*preDiscountCharge)/100 +
                (((discountPercent*preDiscountCharge)%100 >= 50) ? 1 : 0); //Apply rounding
        int finalCharge = preDiscountCharge - discountAmount;
        return new RentalAgreement(toolCode, toolType, toolBrand, rentalDays,
                                checkoutLocalDate, dueDate, dailyRentalCharge, chargeDays,
                                preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    /**
     * Constructor with all parameters - generates a rental agreement with all values specified
     */
    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays,
                           LocalDate checkoutDate, LocalDate dueDate, int dailyRentalCharge, int chargeDays,
                           int preDiscountCharge, int discountPercent, int discountAmount, int finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    /**
     * Writes all information about the rental agreement
     */
    public void print() {
        System.out.println("Tool Code:              "+toolCode);
        System.out.println("Tool Type:              "+toolType);
        System.out.println("Tool Brand:             "+toolBrand);
        System.out.println("Number of days rented:  "+rentalDays);
        System.out.println("Checkout date:          "+checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        System.out.println("Due date:               "+dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))); //Formatting date with full year for clarity when doing absurdity tests (100+ year rentals)
        System.out.println("Daily rental charge:    "+formatDollar(dailyRentalCharge));
        System.out.println("Number of days charged: "+chargeDays);
        System.out.println("Pre-discount charge:    "+formatDollar(preDiscountCharge));
        System.out.println("Discount percent:       "+discountPercent+"%");
        System.out.println("Discount amount:        "+formatDollar(discountAmount));
        System.out.println("Final charge:           "+formatDollar(finalCharge));
    }

    private String formatDollar(int value){
        return String.format("$%,d",value/100)+String.format(".%02d",value%100);
    }

    /**
     * Gets the number of days that can be charged, subtracting weekends and holidays if applicable
     *
     * @param start
     * @param numberOfDays
     * @param chargeWeekends
     * @param chargeHolidays
     * @return number of days that are to be charged
     */
    public static int getChargeDays(LocalDate start, int numberOfDays, boolean chargeWeekends, boolean chargeHolidays){
        if(!chargeWeekends){
            numberOfDays -= DaysCalculator.getWeekendCount(start,numberOfDays);
        }
        if(!chargeHolidays){
            numberOfDays -= DaysCalculator.getHolidayCount(start,numberOfDays);
        }
        return numberOfDays;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass()!=RentalAgreement.class){
            return false;
        }

        RentalAgreement comparing = (RentalAgreement) obj;

        return toolCode.equals(comparing.toolCode) &&
                toolType.equals(comparing.toolType) &&
                toolBrand.equals(comparing.toolBrand) &&
                rentalDays==comparing.rentalDays &&
                checkoutDate.compareTo(comparing.checkoutDate)==0 &&
                dueDate.compareTo(comparing.dueDate)==0 &&
                dailyRentalCharge==comparing.dailyRentalCharge &&
                chargeDays==comparing.chargeDays &&
                preDiscountCharge==comparing.preDiscountCharge &&
                discountPercent==comparing.discountPercent &&
                discountAmount==comparing.discountAmount &&
                finalCharge==comparing.finalCharge;
    }
}
