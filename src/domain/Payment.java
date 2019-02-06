package domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Payment {

	private LocalDateTime date;
	private double amount;
	
	
	
	public Payment(LocalDateTime date,double amount){
		this.date=date;
		this.amount = amount;
	}
	
	//Setters
	public void setDate(LocalDateTime date){
		this.date=date;

	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	//Getters
	public LocalDateTime getDate(){
		return date;
	}
	
	public double getAmount(){
		return amount;
	}
	

}


