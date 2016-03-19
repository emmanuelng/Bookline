package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.products.Magazine;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class CatalogMagazines extends Catalog
{
	public CatalogMagazines() 
	{
		super("magazine");
	}

	@Override
	public String getName() 
	{
		return "Magazines";
	}

	@Override
	protected void viewDetails(int index) 
	{
		new DetailsMagazine(ShopModel.get().getInventory().getMagazines().get(index)).execute();
	}

	@Override
	protected void displayProducts() 
	{
		int index = 1;
		for ( Magazine m : ShopModel.get().getInventory().getMagazines())
		{
			if(m.getQuantity() > 0) System.out.println(" - " + index + " : "+ m.getName() + ", " + m.getVolume() + ", #" + m.getIssue());
			index++;
		}
	}
}
