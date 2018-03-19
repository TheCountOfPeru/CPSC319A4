/*
 * Taken from https://www.geeksforgeeks.org/graph-and-its-representations/
 */
public class Graph {
	int V;
	myLinkedList adjListArray[];
	
	public Graph(int V){
		this.V = V;
		// define the size of array as 
        // number of vertices
        adjListArray = new myLinkedList[V];
         
        // Create a new list for each vertex
        // such that adjacent nodes can be stored
        for(int i = 0; i < V ; i++){
            adjListArray[i] = new myLinkedList();
        }
	}
	public void addEdge(int src, int dest)
    {
        // Add an edge from src to dest. 
		// src is the starting point and dest is the ending point
        this.adjListArray[src].addToHead(dest);
    }
	// A utility function to print the adjacency list 
    // representation of graph
    public void printGraph()
    {       
        for(int v = 0; v < this.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
        	for (Node tmp = this.adjListArray[v].getHead(); tmp != null; tmp = tmp.getNext())
        		System.out.print(" -> "+tmp.getItem() );
        	
            System.out.println("\n");
        }
    }
	
}
