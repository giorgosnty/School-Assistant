package ui.MSubs.monthlySubscription.create;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import domain.Customer;
import domain.MonthlySubscription;
import domain.Slot;
import memService.MemoryService;
import ui.MainFrame;

public class CreateMonthlyPresenter {
	private Customer c;
	private CreateMonthlySubscriptionView view;
	
	public CreateMonthlyPresenter(CreateMonthlySubscriptionJFrame inst, Customer c) {
		this.view = inst;
		this.c = c;
	}

	 public void start() {
	        view.setCreateMonthlyPresenter(this);
	        view.open();     
	    }

	 public void goBack(MemoryService memService){
		 MainFrame pframe = new MainFrame(memService);
		 pframe.open();
		 view.close();
	 }

	public List<Slot> addLesson(int selectedIndex, List<Slot> enrollment_slots) {
		if(selectedIndex<0){
			view.showError("Error,no Lesson is selected");
			return null;
		}else{
			Slot s= view.getSelectedSlot(selectedIndex);
			enrollment_slots.add(s);
			return enrollment_slots;
		}
		
	}

	public void submitSubscription(List<Slot> enrollment_slots) {
		
		MonthlySubscription sub = new MonthlySubscription(c, LocalDateTime.now());
		for (int i =0 ; i< enrollment_slots.size(); i++ ) {
			sub.addSlot(enrollment_slots.get(i));
			
		}
		c.addMSub(sub);
		view.showInfo("The Subscription was submited successfully!!");
		
	}

	
}
