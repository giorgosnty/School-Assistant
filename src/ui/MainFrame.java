package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import memService.MemoryService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import ui.EditSchedule.EditScheduleFrame;
import ui.EditSchedule.EditSchedulePresenter;
import ui.MSubs.monthlySubscription.MonthlyPresenter;
import ui.MSubs.monthlySubscription.MonthlySubscriptionJFrame;
import ui.PaymentsOptions.PaymentsOptionsFrame;
import ui.PaymentsOptions.PaymentsOptionsPresenter;
import ui.ScheduleReports.ScheduleReportsFrame;
import ui.ScheduleReports.ScheduleReportsPresenter;
import ui.SingleLessonEnrollment.SingleLessonEnrollFrame;
import ui.SingleLessonEnrollment.SingleLessonEnrollPresenter;
import ui.SubmitPresence.SubmitPresenceFrame;
import ui.SubmitPresence.SubmitPresencePresenter;
import ui.customer.CustomerJFrame;
import ui.customer.CustomerPresenter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainFrame extends DefaultJFrame{
	private JPanel contentPane;
	private MemoryService data;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		data = new MemoryService();
		//System.out.println(data.getUnpaidSubs().toString());
		initialize();
	}
	
	public MainFrame(MemoryService data){
		this.data = data;
		initialize();
	}
	
	public void initialize(){
		setBounds(100, 100, 1223, 721);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton clients_btn = new JButton("Client Information");
		clients_btn.setPreferredSize(new Dimension(350, 150));
		clients_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		clients_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerJFrame pframe = new CustomerJFrame(data);
				CustomerPresenter pres = new CustomerPresenter(pframe);
				pres.start();
				close();
			}
		});
		
		JButton sub_btn = new JButton("Monthly Subscription Management");
		sub_btn.setPreferredSize(new Dimension(350, 150));
		sub_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		sub_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MonthlySubscriptionJFrame pframe = new MonthlySubscriptionJFrame(data);
				MonthlyPresenter pres = new MonthlyPresenter(pframe);
				pres.start();
				close();
			}
		});
		
		JButton scheduleReports_btn = new JButton("Schedule Reports");
		scheduleReports_btn.setPreferredSize(new Dimension(350, 150));
		scheduleReports_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		scheduleReports_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScheduleReportsFrame pframe = new ScheduleReportsFrame(data);
				ScheduleReportsPresenter pres = new ScheduleReportsPresenter(pframe);
				pres.start();
				close();
			}
		});
		
		JButton editSchedule_btn= new JButton("Edit Schedule");
		editSchedule_btn.setPreferredSize(new Dimension(350, 150));
		editSchedule_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		editSchedule_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditScheduleFrame pframe = new EditScheduleFrame(data);
				EditSchedulePresenter pres = new EditSchedulePresenter(pframe);
				pres.start();
				close();
			}
		});
		
		JButton singleLesson_btn = new JButton("Single Lesson Enrollment");
		singleLesson_btn.setPreferredSize(new Dimension(350, 150));
		singleLesson_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		singleLesson_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SingleLessonEnrollFrame pframe = new SingleLessonEnrollFrame(data);
				SingleLessonEnrollPresenter pres = new SingleLessonEnrollPresenter(pframe);
				pres.start();
				close();
			}
		});
		
		JButton presence_btn = new JButton("Submit Presence");
		presence_btn.setPreferredSize(new Dimension(350, 150));
		presence_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		presence_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubmitPresenceFrame pframe = new SubmitPresenceFrame(data);
				SubmitPresencePresenter pres = new SubmitPresencePresenter(pframe);
				pres.start();
				close();
			}
		});
		
		JButton payments_btn = new JButton("Payments Management");
		payments_btn.setPreferredSize(new Dimension(350, 150));
		payments_btn.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		payments_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentsOptionsFrame pframe = new PaymentsOptionsFrame(data);
				PaymentsOptionsPresenter pres = new PaymentsOptionsPresenter(pframe);
				pres.start();
				close();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(clients_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(sub_btn, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(scheduleReports_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(singleLesson_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(editSchedule_btn, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(payments_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(presence_btn, GroupLayout.PREFERRED_SIZE, 1125, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(clients_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sub_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scheduleReports_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(singleLesson_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editSchedule_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(payments_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addComponent(presence_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
