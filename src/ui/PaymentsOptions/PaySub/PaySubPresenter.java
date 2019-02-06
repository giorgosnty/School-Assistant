package ui.PaymentsOptions.PaySub;

import ui.MainFrame;
import ui.PaymentsOptions.PaymentsOptionsView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.*;
import memService.MemoryService;

public class PaySubPresenter {
	private PaySubView view;
	private List<MonthlySubscription> m_subs;
	
	public PaySubPresenter(PaySubView view){
		this.view = view;
	}
	
	public void start() {
        view.setPresenter(this);
        view.open();
    }
	
    public void cancel() {
        view.close();
    }
    
    public void goBack(MemoryService data){
    	MainFrame m = new MainFrame(data);
    	m.open();
    	view.close();
    }
    
    public void showClientSubs(String lastname, String telephone){
    	List<Customer> customers = null;
    	if(lastname.equals("")) {
    		customers = view.findCustomerByTelephone(telephone);
    	} else if(telephone.equals("")) {
    		customers = view.findCustomerByLastName(lastname);
    	} else {
    		customers = view.findCustomerByLastNameAndTelephone(lastname, telephone);
    	}
    	m_subs = new ArrayList<MonthlySubscription>();
    	if(customers.size()>0){
    		for(Customer c : customers){
    			MonthlySubscription current = c.getCurrentSubscription();
    			if(!current.isPaid()){
    				m_subs.add(current);
    			}
    		}
    		if(m_subs.size()>0){
    			view.showUnpaidSubscriptionList(m_subs);
    		}
    	} else {
    		view.showUnpaidSubscriptionList(m_subs);
    	}
    }
    
    public void payEntireAmount(int index){
    	m_subs = view.getUnpaidCurrentList();
    	if(m_subs != null) {
    		if(index < m_subs.size()) {
    			MonthlySubscription m = m_subs.get(index);
				m.submitPayment(m.getRemainingAmount());
				view.showInfo("Amount Successfully Paid!");
				showUnpaidSubs();
    		} else {
    			view.showError("Error: Subscription that has been selected does not exist");
    		}
    	}else{
    		view.showError("Error: Amount Field Inexistent. Please refresh your page");
    	}
    }
    
    public void payAmount(String amount, int index){
    	float val;
    	try{
    		val = Float.parseFloat(amount);
    		m_subs = view.getUnpaidCurrentList();
    		if(m_subs != null) {
        		if(index < m_subs.size()) {
        			MonthlySubscription m = m_subs.get(index);
        			if(m.getRemainingAmount() < val || val < 0){
        				view.showError("Error: Inputed amount is out of bounds");
        			} else {
        				m.submitPayment(val);
        				view.showInfo("Amount Successfully Paid!");
        				showUnpaidSubs();
        			}
        		} else {
        			view.showError("Error: Subscription that has been selected does not exist");
        		}
        	}else{
        		view.showError("Error: Amount Field Inexistent. Please refresh your page");
        	}
    	}
    	catch(Exception x){
    		
    		view.showError("Error: Invalid Amount");
    	}
    	view.refreshAmount();
    }
    
    public void showUnpaidSubs() {
    	List<MonthlySubscription> ms = view.getUnpaidSubs();
    	view.showUnpaidSubscriptionList(ms);
    }
}
