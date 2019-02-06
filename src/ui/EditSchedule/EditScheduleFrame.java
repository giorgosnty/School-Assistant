package ui.EditSchedule;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Lesson;
import domain.Room;
import domain.Slot;
import memService.MemoryService;
import ui.DefaultJFrame;
import ui.View;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;

public class EditScheduleFrame extends DefaultJFrame implements EditScheduleView {
	
	private JPanel contentPane;
	private EditSchedulePresenter presenter;
	private MemoryService data;
	private JList lessons_list;
	private JList slots_list;
	private List<Lesson> lessons;
	private List<Slot> slots;
	private JTextField LNameTextField;
	private JTextField DurationTextField;
	private JTextField PricePackageTextField;
	private JTextField startingTimeTextField;
	private JTextField dayTextField;
	private JTextField priceSingleTextField;
	private JTextField descriptionTextField;
	private JTextField teacherTextField;
	private JTextField roomTextField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel Lesson;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_11;
	private JLabel lblSlots_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditScheduleFrame frame = new EditScheduleFrame(new MemoryService());
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
	public EditScheduleFrame(MemoryService data) {
		this.data = data;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1459, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton back_btn = new JButton("Back to Menu");
		back_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.goBack(data);
			}
		});
		
		JLabel lblLessons = new JLabel("Lessons");
		lblLessons.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		
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
		slots_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
            	presenter.showSlotForEdit(slots_list.getSelectedIndex());
            }
        });
		
		JButton new_lesson_btn = new JButton("New Lesson");
		new_lesson_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		new_lesson_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				presenter.createLesson(LNameTextField,DurationTextField,PricePackageTextField,priceSingleTextField,descriptionTextField);
				presenter.clear();
			}
		});
		
		JButton edit_lesson_btn = new JButton("Edit Lesson");
		edit_lesson_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		edit_lesson_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                presenter.editLesson(LNameTextField.getText(), DurationTextField.getText(), PricePackageTextField.getText(), priceSingleTextField.getText(), descriptionTextField.getText());
			}
		});
		
		JButton delete_lesson_btn = new JButton("Delete Lesson");
		delete_lesson_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		delete_lesson_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                presenter.deleteLesson(lessons_list.getSelectedIndex());
			}
		});
		
		JButton new_slot_btn = new JButton("New Slot");
		new_slot_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		new_slot_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.addSlot(lessons_list.getSelectedIndex(),startingTimeTextField,dayTextField,
						teacherTextField,roomTextField);
			}

			
		});
		
		JButton edit_slot_btn = new JButton("Edit Slot");
		edit_slot_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		edit_slot_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                presenter.editSlot(startingTimeTextField.getText(), dayTextField.getText(), teacherTextField.getText(), roomTextField.getText());
			}
		});
		
		JButton delete_slot_btn = new JButton("Delete Slot");
		delete_slot_btn.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		delete_slot_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                presenter.deleteSlot(slots_list.getSelectedIndex());
			}
		});
		
		
		
		LNameTextField = new JTextField();
		LNameTextField.setColumns(10);
		
		
		DurationTextField = new JTextField();
		DurationTextField.setColumns(10);
		
		
		PricePackageTextField = new JTextField();
		PricePackageTextField.setColumns(10);
		
		
		startingTimeTextField = new JTextField();
		startingTimeTextField.setColumns(10);
		
		
		dayTextField = new JTextField();
		dayTextField.setColumns(10);
		
		
		priceSingleTextField = new JTextField();
		priceSingleTextField.setColumns(10);
		
		
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		
		
		teacherTextField = new JTextField();
		teacherTextField.setColumns(10);
		
		
		roomTextField = new JTextField();
		roomTextField.setColumns(10);
		
		lblNewLabel = new JLabel("Name:");
		
		lblNewLabel_1 = new JLabel("Duration:");
		
		lblNewLabel_2 = new JLabel("price Package:");
		
		lblNewLabel_3 = new JLabel("price Single:");
		
		lblNewLabel_4 = new JLabel("Description:");
		
		Lesson = new JLabel("Lesson");
		
		lblNewLabel_6 = new JLabel("Starting Time:");
		
		lblNewLabel_8 = new JLabel("Day:");
		
		lblNewLabel_9 = new JLabel("Teacher:");
		
		lblNewLabel_11 = new JLabel("Room:");
		
		lblSlots_1 = new JLabel("Slots");
		
		JLabel lblEx = new JLabel("ex. 10:15");
		
		JLabel lblInsertFor = new JLabel("Insert 1 for Monday etc.");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lessons_list, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(slots_list, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(lblLessons, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(195)
							.addComponent(lblSlots, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4))
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(LNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(DurationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PricePackageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(priceSingleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Lesson))
							.addGap(79))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(delete_lesson_btn, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(edit_lesson_btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(new_lesson_btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
							.addGap(137)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_9)
								.addComponent(lblNewLabel_11)
								.addComponent(lblInsertFor)
								.addComponent(lblNewLabel_8)
								.addComponent(lblEx)
								.addComponent(new_slot_btn, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
							.addGap(71)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dayTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(startingTimeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(lblSlots_1))
								.addComponent(teacherTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(roomTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(edit_slot_btn, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(delete_slot_btn, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
					.addGap(89))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Lesson)
						.addComponent(lblSlots_1))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(LNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(startingTimeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEx, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(DurationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)))
						.addComponent(back_btn, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(dayTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(PricePackageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblInsertFor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(30)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_3)
										.addComponent(priceSingleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(teacherTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_9))))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_11)
									.addComponent(roomTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_4))
							.addGap(95)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(new_lesson_btn)
								.addComponent(new_slot_btn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(edit_lesson_btn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(edit_slot_btn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(delete_lesson_btn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(delete_slot_btn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLessons, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSlots, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lessons_list, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addComponent(slots_list, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))))
					.addGap(181))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	public void showLessons() {
		List<String> ls = new ArrayList<String>();
		int i = 1;
		for(Lesson l : lessons){
			ls.add(i+") " + l.getName());
			i++;
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
			i++;
		}
		slots_list.setListData(sl.toArray());
	}
	
	public void showLessonForEdit(Lesson l){
		LNameTextField.setText(l.getName());
		DurationTextField.setText(l.getDuration()+"");
		PricePackageTextField.setText(l.getPricePackage()+"");
		priceSingleTextField.setText(l.getpriceSingle()+"");
		descriptionTextField.setText(l.getDescription());
	}
	
	public void showSlotForEdit(Slot slot){
		int min = slot.getStartingTime().getMinute();
		String min_str = "";
		if(min < 10){
			min_str = "0" + min;
		} else{
			min_str = min + "";
		}
		startingTimeTextField.setText(slot.getStartingTime().getHour() + ":" + min_str);
		dayTextField.setText(slot.getDay().getValue()+"");
		teacherTextField.setText(slot.getTeacher());
		roomTextField.setText(slot.getRoom().getName());
	}
	
	public void updateLesson(Lesson lesson, String name, int duration, double price_pack, double price_single, String desc){
		data.updateLesson(lesson, name, duration, price_pack, price_single, desc);
	}
	
	public void updateSlot(Lesson lesson, Slot slot, LocalTime startTime, int day, String teacher, String room){
		data.updateSlot(lesson, slot, startTime, day, teacher, room);
	}
	
	public Lesson getLesson(int index){
		return lessons.get(index);
	}
	
	public void deleteLesson(Lesson l){
		data.deleteLesson(l);
	}
	
	public Slot getSlot(int selectedIndex){
		return slots.get(selectedIndex);
	}
	
	public void deleteSlot(Slot s){
		data.deleteSlot(s);
	}
	
	public void setPresenter(EditSchedulePresenter presenter) {
		this.presenter = presenter;
	}
	
	public void updateLessons() {
		this.lessons = data.getLessons();
		showLessons();
		showSlots(new ArrayList<Slot>());
	}
	
	public void updateSlots() {
		Lesson l = getLesson(lessons_list.getSelectedIndex());
		if(l!= null){
			slots = data.getLessonSlots(l);
			showSlots(slots);
		}
	}

	@Override
	public void addLesson(Lesson l) {
		data.addLesson(l);
		
	}

	@Override
	public void addSlot(Lesson l,Slot s) {
		data.addSlot(l, s);
		
	}

	@Override
	public Room getRoom(String r) {
		return data.getRoom(r);
		
	}

	@Override
	public void addRoom(Room r) {
		data.addRoom(r);
		
	}
	
	public void clearLessonTextFields(){
		LNameTextField.setText("");
		DurationTextField.setText("");
		PricePackageTextField.setText("");
		priceSingleTextField.setText("");
		descriptionTextField.setText("");
	}
	
	public void clearSlotTextFields() {
		dayTextField.setText("");
		startingTimeTextField.setText("");
		teacherTextField.setText("");
		roomTextField.setText("");
	}
	
	public void clearLists(){
		lessons_list.clearSelection();
		slots_list.clearSelection();
	}
}
