import java.io.PrintWriter;

/*
 * Taken from https://www.geeksforgeeks.org/graph-and-its-representations/
 */
public class Graph {
	private	int V;
	private myLinkedList<Vertex> adjListArray[];
	private AdjMatrix matrix;
	public Graph(int V){
		matrix = null;
		this.V = V;
		// define the size of array as 
        // number of vertices
        adjListArray = new myLinkedList[V];
         
        // Create a new list for each vertex
        // such that adjacent nodes can be stored
        for(int i = 0; i < V ; i++){
            adjListArray[i] = new myLinkedList<Vertex>();
        }
	}
	public AdjMatrix getMatrix() {
		return matrix;
	}
	public void setMatrix(AdjMatrix matrix) {
		this.matrix = matrix;
	}
	public void addEdge(int src, Vertex dest)
    {
        // Add an edge from src to dest. 
		// src is the starting point and dest is the ending point
        this.adjListArray[src].addToHead(dest);
    }
	// A utility function to print the adjacency list 
    // representation of graph
    public void printGraph()
    {       
    	System.out.println("Displaying the adjacency list...");
    	System.out.println("--------------------------------");
        for(int v = 0; v < this.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
        	for (Node<Vertex> tmp = this.adjListArray[v].getHead(); tmp != null; tmp = tmp.getNext())
        		System.out.print(" -> "+tmp.getItem().getName() );
            System.out.println("\n");
        }
        System.out.println("--------------------------------");
    }
    /*
     * Utility function to print the adjacency matrix
     */
    public void printAsMatrix() {
    	matrix.PrintMatrix();
    }
    /*
     *  prints BFS traversal from a given source node start_node
     *  from https://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
     */
    public void BFS(Integer start_node, Integer end_node, PrintWriter pw)
    {
    	String results = "";						//We will use a string to record the visited nodes
    	Integer start = start_node;
    	System.out.println("Breadth first traversal starting at node: " + start_node +". Ending at: "+end_node+".");
        // Mark all the vertices as not visited(By default
        // set as false in JAVA)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS, this is to hold the nodes adjacent to the node that is being visited. Or to hold the starting node.
        myLinkedList<Integer> queue = new myLinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[start_node]=true;
        queue.addToTail(start_node);								
 
        while (!queue.isEmpty())
        {
            // Dequeue a vertex/node from the queue 
        	start_node = queue.deleteFromHead();
            results= results.concat(start_node.toString()+", ");//and record that happening in the results string
            // Get all adjacent vertices of the dequeued vertex s into a linkedlist
            // Loop through the linkedlist to find if an adjacent that has not been visited, then mark it
            // visited and enqueue it into the queue
           // myLinkedList<Integer> temp = adjListArray[start_node];  
            for (Node<Vertex> tmp = adjListArray[start_node].getHead(); tmp != null; tmp = tmp.getNext()) {
            	Integer n = (Integer)tmp.getItem().getName();
            	if(n==end_node) {												//Check if the end_node has been reached
            		results=results.concat(n.toString());					//If it has been reached leave the loop and record it to the results
            		visited[n] = true;
            		queue.clear();
            		break;
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
    public void DFSUtil(Integer start_node, Integer end_node, boolean visited[], String result, PrintWriter pw) throws EndFoundException
    {
        // Mark the current node as visited and record it to the results string
        visited[start_node] = true;
        if(start_node==end_node) {
            throw new EndFoundException(result + end_node);	//If the end node is found throw an exception to leave the recursive function 
        }													//Immediately
        else {
        	result = result.concat(start_node+", ");
        }     	
        // Recur for all the vertices adjacent to this vertex
        myLinkedList<Vertex> temp = adjListArray[start_node];  //In a linkedlist collect all the nodes that are adjacent to the current node
        for (Node<Vertex> tmp = temp.getHead(); tmp != null; tmp = tmp.getNext()) {
        	int n = (Integer)tmp.getItem().getName();//Go through the linked list one by one
        	if (!visited[n])
                DFSUtil(n, end_node, visited, result, pw);//If node n is not visited call another recursion
        }
    }
 
    /*
     *  The function to do DFS traversal. It uses recursive DFSUtil().
     *  from https://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
     */
    public void DFS(Integer start_node, Integer end_node, PrintWriter pw) 
    {
    	System.out.println("Depth first traversal starting at node: " +start_node+". Ending at: "+end_node+"." );
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
        String result = "";//We will use a string to record the visited nodes
        // Call the recursive helper function to execute the DFS traversal
        try {
			DFSUtil(start_node, end_node, visited, result, pw);
		} catch (EndFoundException e) {
			System.out.println(e.getMessage());
			pw.println(e.getMessage());
			return;
		}
        pw.println(start_node+", -1, "+end_node);
        System.out.print(start_node+", -1, "+end_node);
        System.out.println();
    }
    /*
     * Converts the current adjacency list to an adjacency matrix
     */
    public void ConvertToMatrix() {
    	AdjMatrix mat = new AdjMatrix(V);
    	for(int i = 0; i<V;i++) {
    		for (Node<Vertex> tmp = this.adjListArray[i].getHead(); tmp != null; tmp = tmp.getNext()) {
    			mat.addToMatrix(i, tmp.getItem().getName(), tmp.getItem().getWeight());
    		}
    	}
		setMatrix(mat);
    }
    /*
     * A utility function to find the vertex with minimum distance value,
     * from the set of vertices not yet included in shortest path tree
     * From https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
     */
    public int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
    /*
     * Function that implements Dijkstra's single source shortest path algorithm 
     * for a graph represented using adjacency matrix
     * representation
     * From https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
     */
    public void dijkstra(int src, int dest)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will be true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
            if(u == dest)
            	break;
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && matrix.getAt(u, v)!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+matrix.getAt(u, v) < dist[v])
                    dist[v] = dist[u] + matrix.getAt(u, v);
        }
        
 
        // print the constructed distance array
        printSolution(dist, src, dest);
    }
    // A utility function to print the constructed distance array
    public void printSolution(int dist[], int src, int dest)
    {
    	System.out.println("----------------------------------------------------------");
    	System.out.println("Vertex "+dest +" is at minimum "+dist[dest]+" units from Vertex "+src+"." );
       
    }
}
