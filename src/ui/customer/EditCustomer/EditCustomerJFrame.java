package ui.customer.EditCustomer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EditCustomerJFrame extends DefaultJFrame implements EditCustomerView {

	private JPanel contentPane;
	private JTextField lastName;
	private JTextField firstName;
	private JTextField phoneNumber;
	private JTextField email;
	private JTextField address;

	private EditPresenter presenter;
	private JLabel phoneLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
	
	private JButton submitButton;
	private JButton cancelButton;
	
	MemoryService memService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCustomerJFrame frame = new EditCustomerJFrame(new MemoryService());
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
	public EditCustomerJFrame(MemoryService memService) {
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
				presenter.cancel();
			}
		});
		cancelButton.setText("Cancel");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(350, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(409))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(emailLabel)
									.addGap(41)
									.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(firstNameLabel)
									.addGap(4)
									.addComponent(firstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lastNameLabel)
									.addGap(6)
									.addComponent(lastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(addressLabel)
									.addGap(23)
									.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(phoneLabel)
									.addGap(36)
									.addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(339))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(129)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(firstNameLabel))
						.addComponent(firstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lastNameLabel))
						.addComponent(lastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(emailLabel))
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(phoneLabel))
						.addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(addressLabel))
						.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(submitButton, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
					.addContainerGap(232, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}



	@Override
	public void setEditPresenter(EditPresenter editPresenter) {
		this.presenter=editPresenter;
	}
	
	@Override
	public void showError(String error){
		JOptionPane.showMessageDialog(null, error);
	}
	
	public void setLastName(String last){
		lastName.setText(last);	
	}
	
	public void setFirstName(String first){
		firstName.setText(first);	
	}
	
	public void setAddress(String addr){
		address.setText(addr);	
	}
	
	public void setEmail(String em){
		email.setText(em);	
	}
	
	public void setPhone(String ph){
		phoneNumber.setText(ph);	
	}
	
	private void submitValuesActionPerformed(ActionEvent e) {
		String first = firstName.getText();
		String last=lastName.getText();
		String addr=address.getText();
		String Email =email.getText();
		String phone=phoneNumber.getText();
		
		Customer cust = new Customer(first,last,addr,Email,phone);
		presenter.submitValues(first,last,addr,Email,phone, memService);
		
	}

	public void returnToCustomerFrame() {
		CustomerJFrame pframe = new CustomerJFrame(memService);
		CustomerPresenter pres = new CustomerPresenter(pframe);
		pres.start();
		presenter.cancel();
	}

}
