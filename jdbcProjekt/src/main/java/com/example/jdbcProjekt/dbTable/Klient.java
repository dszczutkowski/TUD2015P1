package com.example.jdbcProjekt.dbTable;

public class Klient
{
	private long idKlient;
	private int nrStolika;
	private double doZaplaty;
	private boolean wino;
	
	public Klient(int nrStolika, double doZaplaty, boolean wino)
	{
		this.nrStolika = nrStolika;
		this.doZaplaty = doZaplaty;
		this.wino = wino;
	}
	
	public long getId()
	{
		return idKlient;
	}
	
	public void setId(long idKlient)
	{
		this.idKlient = idKlient;
	}
	
	public int getNrStolika()
	{
		return nrStolika;
	}
	
	public void setNrStolika(int nrStolika)
	{
		this.nrStolika = nrStolika;
	}
	
	public double getDoZaplaty()
	{
		return doZaplaty;
	}

	public void setDoZaplaty(double doZaplaty)
	{
		this.doZaplaty = doZaplaty;
	}
	
	public boolean getWino()
	{
		return wino;
	}
	
	public void setWino(boolean wino)
	{
		this.wino = wino;
	}
}
