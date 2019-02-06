package ui.ScheduleReports;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Customer;
import domain.Lesson;
import domain.MonthlySubscription;
import domain.OneLessonSubscription;
import domain.Slot;
import memService.MemoryService;
import ui.DefaultJFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ScheduleReportsFrame extends DefaultJFrame implements ScheduleReportsView{
	private ScheduleReportsPresenter presenter;
	private MemoryService data;
	private JPanel contentPane;
	private JList lessons_list;
	private JList slots_list;
	private JList signed_list;
	private JList present_list;
	private List<Lesson> lessons;
	private List<Slot> slots;
	private JLabel total_signed;
	private JLabel total_present;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleReportsFrame frame = new ScheduleReportsFrame(new MemoryService());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScheduleReportsFrame(MemoryService data) {
		this.data = data;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            presenter.goBack(data);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Lessons");
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
				
		JLabel lblSlots = new JLabel("Slots");
		lblSlots.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		lessons_list = new JList();
		lessons = data.getLessons();
		showLessons();
		lessons_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
            	presenter.showSlots(lessons_list.getSelectedIndex());
            }
        });
		
		slots_list = new JList();
		
		JButton details_btn = new JButton("Show Details");
		details_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		details_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            presenter.showDetails(slots_list.getSelectedIndex());
			}
		});
		
		signed_list = new JList();
		
		present_list = new JList();
		
		JLabel lblSigned = new JLabel("Signed");
		lblSigned.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel lblPresent = new JLabel("Present");
		lblPresent.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel label = new JLabel("Total: ");
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		total_signed = new JLabel("");
		total_signed.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		total_present = new JLabel("");
		total_present.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(56)
									.addComponent(lessons_list, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(83)
									.addComponent(slots_list, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(225)
									.addComponent(details_btn, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(144)
									.addComponent(signed_list, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(65)
									.addComponent(present_list, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(164)
									.addComponent(lblTotal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(total_signed, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(total_present, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(38)))))
					.addContainerGap(71, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addComponent(lblNewLabel)
					.addGap(209)
					.addComponent(lblSlots, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(267)
					.addComponent(lblSigned, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
					.addComponent(lblPresent, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(176))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblSlots, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(27))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPresent, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSigned, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(28)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(slots_list, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
						.addComponent(lessons_list, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
						.addComponent(signed_list, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
						.addComponent(present_list, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(details_btn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(total_signed, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(total_present, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void showLessons() {
		List<String> ls = new ArrayList<String>();
		int i = 1;
		for(Lesson l : lessons){
			ls.add(i+") " + l.getName());
		}
		lessons_list.setListData(ls.toArray());
	}
	
	public void showSlots(List<Slot> slots) {
		this.slots = slots;
		List<String> sl = new ArrayList<String>();
		int i = 1;
		for(Slot s : slots) {
			sl.add(i+")"+ s.getDay() + " " + s.getStartingTime().format(DateTimeFormatter.ISO_TIME) + "-" 
					+ s.getEndingTime().format(DateTimeFormatter.ISO_TIME) + " " + s.getRoom().getName());
		}
		slots_list.setListData(sl.toArray());
	}
	
	public void showDetails(List<Customer> signed, List<Customer> present){
		List<String> s = new ArrayList<String>();
		List<String> p = new ArrayList<String>();
		int i = 1;
		for(Customer c : signed){
			s.add(i+")"+ c.getFirstName() + " " + c.getLastName());
		}
		i=1;
		for(Customer c : present){
			p.add(i+")"+ c.getFirstName() + " " + c.getLastName());
		}
		signed_list.setListData(s.toArray());
		present_list.setListData(p.toArray());
		String s1 = ""+signed.size();
		String s2 = ""+present.size();
		total_signed.setText(s1);
		total_present.setText(s2);
	}
	
	public List<Customer> findSigned(Slot s){
		List<Customer> result = new ArrayList<Customer>();
		List<Customer> customers = data.getCustomers();
		for(Customer c : customers) {
			MonthlySubscription m = c.getCurrentSubscription();
			if(m!=null){
				if(m.hasSlot(s))
					result.add(c);
				else{
					int size = c.getOLSubs().parallelStream().filter(sl->sl.getSlot().equals(s)).collect(Collectors.toList()).size();
					if(size>0)
						result.add(c);
				}
			}
		}
		return result;
	}
	
	public List<Customer> findPresent(Slot s){
		List<Customer> result = new ArrayList<Customer>();
		List<Customer> customers = data.getCustomers();
		for(Customer c : customers) {
			if(c.wasPresent(s, LocalDateTime.now()))
				result.add(c);
			else{
				List<OneLessonSubscription> ol = c.getOLSubs().parallelStream().filter(sl->sl.getSlot().equals(s)).collect(Collectors.toList());
				if(ol.size()>0){
					OneLessonSubscription l = ol.get(0);
					if(Math.abs(l.getDate().compareTo(LocalDateTime.now()))<7)
						result.add(c);
				}
			}
		}
		return result;
	}
	 
	
	public Lesson getLesson(int index) {
		return lessons.get(index);
	}
	
	public Slot getSelectedSlot(int index) {
		return slots.get(index);
	}
	
	public void setPresenter(ScheduleReportsPresenter presenter) {
		this.presenter = presenter;
	}
}
