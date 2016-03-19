package ca.mcgill.cs.cs421.bookline.ui;

import java.text.ParseException;

import ca.mcgill.cs.cs421.bookline.shop.Customer;
import ca.mcgill.cs.cs421.bookline.shop.ShopModel;

public class Login implements Option
{
	private Customer aCustomer;
	private String aMessage = "";
	
	@Override
	public String getName() 
	{
		return "Welcome to Bookline!";
	}

	@Override
	public void execute() 
	{			
		while (aCustomer == null)
		{
			IOManager.clearScreen();
			IOManager.printTitle(getName());
			
			System.out.println("\n1 : Login \n2 : Create an account\n");
			
			try
			{
				printMessage();
				String userInput = IOManager.readInput("Select an option (q to quit) : ");
				
				if (userInput.equals("q"))
				{
					IOManager.clearScreen();
					System.exit(0);
				}
				
				int option = Integer.parseInt(userInput);
				
				System.out.println();
				
				switch (option) 
				{
					case 1:
						login();
						break;
					case 2:
						signup();
						break;
				}
				
			}
			catch (Exception e)
			{
				// Nothing to do
			}
		}
		
		System.out.println("Logged in!");
		
		// Initialize the shop model
		ShopModel.init(aCustomer);
		
		// Execute main menu
		new MainMenu().execute();
	}
	
	private void login ()
	{		
		String email = IOManager.readInput("Enter your email address : ");
		aMessage = "Wrong email, please try again...";
		aCustomer = Customer.get(email);
	}
	
	private void signup() throws ParseException
	{
		String email      = IOManager.readInput("Enter you email address : ");
		String name       = IOManager.readInput("Enter your name : ");
		String address    = IOManager.readInput("Enter your address : ");
		String birthday   = IOManager.readInput("Enter your birthday (yyyy-mm-dd) : "); 
		String creditCard = IOManager.readInput("Enter your credit card # : ");
		
		aCustomer = Customer.create(email, name, address, birthday, creditCard);
		aMessage = "Invalid input. Please try again...";
	}
	
	private void printMessage()
	{
		if(aMessage.equals("")) return;
		System.out.println(aMessage);
		aMessage = "";
	}
}
