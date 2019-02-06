package memService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.*;;

public class MemoryService {
	List<Customer> customers;
	List<Lesson> lessons;
	List<Slot> slots;
	ArrayList<Room> rooms; 
	
	public MemoryService(){
		initialize();
	}
	
	public void initialize(){
		customers = new ArrayList<Customer>();
		lessons = new ArrayList<Lesson>();
		slots = new ArrayList<Slot>();
		rooms = new ArrayList<Room>();
		
		Customer t = new Customer("Triple","H","6972746952","Valaoritou 12","mymail@gmail.com");
		Customer r = new Customer("Rey","Mysterio","6974564322","Valaoritou 14","hismail@gmail.com");
		Customer s = new Customer("Sivester","Stalone","6974364332","Valaoritou 16","hermail@gmail.com");
		Customer a = new Customer("Arnold","Schwarzenneger","6974364332","Valaoritou 16","hermail@gmail.com");
		
		Room r1 = new Room("Derigni", 200);
		Room r2  = new Room("Anotniadou", 200);
		Room r3 = new Room("Equilirium", 100);
		
		Lesson l1 = new Lesson("Cross Fit", 120, 50, 20, "Come and Learn Cross Fit");
		Slot s1 = new Slot(LocalTime.of(12,0), DayOfWeek.MONDAY, "George Ntymenos", true, r1, l1);
		Slot s2 = new Slot(LocalTime.of(12,0), DayOfWeek.WEDNESDAY, "George Ntymenos", true, r1, l1);
		Slot s3 = new Slot(LocalTime.of(12,0), DayOfWeek.FRIDAY, "George Ntymenos", true, r3, l1);
		l1.addSlot(s1);
		l1.addSlot(s2);
		l1.addSlot(s3);
		slots.add(s1);
		slots.add(s2);
		slots.add(s3);
		lessons.add(l1);
		Lesson l2 = new Lesson("Kick Box", 120, 70, 30, "Come and Learn Kick Box from the one and only Mike Tyson! Get ready for deep shit..");
		Slot s21 = new Slot(LocalTime.of(15,0), DayOfWeek.MONDAY, "Mike Tyson", true, r2, l2);
		Slot s22 = new Slot(LocalTime.of(15,0), DayOfWeek.WEDNESDAY, "Mike Tyson", true, r2, l2);
		l2.addSlot(s21);
		l2.addSlot(s22);
		slots.add(s21);
		slots.add(s22);
		lessons.add(l2);
		Lesson l3 = new Lesson("Kung Fu", 120, 40, 10, "Come and Learn Kung Fu from Bruce Lee himself! Raised from the dead only for us!");
		Slot s31 = new Slot(LocalTime.of(15,0), DayOfWeek.TUESDAY, "Bruce Lee", true, r3, l3);
		Slot s32 = new Slot(LocalTime.of(15,0), DayOfWeek.THURSDAY, "Bruce Lee", true, r1, l3);
		l3.addSlot(s31);
		l3.addSlot(s32);
		slots.add(s31);
		slots.add(s32);
		lessons.add(l3);
		Lesson l4 = new Lesson("Pilates", 180, 40, 10, "Come and Learn Pilates in order to build and nice and healthy body!");
		Slot s41 = new Slot(LocalTime.of(18,0), DayOfWeek.TUESDAY, "Ioanna Nikita", true, r1, l4);
		Slot s42 = new Slot(LocalTime.of(18,0), DayOfWeek.THURSDAY, "Ioanna Nikita", true, r3, l4);
		l4.addSlot(s41);
		l4.addSlot(s42);
		slots.add(s41);
		slots.add(s42);
		lessons.add(l4);
		
		MonthlySubscription tm = new MonthlySubscription(t, LocalDateTime.of(2016, 5, 1, 15,0));
		tm.addSlot(s1);
		tm.addSlot(s32);
		t.addMSub(tm);
		MonthlySubscription rm = new MonthlySubscription(r, LocalDateTime.of(2016, 5, 3, 15,0));
		rm.addSlot(s41);
		rm.addSlot(s21);
		r.addMSub(rm);
		OneLessonSubscription so = new OneLessonSubscription(s, LocalDateTime.of(2016, 5, 1, 15,0));
		so.addSlot(s3);
		s.addOLSub(so);
		OneLessonSubscription ao = new OneLessonSubscription(a, LocalDateTime.of(2016, 5, 1, 15,0));
		ao.addSlot(s22);
		a.addOLSub(ao);
		
		customers.add(t);
		customers.add(r);
		customers.add(s);
		customers.add(a);
	}
	
	public List<Customer> findCustomerByLastName(String lastname){
		return customers.parallelStream().filter(c->c.getLastName().equals(lastname)).collect(Collectors.toList());
	}
	
