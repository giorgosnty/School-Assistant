package domain;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Room {
	private String name;
	
	private ArrayList<Slot> slots = new ArrayList<Slot>();
	
	public Room(String name, int capacity) {
		this.name = name;
		
	}
	
	public String getName() { return name; }
	
	
	public void setName(String name) { this.name = name; }
	
	
	public boolean isAvailable(DayOfWeek day, LocalTime start, int duration) {
		if (slots.isEmpty()) {
			return true;
			
		}
		
		boolean result = true;
		
		for (Slot slot: slots ) {
			if (slot.getDay().equals(day)) {
				if ((slot.getStartingTime().plusMinutes(slot.getLesson().getDuration()).isAfter(start)&& slot.getStartingTime().isBefore(start)) || slot.getStartingTime().equals(start)||(start.plusMinutes(duration).isAfter(slot.getStartingTime())&& start.isBefore(slot.getEndingTime()))) {
					result = false;
				}
			}
		}
		
	
		return result;
	}
	
	public void addSlot(Slot s) {
		if (s!=null) {
			this.slots.add(s);
		}
	}
	
	public ArrayList<Slot> getSlots() {
		return this.slots;
	}
}
