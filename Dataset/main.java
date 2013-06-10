
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dataset d = new Dataset("/Users/tomoplast/Desktop/data.xml");
		System.out.println(d.getPerson("Sophia").getName());


	}

}
