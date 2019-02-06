package ui.customer.CreateCustomer;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.Customer;
import memService.MemoryService;
import net.miginfocom.swing.MigLayout;
import ui.DefaultJFrame;
import ui.customer.CustomerJFrame;
import ui.customer.CustomerPresenter;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CreateCustomerJFrame extends DefaultJFrame implements CreateCustomerView  {

	private JPanel contentPane;
	private JTextField lastName;
	private JTextField firstName;
	private JTextField phoneNumber;
	private JTextField email;
	private JTextField address;
	private JList list;
	private CreatePresenter presenter;
	private JLabel phoneLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
	
	private JButton submitButton;
	private JButton cancelButton;
	private MemoryService memService;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCustomerJFrame frame = new CreateCustomerJFrame(new MemoryService());
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
	public CreateCustomerJFrame(MemoryService memService) {
		this.memService = memService;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		firstNameLabel = new JLabel();
		firstNameLabel.setText("First Name");
		firstName= new JTextField();
		firstName.setColumns(30);
		
		
		lastName = new JTextField();
		lastName.setColumns(30);
		lastNameLabel= new JLabel();
		lastNameLabel.setText("Last Name");
		
		
		emailLabel = new JLabel();
		emailLabel.setText("Email");
		email = new JTextField();
		email.setColumns(30);
		
		phoneNumber = new JTextField();
		phoneNumber.setColumns(30);
		phoneLabel = new JLabel();
		phoneLabel.setText("Phone");
		
		address = new JTextField();
		address.setColumns(30);
		addressLabel = new JLabel();
		addressLabel.setText("Address");
		
		
		
		
		submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitValuesActionPerformed(e);
			}

			

			
		});
		submitButton.setText("Submit");
		
		
		cancelButton = new JButton();
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToCustomerFrame();
			}
		});
		cancelButton.setText("Cancel");
		
		List<Customer> arr = memService.getCustomers();
		list = new JList(arr.toArray());
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(423)
							.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(264)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(firstNameLabel)
								.addComponent(lastNameLabel)
								.addComponent(phoneLabel)
								.addComponent(addressLabel)
								.addComponent(emailLabel))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(address, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(phoneNumber, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(email, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(firstName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(397, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(112)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstNameLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastNameLabel))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailLabel))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(phoneLabel))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressLabel))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(257, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		list.setVisible(true);
		
		
	}

	
	public void updateList(List<Customer> arr){
		list.setListData(arr.toArray());
	}

	@Override
	public void setCreatePresenter(CreatePresenter presenter) {
		this.presenter=presenter;
	}
	
	private void submitValuesActionPerformed(ActionEvent e) {
		String first = firstName.getText();
		String last=lastName.getText();
		String addr=address.getText();
		String Email =email.getText();
		String phone=phoneNumber.getText();
		presenter.submitValues(first,last,addr,Email,phone,contentPane,memService);
		
	}
	
	public void returnToCustomerFrame() {
		CustomerJFrame pframe = new CustomerJFrame(memService);
		CustomerPresenter pres = new CustomerPresenter(pframe);
		pres.start();
		presenter.cancel();
	}
	
	@Override
	public void showInfo(String message){
		JOptionPane.showMessageDialog(null, message);
	 }
	
	



	

}
