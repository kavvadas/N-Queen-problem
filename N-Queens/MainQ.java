
import java.util.Scanner;

public class MainQ {

	public static void main(String[] args) {
		GeneticAlgorithm algorithm = new GeneticAlgorithm();
		Scanner t=new Scanner(System.in);
		 
		System.out.println("Give the number of Queens:");
		int queens=t.nextInt();
			 
		while(queens!=0) {
			long start = System.currentTimeMillis();
						 
			int res[]=algorithm.run(queens, 10000, 0.09,10000 );
			
						 
			long end = System.currentTimeMillis();
			if(res!=null) {
				algorithm.print(res);
				System.out.println("Search time:" + (double)(end - start) / 1000 + " sec.");
				
			}else {
				System.out.println("There's no solution.");
			}
			
			System.out.println("Give the number of Queens:");
			queens=t.nextInt();
			
		}
	}

}
