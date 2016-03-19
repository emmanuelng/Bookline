package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Product;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public abstract class Details implements Option
{
	private Product aProduct;
	
	public Details(Product pProduct) 
	{
		aProduct = pProduct;
	}
	
	@Override
	public String getName() 
	{
		return "Product details : " + aProduct.getName();
	}
	
	protected abstract void displayProducts();
	
	private void askCart()
	{
		String userInput = IOManager.readInput("Add to cart (y=yes/n=no) ? ");
		
		if (userInput.equals("y"))
		{
			userInput    = IOManager.readInput("Quantity (1-" + aProduct.getQuantity()+") : ");
			int quantity = Integer.parseInt(userInput);
			
			if (ShopModel.get().getCustomer().addToCart(aProduct, quantity))
			{
				System.out.println("Added to cart.");
			}
			else
			{
				System.out.println("An error occured. Please try again...");
			}
			
			IOManager.readInput("\n- Press ENTER to continue -");
		}
	}
	
	@Override
	public void execute() 
	{
		IOManager.clearScreen();
		IOManager.printTitle(getName());
		
		System.out.println();
		
		displayProducts();
		
		System.out.println();
		
		try
		{
			askCart();
		}
		catch (Exception e)
		{
			// Nothing to do
		}
	}	
}
