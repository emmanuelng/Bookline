package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Book;

public class DetailsBook extends Details
{
	private Book aBook;
	
	public DetailsBook(Book pBook) 
	{
		super(pBook);
		aBook = pBook;
	}

	@Override
	protected void displayProducts() 
	{
		System.out.println("ISBN               : " + aBook.getISBN());
		System.out.println("Author             : " + aBook.getAuthor());
		System.out.println("Publication date   : " + aBook.getDate());
		System.out.println("Publisher          : " + aBook.getPublisher());
		System.out.println("Price              : $" + aBook.getPrice());
		System.out.println("Description        : " + aBook.getDescription());
		System.out.println("Quantity available : " + aBook.getQuantity());	
	}
	
}
