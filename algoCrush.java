import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/*
Sample Input
5 3
1 2 100
2 5 100
3 4 100

Sample Output
200
*/
public class algoCrush {

	public static void main(String args[]){
		Scanner kbd = new Scanner(System.in);
		int n = kbd.nextInt();
		long m = kbd.nextInt();
		
		List<Long> algo = new ArrayList<Long>(n);
		for(int i=0;i<n;i++){
			algo.add((long) 0);
			//System.out.println(algo.get(i));
		}
		do{
			long a = kbd.nextInt();
			long b = kbd.nextInt();
			long k = kbd.nextInt();
			
			for(int i=(int) (a-1);i<=b-1;i++){
				
				long addingK = algo.get(i) + k;
				algo.remove(i);
				algo.add(i,addingK);
				
			}
			m--;
		}while(m != 0);

		System.out.println(Collections.max(algo));
	}
}
