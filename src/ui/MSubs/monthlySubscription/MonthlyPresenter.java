package ui.MSubs.monthlySubscription;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextField;

import domain.Customer;
import domain.MonthlySubscription;
import domain.Slot;
import memService.MemoryService;
import ui.MainFrame;
import ui.MSubs.monthlySubscription.create.CreateMonthlyPresenter;
import ui.MSubs.monthlySubscription.create.CreateMonthlySubscriptionJFrame;
import ui.customer.CustomerJFrame;
import ui.customer.CustomerView;

public class MonthlyPresenter {

	private MonthlySubscriptionView view;
	
	public MonthlyPresenter(MonthlySubscriptionJFrame inst) {
		this.view = inst;
	}

	 public void start() {
	        view.setMonthlyPresenter(this);
	        view.open();     
	 }
	 
	 public void goBack(MemoryService memService){
		 MainFrame pframe = new MainFrame(memService);
		 pframe.open();
		 view.close();
	 }

	public void createSub(MemoryService memService,Customer c) {
		CreateMonthlySubscriptionJFrame mSFrame=new CreateMonthlySubscriptionJFrame(memService);
		CreateMonthlyPresenter presenter=new CreateMonthlyPresenter(mSFrame, c);
		presenter.start();
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
	    	
	    	if(customers.size()>0){
	    		
	    		ArrayList<Customer> cust = new ArrayList<Customer>();
	    		for(int i=0;i<customers.size();i++){
	    			cust.add(customers.get(i));
	    		}
	    		view.showCustomersListData(cust);
	    		
	    	}else{
	    		view.showError("No one found");
	    	}
	    	
			
	}

	public void renewSub(MemoryService memService, Customer chosen) {
		ArrayList<Slot> slots =new ArrayList<Slot>(chosen.getCurrentSubscription().getSlots());
		MonthlySubscription sub = new MonthlySubscription(chosen, LocalDateTime.now());
		for (Slot s : slots ) {
			sub.addSlot(s);
		}
		chosen.addMSub(sub);
		view.showMessage("Subscription was renewed with the slots of the previous subscription");
		
		
	}

	public void showDetails(MemoryService memService, Customer chosen,JList list, JTextField textFieldStart, JTextField textFieldEnd, JTextField textFieldPrice) {
		MonthlySubscription sub = chosen.getCurrentSubscription();
		if  (sub == null) {
			view.showError("This client has no Subscriptions");
		} else {
			ArrayList<Slot> slots = (ArrayList<Slot>) sub.getSlots();
			List<String> cs = new ArrayList<String>();
			int i = 1;
			for(Slot s : slots){
				cs.add(i + ") " + s.getLesson().getName() + ", " + s.getDay() + " " + s.getStartingTime().format(DateTimeFormatter.ISO_TIME) + "-" 
						+ s.getEndingTime().format(DateTimeFormatter.ISO_TIME) + " " + s.getRoom().getName());
				i++;
			}
			list.setListData(cs.toArray());
			textFieldStart.setText(sub.getStartingDate().toString());
			textFieldEnd.setText(sub.getEndingDate().toString());
			textFieldPrice.setText(Double.toString(sub.getTotalPrice()));
		}
		
	}

	

	
}
