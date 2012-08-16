package test;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.*;

public class DatabaseAccess 
{
	Connection con = null;
	
	public boolean Connect(String connectionString, String userName, String password)
	{
		
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(connectionString, userName, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return (con != null);
	}
	
	private void createTables()
	{
		try
		{
			PreparedStatement stmt = con.prepareStatement("create table Student(name varchar(30), age int)");
			stmt.execute(); 
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		boolean initialized = true;
		String tableName = "Student";
		String query = "select * from " + tableName;
		try 
		{
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			initialized = false;
			System.out.println("Student table does not exist.");
			// TODO Auto-generated catch block
			System.out.println("Exception in stmt.Execute. Message = " + e.getMessage());
			e.printStackTrace();
		}
		
		if(initialized)
		{
			System.out.println("Database already initialized. Returning.");
			return;
		}
		
		createTables();
	}
	
	private void clearScreen()
	{
		//Runtime.getRuntime().exec("cls");
		for(int i = 0; i < 25; i++)
			System.out.println();
	}
	
	public void displayMemberMenu()
	{
		try
		{
			clearScreen();
			System.out.println("1. Add Member");
			System.out.println("2. Delete Member");
			System.out.println("3. Exit");
			System.out.println("Please enter your choice : ");
			DataInputStream dis = new DataInputStream(System.in);
			int choice = Integer.parseInt(dis.readLine());
			
			switch(choice)
			{
			case 1:
			case 2:
			case 3:
				break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void displayMainMenu()
	{
		while(true)
		{
			try
			{
				clearScreen();
				System.out.println("1. Member");
				System.out.println("2. Book");
				System.out.println("3. Exit");
				System.out.println("Please enter your choice : ");
				DataInputStream dis = new DataInputStream(System.in);
				int choice = Integer.parseInt(dis.readLine());
				
				switch(choice)
				{
				case 1:
					displayMemberMenu();
					break;
				case 2:
				case 3:
					System.out.println("Exiting...");
					System.exit(0);
					break;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		DatabaseAccess dbAccess = new DatabaseAccess();
		String connectionString = "jdbc:odbc:access";
		boolean result = dbAccess.Connect(connectionString, "a", "a");
		System.out.println("Connect result = " + result);
		dbAccess.init();
		
		dbAccess.displayMainMenu();
	}
}
