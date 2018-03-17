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
		try {
			scanner = new Scanner(fileIn);
			while(scanner.hasNextLine()) {
				temp = scanner.nextLine();
				stringtokenizer = new StringTokenizer(temp, " ");
				String[] tokens = new String[stringtokenizer.countTokens()]; 
				int i =0;
				while (stringtokenizer.hasMoreElements()) {
			        tokens[i] = stringtokenizer.nextToken();
			        i++;
			    }
				for(int j=0;j<tokens.length;j++)
					System.out.print(tokens[j]+" ");
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Failed to read the text file. Quitting...");
			System.exit(-1);
		}
	}

}
