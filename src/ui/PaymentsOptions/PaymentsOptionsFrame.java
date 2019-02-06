package ui.PaymentsOptions;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import memService.MemoryService;
import net.miginfocom.swing.MigLayout;
import ui.DefaultJFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PaymentsOptionsFrame extends DefaultJFrame implements PaymentsOptionsView{
	private JPanel contentPane;
	private PaymentsOptionsPresenter presenter;
	private MemoryService data;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentsOptionsFrame win = new PaymentsOptionsFrame(new MemoryService());
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
	public PaymentsOptionsFrame(MemoryService data) {
		this.data = data;
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1223, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton showunpaid_btn = new JButton("Show unpaid Subscriptions");
		showunpaid_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.showUnpaid(data);
			}
		});
		showunpaid_btn.setPreferredSize(new Dimension(350, 150));
		showunpaid_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		
		JButton pay_btn = new JButton("Pay Subscription");
		pay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.paySub(data);
			}
		});
		pay_btn.setPreferredSize(new Dimension(350, 150));
		pay_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		
		JButton back_btn = new JButton("Back to Menu");
		back_btn.setPreferredSize(new Dimension(350, 150));
		back_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.goBack(data);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(showunpaid_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(91)
							.addComponent(pay_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(215, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(98)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(showunpaid_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pay_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setPresenter(PaymentsOptionsPresenter p){
		this.presenter = p; 
	}
}
