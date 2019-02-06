package ui.SingleLessonEnrollment;

import java.util.List;

import domain.Customer;
import domain.OneLessonSubscription;
import domain.Slot;
import ui.View;

public interface SingleLessonEnrollView extends View{
	
	public void setPresenter(SingleLessonEnrollPresenter presenter);
	
	public List<Customer> findCustomerByLastName(String lastname);
	
	public List<Customer> findCustomerByTelephone(String telephone);
	
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone);
	
	public void showCustomers(List<Customer> customers);
	
	public Customer getSelectedCustomer(int index);
	
	public Slot getSelectedSlot(int index);
	
	public void updateMemory(Customer c, OneLessonSubscription ol);
}
