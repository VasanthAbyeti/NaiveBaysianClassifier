
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.BufferedReader;

public class BayesianClassifier {

	
	private String inputFileName;
	private int totalCount;
	private Hashtable<String,ResultNode> resultNodes;
	
	public BayesianClassifier(String fileName)
	{
		inputFileName=fileName;
		totalCount=0;
	}
	
	public void classify() throws IOException
	{
		try
		{
		File file = new File(inputFileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String values[];
		String line = br.readLine();
		
		if(line==null)
			throw new Exception("File is Empty.");
		
		String[] valuesHeadings = line.split("\t");
		int numOfFeatures = valuesHeadings.length;
		
		resultNodes =new Hashtable<String,ResultNode>();
		
		line = br.readLine();
		String res;
		while(line!=null)
		{
			values = line.split("\t");
			res = values[numOfFeatures-1];
			if(resultNodes.get(res)==null)
				resultNodes.put(res, new ResultNode(res));
			
			resultNodes.get(res).resultCount++;
			for(int i=0;i<numOfFeatures-1;i++)
			{
				resultNodes.get(res).addFeature(valuesHeadings[i], values[i]);
			}
			line = br.readLine();
			totalCount++;
		}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	}
	
	public void printClassifier()
	{
		Set<Entry<String, ResultNode>> s = resultNodes.entrySet();
		for(Entry<String, ResultNode> E: s)
		{
			E.getValue().printClassifier(totalCount);
		}
	}
	
	public void predict(String predictionFile)
	{
		try
		{
		File file = new File(predictionFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String headings[] = br.readLine().split("\t");
		String values[] = null;
		String line = br.readLine();
		values = line.split("\t");
		
		
		int i =0;
		Double d = 0.1;
		
		List<Integer> in = new LinkedList<Integer>();
		
		LinkedList<Integer> inL = new LinkedList<Integer>();
		
		ArrayList<Integer> inA = new ArrayList<Integer>();
		
		ListIterator<Integer> it =  inL.listIterator();
		
		
		Set<Entry<String, ResultNode>> s = resultNodes.entrySet();
		for(Entry<String, ResultNode> E: s)
		{
			d=0.0;
			System.out.print("Key : " + E.getKey() + " -> ");
			for(String str : values)
			{
				d=d+E.getValue().getProbability(headings[i], str.trim());
			}
			System.out.println(d);
			i++;
		}
		
		System.out.println(Integer.MAX_VALUE);
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	}
}
