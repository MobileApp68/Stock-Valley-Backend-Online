package agriwebb.DTO.Tools;

public class ToolsArray {

    private String id;

    private String toolName;

    private double quantity;

    public ToolsArray(String id, String toolName, double quantity){

        this.id=id;
        this.toolName=toolName;
        this.quantity=quantity;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getToolName() {
        return toolName;
    }
    public void setToolName(String toolName) {
        this.toolName = toolName;
    }


    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
