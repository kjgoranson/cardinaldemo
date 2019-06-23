import exceptions.InvalidToolTypeException;

public class Tool {
    private String toolType;

    private int dailyCharge; //Using int to avoid any error caused by floating point arithmetic. Price is in pennies.

    private boolean weekendCharge;
    private boolean holidayCharge;

    public Tool(String toolType) throws Exception{
        this.toolType = toolType;
        setToolInfo();
    }

    /**
     * Sets tool brand based on toolType provided in constructor
     *
     * @throws Exception if tool type provided is not valid an exception is thrown
     */
    private void setToolInfo() throws Exception{
        switch (toolType){
            case "Ladder":
                dailyCharge = 199;
                weekendCharge = true;
                holidayCharge = false;
                break;
            case "Chainsaw":
                dailyCharge = 149;
                weekendCharge = false;
                holidayCharge = true;
                break;
            case "Jackhammer":
                dailyCharge = 299;
                weekendCharge = false;
                holidayCharge = false;
                break;
            default:
                throw new InvalidToolTypeException(toolType);
        }
    }

    public String getToolType() {
        return toolType;
    }

    public int getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
