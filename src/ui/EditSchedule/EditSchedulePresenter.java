package ui.EditSchedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Lesson;
import domain.Room;
import domain.Slot;
import memService.MemoryService;
import ui.MainFrame;
import ui.customer.CreateCustomer.CreateCustomerJFrame;
import ui.customer.CreateCustomer.CreateCustomerView;

public class EditSchedulePresenter {
	private EditScheduleView view;
	private Lesson l;
	private Lesson lesson_to_edit;
	private Slot slot_to_edit;
	private Slot s;
	
	//private MemoryService data;
	
	public EditSchedulePresenter(EditScheduleView inst) {
		this.view = inst;
	}

	 public void start() {
        view.setPresenter(this);
        view.open();     
       
    }

	 
	 public void cancel() {
		view.close();
		
	}
	 
	 public void clear() {
		 view.clearLessonTextFields();
		 view.clearSlotTextFields();
		 view.clearLists();
	 }

	public void showSlots(int lessonIndex) {
		if(lessonIndex>=0){
			Lesson l = view.getLesson(lessonIndex);
			lesson_to_edit = l;
			List<Slot> slots = l.getSlots();
			view.showSlots(slots);
			view.showLessonForEdit(l);
			view.clearSlotTextFields();
		}
	}

	public void deleteLesson(int selectedIndex) {
		if(selectedIndex<0){
			JOptionPane.showMessageDialog(null, "Error: Lesson has not been selected");
		} else {
			Lesson l = view.getLesson(selectedIndex);
			view.deleteLesson(l);
			view.updateLessons();
		}
	}
	
	public void deleteSlot(int selectedSlot){
		if(selectedSlot<0){
			JOptionPane.showMessageDialog(null, "Error: Slot has not been selected");
		} else {
			Slot s = view.getSlot(selectedSlot);
			view.deleteSlot(s);
			view.updateSlots();
		}
	}

	public void createLesson(JTextField lNameTextField, JTextField durationTextField, JTextField pricePackageTextField, JTextField priceSingleTextField, JTextField descriptionTextField) {
		l= new Lesson(lNameTextField.getText(),Integer.parseInt(durationTextField.getText()),Double.parseDouble(pricePackageTextField.getText()),Double.parseDouble(priceSingleTextField.getText()),descriptionTextField.getText());
		view.addLesson(l);
		view.updateLessons();
		view.showInfo("Lesson Submitted successfully!");
	}

	public void addSlot(int selectedIndex, JTextField startingTimeTextField,
			JTextField dayTextField, JTextField teacherTextField, 
			JTextField roomTextField) {
		if(selectedIndex<0){
			view.showError("Error: Lesson has not been selected");
		} else {
			l = view.getLesson(selectedIndex);
		}
		
		if(dayTextField.getText().equals("") || startingTimeTextField.getText().equals("")||teacherTextField.getText().equals("")
				|| roomTextField.getText().equals("")){
			view.showError("One or more Fields are empty");
		} else{
			Room r =view.getRoom(roomTextField.getText());
			
			DayOfWeek d = DayOfWeek.of(Integer.parseInt(dayTextField.getText()));
			LocalTime t = LocalTime.parse(startingTimeTextField.getText());
			boolean av = true;
			if (r!= null) {
				av = r.isAvailable(d, t, l.getDuration());
			} else {
				r = new Room(roomTextField.getText(), 100);
				view.addRoom(r);
			}
			if (av) {
				s = new Slot(t,d, teacherTextField.getText(),true, r, l);
				view.addSlot(l, s);
				view.updateSlots();
				view.showInfo("Slot Created successfully.");
				
			} else {
				view.showError("This room is not available the time and day you entered. Change the input.");
			}
		}
		 
		
				
		
	}

	public void editLesson(String name, String dur, String pp, String ps, String desc) {
		if(lesson_to_edit!=null){
			if(name.equals("") || dur.equals("") || pp.equals("") || ps.equals("") || desc.equals("")){
				view.showError("One or more fields are empty");
			} else {
				int duration;
				double price_pack, price_single;
				try{
					duration = Integer.parseInt(dur);
					price_pack = Double.parseDouble(pp);
					price_single = Double.parseDouble(ps);
					view.updateLesson(lesson_to_edit, name, duration, price_pack, price_single, desc);		
					view.showInfo("Lesson successfully edited!");
					lesson_to_edit = null;
					slot_to_edit = null;
					view.clearLessonTextFields();
					view.clearSlotTextFields();
					view.clearLists();
					view.updateLessons();
				}
				catch(Exception x){
					view.showError("Price or duration fields are not valid");
				}
			}
		}else{
			view.showError("No lesson has been selected yet.");
		}
	}

	public void showSlotForEdit(int selectedIndex) {
		if(selectedIndex>=0){
			if(lesson_to_edit != null){
				slot_to_edit = view.getSlot(selectedIndex);
				if(slot_to_edit!=null){
					view.showSlotForEdit(slot_to_edit);
				}
			}
		}
	}

	public void editSlot(String startTime, String day, String teacher, String room) {
		if(lesson_to_edit == null || slot_to_edit == null){
			view.showError("Lesson or Slot has not been selected");
		}else{
			if(startTime.equals("") || day.equals("") || teacher.equals("") || room.equals("")){
				view.showError("One or more fields are empty");
			}else {
				int day_num;
				try {
					day_num = Integer.parseInt(day);
				} catch(Exception x){
					day_num = -1;
					view.showError("Wrong day format");
				}
				if(day_num > 0){
					if(day_num < 8){
						LocalTime start = null;
						try{
							start = LocalTime.parse(startTime);
						} catch(Exception x){
							view.showError("Wrong starting Time format");
						}
						if(start != null){
							view.updateSlot(lesson_to_edit, slot_to_edit, start, day_num, teacher, room);
							view.showInfo("Slot successfully Edited!");
							view.updateSlots();
							slot_to_edit = null;
							lesson_to_edit = null;
							view.clearSlotTextFields();
						}
					}else{
						view.showError("Error: Wrong day format");
					}
				}
			}
		}
	}

	public void goBack(MemoryService data) {
		MainFrame m = new MainFrame(data);
		m.open();
		view.close();
	}

	
}
