package com.example.jdbcProjekt.dbManager;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.example.jdbcProjekt.dbTable.Client;
import com.example.jdbcProjekt.dbTable.Meal;

public class MealManagerTest 
{
	MealManager mealManager = new MealManager();
	ClientManager clientManager = new ClientManager();
	
	
	
	@Test
	public void checkConnection() 
	{
		assertNotNull(mealManager.getConnection());
	}
	
	@Test
	public void checkRemovingOneMeal() throws SQLException
	{
		Meal meal = new Meal();
		mealManager.removeMeals();
		
		assertEquals(0,mealManager.removeOneMeal(meal));
	}
}