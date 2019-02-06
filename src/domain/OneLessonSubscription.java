package domain;

import java.time.LocalDateTime;

public class OneLessonSubscription extends Subscription {
	private Payment payment;
	private Slot slot;
	private LocalDateTime date;
	private Presence p;
	
	public OneLessonSubscription(Customer customer, LocalDateTime date) {
		super(customer);
		this.date = date;
		
	}
	
	//tested
	public void addSlot(Slot slot) {
		if (slot!= null && this.slot==null) {	
			this.slot = slot;
			totalPrice = slot.getLesson().getpriceSingle();
		}
	}
	
	//tested
	public boolean submitPayment(double amount) {
		if (amount == slot.getLesson().getpriceSingle() && !this.isPaid()) {
			Payment paym = new Payment (LocalDateTime.now(), amount);
			this.payment= paym;
			setPaid();
			return true;
		} else {
			return false;
		}
	}	
	
	public Slot getSlot() {
		return slot;
	}
	
	public void setSlot(Slot slot){
		this.slot = slot;
	}
	
	//tested
	public void addPresence(Presence p) {
		if (p!=null) {
			if (p.getSlot().equals(this.slot)){
				this.p = p;
			}
		}
			
	}
	
	public void setTotalPrice(double amount) {
		this.totalPrice = amount;
	}
	
	public Presence getPresence() {
		return p;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	

}
