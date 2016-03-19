package ca.mcgill.cs.cs421.bookline.ui;

import java.util.Scanner;

public class IOManager 
{
	private static Scanner sc = new Scanner(System.in);
	
	public static String readInput (String pPrompt)
	{
		System.out.print(pPrompt);
		return sc.nextLine();
	}
	
	public static void clearScreen ()
	{
		for (int i = 0; i < 10000; i++) 
		{
			System.out.println();	
		}
	}
	
	public static void printTitle (String pTitle)
	{
		System.out.println("== " + pTitle);
	}
}
