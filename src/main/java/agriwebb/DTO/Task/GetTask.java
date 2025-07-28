package agriwebb.DTO.Task;


import java.util.List;


public class GetTask {

    private List<TaskArray> tasks;

    private String additionalNotes;




    public GetTask( List<TaskArray> tasks, String additionalNotes){
        this.tasks=tasks;
        this.additionalNotes=additionalNotes;

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

}
