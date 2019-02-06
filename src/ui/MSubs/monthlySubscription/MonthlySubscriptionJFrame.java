package ui.MSubs.monthlySubscription;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.Customer;
import memService.MemoryService;
import net.miginfocom.swing.MigLayout;
import ui.DefaultJFrame;
import ui.customer.CustomerJFrame;
import ui.customer.CustomerPresenter;

public class MonthlySubscriptionJFrame extends DefaultJFrame implements MonthlySubscriptionView  {
																 
	private JPanel contentPane;
	
	private JLabel byNameLabel;
	private JLabel byPhoneNumLabel;
	private List<Customer> customers;
	
	private JTextField byName;
	private JTextField byPhoneNum;
	private MonthlyPresenter presenter;
	private MemoryService memService;
	private JButton back_btn;
	private JButton search_btn;
	private JLabel resultLabel;
	private JButton monthly_s_btn;
	private JButton renew_btn;
	private JScrollPane scrollPane;
	private JList custList;
	private JButton show_btn;
	private JTextField textFieldStart;
	private JTextField textFieldEnd;
	private JTextField textFieldPrice;
	private JLabel lblStartingDate;
	private JLabel lblEndingDate;
	private JLabel lblPrice;
	private JLabel lblSlots;
	private JList showList;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlySubscriptionJFrame frame = new MonthlySubscriptionJFrame(new MemoryService());
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
	public MonthlySubscriptionJFrame(MemoryService memService) {
		this.memService=memService;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][][][][][]", "[][][][][][][132.00][41.00][][44.00][184.00,grow]"));
		setContentPane(contentPane);
		
		
		back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                presenter.goBack(memService);
              
			}
		});
		contentPane.add(back_btn, "flowx,cell 0 0");
		
		byPhoneNum = new JTextField();
		byPhoneNum.setColumns(30);
		
		
		byNameLabel = new JLabel();
		byNameLabel.setText("Search by Name");
		byNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		byName = new JTextField();
		byName.setColumns(30);
		
		contentPane.add(byNameLabel, "cell 1 0");
		contentPane.add(byName, "cell 2 0");
		
		byPhoneNumLabel = new JLabel();
		byPhoneNumLabel.setText("Search by phone Number");
		byPhoneNumLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		contentPane.add(byPhoneNumLabel,"cell 1 2,alignx right");
		contentPane.add(byPhoneNum,"flowx,cell 2 2");
		
		
	
		monthly_s_btn = new JButton("Create Monthly Subscription");
		monthly_s_btn.setPreferredSize(new Dimension(150, 50));
		monthly_s_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		monthly_s_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int selected = custList.getSelectedIndex();
				
				if(selected >= 0){
					Customer chosen = customers.get(selected);
					if(chosen.isEligibleForNewSub()){
						presenter.createSub(memService, chosen);
					}else{
						showError("Customer has not paid previous subscription.");
					}
					
				}else{
				
					showError("Error occured! Customer has not been selected");
				}  
			}
		});
		
		
		custList = new JList();
		custList.setVisibleRowCount(3);
		custList.setVisibleRowCount(3);
		customers = memService.getCustomers();
		showCustomersListData(customers);
		
		contentPane.add(custList, "cell 2 5,grow");
		
				show_btn = new JButton("Show Subscription Details");
				show_btn .setPreferredSize(new Dimension(150, 50));
				show_btn .setFont(new Font("Segoe UI", Font.PLAIN, 21));
				show_btn .addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selected = custList.getSelectedIndex();
						Customer chosen = customers.get(selected);
						if(selected >= 0){
							presenter.showDetails(memService, chosen,showList,textFieldStart,textFieldEnd,textFieldPrice);
						}else{
							showError("Error occured! Customer has not been selected");
						}
      
					}
				});
		contentPane.add(show_btn ,"cell 1 6,alignx right");
		contentPane.add(monthly_s_btn,"cell 2 6");
		
		renew_btn = new JButton("Renew Monthly Subscription");
		renew_btn.setPreferredSize(new Dimension(200, 50));
		renew_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		renew_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int selected = custList.getSelectedIndex();
				Customer chosen = customers.get(selected);
				if(selected >= 0){
					presenter.renewSub(memService, chosen);
				}else{
					showError("Error occured! Customer has not been selected");
				}
				
			}
		});
		contentPane.add(renew_btn, "cell 2 6" );
		
		
		search_btn = new JButton("Search");
		search_btn .setPreferredSize(new Dimension(150, 50));
		search_btn .setFont(new Font("Segoe UI", Font.PLAIN, 21));
		search_btn .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed();
      
			}
		});
		
		contentPane.add(search_btn ,"cell 2 2,alignx right");
		
		lblStartingDate = new JLabel("Starting Date");
		contentPane.add(lblStartingDate, "cell 0 7,alignx trailing");
		
		textFieldStart = new JTextField();
		contentPane.add(textFieldStart, "cell 1 7,growx");
		textFieldStart.setColumns(10);
		
		lblEndingDate = new JLabel("Ending Date");
		contentPane.add(lblEndingDate, "cell 0 8,alignx trailing");
		
		textFieldPrice = new JTextField();
		contentPane.add(textFieldPrice, "cell 1 8,growx");
		textFieldPrice.setColumns(10);
		
		lblPrice = new JLabel("Price");
		contentPane.add(lblPrice, "cell 0 9,alignx trailing");
		
		textFieldEnd = new JTextField();
		contentPane.add(textFieldEnd, "cell 1 9,growx");
		textFieldEnd.setColumns(10);
		
		lblSlots = new JLabel("Slots");
		contentPane.add(lblSlots, "cell 0 10,align right");
		
		showList = new JList();
		contentPane.add(showList, "cell 1 10,grow");
				
			
				
	}
	
	private void searchActionPerformed(){
		String name=byName.getText();
		System.out.print(name);
		String phone=byPhoneNum.getText();
		presenter.searchCustomer(name,phone);
	}


	@Override
	public void setMonthlyPresenter(MonthlyPresenter presenter) {
		this.presenter=presenter;
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

	@Override
	public void showMessage(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}
		
	@Override
	public void showCustomersListData(List<Customer> customers) {
		this.customers = customers;
		List<String> cs = new ArrayList<String>();
		for(Customer c : customers){
			cs.add(c.getFirstName() + " " + c.getLastName() + ", Tel: " + c.getPhoneNumber());
		}
		custList.setListData(cs.toArray());
		
	}

	



}
