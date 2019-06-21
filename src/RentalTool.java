import exceptions.InvalidToolCodeException;

public class RentalTool extends Tool{

    private String toolCode;
    private String toolBrand;

    public RentalTool(String code) throws Exception{
        super(getToolTypeFromCode(code));
        this.toolCode = code;
        setToolBrand();
    }

    /**
     * Sets tool brand based on toolCode provided in constructor
     *
     * @throws Exception if tool code provided is not valid an exception is thrown
     */
    private void setToolBrand() throws Exception{
        switch(toolCode){
            case "LADW":
                toolBrand = "Werner";
                break;
            case "CHNS":
                toolBrand = "Stihl";
                break;
            case "JAKR":
                toolBrand = "Ridgid";
                break;
            case "JAKD":
                toolBrand = "DeWalt";
                break;
            default: throw new InvalidToolCodeException(toolCode);
        }
    }

    /**
     * I am making the assumption that the first 3 characters of a tool code always specifies the tool type
     * This assumption will help with performance if more tools were added
     *
     * @param code 4-character tool code
     * @return String of the type of tool specified by the code
     */
    public static String getToolTypeFromCode(String code){
        switch(code.substring(0,3)){
            case "LAD":
                return "Ladder";
            case "CHN":
                return "Chainsaw";
            case "JAK":
                return "Jackhammer";
            default:
                return "Not Found";
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolBrand() {
        return toolBrand;
    }
}
