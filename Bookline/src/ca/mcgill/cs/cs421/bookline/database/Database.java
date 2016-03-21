package ca.mcgill.cs.cs421.bookline.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
	private static final String URL      = "jdbc:postgresql://comp421.cs.mcgill.ca/cs421";
	private static final String USERNAME = "cs421g30";
	private static final String PASSWORD = "[comp421_69]";
	
	private static Database aInstance;
	
	private Connection aConnection;
	private Statement  aStatement;
	
	public static Database get ()
	{
		if (aInstance == null) aInstance = new Database();
		return aInstance;
	}
	
	private Database() 
	{
		connect();	
	}
	
	private void connect ()
	{
		try 
		{			
			// Set the driver
			DriverManager.registerDriver(new org.postgresql.Driver());
			
			// Connect to the database
			aConnection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			// Initialize the statement
			aStatement = aConnection.createStatement();
		} 
		catch (Exception e) 
		{
			System.out.println("Error : Cannot connect to the database.");
			System.exit(1);
		}
	}
	
	private Result resultSetToResult (ResultSet pResult) throws SQLException
	{
		ResultSetMetaData metadata = pResult.getMetaData();
		
		int nbColumns = metadata.getColumnCount();
		Result result = new Result();
		
		while(pResult.next())
		{
			Row row = new Row();
			
			for(int i=1; i <= nbColumns; i++)
			{
				row.add(metadata.getColumnName(i), pResult.getObject(i));
			}
			
			result.addRow(row);
		}
		
		return result;
	}
	
	public Result query(String pQuery)
	{
		try 
		{
			ResultSet resultSet;
			String operation = pQuery.split(" ")[0].toLowerCase();
			
			if (operation.equals("insert") || operation.equals("update"))
			{
				aStatement.executeUpdate(pQuery); 
				return new Result();
			}
			else
			{
				resultSet = aStatement.executeQuery(pQuery);
				return resultSetToResult(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			return null;
		}
	}
	
	public void close()
	{
		try 
		{
			aConnection.close();
		} 
		catch (SQLException e) 
		{
			// Nothing to do
		}
	}
}
