package ui.SubmitPresence;

import java.util.List;

import domain.Customer;
import domain.Slot;
import ui.View;

public interface SubmitPresenceView extends View {
	public void setPresenter(SubmitPresencePresenter presenter);
	
	public Customer getSelectedCustomer(int customerIndex);
	
	public Slot getSelectedSlot(int index);
	
	public void showCustomers(List<Customer> customers);
	
	public List<Customer> findCustomerByLastName(String lastname);
	
	public List<Customer> findCustomerByTelephone(String telephone);
	
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone);
	
	public void showSlots(List<Slot> slots);
	
	public boolean updateMemory(Customer c, Slot s);
}
