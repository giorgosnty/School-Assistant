package test;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import memService.MemoryService;
import ui.customer.CustomerPresenter;
import ui.customer.CustomerView;

public class CustomerViewStub extends ViewStub implements CustomerView{

	private CustomerPresenter presenter;
	private MemoryService data;
	private ArrayList<Customer> customers;
	

	public CustomerViewStub(MemoryService data){
		this.data = data;
	}
 


	@Override
	public void setCustomerPresenter(CustomerPresenter presenter) {
		
		this.presenter=presenter;
	}

	@Override
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone) {
		return data.findCustomerByLastNameAndTelephone(lastname, telephone);
		
	}

	@Override
	public List<Customer> findCustomerByLastName(String lastname) {
		return data.findCustomerByLastName(lastname);
	}

	@Override
	public List<Customer> findCustomerByTelephone(String telephone) {
		return data.findCustomerByTelephone(telephone);
	}


	@Override
	public void showCustomersListData(List<Customer> cust) {
		customers = (ArrayList<Customer>)cust;
		List<String> cs = new ArrayList<String>();
		for(Customer c : customers){
			cs.add(c.getFirstName() + " " + c.getLastName() + ", Tel: " + c.getPhoneNumber());
		}
		//custList.setListData(cs.toArray());
	}

}
