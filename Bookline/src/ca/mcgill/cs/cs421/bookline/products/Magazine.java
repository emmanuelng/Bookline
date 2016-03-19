package ca.mcgill.cs.cs421.bookline.products;

import java.util.Date;

public class Magazine extends Product 
{
	private Date aDate;
	private int  aVolume;
	private int  aIssue;
	
	public Magazine(int pId, Date date, int pVolume, int pIssue) 
	{
		super(pId);
		
		aDate   = date;
		aVolume = pVolume;
		aIssue  = pIssue;
	}
	
	public Date getDate()
	{
		return aDate;
	}
	
	public int getVolume()
	{
		return aVolume;
	}
	
	public int getIssue()
	{
		return aIssue;
	}

}
