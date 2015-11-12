package com.example.jdbcProjekt.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcProjekt.dbTable.*;

public class MealManager 
{
	private Connection connection;
	
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	private String createTableMeal = "CREATE TABLE Meal(idMeal BIGINT UNIQUE GENERATED BY DEFAUL AS IDENTITY)"+
			"idClient BIGINT, name VARCHAR(30), amount INTEGER, price DECIMAL, "+
			"FOREIGN KEY (idClient) REFERENCES Magazyn(idClient) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	private PreparedStatement addMealStmt;
	private PreparedStatement deleteOneMealStmt;
	private PreparedStatement deleteAllMealsStmt;
	private PreparedStatement updateMealStmt;
	private PreparedStatement getAllMealsStmt;
	
	private Statement statement;
	
	public MealManager()
	{
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			boolean firstTableExists = false;
			while (rs.next()) {
				if ("Meal".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
				if ("Client".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					firstTableExists = true;
				}
			}

			if (!tableExists && firstTableExists)
				statement.executeUpdate(createTableMeal);

			addMealStmt = connection
					.prepareStatement("INSERT INTO Meal(idClient, name, amount, price) VALUES (?, ?, ?, ?)");
			deleteOneMealStmt = connection
					.prepareStatement("DELETE FROM Meal WHERE idMeal = ?");
			deleteAllMealsStmt = connection
					.prepareStatement("DELETE FROM Meal");
			updateMealStmt = connection
					.prepareStatement("UPDATE Meal SET idClient = ? WHERE idMeal = ?");
			getAllMealsStmt = connection
					.prepareStatement("SELECT idMeal, idClient, name, amount, price FROM Meal");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	MealManager mealManager = new MealManager();
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public List<Meal> getAllOrders() {
		List<Meal> meals = new ArrayList<Meal>();

		try {
			ResultSet rs = getAllMealsStmt.executeQuery();

			while (rs.next()) {
				Meal meal = new Meal();
				meal.setIdMeal(rs.getInt("idMeal"));
				meal.setIdClient(rs.getLong("idClient"));
				meal.setName(rs.getString("name"));
				meal.setAmount(rs.getInt("amount"));
				meal.setPrice(rs.getDouble("price"));
				meals.add(meal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return meals;
	}
}
