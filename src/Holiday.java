import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.LocalDate;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Holiday {
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
