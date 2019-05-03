import java.util.*;

public class GraphImplementation implements Graph 
{
    public int[][] adjMatrix;
    public int vertices;
    public int edges;

    public GraphImplementation(int vertices) // assignment specification requirement
    {
        adjMatrix = new int[vertices][vertices]; // initialized to zero
        this.vertices = vertices;
        this.edges = 0;
    }

    public void addEdge(int v1, int v2) 
    {
        adjMatrix[v1][v2] = 1; // weight
        edges++; 
    }

    public List<Integer> topologicalSort()
    {
        List<Integer> tsort = new ArrayList<>();
        int[] incident = new int[vertices];

        // set incident array to zero
        for(int i = 0; i < vertices; i++)
        {
            incident[i] = 0;
        }

        //update incident array
        for(int i = 0; i < vertices; i++) 
        {
            for(int j = 0; j < vertices; j++) 
            {
                incident[j] += adjMatrix[i][j];
            }
        }

        //find index of item with zero count
        for(int i = 0; i < vertices; i++)
        {
            for(int j = 0; j < vertices; j++)
            {
                if(incident[j] == 0)
                {
                    int[] caster = neighbors(j);

                    for(int k = 0; k < caster.length; k++)
                    {
                        // cannot be converted to int. weird workaround
                        incident[caster[k]]--;
                    }

                    tsort.add(j); 
                    incident[j]--;
                }
            }
        }
        //System.out.println("Sort: "+tsort);
        return tsort;
    }

    public int[] neighbors(int vertex) 
    {
        int count = 0;
        int neighborCount = 0;

        for (int i = 0; i < vertices; i++) 
        {
            if (adjMatrix[vertex][i] > 0) 
            {
                count++;
            }
        }
        int[] neighbors = new int[count];
        for (int i = 0; i < vertices; i++) 
        {
            if (adjMatrix[vertex][i] > 0) 
            
            {
                neighbors[neighborCount++] = i;
            }
        }

        return neighbors;
    }
}