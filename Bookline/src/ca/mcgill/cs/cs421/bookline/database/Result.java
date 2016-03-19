package ca.mcgill.cs.cs421.bookline.database;

import java.util.ArrayList;
import java.util.Iterator;

public class Result implements Iterable<Row>
{
	private ArrayList<Row> aRows;
	
	public Result() 
	{
		aRows = new ArrayList<>();
	}
	
	public boolean isEmpty ()
	{
		return aRows.isEmpty();
	}
	
	public void addRow (Row pRow)
	{
		aRows.add(pRow);
	}
	
	public Row getRow (int pIndex)
	{
		return new Row (aRows.get(pIndex));
	}
	
	@Override
	public Iterator<Row> iterator() 
	{
		return aRows.iterator();
	}
}
