package com.example.jdbcProjekt.dbTable;

public class Meal
{
	private long idMeal;
	private String name;
	private int amount;
	private double price;
	
	public Meal()
	{
		
	}
	
	public Meal(String name, int amount, double price)
	{
		this.name = name;
		this.amount = amount;
		this.price = price;
	}
	
	public long getIdMeal()
	{
		return idMeal;
	}
	
	public void setIdMeal(long idMeal)
	{
		this.idMeal = idMeal;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
}