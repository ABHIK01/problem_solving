import java.util.*;

public class MaximizeCapital{
    public static int maximumCapital(int c, int k, int[] capitals,int[] profits) {

        PriorityQueue<int[]> minCapitalsPq = new PriorityQueue<>((a,b)->a[0]-b[0]);

        for(int i = 0;i<capitals.length;i++){
            minCapitalsPq.offer(new int[]{capitals[i],profits[i]});
        }

        PriorityQueue<Integer> profitsMaxPq = new PriorityQueue<>((a,b)->b-a);

        int currentCapital = c;

        for(int i = 0;i<k;i++){

            while(!minCapitalsPq.isEmpty() && currentCapital >=  minCapitalsPq.peek()[0]){
                int[] project = minCapitalsPq.poll();
                profitsMaxPq.offer(project[1]);
            }

            if(profitsMaxPq.isEmpty()) break;


            int maxProfit = profitsMaxPq.poll();

            currentCapital += maxProfit;

        }

        return currentCapital;




    }
}