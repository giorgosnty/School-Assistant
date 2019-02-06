package ui.SingleLessonEnrollment;

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
import ui.PaymentsOptions.PaySub.PaySubView;

public class SingleLessonEnrollPresenter {
	private SingleLessonEnrollView view;
	
	public SingleLessonEnrollPresenter(SingleLessonEnrollView view){
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
	
	public void enrollSlot(int customerIndex, int slotIndex, boolean next, boolean second) {
		if(customerIndex < 0 || slotIndex < 0) {
			view.showError("Error: Customer or slots has not been selected");
		} else{
			if(!next && !second){
				view.showError("Error: Date has not been selected");
			}else {
				Customer c = view.getSelectedCustomer(customerIndex);
				Slot s = view.getSelectedSlot(slotIndex);
				int today = LocalDateTime.now().getDayOfWeek().getValue();
				int distance = s.getDay().getValue() - today;
				if(distance < 0){
					distance += 7;
				}
				OneLessonSubscription ol = null;
				if(next){
					ol = new OneLessonSubscription(c, LocalDateTime.now().plusDays(distance));
				} else{
					ol = new OneLessonSubscription(c, LocalDateTime.now().plusDays(distance+7));
				}
				ol.addSlot(s);
				view.updateMemory(c,ol);
				view.showInfo("Subscription successfully added!");
			}
		}
	}
}
