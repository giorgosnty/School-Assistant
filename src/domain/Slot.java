package domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Slot {
	private LocalTime startingTime;
	private LocalTime endingTime;
	private DayOfWeek day;
	private String teacher;
	private boolean active;
	private Room room;
	private Lesson lesson;
	
	public Slot() {}
	
	public Slot(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public Slot(LocalTime startingTime, DayOfWeek day, String teacher, boolean active, Room room, Lesson lesson){
		this.startingTime = startingTime;
		this.endingTime = startingTime.plusMinutes(lesson.getDuration());
		this.day = day;
		this.teacher = teacher;
		this.active = active;
		this.lesson = lesson;
		this.room  = room;
		
	}
	
	public LocalTime getStartingTime() { 
		return startingTime;
	}
	
	public LocalTime getEndingTime() { 
		return endingTime; 
	}
	public DayOfWeek getDay() { return day; }
	public String getTeacher() { return teacher; }
	public boolean isActive() { return active; }
	public Room getRoom() { return room; }
	
	public Lesson getLesson() {
		return this.lesson;
		
	}
	
	public void setStartingTime(LocalTime startingTime) { 
		if (lesson != null) {
			this.startingTime = startingTime; 
			this.endingTime = startingTime.plusMinutes(lesson.getDuration());
		}
	}
	public void setLesson(Lesson l) { this.lesson = l; }
	public void setDay(DayOfWeek day) { this.day = day; }
	public void setTeacher(String teacher) { this.teacher = teacher; }
	public void setActive() { this.active = true; }
	public void setInActive() { this.active = false; }
	
	
	public boolean setRoom(Room room) { 
		if (day!= null && startingTime!=null && lesson!=null){
			if (room.isAvailable(day, startingTime, lesson.getDuration())) {
				this.room = room;
				room.addSlot(this);
				return true;
			}
		}
		return false;
	}
}
