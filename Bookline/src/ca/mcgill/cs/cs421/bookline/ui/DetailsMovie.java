package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Movie;

public class DetailsMovie extends Details
{
	private Movie aMovie;
	
	public DetailsMovie(Movie pMovie) 
	{
		super(pMovie);
		aMovie = pMovie;
	}

	@Override
	protected void displayProducts() 
	{
		System.out.println("Date               : " + aMovie.getYear());
		System.out.println("Duration           : " + aMovie.getDuration());
		System.out.println("Description        : " + aMovie.getDescription());
		System.out.println("Quantity available : " + aMovie.getQuantity());
		System.out.println("Rating             : PG-" + aMovie.getRating());
	}
}
