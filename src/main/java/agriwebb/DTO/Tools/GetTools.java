package agriwebb.DTO.Tools;

import java.util.List;

public class GetTools {

    private List<ToolsArray> tools;



    public GetTools(List<ToolsArray> tools){
        this.tools=tools;
    }


    public List<ToolsArray> getTools() {
        return tools;
    }
    public void setTools(List<ToolsArray> tools) {
        this.tools = tools;
    }

}
