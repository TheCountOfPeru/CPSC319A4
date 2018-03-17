
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
		//Command line argument verification 
		
				if(args.length != 4) {
					System.out.println("Incorrect number of inputs. Quitting...");
					System.exit(-1);
				}
				if(!getFileExtension(args[0]).equals(".txt") || !getFileExtension(args[1]).equals(".txt") || !getFileExtension(args[2]).equals(".txt")
						|| !getFileExtension(args[3]).equals(".txt") ) {
					System.out.println("Unable to use files that are not text files. Check your file names. Quitting...");
					System.exit(-1);
				}

	}

}
