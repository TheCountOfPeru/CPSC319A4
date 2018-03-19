import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Data structures taken from CPSC319 lecture notes
 * Implementation of graph structure adapted from http://www.java2s.com/Code/Java/Collections-Data-Structure/Adirectedgraphdatastructure.htm
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
	
	public static void main(String[] args) {
		File fileIn;
		Scanner scanner;
		String temp = "";
		StringTokenizer stringtokenizer;
		int v_count;
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
		int i =0;
		int j, k;
		try {
			scanner = new Scanner(fileIn);
			System.out.println("Scanning the input file...");
			System.out.println();
			
			//Process first row first to identify how many vertices to accommodate for
			//Also add it to the adjacency list
			temp = scanner.nextLine();
			stringtokenizer = new StringTokenizer(temp, " ");
			int V = stringtokenizer.countTokens();
			System.out.println(V);
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
			/*while(scanner.hasNextLine()) {
				temp = scanner.nextLine();
				stringtokenizer = new StringTokenizer(temp, " ");
				arr = new int[V]; 
				i = 0;
				for(j = 0; j < V;j++) {//Parse one row into an array
					arr[j] = Integer.parseInt(stringtokenizer.nextToken());
					
				}
				for(k=0, j = 0;k<V;k++,j++) {//Parse the previous to add any edges to the graph
					if(arr[j]!=0)
						graph.addEdge(k, j);
					System.out.println(arr[j]);
				}
				for(j=0;j<arr.length;j++)
					System.out.print(arr[j]+" ");
				System.out.println();
			}*/
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to read the text file. Quitting...");
			System.exit(-1);
		}
		graph.printGraph();
	}

}
