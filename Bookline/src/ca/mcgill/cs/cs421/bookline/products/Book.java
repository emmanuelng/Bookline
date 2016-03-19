package ca.mcgill.cs.cs421.bookline.products;

import java.util.Date;

import ca.mcgill.cs.cs421.bookline.database.Database;
import ca.mcgill.cs.cs421.bookline.database.Result;
import ca.mcgill.cs.cs421.bookline.database.Row;

public class Book extends Product
{
	private String aISBN;
	private String aAuthor;
	private Date   aDate;
	private String aPublisher;
	private String aDescription;
	private int    aRentAmount;
	
	public Book(int pId) 
	{
		super(pId);
		init();
	}
	
	private void init()
	{
		String query  = "SELECT * FROM books WHERE id=" + getId();
		Result result = Database.get().query(query);
		
		Row firstRow = result.getRow(0);
		
		aISBN        = (String)firstRow.get("isbn");
		aAuthor      = (String)firstRow.get("author");
		aDate        = (Date)firstRow.get("date");
		aPublisher   = (String)firstRow.get("publisher");
		aDescription = (String)firstRow.get("description");
		aRentAmount  = (int)firstRow.get("rentamount");
	}
	
	public String getISBN()
	{
		return aISBN;
	}
	
	public String getAuthor()
	{
		return aAuthor;
	}
	
	public Date getDate()
	{
		return aDate;
	}
	
	public String getPublisher()
	{
		return aPublisher;
	}
	
	public String getDescription()
	{
		return aDescription;
	}
	
	public int getRentAmount()
	{
		return aRentAmount;
	}
}
