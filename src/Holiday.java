import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Function;

public class Holiday {

    public static Holiday[] holidays = {
            new Holiday(year -> {//4th of July
                LocalDate defaultDay = LocalDate.of(year,7,4);
                if(defaultDay.getDayOfWeek()== DayOfWeek.SATURDAY){
                    return defaultDay.minusDays(1);
                }
                else if(defaultDay.getDayOfWeek()==DayOfWeek.SUNDAY){
                    return defaultDay.plusDays(1);
                }
                else{
                    return defaultDay;
                }
            }), new Holiday(year -> {//Labor Day
        LocalDate defaultDay = LocalDate.of(year,9,1);
        if(defaultDay.getDayOfWeek()==DayOfWeek.MONDAY){
            return defaultDay;
        }
        else{
            return defaultDay.plusDays(8-defaultDay.getDayOfWeek().getValue()); //add days to get to the next Monday
        }
    })};

    Function<Integer, LocalDate> findDayForYear;

    public Holiday(Function<Integer, LocalDate> findDayForYear){
        this.findDayForYear = findDayForYear;
    }

    /**
     * Gets the number of times the holiday is included in a date range given a start date and number of days after the start date
     * This does not include the start day, but does include the final day
     *
     * @param start
     * @param days
     * @return
     */
    public int countInRange(LocalDate start, int days){
        LocalDate end = start.plusDays(days);
        int startYear = start.getYear();
        int endYear = end.getYear();
        if(startYear==endYear){
            LocalDate holidayDate = findDayForYear.apply(startYear);
            return (start.compareTo(holidayDate)<0 && end.compareTo(holidayDate)>=0) ? 1 : 0;
        }
        else{
            LocalDate holidayInStartYear = findDayForYear.apply(startYear);
            int startYearCount = (start.compareTo(holidayInStartYear)<0) ? 1 : 0;

            LocalDate holidayInEndYear = findDayForYear.apply(endYear);
            int endYearCount = (end.compareTo(holidayInEndYear)>=0) ? 1 : 0;

            int inBetweenYears = endYear-startYear-1; //Number of full years between start and end

            return startYearCount+endYearCount+inBetweenYears;
        }
    }
}
