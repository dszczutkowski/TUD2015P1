package com.example.jdbcProjekt.dbTable;

public class Danie
{
	private long idDanie;
	private String nazwa;
	private int ilosc;
	private double cena;
	
	public Danie()
	{
		
	}
	
	public Danie(String nazwa, int ilosc, double cena)
	{
		this.nazwa = nazwa;
		this.ilosc = ilosc;
		this.cena = cena;
	}
	
	public long getIdDanie()
	{
		return idDanie;
	}
	
	public void setIdDanie(long idDanie)
	{
		this.idDanie = idDanie;
	}
	
	public String getNazwa()
	{
		return nazwa;
	}
	
	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}
	
	public int getIlosc()
	{
		return ilosc;
	}
	
	public void setIlosc(int ilosc)
	{
		this.ilosc = ilosc;
	}
	
	public double getCena()
	{
		return cena;
	}
	
	public void setCena(double cena)
	{
		this.cena = cena;
	}
}
