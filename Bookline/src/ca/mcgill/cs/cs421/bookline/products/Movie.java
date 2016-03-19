package ca.mcgill.cs.cs421.bookline.products;

import ca.mcgill.cs.cs421.bookline.database.Database;
import ca.mcgill.cs.cs421.bookline.database.Result;
import ca.mcgill.cs.cs421.bookline.database.Row;

public class Movie extends Product 
{
	private int    aRating;
	private int    aDuration;
	private int    aYear;
	private String aDescription;
	
	public Movie(int pId) 
	{
		super(pId);
		init();
	}
	
	private void init()
	{
		String query  = "SELECT * FROM movies WHERE id="+ getId();
		Result result = Database.get().query(query);
		
		Row firstRow = result.getRow(0);
		
		aRating      = (int)firstRow.get("rating");
		aDuration    = (int)firstRow.get("duration");
		aYear        = (int)firstRow.get("year");
		aDescription = (String)firstRow.get("description");
	}
	
	public int getRating() 
	{
		return aRating;
	}

	public int getDuration() 
	{
		return aDuration;
	}

	public int getYear() 
	{
		return aYear;
	}

	public String getDescription()
	{
		return aDescription;
	}
}
