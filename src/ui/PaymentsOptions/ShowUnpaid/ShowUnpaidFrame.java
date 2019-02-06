package ui.PaymentsOptions.ShowUnpaid;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.MonthlySubscription;
import memService.MemoryService;
import ui.DefaultJFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class ShowUnpaidFrame extends DefaultJFrame implements ShowUnpaidView {
	private MemoryService data;
	private ShowUnpaidPresenter presenter;
	private JPanel contentPane;
	private JList subsList;
	private JTextPane subDetailsText;
	private List<MonthlySubscription> ms;
	private JLabel lblSubscriptionDetails;
	private JButton back_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUnpaidFrame frame = new ShowUnpaidFrame(new MemoryService());
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
	public ShowUnpaidFrame(MemoryService data) {
		this.data = data;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUnpaidSubscriptions = new JLabel("Unpaid Subscriptions");
		lblUnpaidSubscriptions.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		
		subsList = new JList();
		subsList.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		ms = data.getUnpaidSubs();
    	showUnpaidSubscriptionList(ms);
		subDetailsText = new JTextPane();
		subDetailsText.setEditable(false);
		
		lblSubscriptionDetails = new JLabel("Subscription Details");
		lblSubscriptionDetails.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		
		JButton dhowdetails_btn = new JButton("Show Details");
		dhowdetails_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = subsList.getSelectedIndex();
	            presenter.showSubDetails(selected);
			}
		});
		
		back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            presenter.goBack(data);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(118)
					.addComponent(subsList, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
					.addGap(118)
					.addComponent(subDetailsText, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(202)
					.addComponent(dhowdetails_btn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(825, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(161)
					.addComponent(lblUnpaidSubscriptions)
					.addPreferredGap(ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
					.addComponent(lblSubscriptionDetails, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addGap(226))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(985, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnpaidSubscriptions)
						.addComponent(lblSubscriptionDetails, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(subsList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(subDetailsText, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(dhowdetails_btn, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void showUnpaidSubscriptionList(List<MonthlySubscription> ms){
		this.ms = ms;
		List<String> subs = new ArrayList<String>();
		for(MonthlySubscription s : ms){
			subs.add(s.getCustomer().getFirstName() + " " + s.getCustomer().getLastName() + ": " + s.getRemainingAmount());
		}
		subsList.setListData(subs.toArray());
	}
	
	public MonthlySubscription findSub(int index){
		return ms.get(index);
	}
	
	public void showSubInfo(String info){
		subDetailsText.setText(info);
	}
	
	public void setPresenter(ShowUnpaidPresenter p){
		this.presenter = p;
	}
}
