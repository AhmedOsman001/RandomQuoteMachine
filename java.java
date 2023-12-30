import java.util.Scanner;

class FCFSExample {

    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        int n,q;
        n = r.nextInt();
        q=r.nextInt();
        String name[] = new String[n];
        int arri[] = new int[n];
        int brus[] = new int[n];
        for (int i = 0; i < n; i++) {
            name[i] = r.next();
        }
        for (int i = 0; i < n; i++) {
            arri[i] = r.nextInt();
        }
        for (int i = 0; i < n; i++) {
            brus[i] = r.nextInt();
        }

        FCFS(n,name,arri,brus);
        RR(n,name,arri,brus,q);

    }
    static void FCFS(int n, String name[], int arri[], int brus[]) {
        int wait[] = new int[n], turn[] = new int[n], sum = 0;
        double avewait = 0, aveturn = 0;
 
        for (int i = 0; i < n; i++) {
            wait[i] = sum - arri[i];
            sum += brus[i];
            avewait += wait[i];
        }
 
        for (int i = 0; i < n; i++) {
            turn[i] = wait[i] + brus[i];
            aveturn += turn[i];
        }
 
 
        System.out.print("Waiting Time = ");
        for (int i : wait) {
            System.out.print(i + " ");
        }
         System.out.println("");
        System.out.print("Turnaround Time = ");
        for (int i : turn) {
            System.out.print(i + " ");
        }
 
        System.out.println("");
        System.out.println("avewait = " + avewait / n);
        System.out.println("aveturn = " + aveturn / n);
 
    }
    static void RR(int n, String name[], int arri[], int brus[],int q){
        int wait[]=new int[n],temp[]=new int[n],sm=0,check=1;
        double avewait=0,aveturn=0;
        System.arraycopy(brus, 0, temp, 0, n);
 
        while(check==1){
            check=0;
            for(int i=0;i<n;i++){
                if(brus[i]>0){
                    check=1;
                    wait[i]+=sm-arri[i];
                    int o=(q>=brus[i]?brus[i]:q);
                    sm+=o;
                    arri[i]=sm;
                    brus[i]-=o;
                }
            }
        }
 
 
    System.out.println("Waiting Time = ");
    for (int i = 0; i < n; i++) {
        System.out.println(name[i]+"= "+(wait[i]));
        avewait+=wait[i];
        aveturn+=wait[i]+temp[i];
    }
    System.out.println("Turnaround Time = ");
    for (int i = 0; i < n; i++) {
        System.out.println(name[i]+"= "+(temp[i]+wait[i]));
    }
 
    System.out.println("avewait = " + avewait / n);
    System.out.println("aveturn = " + aveturn / n);
 
    }

     static void performProcessScheduling(int n, int[] processIds, int[] burstTimes) {
        int[][] A = new int[100][4];
        int total = 0;
        float avg_wt, avg_tat;

        for (int i = 0; i < n; i++) {
            A[i][1] = burstTimes[i];
            A[i][0] = processIds[i];
        }

        // Sort processes according to their Burst Time.
        for (int i = 0; i < n; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j][1] < A[index][1]) {
                    index = j;
                }
            }
            int temp = A[i][1];
            A[i][1] = A[index][1];
            A[index][1] = temp;
            temp = A[i][0];
            A[i][0] = A[index][0];
            A[index][0] = temp;
        }

        A[0][2] = 0;

        // Calculation of Waiting Times
        for (int i = 1; i < n; i++) {
            A[i][2] = 0;
            for (int j = 0; j < i; j++) {
                A[i][2] += A[j][1];
            }
            total += A[i][2];
        }

        avg_wt = (float) total / n;
        total = 0;

        // Calculation of Turn Around Time and printing the data.
        System.out.println("P\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            A[i][3] = A[i][1] + A[i][2];
            total += A[i][3];
            System.out.println("P" + A[i][0] + "\t"
                    + A[i][1] + "\t" + A[i][2]
                    + "\t" + A[i][3]);
        }

        avg_tat = (float) total / n;
        System.out.println("Average Waiting Time= " + avg_wt);
        System.out.println("Average Turnaround Time= " + avg_tat);
    }

}