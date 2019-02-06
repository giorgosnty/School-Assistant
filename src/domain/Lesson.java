package domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lesson {
	private String name;
	private int duration; //in minutes 
	private double pricePackage;
	private double priceSingle;
	private String description;
	private List<Slot> slots;
	
	public Lesson(String name, int duration, double pricePackage, double priceSingle, String description){
		this.name = name;
		this.duration = duration;
		this.pricePackage = pricePackage;
		this.priceSingle = priceSingle;
		this.description = description;
		slots = new ArrayList<Slot>();
	}
	
	public String getName() { return name; }
	public int getDuration() { return duration; }
	public double getPricePackage() { return pricePackage; }
	public double getpriceSingle() { return priceSingle; }
	public String getDescription() { return description; }
	
	public void setName(String name) { this.name = name; }
	public void setDuration(int duration) { this.duration = duration; }
	public void setPricePackage(double pricePackage) { this.pricePackage = pricePackage; }
	public void setpriceSingle(double priceSingle) { this.priceSingle = priceSingle; }
	public void setDescription(String description) { this.description = description; }
	
	public void addSlot(Slot slot){
		slots.add(slot);
	}
	
	public Slot getSlot(DayOfWeek d) {
		if(d!=null &&slots != null){
			List<Slot> found_slots = slots.parallelStream().filter(s -> s.getDay().getValue() == d.getValue())
					.collect(Collectors.toList());
			if(found_slots.size()>0){
				return found_slots.get(0);
			}
		}
		return null;
		
	}
	
	public Slot getSlot(DayOfWeek d, LocalTime startingTime) {
		if(d != null && slots != null && startingTime != null){
			List<Slot> found_slots = slots.parallelStream().filter(s -> s.getDay().getValue() == d.getValue() && startingTime.equals(s.getStartingTime()))
					.collect(Collectors.toList());
			if(found_slots.size()>0){
				return found_slots.get(0);
			}
		}
		return null;
	}
	
	public Slot getSlot(LocalTime startingTime) {
		if(slots != null){
			List<Slot> found_slots = slots.parallelStream().filter(s -> startingTime.equals(s.getStartingTime()))
					.collect(Collectors.toList());
			if(found_slots.size()>0){
				return found_slots.get(0);
			}
		}
		return null;
	}
	
	public List<Slot> getSlots() {
		return slots.parallelStream().collect(Collectors.toList());
	}
	
	public Slot getSlot(String startingTime) {
		List<Slot> found_slots = slots.parallelStream().filter(s -> startingTime.equals(s.getStartingTime()))
				.collect(Collectors.toList());
		if(found_slots.size()>0){
			return found_slots.get(0);
		}
		else {
			return null;
		}
	}

	public boolean hasSlot(Slot slot) {
		if(slots != null){
			if(slots.contains(slot)){
				return true;
			}
		}
		return false;
	}

	public void removeSlot(Slot slot) {
		if(slots != null){
			slots.remove(slot);
		}
	}
}
