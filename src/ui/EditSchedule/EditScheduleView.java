package ui.EditSchedule;

import java.time.LocalTime;
import java.util.List;

import domain.Lesson;
import domain.Room;
import domain.Slot;
import ui.View;

public interface EditScheduleView extends View{
	
	public void setPresenter(EditSchedulePresenter presenter);
	
	public void showSlots(List<Slot> slots);
	
	public Lesson getLesson(int index);
	
	public void deleteLesson(Lesson l);

	public void updateLessons();

	public Slot getSlot(int selectedIndex);

	public void deleteSlot(Slot s);

	public void updateSlots();

	public void addLesson(Lesson l);
	
	
	public Room getRoom(String r);

	public void addRoom(Room r);

	public void addSlot(Lesson l, Slot s);

	public void showLessonForEdit(Lesson l);

	public void updateLesson(Lesson lesson, String name, int duration, double price_pack, double price_single, String desc);
	
	public void clearLessonTextFields();
	
	public void clearSlotTextFields();
	
	public void clearLists();

	public void showSlotForEdit(Slot slot);

	public void updateSlot(Lesson lesson, Slot slot, LocalTime startTime, int day, String teacher, String room);
}
