import java.io.IOException;

public class Driver {

	
	public static void main(String args[])
	{
		BayesianClassifier bc = new BayesianClassifier(args[0]);
		try {
			bc.classify();
			//bc.printClassifier();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bc.predict(args[1]);
		
	}
}
