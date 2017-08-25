import java.util.Scanner;
/*
Sample Input 0

4
3 2 1 3
Sample Output 0

2
*/public class bdayCandles {
	
    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int height[] = new int[n];
        for(int height_i=0; height_i < n; height_i++){
            height[height_i] = in.nextInt();
        }
        int max = 0;
        for(int i=0; i<height.length;i++){
             max =  height[i];
            if(max < height[i]){
                max = height[i];
            }
        }
        //System.out.println(max);   
        int count = 0;
        for(int i=0;i<height.length; i++){
        	if(height[i] == max){
        		count++;
        	}
        }
        System.out.println(count); 
        
    }

}
