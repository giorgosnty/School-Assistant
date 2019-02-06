package ui.customer;
import java.awt.BorderLayout;

import domain.Customer;
import memService.MemoryService;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JList;
import javax.swing.JOptionPane;

import ui.DefaultJFrame;
import ui.View;
import ui.customer.CreateCustomer.CreateCustomerJFrame;
import ui.customer.CreateCustomer.CreatePresenter;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CustomerJFrame extends DefaultJFrame implements CustomerView  {

	private JPanel contentPane;
	private CustomerPresenter presenter;
	
	private JTextField byName;
	private JTextField byPhoneNum;
	
	
	private JLabel byNameLabel;
	private JLabel byPhoneNumLabel;
	private JLabel resultLabel;
	
	private JList list;
	private JList custList;
	
	private List<Customer> customers;
	String firstName;
	String lastName;
	String address; 
	private MemoryService memService;
	private JButton back_btn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerJFrame frame = new CustomerJFrame(new MemoryService());
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
	public CustomerJFrame(MemoryService memService) {
		this.memService = memService;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		byNameLabel = new JLabel();
		byNameLabel.setText("Search by Name");
		byNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		byName= new JTextField();
		byName.setColumns(30);
		
		byPhoneNumLabel = new JLabel();
		byPhoneNumLabel.setText("Search by phone Number");
		byPhoneNumLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		byPhoneNum= new JTextField();
		byPhoneNum.setColumns(30);

		
		JButton search = new JButton("Search");
		search.setPreferredSize(new Dimension(150, 50));
		search.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                searchActionPerformed(e);
              
			}
		});
		
		back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                presenter.goBack(memService);
              
			}
		});
		
		
		
		custList = new JList();
		custList.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		custList.setVisibleRowCount(3);
		customers = memService.getCustomers();
		showCustomersListData(customers);
		//customers = memService.getCustomers();
		//showSelectedCustomers(customers);
		
		
		JButton clients_btn = new JButton("Create Client");
		clients_btn.setPreferredSize(new Dimension(150, 50));
		clients_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		clients_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.createNewCustomer(memService);
                
			}
		});
		
		JButton edit_btn = new JButton("Edit Customer");
		edit_btn.setPreferredSize(new Dimension(200, 50));
		edit_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		edit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = custList.getSelectedIndex();
				if(selected >=0){
					Customer chosen = customers.get(selected);
					presenter.fillTextFields(selected,chosen, memService);
				}else{
					showError("Error occured! Customer has not been selected");
				}
				
			}

			
		});
		
		JButton delete_btn = new JButton("Delete Customer");
		delete_btn.setPreferredSize(new Dimension(200, 50));
		delete_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = custList.getSelectedIndex();
				Customer chosen = customers.get(selected);
				if(selected >= 0){
					presenter.deleteCustomer(memService, chosen);
				}else{
					showError("Error occured! Customer has not been selected");
				}
			}
		});
	
	
		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.setPreferredSize(new Dimension(200, 50));
		cancel_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.cancel();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(cancel_btn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(42)
									.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(268)
												.addComponent(byNameLabel)
												.addGap(4)
												.addComponent(byName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(197)
												.addComponent(byPhoneNumLabel)
												.addGap(4)
												.addComponent(byPhoneNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(custList, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
							.addGap(76)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(delete_btn, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
								.addComponent(clients_btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(edit_btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(162))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(byNameLabel)
								.addComponent(byName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(byPhoneNumLabel)
								.addComponent(byPhoneNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addComponent(clients_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(edit_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(delete_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(345)
							.addComponent(cancel_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(custList, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	public void showCustomersListData(List<Customer> customers) {
		this.customers = customers;
		List<String> cs = new ArrayList<String>();
		for(Customer c : customers){
			cs.add(c.getFirstName() + " " + c.getLastName() + ", Tel: " + c.getPhoneNumber());
		}
		custList.setListData(cs.toArray());
		
	}

	@Override
	public void setCustomerPresenter(CustomerPresenter presenter) {
		this.presenter=presenter;
		
	}

	
	
	private void searchActionPerformed(ActionEvent e){
		String name=byName.getText();
		String phone=byPhoneNum.getText();
		presenter.searchCustomer(name,phone);
	}

	@Override
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone) {
		return memService.findCustomerByLastNameAndTelephone(lastname, telephone);
	}

	@Override
	public List<Customer> findCustomerByLastName(String lastname) {
		return memService.findCustomerByLastName(lastname);

	}

	@Override
	public List<Customer> findCustomerByTelephone(String telephone) {
		return memService.findCustomerByTelephone(telephone);
	}

	
	
	
	

	
	
}
