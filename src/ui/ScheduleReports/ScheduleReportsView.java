package ui.ScheduleReports;

import java.util.List;

import domain.Customer;
import domain.Lesson;
import domain.Slot;
import ui.View;

public interface ScheduleReportsView extends View{
	public void setPresenter(ScheduleReportsPresenter presenter);
	
	public Lesson getLesson(int index);
	
	public void showSlots(List<Slot> slots);
	
	public Slot getSelectedSlot(int index);
	
	public List<Customer> findSigned(Slot s);
	
	public List<Customer> findPresent(Slot s);
	
	public void showDetails(List<Customer> signed, List<Customer> present);
}
