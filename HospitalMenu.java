import java.awt.*;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HospitalMenu {

	private JFrame frame;
	private JTextField txtPatientName;
	private JSpinner spinPatientAge;
	private JTextField txtPhysicianSpecialty;
	private JTextField txtPhysicianName;
	private JComboBox cbVisitName;
	private JTextField txtVisitCondition;
	private JComboBox cbAvailablePhysician;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnNewPatient;
	private JButton btnNewPhysician;
	private JButton btnAddNewVisit;
	private JComboBox cbListPV;
	private JComboBox cbAttendingPhysician;
	
	PatientList patientList = new PatientList();
    PhysicianList physicianList = new PhysicianList();
    VisitList visitList = new VisitList();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalMenu window = new HospitalMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HospitalMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Hospital Management System");
		frame.setBounds(100, 100, 715, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setBounds(25, 15, 130, 15);
		frame.getContentPane().add(lblPatientName);
		
		txtPatientName = new JTextField();
		txtPatientName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtPatientName.getText().equals("")) {
					btnNewPatient.setEnabled(true);
				} else {
					btnNewPatient.setEnabled(false);
				}
			}
		});
		txtPatientName.setHorizontalAlignment(SwingConstants.LEFT);
		txtPatientName.setBounds(20, 30, 160, 25);
		frame.getContentPane().add(txtPatientName);
		txtPatientName.setColumns(10);
		
		JLabel lblPatientAge = new JLabel("Age");
		lblPatientAge.setBounds(25, 65, 50, 15);
		frame.getContentPane().add(lblPatientAge);
		
		spinPatientAge = new JSpinner();
		spinPatientAge.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinPatientAge.setToolTipText("");
		spinPatientAge.setBounds(20, 80, 60, 25);
		frame.getContentPane().add(spinPatientAge);
		
		JLabel lblPatientSex = new JLabel("Sex");
		lblPatientSex.setBounds(95, 65, 85, 15);
		frame.getContentPane().add(lblPatientSex);
		
		JComboBox cbPatientSex = new JComboBox();
		cbPatientSex.addItem("Male");
		cbPatientSex.addItem("Female");
		cbPatientSex.setBackground(Color.WHITE);
		cbPatientSex.setToolTipText("");
		cbPatientSex.setBounds(90, 80, 90, 25);
		frame.getContentPane().add(cbPatientSex);


		btnNewPatient = new JButton("Add New Patient");
		btnNewPatient.setEnabled(false);
		btnNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				patientList.addPatient(0, txtPatientName.getText(), "Age: " + spinPatientAge.getValue() + " Sex: " + cbPatientSex.getSelectedItem());
				textArea.setText("Current Patients:\n" + patientList.printTree());
				cbVisitName.setModel(new DefaultComboBoxModel(patientList.printTree().split("\n")));

				cbListPV.setModel(new DefaultComboBoxModel(patientList.printTree().split("\n")));
				txtPatientName.setText("");
				btnNewPatient.setEnabled(false);
			}
		});
		btnNewPatient.setBackground(Color.LIGHT_GRAY);
		btnNewPatient.setBounds(25, 115, 150, 25);
		frame.getContentPane().add(btnNewPatient);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBackground(Color.WHITE);
		separator1.setForeground(Color.GRAY);
		separator1.setBounds(5, 150, 190, 1);
		frame.getContentPane().add(separator1);
		
		JLabel lblPhysicianName = new JLabel("Physician Name");
		lblPhysicianName.setBounds(25, 165, 130, 15);
		frame.getContentPane().add(lblPhysicianName);
		
		txtPhysicianName = new JTextField();
		txtPhysicianName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtPhysicianName.getText().equals("")) {
					btnNewPhysician.setEnabled(true);
				} else {
					btnNewPhysician.setEnabled(false);
				}
			}
		});
		txtPhysicianName.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhysicianName.setColumns(10);
		txtPhysicianName.setBounds(20, 180, 160, 25);
		frame.getContentPane().add(txtPhysicianName);
		
		JLabel lblPhysicianSpecialty = new JLabel("Physician Specialty");
		lblPhysicianSpecialty.setBounds(25, 215, 130, 15);
		frame.getContentPane().add(lblPhysicianSpecialty);
		
		txtPhysicianSpecialty = new JTextField();
		txtPhysicianSpecialty.setToolTipText("");
		txtPhysicianSpecialty.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhysicianSpecialty.setColumns(10);
		txtPhysicianSpecialty.setBounds(20, 230, 160, 25);
		frame.getContentPane().add(txtPhysicianSpecialty);
		
		btnNewPhysician = new JButton("Add New Physician");
		btnNewPhysician.setEnabled(false);
		btnNewPhysician.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				physicianList.addPhysician(0, txtPhysicianName.getText(), txtPhysicianSpecialty.getText());
				textArea.setText("Current Physicians:\n" + physicianList.printTree());
				cbAvailablePhysician.setModel(new DefaultComboBoxModel(physicianList.printTree().split("\n")));
				cbAttendingPhysician.setModel(new DefaultComboBoxModel(physicianList.printTree().split("\n")));
				txtPhysicianName.setText("");
				txtPhysicianSpecialty.setText("");
				btnNewPhysician.setEnabled(false);
			}
		});
		btnNewPhysician.setBackground(Color.LIGHT_GRAY);
		btnNewPhysician.setBounds(25, 265, 150, 25);
		frame.getContentPane().add(btnNewPhysician);
		
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.GRAY);
		separator2.setBackground(Color.WHITE);
		separator2.setBounds(5, 300, 190, 1);
		frame.getContentPane().add(separator2);
		
		JLabel lblExistingPatientName = new JLabel("Existing Patient Name");
		lblExistingPatientName.setBounds(25, 315, 130, 15);
		frame.getContentPane().add(lblExistingPatientName);
		
		cbVisitName = new JComboBox();
		cbVisitName.addItem("");
		cbVisitName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cbVisitName.setBackground(Color.WHITE);
		cbVisitName.setBounds(20, 330, 160, 25);
		frame.getContentPane().add(cbVisitName);
		
		JLabel lblPatientVisitCondition = new JLabel("Patient Visit Condition");
		lblPatientVisitCondition.setBounds(25, 365, 130, 15);
		frame.getContentPane().add(lblPatientVisitCondition);
		
		txtVisitCondition = new JTextField();
		txtVisitCondition.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtVisitCondition.getText().equals("")) {
					btnAddNewVisit.setEnabled(true);
				} else {
					btnAddNewVisit.setEnabled(false);
				}
			}
		});
		txtVisitCondition.setToolTipText("");
		txtVisitCondition.setHorizontalAlignment(SwingConstants.LEFT);
		txtVisitCondition.setColumns(10);
		txtVisitCondition.setBounds(20, 380, 160, 25);
		frame.getContentPane().add(txtVisitCondition);
		
		btnAddNewVisit = new JButton("Add New Visit");
		btnAddNewVisit.setEnabled(false);
		btnAddNewVisit.setBackground(Color.LIGHT_GRAY);
		btnAddNewVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    visitList.add(String.valueOf(cbVisitName.getSelectedItem()), String.valueOf(cbAvailablePhysician.getSelectedItem()), txtVisitCondition.getText());
			    // patientList.addPatientVisit(String.valueOf(cbVisitName.getSelectedItem()), String.valueOf(cbAvailablePhysician.getSelectedItem()), txtVisitCondition.getText());
				textArea.setText("Current Visits:\n" + visitList.getVisits() + "\n");
				// textArea.setText("Current Visits for " + String.valueOf(cbVisitName.getSelectedItem()) + ":\n" + patientList.getPatientVisits(String.valueOf(cbVisitName.getSelectedItem())));
				txtVisitCondition.setText("");
				btnAddNewVisit.setEnabled(false);
			}
		});
		
		JLabel lblAvailablePhysician = new JLabel("Available Physician");
		lblAvailablePhysician.setBounds(25, 415, 130, 15);
		frame.getContentPane().add(lblAvailablePhysician);
		
		cbAvailablePhysician = new JComboBox();
		cbAvailablePhysician.setBackground(Color.WHITE);
		cbAvailablePhysician.setBounds(20, 430, 160, 25);
		frame.getContentPane().add(cbAvailablePhysician);
		btnAddNewVisit.setBounds(25, 465, 150, 25);
		frame.getContentPane().add(btnAddNewVisit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(200, 20, 300, 475);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.WHITE);	
		
		JButton btnListPatients = new JButton("List Patients");
		btnListPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("Current Patients:\n" + patientList.printTree());
			}
		});
		btnListPatients.setBackground(Color.LIGHT_GRAY);
		btnListPatients.setBounds(525, 30, 150, 25);
		frame.getContentPane().add(btnListPatients);
		
		JButton btnListPhysicians = new JButton("List Physicians");
		btnListPhysicians.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("Current Physicians:\n" + physicianList.printTree());
			}
		});
		btnListPhysicians.setBackground(Color.LIGHT_GRAY);
		btnListPhysicians.setBounds(525, 80, 150, 25);
		frame.getContentPane().add(btnListPhysicians);
		
		JButton btnListVisits = new JButton("List Visits");
		btnListVisits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Current Visits:\n" + visitList.getVisits());
			}
		});
		btnListVisits.setBackground(Color.LIGHT_GRAY);
		btnListVisits.setBounds(525, 130, 150, 25);
		frame.getContentPane().add(btnListVisits);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.GRAY);
		separator3.setBackground(Color.WHITE);
		separator3.setBounds(505, 185, 190, 1);
		frame.getContentPane().add(separator3);
				
		JLabel lblVisitingPatientName = new JLabel("Visiting Patient Name");
		lblVisitingPatientName.setBounds(525, 215, 130, 15);
		frame.getContentPane().add(lblVisitingPatientName);
		
		cbListPV = new JComboBox();
		cbListPV.setBackground(Color.WHITE);
		cbListPV.setBounds(520, 230, 160, 25);
		frame.getContentPane().add(cbListPV);
		
		JButton btnListPatientVisits = new JButton("List Patient Visits");
		btnListPatientVisits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = String.valueOf(cbListPV.getSelectedItem());
				String beforeFirstBreak = text.split("\\|")[0].trim();
				textArea.setText("List of " + beforeFirstBreak +"'s visits:\n" + visitList.getPatientVisits(beforeFirstBreak));
			}
		});
		btnListPatientVisits.setBackground(Color.LIGHT_GRAY);
		btnListPatientVisits.setBounds(525, 265, 150, 25);
		frame.getContentPane().add(btnListPatientVisits);
		
		JLabel lblAttendingPhysician = new JLabel("Attending Physician");
		lblAttendingPhysician.setBounds(525, 315, 130, 15);
		frame.getContentPane().add(lblAttendingPhysician);
		
		cbAttendingPhysician = new JComboBox();
		cbAttendingPhysician.setBackground(Color.WHITE);
		cbAttendingPhysician.setBounds(520, 330, 160, 25);
		frame.getContentPane().add(cbAttendingPhysician);
		
		JButton btnListPhysicianVisits = new JButton("List Physician Visits");
		btnListPhysicianVisits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                String text = String.valueOf(cbAttendingPhysician.getSelectedItem());
                String beforeFirstBreak = text.split("\\|")[0].trim();
                textArea.setText("List of " + beforeFirstBreak +"'s patients:\n" + visitList.patientsWithPhysician(beforeFirstBreak));
			}
		});
		btnListPhysicianVisits.setBackground(Color.LIGHT_GRAY);
		btnListPhysicianVisits.setBounds(525, 365, 150, 25);
		frame.getContentPane().add(btnListPhysicianVisits);
	}
}
