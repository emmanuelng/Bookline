package ca.mcgill.cs.cs421.bookline.ui;

import ca.mcgill.cs.cs421.bookline.database.Database;

public class MainMenu implements Option
{
	@Override
	public String getName() 
	{
		return "Main menu";
	}

	@Override
	public void execute() 
	{
		Menu menu = new Menu(getName());
		
		menu.addOption(new CatalogBooks());
		menu.addOption(new CatalogMovies());
		menu.addOption(new CatalogMagazines());
		menu.addOption(new ViewCart());
		menu.addOption(new Search());
		
		menu.execute();
		Database.get().close();
	}
}
