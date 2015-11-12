package com.example.jdbcProjekt.dbManager;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.example.jdbcProjekt.dbTable.Client;

public class ClientManagerTest {

	ClientManager clientManager = new ClientManager();
	
	private final static int SEAT_1 = 1;
	private final static double PAYMENT_1 = 350;
	private final static boolean WINE_1 = true;
	
	@Test
	public void checkConnection() 
	{
		assertNotNull(clientManager.getConnection());
	}
	
	@Test
	public void checkAdding() throws SQLException
	{
		Client Client = new Client(SEAT_1, PAYMENT_1, WINE_1);
		
		clientManager.removeClients();
		assertEquals(1,clientManager.addClient(Client));
		
		List<Client>idClient = clientManager.getAllClients();
		Client idClientRetrieved = idClient.get(0);
		
		assertEquals(SEAT_1, idClientRetrieved.getSeatNumber());
		assertEquals(PAYMENT_1, PAYMENT_1, idClientRetrieved.getPayment());
		assertEquals(WINE_1, idClientRetrieved.getWine());
	}
}
