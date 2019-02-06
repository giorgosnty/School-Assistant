package ui.ScheduleReports;

import java.util.List;

import javax.swing.JOptionPane;

import domain.Customer;
import domain.Lesson;
import domain.Slot;
import memService.MemoryService;
import ui.MainFrame;

public class ScheduleReportsPresenter {
	private ScheduleReportsView view;
	
	public ScheduleReportsPresenter(ScheduleReportsView view){
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
	
	public void showSlots(int lessonIndex) {
		Lesson l = view.getLesson(lessonIndex);
		List<Slot> slots = l.getSlots();
		view.showSlots(slots);
	}
	
	public void showDetails(int slotIndex) {
		if(slotIndex < 0){
			view.showError("Error: Slot has not been selected exists");
		} else {
			Slot s = view.getSelectedSlot(slotIndex);
			List<Customer> signed = view.findSigned(s);
			List<Customer> present = view.findPresent(s);
			
			view.showDetails(signed, present);
		}
		
	}
}
