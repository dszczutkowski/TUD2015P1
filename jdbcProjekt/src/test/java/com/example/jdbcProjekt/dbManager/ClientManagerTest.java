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
	
	private final static int SEAT_2 = 4;
	private final static double PAYMENT_2 = 80;
	private final static boolean WINE_2 = false;
	
	private final static int SEAT_3 = 6;
	private final static double PAYMENT_3 = 650;
	private final static boolean WINE_3 = true;
	
	private final static int MIN_PAYMENT = 100;
	
	@Test
	public void checkConnection() 
	{
		assertNotNull(clientManager.getConnection());
	}
	
	@Test
	public void checkAdding() throws SQLException
	{
		Client client = new Client(SEAT_1, PAYMENT_1, WINE_1);
		
		clientManager.removeClients();
		assertEquals(1, clientManager.addClient(client));
		
		List<Client>clients = clientManager.getAllClients();
		Client idClientRetrieved = clients.get(0);
		
		assertEquals(SEAT_1, idClientRetrieved.getSeatNumber());
		assertEquals(PAYMENT_1, 1, idClientRetrieved.getPayment());
		assertEquals(WINE_1, idClientRetrieved.getWine());
	}
	
	@Test
	public void checkRemoveOneClient() throws SQLException
	{
		Client client = new Client(SEAT_1, PAYMENT_1, WINE_1);
	
		clientManager.removeClients();
		
		assertEquals(1, clientManager.addClient(client));
		assertEquals(0, clientManager.removeOneClient(client));
	}
	
	@Test
	public void checkUpdate() throws SQLException
	{
		clientManager.removeClients();
		
		Client clientToUpdate = new Client(SEAT_2, PAYMENT_2, WINE_2);
		
		assertEquals(1, clientManager.addClient(clientToUpdate));
		
		clientManager.updateClients(false, MIN_PAYMENT);
		
		List<Client>clients = clientManager.getAllClients();
		Client clientRetrieved = clients.get(0);
		
		assertTrue(clientRetrieved.getPayment()>MIN_PAYMENT);
		assertEquals(clientRetrieved.getWine(), true);
	}
	
	@Test
	public void checkCounting() throws SQLException
	{
		clientManager.removeClients();
		
		Client c1 = new Client(SEAT_1, PAYMENT_1, WINE_1);
		assertEquals(1, clientManager.addClient(c1));
		Client c2 = new Client(SEAT_2, PAYMENT_2, WINE_2);
		assertEquals(1, clientManager.addClient(c2));
		Client c3 = new Client(SEAT_3, PAYMENT_3, WINE_3);
		assertEquals(1, clientManager.addClient(c3));
		
		assertEquals(3, clientManager.getCount());
	}
}
