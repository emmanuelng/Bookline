package ca.mcgill.cs.cs421.bookline.shop;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ca.mcgill.cs.cs421.bookline.database.Database;
import ca.mcgill.cs.cs421.bookline.database.Result;
import ca.mcgill.cs.cs421.bookline.database.Row;
import ca.mcgill.cs.cs421.bookline.products.Product;

public class Customer 
{
	private String aEmail;
	private String aName;
	private String aBirthday;
	private String aAddress;
	private String aCreditCard;
	
	private HashMap<Product, Integer> aCart;
	
	public static Customer get(String pEmail)
	{
		String query  = "SELECT * FROM customers WHERE email='"+ pEmail +"'";
		Result result = Database.get().query(query);
		
		if (result == null) return null;
		
		Row firstRow = result.getRow(0);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		return new Customer (
				(String)firstRow.get("email"), 
				(String)firstRow.get("name"),
				(String)firstRow.get("address"),
				formatter.format((Date)firstRow.get("birthday")),
				(String)firstRow.get("creditcard"));
	}
	
	public static Customer create(String pEmail, String pName, String pAddress, 
			String pBirthday, String pCreditCard)
	{
		String query = "INSERT INTO customers "
				+ "VALUES ("
				+ "'"+ pEmail      +"',"
				+ "'"+ pName       +"',"
				+ "'"+ pAddress    + "',"
				+ "'"+ pBirthday   +"',"
				+ "'"+ pCreditCard +"')";
		
		Result result = Database.get().query(query);
		
		if (result == null) return null;
		
		return new Customer(pEmail, pName, pAddress, pBirthday, pCreditCard);
	}
	
	private Customer(String pEmail, String pName, String pAddress, 
			String pBirthday, String pCreditCard) 
	{
		aEmail      = pEmail;
		aName       = pName;
		aAddress    = pAddress;
		aBirthday   = pBirthday;
		aCreditCard = pCreditCard;
		
		aCart = new HashMap<>();
	}
	
	public String getEmail()
	{
		return aEmail;
	}
	
	public String getName()
	{
		return aName;
	}
	
	public String getAddress()
	{
		return aAddress;
	}
	
	public String getBirthday()
	{
		return aBirthday;
	}
	
	public String getCreditCard()
	{
		return aCreditCard;
	}
	
	public Map<Product, Integer> getCart()
	{
		return Collections.unmodifiableMap(aCart);
	}
	
	public boolean addToCart(Product pProduct, int pQuantity)
	{
		if (pQuantity <= 0) return false;
		
		if (pProduct.setQuantity(pProduct.getQuantity() - pQuantity))
		{
			aCart.put(pProduct, pQuantity);
			return true;
		}
		
		return false;
	}
	
	public void removeFromCart(Product pProduct)
	{
		if (pProduct.setQuantity(pProduct.getQuantity() + aCart.get(pProduct)))
		{
			aCart.remove(pProduct);
		}
	}
	
	public boolean isCartEmpty()
	{
		return aCart.isEmpty();
	}
	
	public int getBill()
	{
		int cost = 0;
		
		for (Product p : aCart.keySet())
		{
			cost += (aCart.get(p) * p.getPrice());
		}
		
		return cost;
	}
	
	public boolean checkout ()
	{	
		if (isCartEmpty()) return false;
		
		String query  = "SELECT MAX(ponumber) AS maxid FROM orders";
		Result result = Database.get().query(query);
		
		int ponumber = (int)result.getRow(0).get("maxid") + 1;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		
		query = "INSERT INTO orders "
				+ "VALUES (" 
				+ ponumber  + ",'" 
				+ today     + "'," 
				+ getBill() + ")";
		
		if(Database.get().query(query) == null) return false;
		
		return true;
	}
}