	public List<Customer> findCustomerByTelephone(String telephone){
		return customers.parallelStream().filter(c->c.getPhoneNumber().equals(telephone)).collect(Collectors.toList());
	}
	
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone){
		return customers.parallelStream().filter(c->c.getPhoneNumber().equals(telephone) && c.getLastName().equals(lastname)).collect(Collectors.toList());
	}
	
	public List<MonthlySubscription> getUnpaidSubs(){
		List<MonthlySubscription> subs = new ArrayList<MonthlySubscription>();
		for(Customer c: customers){
			MonthlySubscription ms = c.getCurrentSubscription();
			if(ms != null){
				if(!ms.isPaid()){
					subs.add(ms);
				}
			}
		}
		return subs;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public List<Lesson> getLessons() {
		return lessons;
	}
	
	public List<Slot> getSlots() {
		return slots;
	}
	
	public Room getRoom(String roomName){
		Room room = null;
		for(Room r:rooms){
			if(roomName==r.getName()){
				room =r;
			}
		}
		return room;
	}

	public void editCustomer(String first, String last, String addr, String email, String phone, Customer old) {
		if(old!=null){
			for(Customer c : customers){
				if(c.getFirstName().equals(old.getFirstName()) && c.getLastName().equals(old.getLastName())){
					if(c.getEmailAddress().equals(old.getEmailAddress()) && c.getAddress().equals(old.getAddress())){
						c.setAddress(addr);
						c.setEmailAddress(email);
						c.setFirstName(first);
						c.setLastName(last);
						c.setPhoneNumber(phone);
						break;
					}
				}
			}
		}
	}

	public void deleteCustomer(Customer cust) {
		customers.remove(cust);
	}

	public void deleteLesson(Lesson l) {
		for(Customer c : customers){
			MonthlySubscription m = c.getCurrentSubscription();
			if(m !=null){
				List<Slot> mslots = m.getSlots();
				if(mslots.size()>0){
					List<Slot> deleteList = new ArrayList<Slot>();
					for(Slot s : mslots){
						if(s.getLesson().equals(l)){
							//m.removeSlot(s);
							deleteList.add(s);
							if(mslots.size()==0)
								break;
						}
					}
					for(Slot s : deleteList){
						m.removeSlot(s);
					}
				}
			}
			List<OneLessonSubscription> ols = c.getOLSubs();
			if(ols != null){
				List<OneLessonSubscription> deleteList = new ArrayList<OneLessonSubscription>();
				for(OneLessonSubscription s : ols){
					if(s.getSlot().getLesson().equals(l)){
						//c.removeOlSub(s);
						deleteList.add(s);
					}
				}
				for(OneLessonSubscription s : deleteList){
					c.removeOlSub(s);
				}
			}
		}
		slots = slots.parallelStream().filter(s->!s.getLesson().equals(l)).collect(Collectors.toList());
		lessons.remove(l);
	}
	
	public void deleteSlot(Slot slot) {
		for(Customer c : customers){
			MonthlySubscription m = c.getCurrentSubscription();
			if(m !=null){
				List<Slot> mslots =m.getSlots();
				if(mslots.size()>0){
					List<Slot> deleteList = new ArrayList<Slot>();
					for(Slot s : mslots){
						if(s.equals(slot)){
							deleteList.add(s);
						}
					}
					for(Slot s : deleteList){
						m.removeSlot(s);
					}
				}
			}
			List<OneLessonSubscription> ols = c.getOLSubs();
			if(ols != null) {
				List<OneLessonSubscription> deleteList = new ArrayList<OneLessonSubscription>();
				for(OneLessonSubscription s : ols){
					if(s.getSlot().equals(slot)){
						//c.removeOlSub(s);
						deleteList.add(s);
					}
				}
				for(OneLessonSubscription s : deleteList){
					c.removeOlSub(s);
				}
			}
		}
		for(Lesson l : lessons){
			if(l.hasSlot(slot)){
				l.removeSlot(slot);
			}
		}
		slots = slots.parallelStream().filter(s->!s.equals(slot)).collect(Collectors.toList());
		lessons = lessons.parallelStream().filter(l->l.getSlots().size()>0).collect(Collectors.toList());
	}

	public List<Slot> getLessonSlots(Lesson lesson) {
		for(Lesson l : lessons){
			if(l.getName().equals(lesson.getName())){
				return l.getSlots();
			}
		}
		return null;
	}
	
	public void addLesson(Lesson l){
		lessons.add(l);
	}

	public void addSlot(Lesson l, Slot s) {
		for(Lesson ls : lessons){
			if(ls.equals(l)){
				ls.addSlot(s);
			}
		}
		slots.add(s);
		
	}

	public void addRoom(Room r) {
		rooms.add(r);
		
	}

	public void updateLesson(domain.Lesson lesson, String name, int duration, double price_pack, double price_single, String desc) {
		for(Lesson l : lessons){
			if(l.equals(lesson)){
				l.setName(name);
				l.setDuration(duration);
				l.setPricePackage(price_pack);
				l.setpriceSingle(price_single);
				l.setDescription(desc);
				break;
			}
		}
	}

	public void updateSlot(domain.Lesson lesson, Slot slot, LocalTime startTime, int day, String teacher, String room) {
		for(Lesson l : lessons){
			if(l.equals(lesson)){
				List<Slot> lslots = l.getSlots();
				for(Slot ls : lslots){
					if(ls.equals(slot)){
						ls.setDay(DayOfWeek.of(day));
						ls.setStartingTime(startTime);
						ls.setTeacher(teacher);
						boolean found = false;
						for(Room r : rooms){
							if(r.getName().equals(room)){
								ls.setRoom(r);
								found = true;
								break;
							}
						}
						if(!found){
							Room r = new Room(room,200);
							ls.setRoom(r);
						}
						break;
					}
				}
			}
		}
	}
}
