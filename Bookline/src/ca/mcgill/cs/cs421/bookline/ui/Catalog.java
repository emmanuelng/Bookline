package ca.mcgill.cs.cs421.bookline.ui;

public abstract class Catalog implements Option
{
	private String aProductName;
	
	public Catalog(String pProductName) 
	{
		aProductName = pProductName;
	}

	@Override
	public void execute() 
	{
		while (true)
		{
			IOManager.clearScreen();
			IOManager.printTitle(getName());
			
			System.out.println();
			
			System.out.println("The following " + aProductName + "s are available :\n");
			
			displayProducts();
			
			System.out.println();
			
			try
			{
				String input = IOManager.readInput("Select a " + aProductName + " (q to return) : ");
				
				if (input.equals("q")) break;
				
				int index = Integer.parseInt(input) - 1;
				viewDetails(index);
			}
			catch (Exception e)
			{
				// Nothing to do
			}
		}
	}

	protected abstract void viewDetails(int index);

	protected abstract void displayProducts();

}
