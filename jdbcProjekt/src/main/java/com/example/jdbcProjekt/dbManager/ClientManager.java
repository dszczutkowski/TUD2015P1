package com.example.jdbcProjekt.dbManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcProjekt.dbTable.Client;

public class ClientManager 
{
	private Connection connection;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private String createTableClient = "CREATE TABLE Client(idClient BIGINT GENERATED BY DEFAULT AS IDENTITY, seatNumber INTEGER, payment DECIMAL, wine BOOLEAN)";
	
	private PreparedStatement addClientStmt;
	private PreparedStatement deleteOneClientStmt;
	private PreparedStatement deleteAllClientsStmt;
	private PreparedStatement getAllClientsStmt;
	private PreparedStatement updateClientsStmt;
	private PreparedStatement countAllClientsStmt;
	
	private Statement statement;
	
	public ClientManager()
	{
		try
		{
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next())
			{
				if("Client".equalsIgnoreCase(rs.getString("TABLE_NAME")))
				{
					tableExists = true;
					break;
				}
			}
			
			if(!tableExists)
				statement.executeUpdate(createTableClient);
			
			addClientStmt = connection
					.prepareStatement("INSERT INTO Client (seatNumber, payment, wine) VALUES (?, ?, ?)");
			updateClientsStmt = connection
					.prepareStatement("UPDATE Client SET wine = ? WHERE payment > ?");
			deleteOneClientStmt = connection
					.prepareStatement("DELETE FROM Client WHERE idClient = ?");
			deleteAllClientsStmt = connection
					.prepareStatement("DELETE FROM Client;");
			getAllClientsStmt = connection
					.prepareStatement("SELECT idClient, seatNumber, payment, wine FROM Client");
			countAllClientsStmt = connection
					.prepareStatement("SELECT COUNT(*) FROM Client");
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	public Connection getConnection()
	{
		return connection;
	}
	
	public int getCount()
	{
		try 
		{
			ResultSet result = countAllClientsStmt.executeQuery();
			if(result.next())
			{
				return result.getInt(1);
			}
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int removeOneClient(Client idClient)
	{
		int count = 0;
		try
		{
			deleteOneClientStmt.setLong(1, idClient.getIdClient());
			count = deleteOneClientStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public void removeClients() throws SQLException
	{
		try
		{
			connection.setAutoCommit(false);
			deleteAllClientsStmt.executeUpdate();
			connection.commit();
		} catch(SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		} finally
		{
			connection.setAutoCommit(true);
		}
	}
	
	public void updateClients(boolean wine, double payment) throws SQLException
	{
		int records = getCount();
		List<Client>clients = getAllClients();
		
		updateClientsStmt.setBoolean(1, wine);
		updateClientsStmt.setDouble(2, payment);
		
		for(int i=0; i<records; i++)
		{
			Client idClientRetrieved = clients.get(i);
			updateClientsStmt.setLong(3, idClientRetrieved.getIdClient());
			
			try
			{
				updateClientsStmt.executeUpdate();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public int addClient(Client idClient)
	{
		int count = 0;
		
		try
		{
			addClientStmt.setInt(1, idClient.getSeatNumber());
			addClientStmt.setDouble(2, idClient.getPayment());
			addClientStmt.setBoolean(3, idClient.getWine());
			
			count = addClientStmt.executeUpdate();
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<Client> getAllClients()
	{
		List<Client>clients = new ArrayList<Client>();
		
		try
		{
			ResultSet rs = getAllClientsStmt.executeQuery();
			
			while(rs.next())
			{
				Client c = new Client();
				c.setIdClient(rs.getInt("idClient"));
				c.setSeatNumber(rs.getInt("seatNumber"));
				c.setPayment(rs.getDouble("payment"));
				c.setWine(rs.getBoolean("wine"));
				clients.add(c);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return clients;
	}
}