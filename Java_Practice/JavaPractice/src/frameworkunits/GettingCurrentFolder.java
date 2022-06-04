package frameworkunits;

public class GettingCurrentFolder {
	
	public static void main(String[] args) {
		
		String currentFolderPath=System.getProperty("user.dir");
		
		System.out.println(currentFolderPath);
	}

}
