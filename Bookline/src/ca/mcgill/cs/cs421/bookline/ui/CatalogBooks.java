package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Book;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class CatalogBooks extends Catalog
{
	public CatalogBooks() 
	{
		super("book");
	}

	@Override
	public String getName() 
	{
		return "Books";
	}

	@Override
	protected void viewDetails(int index) 
	{
		new DetailsBook(ShopModel.get().getInventory().getBooks().get(index)).execute();
	}

	@Override
	protected void displayProducts() 
	{
		int i = 1;
		for(Book b : ShopModel.get().getInventory().getBooks())	
		{
			System.out.println(" - " + i + " : " + b.getName());
			i++;
		}
	}
}
