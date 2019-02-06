package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Customer {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String emailAddress;
	private int credit = 0;
	private ArrayList<Presence> presences = new ArrayList<Presence>();
	
	private ArrayList<MonthlySubscription> mon_subs = new ArrayList<MonthlySubscription>();
	private ArrayList<OneLessonSubscription> ol_subs = new ArrayList<OneLessonSubscription>();
	
	
	//Constructor
	public Customer() {
		
		
	}
	
	public Customer(String firstName,String lastName,String phoneNumber,String address,String emailAddress){
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.emailAddress=emailAddress;
		
		
		
	}
	
	//Setters
	public void setFirstName(String firstName){
		this.firstName =firstName;
	}
	

	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	

	public void setAddress(String address){
		this.address=address;
	}
	

	public void setEmailAddress(String  emailAddress){
		this.emailAddress=emailAddress;
	}
	
	
	//Getters
	public String getFirstName(){
		return firstName;
	}
	

	public String getLastName(){
		return lastName;
	}
	

	public String getPhoneNumber(){
		return phoneNumber;
	}
	

	public String getAddress(){
		return address;
	}
	

	public String getEmailAddress(){
		return emailAddress;
	}
	
	

	

	
	
	
	
	public String toString() {
		
		return ("First Name: " + firstName + "\n" + "Last Name: " + lastName + "\n" + "Phone Number: " + phoneNumber + "\n"
		+ "Address: " + address + "\n" + "EmailAddress: " + emailAddress);
		
	}
	
	//tested
	public boolean isEligibleForNewSub() {
		
		MonthlySubscription last_sub = this.getCurrentSubscription();
		boolean result = last_sub.isPaid();
		return result;
		
	}
	
	//tested
	private boolean hasSlot(Slot slot){
			
			MonthlySubscription last_sub = this.getCurrentSubscription();
			if(last_sub!=null){
			boolean result = last_sub.hasSlot(slot);
			return result;
			}
			return false;
		}
	
	//tested
	public boolean submitPresence(Slot slot) {
		if (!hasSlot(slot)) {
			return false;
		} else {
			
			LocalDateTime datetime = LocalDateTime.now();
			MonthlySubscription last_sub = this.getCurrentSubscription();
			
				Presence pres = new Presence(datetime, slot);
				presences.add(pres);
				last_sub.addPresence(pres);
				return true;
			
			
		}
		
	}
	
	//tested
	public boolean submitPaymentMonthly( double amount) {
		return getCurrentSubscription().submitPayment(amount);
		
		
		
	}
	
	//tested
	public boolean submitPaymentOL (double amount, OneLessonSubscription o_l_s) {
		if (amount == o_l_s.getSlot().getLesson().getpriceSingle()) {
			o_l_s.submitPayment(amount);
			return true;
		} else {
			return false;
		}
	}
	
	//tested
	public MonthlySubscription getCurrentSubscription() {
		if(mon_subs.size()>0){
			int index = (this.mon_subs).size() - 1;
			return mon_subs.get(index);
		}
		return null;
	}
	
	//tested
	public MonthlySubscription getLastSubscription() {
		if(mon_subs.size()>1){
		int index = (this.mon_subs).size() - 2;
		return mon_subs.get(index);
		}
		return null;
	}
	
	
	public int getCredit() {
		MonthlySubscription last_sub = this.getCurrentSubscription();
		if (last_sub!=  null && last_sub.isActive()) {
			LocalDateTime date = last_sub.getEndingDate();
			LocalDateTime datenow = LocalDateTime.now();
			if (datenow.isAfter(date)) {
				last_sub.setInactive();
				last_sub.calculateAbsences();
				credit = last_sub.getAbsences();
				

			}
		}
		
		return credit;
	}
	
	//tested
	public void addMSub(MonthlySubscription sub) {
		if (sub!=null) {
			this.mon_subs.add(sub);
		}
	}
	
	//tested
	public void addOLSub(OneLessonSubscription sub) {
		if (sub!=null) {
			this.ol_subs.add(sub);
		}
	}
	
	public ArrayList<MonthlySubscription> getMonthlySubs() {
		return mon_subs;
	}
	
	public ArrayList<OneLessonSubscription> getOLSubs() {
		return ol_subs;
	}
	
	
	
	public void removeOlSub(OneLessonSubscription s) {
		if(s != null){
			ol_subs.remove(s);
		}
	} 
	
	public boolean wasPresent(Slot s, LocalDateTime day){
		if(s!=null && day!=null){
			List<Presence> ps = presences.parallelStream().filter(p->p.getSlot().equals(s)).collect(Collectors.toList());
			if(ps.size()>0){
				for(Presence p : ps){
					if(Math.abs(p.getDate().compareTo(day))<7)
						return true;
				}
			}
		}
		return false;
	}
	
	
	
}
