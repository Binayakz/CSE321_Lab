import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("G:\\Spring-2021\\CSE321\\CSE321_ Lab\\Lab05\\src\\SJF_Premptive_input.txt"));

        LinkedList<SJF_Premptive> sjf = new LinkedList<>();

        String str;
        int totalBurstTime = 0;
        int index = 0;

        try {
            while ((str = br.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(str, " ");

                sjf.add(new SJF_Premptive(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

                totalBurstTime += sjf.get(index).bT;
                index++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;

//        Processes are scheduling.
        while (currentTime <= totalBurstTime) {

            SJF_Premptive currentProcess = null;
            int minBurst = Integer.MAX_VALUE;

            for (SJF_Premptive i : sjf) {
                if (i.getrB() != 0 && i.aT <= currentTime && i.getrB() < minBurst) {
                    minBurst = i.getrB();
                    currentProcess = i;

                    if (currentProcess.getrB() == currentProcess.bT) {
                        currentProcess.setStartTime(currentTime);
                    }
                }
            }

            currentTime++;

            if (currentProcess != null) {
                currentProcess.setrB(currentProcess.getrB() - 1);

                if (currentProcess.getrB() == 0) {
                    currentProcess.setCompletionTime(currentTime);
                }
            }
        }

        double avgTurnaroundTime = 0.0;
        double avgWaitingTime = 0.0;
        int noOfProcess = 0;

        System.out.println("Process | Arrival_Time | Burst_Time | Start_Time | Completion_Time(CT)  | Turnaround_Time(TAT) | Waiting_Time (WT)");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (SJF_Premptive process: sjf) {
            String  id = process.id;
            int aT = process.aT;
            int bT = process.bT;
            int sT = process.getStartTime();
            int cT = process.getCompletionTime();
            int taT = cT - aT;
            int wT = taT - bT;

            avgTurnaroundTime += taT;
            avgWaitingTime += wT;
            noOfProcess ++;

            System.out.println("  " + id + "\t\t\t" + aT + "\t\t\t  " + bT
                    + "\t\t\t   " + sT + "\t\t\t\t" + cT + "\t\t\t\t\t   " + taT + "\t\t\t\t\t" + wT );
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }

        avgTurnaroundTime /= (noOfProcess * 1.0);
        avgWaitingTime /= (noOfProcess * 1.0);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}