package ui.SubmitPresence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Customer;
import domain.MonthlySubscription;
import domain.OneLessonSubscription;
import domain.Slot;
import memService.MemoryService;
import ui.MainFrame;
import ui.SingleLessonEnrollment.SingleLessonEnrollView;

public class SubmitPresencePresenter {
	private SubmitPresenceView view;
	private Customer c;
	
	public SubmitPresencePresenter(SubmitPresenceView view){
		this.view = view;
	}
	
	public void start() {
        view.setPresenter(this);
        view.open();
    }
	
	public void goBack(MemoryService data) {
		MainFrame m = new MainFrame(data);
    	m.open();
    	view.close();
	}
	
	public void searchCustomers(String lastname, String telephone){
		List<Customer> customers = null;
    	if(lastname.equals("")) {
    		customers = view.findCustomerByTelephone(telephone);
    	} else if(telephone.equals("")) {
    		customers = view.findCustomerByLastName(lastname);
    	} else {
    		customers = view.findCustomerByLastNameAndTelephone(lastname, telephone);
    	}
    	view.showCustomers(customers);
	}
	
	public void showSlots(int customerIndex) {
		if(customerIndex < 0) {
			view.showError("Error: Customer has not been selected");
		} else{
			c = view.getSelectedCustomer(customerIndex);
			MonthlySubscription ms = c.getCurrentSubscription();
			List<Slot> slots = new ArrayList<Slot>();
			if(ms!= null){
				if(ms.isActive()){
					slots.addAll(ms.getSlots());
				}
			}
			view.showSlots(slots);
		}
	}
	
	public void submitPresence(int slotIndex){
		if(slotIndex < 0) {
			JOptionPane.showMessageDialog(null, "Error: Slot has not been selected");
		} else {
			Slot s = view.getSelectedSlot(slotIndex);
			boolean result = view.updateMemory(c, s);
			if(result){
				view.showInfo("Presence successfully submitted");
			} else {
				view.showError("Error: Presence not submitted");
			}
		}
	}
}
