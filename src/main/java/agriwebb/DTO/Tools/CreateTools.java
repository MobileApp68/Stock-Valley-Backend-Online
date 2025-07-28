package agriwebb.DTO.Tools;

import java.util.List;



public class CreateTools {

    private List<ToolsArray> tools;

    private String ownerToken;


    public CreateTools( List<ToolsArray> tools, String ownerToken){

        this.tools=tools;
        this.ownerToken=ownerToken;
    }


    public List<ToolsArray> getTools() {
        return tools;
    }
    public void setTools(List<ToolsArray> tools) {
        this.tools = tools;
    }


    public String getOwnerToken() {
        return ownerToken;
    }
    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }

}
