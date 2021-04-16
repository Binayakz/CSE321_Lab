public class Round_Robin {

    String id;
    int bT;
    private int rB;
    private int startTime;
    private int completionTime;

    public Round_Robin(String id, int bT) {
        this.id = id;
        this.bT = bT;
        rB = bT;
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

    public int getrB() {
        return rB;
    }

    public void setrB(int rB) {
        this.rB = rB;
    }
}
