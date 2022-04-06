
import java.util.Arrays;
import java.util.Comparator;

public class GeneticAlgorithm {


    public int[] run(int n, int populationSize, double mutationProbability, int numOfGenerations) {

        populationSize = populationSize - (populationSize % 2); // each one should get a mate.

        int[][] population = generatePopulation(n, populationSize);

        int maxFitness = getMaxFitness(n);


        for (int x = 0; x < numOfGenerations; x++) {

            population = getPopulation(population);

            population = handleCrossovers(population, n);

            for (int i = 0; i < populationSize; i++) {

                if (getFitness(population[i]) == maxFitness)
                    return population[i];

                population[i] = Mutate(population[i], mutationProbability);
                if (getFitness(population[i]) == maxFitness)
                    return population[i];

            }

        }

        return null;
    }

    private int[][] handleCrossovers(int[][] population, int n) {
        for (int i = 0; i < population.length; i += 2) {

            int crossoverPos = (int) (Math.random() * n);

            for (int j = 0; j < crossoverPos; j++) {
                int tmp = population[i][j];
                population[i][j] = population[i+1][j];
                population[i+1][j] = tmp;
            }

        }
        return population;
    }

    private int[][] getPopulation(int[][] population) {
        return population;
    }

    private int[] Mutate(int[] genes, double mutationProbability) {
        if (mutationProbability>=Math.random())
        	genes[(int)(Math.random()*genes.length)] = (int)(Math.random()*genes.length);

        return genes;
    }


    private int getFitness(int[] genes) {
        return getMaxFitness(genes.length) - calculateFitness(genes);
    }

    private int getMaxFitness(int n) {
        return n*(n-1)/2;
    }

    private int[] generateChromosome(int n) {
    	int[] genes=new int[n];
    	for (int i = 0; i < genes.length; i++)
            genes[i] = (int) (Math.random() * genes.length);

        return genes;
    }
    

    // Returns heuristic cost
    public static int calculateFitness(int[] genes) {
        int nonThreats  = 0;
        // increment cost if two queens are in same row or in same diagonal.
        for (int i = 0; i < genes.length; i++)
            for (int j = i + 1; j < genes.length; j++)
                if (genes[i] == genes[j] || Math.abs(genes[i] - genes[j]) == j - i)
                	nonThreats += 1;

        return nonThreats;
    }

    private int[][] generatePopulation(int n, int populationSize) {
        int[][] population = new int[populationSize][];
        for (int i = 0; i < populationSize; i++)
            population[i] = generateChromosome(n);

        return population;
    }
    void print(int[] genes)
    {
        System.out.print("Chromosome : |");
        for(int i = 0; i < genes.length; i++)
        {
            System.out.print(genes[i]);
            System.out.print("|");
        }
        System.out.print(", Fitness : ");
        System.out.println(getFitness(genes));

        System.out.println("------------------------------------");
        for(int i = 0; i < genes.length; i++)
        {
            for(int j=0; j < genes.length; j++)
            {
                if(genes[j] == i)
                {
                    System.out.print("|Q");
                }
                else
                {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }
        System.out.println("------------------------------------");
    }
}