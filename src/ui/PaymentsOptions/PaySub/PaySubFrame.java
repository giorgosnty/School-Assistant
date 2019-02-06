package ui.PaymentsOptions.PaySub;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import domain.Customer;
import domain.MonthlySubscription;
import memService.MemoryService;
import net.miginfocom.swing.MigLayout;
import ui.DefaultJFrame;

public class PaySubFrame extends DefaultJFrame implements PaySubView {

	private PaySubPresenter presenter;
	private MemoryService data;
	private JList subsList;
	private JTextField nameTextField;
	private JTextField telephoneTextField;
	private JTextField payValueText;
	private JButton payAmount;
	private List<MonthlySubscription> ms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaySubFrame win = new PaySubFrame(new MemoryService());
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaySubFrame(MemoryService data) {
		this.data = data;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1223, 721);
		
		JLabel lblSearchClient = new JLabel("Search Client");
		lblSearchClient.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		
		nameTextField = new JTextField();
		nameTextField.setColumns(15);
		
		telephoneTextField = new JTextField();
		telephoneTextField.setColumns(15);
		
		JButton search_btn = new JButton("Search");
		search_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.showClientSubs(nameTextField.getText(), telephoneTextField.getText());
			}
		});
		
		subsList = new JList();
		subsList.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		ms = data.getUnpaidSubs();
    	showUnpaidSubscriptionList(ms);
    	
		JButton payAmount = new JButton("Pay Chosen Amount");
		payAmount.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		payAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = subsList.getSelectedIndex();
				if(selected != -1)
					presenter.payAmount(payValueText.getText(), selected);
				else
					JOptionPane.showMessageDialog(null, "Error: No Subscription Selected yet");
			}
		});
		
		payValueText = new JTextField();
		payValueText.setColumns(15);
		
		JLabel lblAmount = new JLabel("Amount to Pay");
		lblAmount.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		
		JButton btnPayEntirePrice = new JButton("Pay Entire Price");
		btnPayEntirePrice.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnPayEntirePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = subsList.getSelectedIndex();
				if(selected != -1)
					presenter.payEntireAmount(selected);
				else
					JOptionPane.showMessageDialog(null, "Error: No Subscription Selected yet");
			}
		});
		
		JButton back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.goBack(data);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(84)
					.addComponent(lblSearchClient)
					.addContainerGap(836, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(nameLabel)
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelephone)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(telephoneTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(search_btn, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(511, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(131)
					.addComponent(subsList)
					.addContainerGap(1070, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(695, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(payValueText, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addGap(383))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(664, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnPayEntirePrice, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(payAmount, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
					.addGap(365))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearchClient))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nameLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelephone)
							.addGap(10)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(telephoneTextField, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addComponent(search_btn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addGap(82)
					.addComponent(subsList)
					.addGap(93)
					.addComponent(lblAmount, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(payValueText, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(payAmount, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnPayEntirePrice, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(120))
		);
		getContentPane().setLayout(groupLayout);
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
	
	public List<MonthlySubscription> getUnpaidCurrentList() {
		return ms;
	}
	
	public void showUnpaidSubscriptionList(List<MonthlySubscription> ms){
		this.ms = ms;
		List<String> subs = new ArrayList<String>();
		for(MonthlySubscription s : ms){
			subs.add(s.getCustomer().getFirstName() + " " + s.getCustomer().getLastName() + ": " + s.getRemainingAmount());
		}
		subsList.setListData(subs.toArray());
	}
	
	public void refreshAmount(){
		if(payAmount != null){
			payValueText.setText("");
		}
	}
	
	public List<MonthlySubscription> getUnpaidSubs() {
		return data.getUnpaidSubs();
	}
	
	public void setPresenter(PaySubPresenter p) {
		this.presenter = p;
	}
}
