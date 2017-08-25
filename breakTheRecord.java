package aremdia;
/*
Sample Input 1

10
3 4 21 36 10 28 35 5 24 42
Sample Output 1

4 0
*/import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class breakTheRecord {
	 static int[] getRecord(int[] s){
	        // Complete this function
		 
		 int high=0,low=0;
		 int rec[] = new int[2];
		 int highest,lowest;
		 highest=lowest = s[0];
		 for(int i=0;i<s.length;i++){
			if(s[i] > highest){
				highest = s[i];
				high++;
			}
			else if(s[i] < lowest){
				lowest = s[i];
				low++;
			}
		 }

		 rec[0] = high; rec[1] = low;
		 return rec;
	    }

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        int n = in.nextInt();
	        int[] s = new int[n];
	        for(int s_i=0; s_i < n; s_i++){
	            s[s_i] = in.nextInt();
	        }
	        int[] result = getRecord(s);
	        String separator = "", delimiter = " ";
	        for (Integer val : result) {
	            System.out.print(separator + val);
	            separator = delimiter;
	        }
	        System.out.println("");
	    }
}
