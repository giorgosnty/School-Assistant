package ui.customer;
import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import ui.MainFrame;
import ui.customer.CreateCustomer.CreateCustomerJFrame;
import ui.customer.CreateCustomer.CreatePresenter;
import ui.customer.EditCustomer.EditCustomerJFrame;
import ui.customer.EditCustomer.EditPresenter;
import memService.MemoryService;

public class CustomerPresenter {
	private CustomerView view;
	
	
	public CustomerPresenter(CustomerView inst) {
		this.view = inst;
	}

	 public void start() {
	        view.setCustomerPresenter(this);
	        view.open();     
	       
	    }
	 
	 public void createNewCustomer(MemoryService memService){
		 
			 CreateCustomerJFrame inst = new CreateCustomerJFrame(memService);
			 CreatePresenter presenter = new CreatePresenter(inst);
			 presenter.start();
			 view.close();
	 }
	 
	
	 
	 public void goBack(MemoryService memService){
		 MainFrame pframe = new MainFrame(memService);
		 pframe.open();
		 view.close();
	 }


	public void cancel() {
		view.close();
		
	}

	public void searchCustomer(String lastname,String telephone) {
		List<Customer> customers = null;
    	
		if(lastname.equals("")&&(!telephone.equals(""))) {
    		customers = view.findCustomerByTelephone(telephone);
    	} else if(telephone.equals("")&&!lastname.equals("")) {
    		customers = view.findCustomerByLastName(lastname);
    	} else if(!(lastname.equals("")&&telephone.equals(""))){
    		customers = view.findCustomerByLastNameAndTelephone(lastname, telephone);
    	}else{
    		view.showError("Error occured,no input was provided");
    	}
    	
    	if(customers!=null){
    		
    		ArrayList<Customer> cust = new ArrayList<Customer>();
    		for(int i=0;i<customers.size();i++){
    			cust.add(customers.get(i));
    		}
    		view.showCustomersListData(cust);
    		view.showInfo("Client was added successfully!");
    	}else{
    		view.showError("No one found");
    	}
    	
		
	}
	

	public void fillTextFields(int selected,Customer c, MemoryService memService) {
		String first=c.getFirstName();
		String last=c.getLastName();
		String email=c.getEmailAddress();
		String phone=c.getPhoneNumber();
		String address=c.getAddress();
		
		 //if(customerFound){
			EditCustomerJFrame inst = new EditCustomerJFrame(memService);
			EditPresenter presenter = new EditPresenter(inst);
	        presenter.start();
			presenter.fillBlanksForEdit(first, last, email, phone, address,c);
			view.close();
		 //}else{
		 //	 view.showError("Error occured,You need to search for a client first");
		 //}
		 //customerFound=false;
	}
	
	public void deleteCustomer(MemoryService memService, Customer cust){
		memService.deleteCustomer(cust);
		view.showInfo("Customer has been deleted successfully");
		view.showCustomersListData(memService.getCustomers());
	}
	
	
}
