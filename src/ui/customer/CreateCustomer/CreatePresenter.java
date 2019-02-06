package ui.customer.CreateCustomer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Customer;
import memService.MemoryService;

public class CreatePresenter {
	private CreateCustomerView view;
	
	public CreatePresenter(CreateCustomerView inst) {
		this.view = inst;
	}

	 public void start() {
        view.setCreatePresenter(this);
        view.open();     
       
    }

	 
	 public void cancel() {
		view.close();
		
	}

	public void submitValues(String first,String last,String addr,String Email,String phone,JPanel contentPane,MemoryService memService) {
		
	
		Customer c = new Customer(first,last,phone,addr,Email);
		
		List<Customer> arr =memService.getCustomers();
		arr.add(c);
		
		view.updateList(arr);
		
		view.showInfo("Client Successfully Submited");
		
		view.returnToCustomerFrame();
	}
	
	
	
}
