package ca.mcgill.cs.cs421.bookline.ui;

import java.util.ArrayList;

import ca.mcgill.cs.cs421.bookline.database.Database;
import ca.mcgill.cs.cs421.bookline.database.Result;
import ca.mcgill.cs.cs421.bookline.database.Row;
import ca.mcgill.cs.cs421.bookline.products.Book;
import ca.mcgill.cs.cs421.bookline.products.Magazine;
import ca.mcgill.cs.cs421.bookline.products.Movie;
import ca.mcgill.cs.cs421.bookline.products.Product;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class Search implements Option
{
	private ArrayList<Product> aResult; 
	
	public Search() {}
	
	@Override
	public String getName() 
	{
		return "Search a product";
	}

	@Override
	public void execute() 
	{		
		IOManager.clearScreen();
		IOManager.printTitle(getName());
		
		System.out.println();
		String input = IOManager.readInput("Search (q to return) : ");
		System.out.println();
		
		while (true)
		{
			if (input.equals("q")) break;
			search(input);
			if (!selectProduct()) break;
		}
	}
	
	private void search(String pKey) 
	{
		aResult = new ArrayList<>();
		
		IOManager.clearScreen();
		IOManager.printTitle("Search results");
		
		System.out.println();
		
		String query = "SELECT id, name "
				+ "FROM products "
				+ "WHERE LOWER(name) "
				+ "LIKE LOWER('%"+ pKey +"%')";
		
		Result result = Database.get().query(query);
		
		if (result.isEmpty())
		{
			System.out.println("(no result)");
		}
		else
		{
			ArrayList<Integer> ids = new ArrayList<>(); 
			for(Row r : result)
			{
				ids.add((int)r.get("id"));
			}
			
			// Add Product objects to result
			searchBooks(ids);
			searchMovies(ids);
			searchMagazines(ids);
			
			// Print the results
			String type = "";
			int index = 1;
			
			for(Product p : aResult)
			{
				if(p instanceof Book) type = "Book";
				else if(p instanceof Movie) type = "Movie";
				else if(p instanceof Magazine) type = "Magazine";
				
				System.out.println(" - " + index + " : " + type + " : " + p.getName());
				index++;
			}
		}
	}
	
	private void searchBooks (ArrayList<Integer> pIds)
	{
		for(int id : pIds)
		{
			for(Book b : ShopModel.get().getInventory().getBooks())
			{
				if(id == b.getId())
				{
					aResult.add(b);
				}
			}
		}
	}
	
	private void searchMovies (ArrayList<Integer> pIds)
	{
		for(int id : pIds)
		{
			for(Movie m : ShopModel.get().getInventory().getMovies())
			{
				if(id == m.getId())
				{
					aResult.add(m);
				}
			}
		}
	}
	
	private void searchMagazines (ArrayList<Integer> pIds)
	{
		for(int id : pIds)
		{
			for(Magazine m : ShopModel.get().getInventory().getMagazines())
			{
				if(id == m.getId())
				{
					aResult.add(m);
				}
			}
		}
	}
	
	private boolean selectProduct()
	{
		try
		{
			String input = IOManager.readInput("\nSelect a product (q to return) : ");
			
			if (input.equals("q")) return false;
			
			int i = Integer.parseInt(input) - 1;
			
			Product p = aResult.get(i);
			
			if(p instanceof Book) new DetailsBook((Book)p).execute();
			else if(p instanceof Movie) new DetailsMovie((Movie)p).execute();
			else if(p instanceof Magazine) new DetailsMagazine((Magazine)p).execute();
		}
		catch (Exception e)
		{
			// Nothing to do	
		}
		
		return true;
	}
}
