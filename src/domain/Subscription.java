package domain;

public abstract class Subscription {
	
	 double totalPrice; //kk
	private boolean paid; //kk
	
	private Customer customer; //kk
	

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public Subscription(Customer customer) {
		this.customer = customer;
		paid = false;
		
		totalPrice = 0;
	}
	
	
	 public boolean isPaid() {
		return paid;
	}
	 
	 public void setPaid() {
		 paid = true;
	 }
	 
	 public Customer getCustomer() {
		 return customer;
	 }
	 
//	 public abstract void submitPayment();

}
