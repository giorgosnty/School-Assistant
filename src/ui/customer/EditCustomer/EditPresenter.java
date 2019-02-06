package ui.customer.EditCustomer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Customer;
import memService.MemoryService;

public class EditPresenter {

	private EditCustomerView view;
	private Customer temp_c;
	
	public EditPresenter(EditCustomerView inst) {
		this.view = inst;
	}

	 public void start() {
	        view.setEditPresenter(this);
	        view.open(); 
	        
	       
	    }

	public void cancel() {
		view.close();
		
	}

	public void fillBlanksForEdit(String first, String last, String email, String phone, String address,Customer c) {
		view.setLastName(last);
		view.setFirstName(first);
		view.setAddress(address);
		view.setPhone(phone);
		view.setEmail(email);
		this.temp_c = c;
	//	ArrayList<Customer> ar =memService.getCustomers();
		//ar.remove(c);
		
		
	}

	public void submitValues(String first, String last, String addr, String email, String phone, MemoryService memService) {
		memService.editCustomer(first, last, addr, email, phone, temp_c);

		view.showInfo("Client Successfully Submited");
		
		view.returnToCustomerFrame();
	}
	
	
}
