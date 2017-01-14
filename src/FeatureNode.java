
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

public class FeatureNode {
	
	public String featureName;
	public Hashtable<String, Integer> valueTable;
	
	public FeatureNode(String name)
	{
		featureName = name;
		valueTable = new Hashtable<String,Integer>();
	}
	
	public void addValue(String value)
	{
		if(valueTable.get(value)==null)
			valueTable.put(value,1);
		else
		{
			int count = valueTable.get(value);
			valueTable.remove(value);
			valueTable.put(value, count+1);
		}
	}
	
	public double getValue(String value)
	{
		if (valueTable.get(value)==null)
				return 0.0;
		
		return Double.parseDouble(valueTable.get(value).toString());
	}
	
	public void printClassifier(int resultCount, String result)
	{
		Set<Entry<String, Integer>> s = valueTable.entrySet();
		for(Entry<String, Integer> E: s)
		{
			System.out.println(featureName +"="+ E.getKey()+"|result="+result+" :"+E.getValue()+"/"+resultCount);
		}
	}
}