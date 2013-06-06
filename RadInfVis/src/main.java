
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dataset d = new Dataset("/Users/tomoplast/Documents/workspace/Data/src/data.xml");
		System.out.println(d.getActivities()[3].getDay());

	}

}
