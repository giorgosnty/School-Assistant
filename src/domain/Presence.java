package domain;


import java.time.LocalDateTime;
import java.util.Date;

public class Presence {

	private LocalDateTime date;
	private Slot slot;
	
	
	public Presence(LocalDateTime date, Slot slot){
		this.date = date;
		this.slot = slot;
	}
	
	public void  setDate(LocalDateTime date){
		this.date=date;
	}
	
	public LocalDateTime getDate(){
		return date;
	}
	
	public Slot getSlot() {
		return this.slot;
	}

	
}
