package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Magazine;

public class DetailsMagazine extends Details
{
	private Magazine aMagazine;
	
	public DetailsMagazine(Magazine pMagazine) 
	{
		super(pMagazine);
		aMagazine = pMagazine;
	}

	@Override
	protected void displayProducts() 
	{
		System.out.println("Volume             : " + aMagazine.getVolume());
		System.out.println("Issue              : " + aMagazine.getIssue());
		System.out.println("Date               : " + aMagazine.getDate());
		System.out.println("Quantity available : " + aMagazine.getQuantity());
	}

}
