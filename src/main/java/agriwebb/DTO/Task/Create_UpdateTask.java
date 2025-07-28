package agriwebb.DTO.Task;


import java.util.List;


public class Create_UpdateTask {

    private List<TaskArray> tasks;

    private String additionalNotes;

    private String ownerToken;


    public Create_UpdateTask( List<TaskArray> tasks, String additionalNotes, String ownerToken){
        this.tasks=tasks;
        this.additionalNotes=additionalNotes;
        this.ownerToken=ownerToken;
    }



    public List<TaskArray> getTasks() {
        return tasks;
    }
    public void setTasks(List<TaskArray> tasks) {
        this.tasks = tasks;
    }


    public String getAdditionalNotes() {
        return additionalNotes;
    }
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }


    public String getOwnerToken() {
        return ownerToken;
    }
    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }
}
