import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Data structures taken from CPSC319 lecture notes
 */
public class Assign4 {
	/**
	 * Adapted from https://stackoverflow.com/a/21974043
	 * @param aString - The name of a text file.
	 * @return The file extension if it exists, blank otherwise
	 */
	public static String getFileExtension(String aString) {
	    try {
	        return aString.substring(aString.lastIndexOf("."));
	    } catch (Exception e) {
	        return "";
	    }
	}
	public static void PrintQueryList(myLinkedList<QueryNodes> queries) {
		System.out.println("Displaying list of queries to execute...");
		for (Node<QueryNodes> tmp = queries.getHead(); tmp != null; tmp = tmp.getNext()) 
        	System.out.println(tmp.getItem().toString());
		System.out.println("-------------------------------------------------------");
	}
	
	public static void main(String[] args) {
		File fileIn, query;
		PrintWriter pw;
		Scanner scanner;
		StringTokenizer stringtokenizer;
		int v_count;
		myLinkedList<QueryNodes> queries = null;
		//Command line argument verification 
		/*
		if(args.length != 4) {
			System.out.println("Incorrect number of inputs. Quitting...");
			System.exit(-1);
		}
		if(!getFileExtension(args[0]).equals(".txt") || !getFileExtension(args[1]).equals(".txt") || !getFileExtension(args[2]).equals(".txt")
				|| !getFileExtension(args[3]).equals(".txt") ) {
			System.out.println("Unable to use files that are not text files. Check your file names. Quitting...");
			System.exit(-1);
		}*/
		//Build the adjacency matrix from an input file
		fileIn = new File(args[0]);
		Graph graph = null;
		int j, k;
		try {
			scanner = new Scanner(fileIn);
			System.out.println("Scanning the input file...");
			//Process first row first to identify how many vertices to accommodate for
			//Also add it to the adjacency list
			stringtokenizer = new StringTokenizer(scanner.nextLine(), "	");
			int V = stringtokenizer.countTokens();
			graph = new Graph(V);
			int[] arr = new int[V];
			for(j = 0; j < V;j++) {
				arr[j] = Integer.parseInt(stringtokenizer.nextToken());
			}
			for(k=0;k<V;k++) {
				if(arr[k]!=0)
					graph.addEdge(0, k);
			}
			//Process remaining rows
			for(int g= 1; g< V;g++) {
				stringtokenizer = new StringTokenizer(scanner.nextLine(), "	");
				arr = new int[V]; 
				for(j = 0; j < V;j++) {//Parse one row into an array
					arr[j] = Integer.parseInt(stringtokenizer.nextToken());
				}
				for(j = 0;j<V;j++) {//Parse the array to add edges to the graph
					if(arr[j]!=0)
						graph.addEdge(g,j);
				}
			}
			scanner.close();
			System.out.println("Graph successfully constructed...");
			graph.printGraph();	
		} catch (FileNotFoundException e) {
			System.out.println("Failed to read the input text file. Quitting...");
			System.exit(-1);
		}
		query = new File(args[1]);
		System.out.println("Scanning the query file...");
		try {
			scanner = new Scanner(query);
			queries = new myLinkedList<QueryNodes>();
			while(scanner.hasNextLine()) {
				stringtokenizer = new StringTokenizer(scanner.nextLine(),"	");
				Integer x = Integer.parseInt(stringtokenizer.nextToken());
				Integer y = Integer.parseInt(stringtokenizer.nextToken());
				queries.addToTail(new QueryNodes(x,y));
			}
			PrintQueryList(queries);
			scanner.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Failed to read the query text file. Quitting...");
			System.exit(-1);
		}
		
		
		//Perform depth first queries, Print the results to a file
		try {
			pw = new PrintWriter(args[2]);
			for (Node<QueryNodes> tmp = queries.getHead(); tmp != null; tmp = tmp.getNext())
	            	graph.DFS(tmp.getItem().getStart(), tmp.getItem().getEnd(), pw);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException for the depth first query method.");
		}
		//Breadth depth first queries, Print the results to a file
		System.out.println("----------------------------------------------------------------------");
		try {
			pw = new PrintWriter(args[3]);
			for (Node<QueryNodes> tmp = queries.getHead(); tmp != null; tmp = tmp.getNext())
					graph.BFS(tmp.getItem().getStart(), tmp.getItem().getEnd(), pw);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException for the breadth first query method.");
		} 
		
	}

}
