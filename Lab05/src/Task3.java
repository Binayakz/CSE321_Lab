import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("G:\\Spring-2021\\CSE321\\CSE321_ Lab\\Lab05\\src\\Round_Robin_input.txt"));

        LinkedList<Round_Robin> round_robins = new LinkedList<>();
        Queue<Round_Robin> queue = new LinkedList<>();

        String str;
        int totalBurstTime = 0;
        int index = 0;

        try {
            while ((str = br.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(str, " ");

                round_robins.add(new Round_Robin(st.nextToken(), Integer.parseInt(st.nextToken())));
                queue.add(round_robins.get(index));

                totalBurstTime += round_robins.get(index).bT;
                index++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;

//        Processes are scheduling.
        while (currentTime < totalBurstTime) {


            if (!queue.isEmpty()) {

                Round_Robin currentProcess = queue.poll();

                if (currentProcess.getrB() == currentProcess.bT) {
                    currentProcess.setStartTime(currentTime);
                }

                if (currentProcess.getrB() > 4) {
                    currentTime += 4;
                    currentProcess.setrB(currentProcess.getrB() - 4);
                    queue.add(currentProcess);
                }

                else {
                    currentTime += currentProcess.getrB();
                    currentProcess.setrB(0);
                    currentProcess.setCompletionTime(currentTime);
                }
            }
        }

        double avgWaitingTime = 0.0;
        double avgTurnaroundTime = 0.0;
        int noOfProcess = 0;

        System.out.println("Process | Burst_Time | Start_Time | Completion_Time(CT)  | Turnaround_Time(TAT) | Waiting_Time (WT)");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Round_Robin process: round_robins) {
            String  id = process.id;
            int bT = process.bT;
            int sT = process.getStartTime();
            int cT = process.getCompletionTime();
            int wT = cT - bT;

            avgWaitingTime += wT;
            avgTurnaroundTime += cT;
            noOfProcess ++;

            System.out.println("  " + id + "\t\t\t" + bT + "\t\t\t   " + sT + "\t\t\t\t" + cT + "\t\t\t\t\t   " + cT + "\t\t\t\t\t" + wT );
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }

        avgTurnaroundTime /= (noOfProcess * 1.0);
        avgWaitingTime /= (noOfProcess * 1.0);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}