import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("G:\\Spring-2021\\CSE321\\CSE321_ Lab\\Lab05\\src\\Priority_Scheduling_input.txt"));

        LinkedList<Priority_Scheduling> priority_scheduling = new LinkedList<>();

        String str;
        int totalBurstTime = 0;
        int index = 0;

        try {
            while ((str = br.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(str, " ");
                priority_scheduling.add(new Priority_Scheduling(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                totalBurstTime += priority_scheduling.get(index).bT;
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;

        for (int i = 0; i < index ; i++) {

            Priority_Scheduling currentProcess = null;
            int maxPriority = Integer.MAX_VALUE;
            int minBurstTime = Integer.MAX_VALUE;

            for (Priority_Scheduling ps: priority_scheduling) {

                if (ps.getCompletionTime() == 0 && ( (ps.priority < maxPriority) || ( (ps.priority == maxPriority) && (ps.bT < minBurstTime) ) ) ) {
                    maxPriority = ps.priority;
                    currentProcess = ps;
                    minBurstTime = ps.bT;
                }
            }

            if (currentProcess != null) {
                currentProcess.setStartTime(currentTime);
                currentTime += currentProcess.bT;
                currentProcess.setCompletionTime(currentTime);
            }
        }


        double avgWaitingTime = 0.0;
        double avgTurnaroundTime = 0.0;
        int noOfProcess = 0;

        System.out.println("Process | Burst_Time | Priority | Start_Time | Completion_Time(CT)  | Turnaround_Time(TAT) | Waiting_Time (WT)");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Priority_Scheduling process: priority_scheduling) {
            String  id = process.id;
            int bT = process.bT;
            int priority = process.priority;
            int sT = process.getStartTime();
            int cT = process.getCompletionTime();
            int wT = cT - bT;

            avgWaitingTime += wT;
            avgTurnaroundTime += cT;
            noOfProcess ++;

            System.out.println("  " + id + "\t\t\t" + bT + "\t\t\t  " + priority
                    + "\t\t\t   " + sT + "\t\t\t\t" + cT + "\t\t\t\t\t   " + cT + "\t\t\t\t\t" + wT );
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }

        avgTurnaroundTime /= (noOfProcess * 1.0);
        avgWaitingTime /= (noOfProcess * 1.0);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}
