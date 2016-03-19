package ca.mcgill.cs.cs421.bookline.ui;

import java.util.ArrayList;

import ca.mcgill.cs.cs421.bookline.products.Product;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class ViewCart implements Option
{
	private ArrayList<Product> aProducts;
	
	@Override
	public String getName() 
	{
		return "View cart";
	}

	@Override
	public void execute() 
	{
		while (true)
		{
			IOManager.clearScreen();
			IOManager.printTitle(getName());
			
			System.out.println();
			
			System.out.println("The following product are "
					+ "in your cart (name, quantity) :\n");
			
			if(ShopModel.get().getCustomer().isCartEmpty()) 
			{
				System.out.println("(No Item)");
			}
			
			aProducts = new ArrayList<>();
			
			int index = 1;
			for(Product p : ShopModel.get().getCustomer().getCart().keySet())
			{
				aProducts.add(p);
				System.out.println(" - " + index + " : " + p.getName() + ", " + 
						ShopModel.get().getCustomer().getCart().get(p));
				index++;
			}
			
			System.out.println("\nCurrent balance : $" 
					+ ShopModel.get().getCustomer().getBill());
			
			System.out.println();
			
			if (askRemoveItem()) continue;
			
			askCheckout(); 
			
			break;
		}
	}
	
	private boolean askRemoveItem()
	{
		try
		{
			String input = IOManager.readInput("Remove item (y=yes/n=no) ? ");
			
			if(input.equals("y"))
			{
				input = IOManager.readInput("Select one item : ");
				int index = Integer.parseInt(input) - 1;
				
				Product p = aProducts.get(index);
				
				ShopModel.get().getCustomer().removeFromCart(p);
				
				System.out.println("Item removed.");
				
				return true;
			}
				
		}
		catch (Exception e)
		{
			// Nothing to do
		}
		
		return false;
	}
	
	private void askCheckout()
	{
		try 
		{
			String input = IOManager.readInput("Proceed to checkout (y=yes/n=no) ? ");
			
			if(input.equals("y"))
			{
				ShopModel.get().getCustomer().checkout();
				IOManager.readInput("\nThank you very much. "
						+ "Your products will be shipped soon.\n\n"
						+ "- Press ENTER to continue -");
			}
		} 
		catch (Exception e) 
		{
			// Nothing to do
		}
	}
	

}
