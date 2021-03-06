package ui.SubmitPresence;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Customer;
import domain.OneLessonSubscription;
import domain.Slot;
import memService.MemoryService;
import ui.DefaultJFrame;
import ui.SingleLessonEnrollment.SingleLessonEnrollPresenter;

public class SubmitPresenceFrame extends DefaultJFrame implements SubmitPresenceView {

	private SubmitPresencePresenter presenter;
	private JPanel contentPane;
	private MemoryService data;
	private JTextField lastNameText;
	private JTextField telephoneText;
	private JList customersList;
	private JList lessonsList;
	private List<Customer> customers;
	private List<Slot> slots;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmitPresenceFrame frame = new SubmitPresenceFrame(new MemoryService());
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
	public SubmitPresenceFrame(MemoryService data) {
		this.data = data;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            presenter.goBack(data);
			}
		});
		
		JLabel lblSearchClient = new JLabel("Search Client");
		lblSearchClient.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel lblName = new JLabel("Last Name");
		lblName.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		lastNameText = new JTextField();
		lastNameText.setColumns(10);
		
		telephoneText = new JTextField();
		telephoneText.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            presenter.searchCustomers(lastNameText.getText(), telephoneText.getText());
			}
		});
		
		customersList = new JList();
		customers = data.getCustomers();
		showCustomers(customers);
		customersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
            	presenter.showSlots(customersList.getSelectedIndex());
            }
        });
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel lblLessons = new JLabel("Lesson Slots");
		lblLessons.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		lessonsList = new JList();
		
		JButton submit_btn = new JButton("Submit Presence");
		submit_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            presenter.submitPresence(lessonsList.getSelectedIndex());
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(45)
									.addComponent(back_btn)
									.addGap(94)
									.addComponent(lblSearchClient, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(116)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(customersList, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lastNameText, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(23)
													.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
											.addGap(43)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblTelephone, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
												.addComponent(telephoneText, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(54)
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(lessonsList, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
									.addGap(81)
									.addComponent(submit_btn))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(214)
							.addComponent(lblCustomers, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(290)
							.addComponent(lblLessons, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSearchClient, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTelephone, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lastNameText, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(telephoneText, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))))
							.addGap(55)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCustomers, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(customersList, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblLessons, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lessonsList, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(470)
							.addComponent(submit_btn, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	

	public List<Customer> findCustomerByLastName(String lastname){
		return data.findCustomerByLastName(lastname);
	}
	
	public List<Customer> findCustomerByTelephone(String telephone){
		return data.findCustomerByTelephone(telephone);
	}
	
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone){
		return data.findCustomerByLastNameAndTelephone(lastname, telephone);
	}
	
	public void showCustomers(List<Customer> customers){
		this.customers = customers;
		List<String> cs = new ArrayList<String>();
		int i = 1;
		for(Customer c : customers){
			cs.add(i + ") " + c.getFirstName() + " " + c.getLastName() + ", " + c.getPhoneNumber());
			i++;
		}
		customersList.setListData(cs.toArray());
	}
	
	public void showSlots(List<Slot> slots){
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
	
	public Customer getSelectedCustomer(int index) {
		return customers.get(index);
	}
	
	public Slot getSelectedSlot(int index) {
		return this.slots.get(index);
	}
	
	public boolean updateMemory(Customer c, Slot s){
		Customer cur = data.findCustomerByLastNameAndTelephone(c.getLastName(), c.getPhoneNumber()).get(0);
		boolean result = cur.submitPresence(s);
		return result;
	}
	
	public void setPresenter(SubmitPresencePresenter presenter){
		this.presenter = presenter;
	}
}
