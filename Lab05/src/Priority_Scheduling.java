public class Priority_Scheduling {

    String id;
    int bT;
    int priority;
    private int startTime;
    private int completionTime;

    public Priority_Scheduling(String id, int bT, int priority) {
        this.id = id;
        this.bT = bT;
        this.priority = priority;
        completionTime = 0;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

}
