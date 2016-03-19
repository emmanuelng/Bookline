package ca.mcgill.cs.cs421.bookline.ui;

import java.util.ArrayList;

import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class Menu implements Option
{
	private String            aTitle;
	private ArrayList<Option> aOptions;
	
	public Menu(String pTitle) 
	{
		aTitle   = pTitle;
		aOptions = new ArrayList<>();
	}
	
	public void addOption(Option pOption)
	{
		aOptions.add(pOption);
	}
	
	public void selectOption(int pIndex)
	{
		try 
		{
			IOManager.clearScreen();
			aOptions.get(pIndex - 1).execute();
			IOManager.clearScreen();
		} 
		catch (Exception e) 
		{
			// Nothing to do
		}
	}
	
	@Override
	public String getName() 
	{
		return aTitle;
	}

	@Override
	public void execute() 
	{
		while (true)
		{
			IOManager.clearScreen();
			IOManager.printTitle(getName());
			
			System.out.println("\nLogged as : " + ShopModel.get().getCustomer().getName());
			
			System.out.println();
			
			int index = 1;
			for(Option option : aOptions)
			{
				System.out.println(index + " : " + option.getName());
				index++;
			}
			
			System.out.println();
			
			try
			{
				String userInput = IOManager.readInput("Select an option (q to exit): ");
				
				if(userInput.equals("q")) break;
				
				int optionIndex  = Integer.parseInt(userInput);
				
				selectOption(optionIndex);
			}
			catch (Exception e)
			{
				// Nothing to do
			}
		}
		
		IOManager.clearScreen();
	}

}
