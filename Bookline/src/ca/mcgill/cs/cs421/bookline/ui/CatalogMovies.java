package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Movie;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class CatalogMovies extends Catalog
{
	public CatalogMovies() 
	{
		super("movie");
	}

	@Override
	public String getName() 
	{
		return "Movies";
	}

	@Override
	protected void viewDetails(int index) 
	{
		new DetailsMovie(ShopModel.get().getInventory().getMovies().get(index)).execute();
	}

	@Override
	protected void displayProducts() 
	{
		int i = 1;
		for(Movie m : ShopModel.get().getInventory().getMovies())	
		{
			System.out.println(" - " + i + " : " + m.getName());
			i++;
		}
	}
}