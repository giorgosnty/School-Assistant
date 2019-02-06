package domain;


import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MonthlySubscription extends Subscription {
	
	private ArrayList<Payment> payments = new ArrayList<Payment>();
	private ArrayList<Presence> sub_pres = new ArrayList<Presence>();
	private ArrayList<Slot> slots = new ArrayList<Slot>();
	private LocalDateTime  startingDate; //kk
	private LocalDateTime endingDate; //kk
	private int absences; //kk
	private boolean active;
	
	
	private double remainingAmount;
	
	
	
	public MonthlySubscription(Customer customer, LocalDateTime date  ){
		super(customer);
		startingDate = date;
		endingDate = date.plusWeeks(4);
		absences = 0;
		totalPrice = 0;
		active = true;
		
	}
	
	public void addSlot (Slot slot){
		if (slot!=null) {
			slots.add(slot);
			totalPrice += slot.getLesson().getPricePackage();
			remainingAmount = totalPrice;
		}	
	}
	
	
	
	public boolean hasSlot(Slot slot) {
		if (slot!=null) {	
			List<Slot> result = slots.parallelStream().filter(s->s.equals(slot)).collect(Collectors.toList());
			if (result.size()>0) {
				return true;
			} 
		}
		return false;
	}
	
	
	//tested
	public boolean submitPayment(double amount) {
		if (amount<=remainingAmount && !this.isPaid())	 {
			Payment paym = new Payment (LocalDateTime.now(), amount);
			remainingAmount -= amount;
			if (remainingAmount ==0) {
				setPaid();
			}
			return true;
		}
		return false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public LocalDateTime getEndingDate() {
		return endingDate;
	}
	
	public LocalDateTime getStartingDate() {
		return startingDate;
	}
	
	public void setInactive() {
		this.active =  false;
	}
	
	public void setTotalPrice(double amount) {
		super.totalPrice = amount;
		remainingAmount = amount;
	}
	
	
	//tested
	public void calculateAbsences(){
		absences = ((slots.size() * 4 )- sub_pres.size());
		
	}
	
	public int getAbsences() {
		return absences;
	}
	
	//tested
	public void addPresence(Presence p) {
		if (p!=null) {
			if (this.hasSlot(p.getSlot())) {
				sub_pres.add(p);
			}
		}
	}
	
	public double getRemainingAmount() {
		return remainingAmount;
	}
	
	public void setEndingDate(LocalDateTime date) {
		this.endingDate = date;
	}
	
	public ArrayList<Slot> getSlots() {
		return slots;
	}
	
	public ArrayList<Presence> getPresences() {
		return sub_pres;
	}
	
	public void removeSlot(Slot s) {
		if(s != null){
			slots.remove(s);
		}
	}
	
	
}
