package ca.mcgill.cs.cs421.bookline.database;

import java.util.HashMap;

public class Row 
{
	private HashMap<String, Object> aColumns;
	
	public Row() 
	{
		aColumns = new HashMap<>();
	}
	
	public Row (Row pRow)
	{
		aColumns = new HashMap<>();
		
		for(String key : pRow.aColumns.keySet())
		{
			aColumns.put(key, pRow.aColumns.get(key));
		}
	}
	
	public void add (String pKey, Object pObject)
	{
		aColumns.put(pKey, pObject);
	}
	
	public Object get (String pKey)
	{
		return aColumns.get(pKey);
	}
}
