package ca.mcgill.cs.cs421.bookline.shop;

public class ShopModel 
{
	private Customer  aCustomer;
	private Inventory aInventory;
	
	private static ShopModel aInstance;
	
	public static ShopModel get()
	{
		return aInstance;
	}
	
	public static void init(Customer pCustomer)
	{
		aInstance = new ShopModel(pCustomer);
	}
	
	private ShopModel(Customer pCustomer)
	{
		aCustomer  = pCustomer;
		aInventory = new Inventory();
	}
	
	public Customer getCustomer()
	{
		return aCustomer;
	}
	
	public Inventory getInventory()
	{
		return aInventory;
	}
}
