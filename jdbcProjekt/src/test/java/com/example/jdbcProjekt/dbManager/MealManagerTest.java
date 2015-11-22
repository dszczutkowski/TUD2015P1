package com.example.jdbcProjekt.dbManager;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

import com.example.jdbcProjekt.dbTable.Client;
import com.example.jdbcProjekt.dbTable.Meal;

public class MealManagerTest 
{
	MealManager mealManager = new MealManager();
	ClientManager clientManager = new ClientManager();
	
	private final static int SEAT_1 = 8;
	private final static double PAYMENT_1 = 888;
	private final static boolean WINE_1 = true;
	
	private final static String NAME_1 = "Salmon";
	private final static int AMOUNT_1 = 2; 
	private final static double PRICE_1 = 35;
	
	Client client = new Client(SEAT_1, PAYMENT_1, WINE_1);
	
	@Test
	public void checkConnection() 
	{
		assertNotNull(mealManager.getConnection());
	}
	
	@Test
	
	public void checkAdding() throws SQLException
	{
		clientManager.addClient(client);
		
		Meal meal = new Meal(NAME_1, AMOUNT_1, PRICE_1);
		
		mealManager.removeMeals();
		assertEquals(1, mealManager.addMeal(meal));
		
		List<Meal>meals = mealManager.getAllMeals();
		Meal mealRetrieved = meals.get(0);
		
		assertEquals(NAME_1, mealRetrieved.getName());
		assertEquals(AMOUNT_1, mealRetrieved.getAmount());
		assertEquals(PRICE_1, mealRetrieved.getPrice(), 0.00f);
	}

	@Test
	public void checkKeyUpdate() throws SQLException
	{
		Meal meal = new Meal(NAME_1, AMOUNT_1, PRICE_1);
		
		mealManager.removeMeals();
		
		assertEquals(1, mealManager.addMeal(meal));
		mealManager.setKeyToNull(meal);
		
		List<Meal>meals = mealManager.getAllMeals();
		Meal mealRetrieved = meals.get(0);
		
		assertNotNull(mealRetrieved.getIdClient());
	}
	
	@Test
	public void checkKeyUpdate2() throws SQLException
	{
		Meal meal = new Meal(NAME_1, AMOUNT_1, PRICE_1);
		
		mealManager.removeMeals();
		
		clientManager.addClient(client);
		assertEquals(1, mealManager.addMeal(meal));
		mealManager.updateKey(meal, client);
		
		assertEquals(meal.getIdClient(), client.getIdClient());
	}
	
	@Test
	public void checkRemoveOneMeal() throws SQLException
	{
		Meal meal = new Meal();
		mealManager.removeMeals();
		
		assertEquals(0,mealManager.removeOneMeal(meal));
	}
}