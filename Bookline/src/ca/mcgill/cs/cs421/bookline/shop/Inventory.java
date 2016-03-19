package ca.mcgill.cs.cs421.bookline.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import ca.mcgill.cs.cs421.bookline.database.*;
import ca.mcgill.cs.cs421.bookline.products.*;

public class Inventory implements Iterable<Product>
{
	private ArrayList<Book>     aBooks;
	private ArrayList<Movie>    aMovies;
	private ArrayList<Magazine> aMagazines;
	
	public Inventory() {}
	
	private HashSet<Integer> getIDs(String pTable)
	{
		HashSet<Integer> pids = new HashSet<>();
		
		String query = "SELECT t.id"
				+ " FROM " + pTable + " t"
				+ " INNER JOIN products p"
				+ " ON t.id = p.id"
				+ " ORDER BY p.name";
		
		Result result = Database.get().query(query);
		
		for(Row row : result)
		{
			pids.add((int)row.get("id"));
		}
		
		return pids;
	}
	
	private void loadBooks()
	{
		aBooks = new ArrayList<>();
		
		for(int id : getIDs("books"))
		{
			aBooks.add(new Book(id));
		}
	}
	
	private void loadMovies()
	{
		aMovies = new ArrayList<>();
		
		for(int id : getIDs("movies"))
		{
			aMovies.add(new Movie(id));
		}
	}
	
	private void loadMagazines()
	{
		aMagazines = new ArrayList<>();
		
		String query;
		Result result;
		for(int id : getIDs("magazines"))
		{
			query = "SELECT * FROM magazines WHERE id=" + id;
			result = Database.get().query(query);
			
			for(Row r : result)
			{
				aMagazines.add(new Magazine(
						id, 
						(Date)r.get("date"), 
						(int)r.get("volume"), 
						(int)r.get("issue")));
			}
		}
	}
	
	public List<Book> getBooks()
	{
		if (aBooks == null) loadBooks();
		return Collections.unmodifiableList(aBooks);
	}
	
	public List<Movie> getMovies()
	{
		if (aMovies == null) loadMovies();
		return Collections.unmodifiableList(aMovies);
	}
	
	public List<Magazine> getMagazines()
	{
		if (aMagazines == null) loadMagazines();
		return Collections.unmodifiableList(aMagazines);
	}

	@Override
	public Iterator<Product> iterator() 
	{
		ArrayList<Product> products = new ArrayList<>();
		
		products.addAll(getBooks());
		products.addAll(getMovies());
		products.addAll(getMagazines());
		
		return products.iterator();
	}
}
