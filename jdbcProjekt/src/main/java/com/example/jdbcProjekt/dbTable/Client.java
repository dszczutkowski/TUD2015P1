package com.example.jdbcProjekt.dbTable;

public class Client
{
	private long idClient;
	private int seatNumber;
	private double payment;
	private boolean wine;
	
	public Client()
	{
		
	}
	
	public Client(int seatNumber, double payment, boolean wine)
	{
		this.seatNumber = seatNumber;
		this.payment = payment;
		this.wine = wine;
	}
	
	public long getIdClient()
	{
		return idClient;
	}
	
	public void setIdClient(long idClient)
	{
		this.idClient = idClient;
	}
	
	public int getSeatNumber()
	{
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber)
	{
		this.seatNumber = seatNumber;
	}
	
	public double getPayment()
	{
		return payment;
	}

	public void setPayment(double payment)
	{
		this.payment = payment;
	}
	
	public boolean getWine()
	{
		return wine;
	}
	
	public void setWine(boolean wine)
	{
		this.wine = wine;
	}
}