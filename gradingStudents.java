import java.util.Scanner;
/*
Sample Input 0
4
73
67
38
33
Sample Output 0

75
67
40
33
*/public class gradingStudents {
	   static int[] solve(int[] grades){
	        // Complete this function
		   for(int i=0;i<grades.length;i++){
			   if(grades[i] >= 38){
				   int remainder = 5 - (grades[i] % 5); 
				   //System.out.println(remainder);
			   if( remainder < 3){
				   grades[i] = grades[i] + remainder;
					   //System.out.println(grades[i] = grades[i] + remainder);
				   }
			   }
		   }
		   return grades;
	    }

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        int n = in.nextInt();
	        int[] grades = new int[n];
	        for(int grades_i=0; grades_i < n; grades_i++){
	            grades[grades_i] = in.nextInt();
	        }
	        int[] result = solve(grades);
	        for (int i = 0; i < result.length; i++) {
	            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
	        }
	        System.out.println("");
	        

	    }
}
