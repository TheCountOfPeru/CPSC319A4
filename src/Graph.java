import java.io.PrintWriter;
import java.util.Iterator;

/*
 * Taken from https://www.geeksforgeeks.org/graph-and-its-representations/
 */
public class Graph {
	int V;
	myLinkedList<Integer> adjListArray[];
	
	public Graph(int V){
		this.V = V;
		// define the size of array as 
        // number of vertices
        adjListArray = new myLinkedList[V];
         
        // Create a new list for each vertex
        // such that adjacent nodes can be stored
        for(int i = 0; i < V ; i++){
            adjListArray[i] = new myLinkedList<Integer>();
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
    /*
     *  prints BFS traversal from a given source node s
     *  from https://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
     */
    public void BFS(int s)
    {
    	System.out.println("Breadth first traversal starting at node: " +s );
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        myLinkedList<Integer> queue = new myLinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.addToTail(s);
 
        while (!queue.isEmpty())
        {
            // Dequeue a vertex from queue and print it
            s = queue.deleteFromHead();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            myLinkedList<Integer> temp = adjListArray[s];  
            for (Node tmp = temp.getHead(); tmp != null; tmp = tmp.getNext()) {
            	int n = (Integer)tmp.getItem();
                if (!visited[n]){
                    visited[n] = true;
                    queue.addToTail(n);
                }
            } 
        }//end of while
        System.out.println();
    }
    // A function used by DFS
    public void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v+" ");
 
        // Recur for all the vertices adjacent to this vertex
        myLinkedList<Integer> temp = adjListArray[v];  
        for (Node tmp = temp.getHead(); tmp != null; tmp = tmp.getNext()) {
        	int n = (Integer)tmp.getItem();
        	if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    /*
     *  The function to do DFS traversal. It uses recursive DFSUtil(). v is the starting node.
     *  from https://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
     */
    public void DFS(int v)
    {
    	System.out.println("Depth first traversal starting at node: " +v );
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
        System.out.println();
    }
}
