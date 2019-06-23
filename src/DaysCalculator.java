import java.time.DayOfWeek;
import java.time.LocalDate;

public class DaysCalculator {

    /**
     * Determines number of days that are weekend days given a start date and a number of days to count after the start date
     * This count will include the final day, but not the start day.
     *
     * @param start LocalDate representing the start date, not included in count
     * @param numberOfDays number of days past start date to count, including the final day
     * @return
     */
    public static int getWeekendCount(LocalDate start, int numberOfDays){
        int fullWeeks = numberOfDays/7;
        int partialWeekDays = numberOfDays%7;

        int weekendDaysInPartialWeek = 0;

        if(start.getDayOfWeek()==DayOfWeek.SUNDAY){ //Start day is Sunday - only get extra weekend day if the partial week ends on a Saturday
            if(partialWeekDays==6){
                weekendDaysInPartialWeek = 1;
            }
        }
        else if(start.getDayOfWeek()==DayOfWeek.SATURDAY){ //Start day is Saturday - get one extra weekend days if there are any days past a full week
            if(partialWeekDays>0){
                weekendDaysInPartialWeek = 1;
            }
        }
        else{ //Start day is during the week
            int endDay = start.getDayOfWeek().getValue()+partialWeekDays;
            if(endDay==6){ //Final day is a Saturday, one extra weekend day
                weekendDaysInPartialWeek = 1;
            }
            else if(endDay>6){ //Final day falls sometime after Saturday, 2 more weekend days
                weekendDaysInPartialWeek = 2;
            }
        }

        return fullWeeks*2 + weekendDaysInPartialWeek;
    }

    /**
     * Determines number of days that are holidays given a start date and a number of days to count after the start date
     * This count will include the final day, but not the start day.
     *
     * @param start LocalDate representing the start date, not included in count
     * @param numberOfDays number of days past start date to count, including the final day
     * @return
     */
    public static int getHolidayCount(LocalDate start, int numberOfDays){
        int count = 0;
        for(Holiday holiday:Holiday.holidays){
            count+=holiday.countInRange(start,numberOfDays);
        }
        return count;
    }
}
