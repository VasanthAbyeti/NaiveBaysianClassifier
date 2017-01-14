
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

public class ResultNode {
	
	private String result;
	public int resultCount;
	private Hashtable<String,FeatureNode> ht;
	
	ResultNode(String resultIn)
	{
		result = resultIn;
		ht = new Hashtable<String,FeatureNode>();
		resultCount=0;
	}
	
	public void addFeature(String feature, String value)
	{
		if(ht.get(feature)==null)
		{
			ht.put(feature, new FeatureNode(feature));
		}
		ht.get(feature).addValue(value);
	}
		
	public void printClassifier(int totalCount)
	{
		
		System.out.println(result +" : " + resultCount + "/" + totalCount);
		
		Set<Entry<String, FeatureNode>> s = ht.entrySet();
		for(Entry<String, FeatureNode> E: s)
		{
			E.getValue().printClassifier(resultCount,result);
		}
	}
	
	public double getProbability(String feature, String Value)
	{
		if((ht.get(feature).getValue(Value))==0)
				return 0;

		return (ht.get(feature).getValue(Value))/ Double.parseDouble(Integer.toString(resultCount));
	}	
}