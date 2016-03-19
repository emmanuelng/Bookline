package ca.mcgill.cs.cs421.bookline.products;

import ca.mcgill.cs.cs421.bookline.database.Database;
import ca.mcgill.cs.cs421.bookline.database.Result;
import ca.mcgill.cs.cs421.bookline.database.Row;

public abstract class Product 
{
	private int    aId;
	private String aName;
	private int    aPrice;
	private int    aQuantity;
	
	public Product(int pId) 
	{
		aId = pId;
		init();
	}
	
	private void init()
	{
		String query = "SELECT * FROM products WHERE id=" + getId();
		Result result = Database.get().query(query);
		
		Row firstRow = result.getRow(0);
		
		aName     = (String)firstRow.get("name");
		aPrice    = (int)firstRow.get("price");
		aQuantity = (int)firstRow.get("quantity");
	}
	
	public int getId()
	{
		return aId;
	}
	
	public String getName()
	{
		return aName;
	}
	
	public int getPrice()
	{
		return aPrice;
	}
	
	public int getQuantity()
	{
		return aQuantity;
	}
	
	public boolean setQuantity(int pQuantity)
	{
		if(pQuantity < 0) return false;
		
		String query = "UPDATE products"
				+ " SET quantity=" + pQuantity
				+ " WHERE id=" + getId();
		
		if(Database.get().query(query) == null) return false;

		aQuantity = pQuantity;
		return true;
	}
}
