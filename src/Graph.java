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
     *  prints BFS traversal from a given source node start_node
     *  from https://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
     */
    public void BFS(Integer start_node, Integer end_node, PrintWriter pw)
    {
    	String results = "";
    	Integer start = start_node;
    	System.out.println("Breadth first traversal starting at node: " + start_node );
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        myLinkedList<Integer> queue = new myLinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[start_node]=true;
        queue.addToTail(start_node);
 
        while (!queue.isEmpty())
        {
            // Dequeue a vertex from queue and print it
        	start_node = queue.deleteFromHead();
            System.out.print(start_node+", ");
            results= results.concat(start_node.toString()+", ");
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            myLinkedList<Integer> temp = adjListArray[start_node];  
            for (Node tmp = temp.getHead(); tmp != null; tmp = tmp.getNext()) {
            	Integer n = (Integer)tmp.getItem();
            	if(n==end_node) {												//Check if the end_node has been reached
            		System.out.println(n+" ");									//If it has been reached leave the loop
            		results=results.concat(n.toString()+" ");
            		visited[n] = true;
            		tmp.setNext(null);
            		queue.clear();
            	}
            	else if(!visited[n]){
                    visited[n] = true;
                    queue.addToTail(n);
                }
            } 
        }//end of while
        if(!visited[end_node])													//If end_node never reached output appropriate results
        	results = start+", -1, "+end_node;
        System.out.println(results);
        pw.println(results);
        	
    }
    // A function used by DFS
    public void DFSUtil(Integer start_node,Integer end_node,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[start_node] = true;
        System.out.print(start_node+", ");
 
        // Recur for all the vertices adjacent to this vertex
        myLinkedList<Integer> temp = adjListArray[start_node];  
        for (Node tmp = temp.getHead(); tmp != null; tmp = tmp.getNext()) {
        	int n = (Integer)tmp.getItem();
        	if (!visited[n])
                DFSUtil(n, end_node, visited);
        }
    }
 
    /*
     *  The function to do DFS traversal. It uses recursive DFSUtil().
     *  from https://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
     */
    public void DFS(int start_node, int end_node, PrintWriter pw)
    {
    	System.out.println("Depth first traversal starting at node: " +start_node );
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper function to print DFS traversal
        DFSUtil(start_node,end_node, visited);
        System.out.println();
    }
}
