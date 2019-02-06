package ui.MSubs.monthlySubscription.create;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Customer;
import domain.Lesson;
import domain.Room;
import domain.Slot;
import memService.MemoryService;
import net.miginfocom.swing.MigLayout;
import ui.DefaultJFrame;
import javax.swing.SwingConstants;

public class CreateMonthlySubscriptionJFrame extends DefaultJFrame implements CreateMonthlySubscriptionView {

	private JPanel contentPane;
	private JButton back_btn;
	private JList lessonsList;
	private JList EnrollmentList;
	private List<Slot> slots;
	
	List<Slot> enrollment_slots;
	CreateMonthlyPresenter presenter;
	private MemoryService memService;
	private JButton add_btn;
	private JButton submit_btn;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMonthlySubscriptionJFrame frame = new CreateMonthlySubscriptionJFrame(new MemoryService());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param c 
	 */
	public CreateMonthlySubscriptionJFrame(MemoryService memService) {
		this.memService=memService;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow][390.00][290.00,grow][][][][]", "[][][][][][][][grow][]"));
		setContentPane(contentPane);
		enrollment_slots =  new ArrayList<Slot>();
	
		back_btn = new JButton("Back to Menu");
		back_btn.setPreferredSize(new Dimension(30, 20));
		back_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            presenter.goBack(memService);
	          
			}
		});
		
		contentPane.add(back_btn, "cell 0 0");
		
		lessonsList = new JList();
		slots = memService.getSlots();
		showLessons(slots);
		contentPane.add(lessonsList,"cell 1 0");
		
		/*Room r1 = new Room("Derigni", 200);
		Lesson l1 = new Lesson("Cross Fit", 120, 50, 20, "Come and Learn Cross Fit");
		Slot slot2= new Slot(LocalTime.of(12,0), DayOfWeek.MONDAY, "George Menos", true, r1, l1);;
		enrollment_slots.add(slot2);*/
		
		submit_btn = new JButton("Submit Subscription");
		submit_btn.setPreferredSize(new Dimension(30, 20));
		submit_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.submitSubscription(enrollment_slots);
	          
			}
		});
		
		
		EnrollmentList = new JList();
		
		contentPane.add(EnrollmentList,"flowx,cell 2 0,alignx left");
		
		add_btn = new JButton("Add Lesson");
		add_btn.setPreferredSize(new Dimension(30, 20));
		add_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enrollment_slots= presenter.addLesson(lessonsList.getSelectedIndex(),enrollment_slots);
				showSelectedLessons(enrollment_slots);
			}
		});
		
		contentPane.add(add_btn,"cell 0 1");
		
		contentPane.add(submit_btn,"cell 0 3");
	
	}

	
	public void showLessons(List<Slot> slots){
		this.slots = slots;
		List<String> cs = new ArrayList<String>();
		int i = 1;
		for(Slot s : slots){
			cs.add(i + ") " + s.getLesson().getName() + ", " + s.getDay() + " " + s.getStartingTime().format(DateTimeFormatter.ISO_TIME) + "-" 
					+ s.getEndingTime().format(DateTimeFormatter.ISO_TIME) + " " + s.getRoom().getName());
			i++;
		}
		lessonsList.setListData(cs.toArray());
	}
	
	public void showSelectedLessons(List<Slot> slots){
		enrollment_slots = slots;
		List<String> cs = new ArrayList<String>();
		int i = 1;
		for(Slot s : enrollment_slots){
			cs.add(i + ") " + s.getLesson().getName() + ", " + s.getDay() + " " + s.getStartingTime().format(DateTimeFormatter.ISO_TIME) + "-" 
					+ s.getEndingTime().format(DateTimeFormatter.ISO_TIME) + " " + s.getRoom().getName());
			i++;
		}
		EnrollmentList.setListData(cs.toArray());
	}
	

	@Override
	public void setCreateMonthlyPresenter(CreateMonthlyPresenter presenter) {
		this.presenter=presenter;
		
	}
	
	@Override
	public Slot getSelectedSlot(int index) {
		return slots.get(index);
	}

}
