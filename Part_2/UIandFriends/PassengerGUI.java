package Final_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DropMode;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class PassengerGUI {
	Passenger passengerBE;

	private JFrame frmPassengerApplication;
	private JTextField flightSearchField;
	private JTextField textField_1;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField flightIdField;
	private JTextField destField;
	private JTextField departField;
	private JTextField dateOfDepartField;
	private JTextField durationOfFlightField;
	private JTextField totalSeatsField;
	private JTextField availableSeatsField;
	private JTextField timeOfDepartField;
	private JTextField priceField;
	private JTextField priceTaxField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerGUI window = new PassengerGUI();
					window.frmPassengerApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PassengerGUI() {
		passengerBE = new Passenger("localhost", 9595);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPassengerApplication = new JFrame();
		frmPassengerApplication.setTitle("Passenger Application");
		frmPassengerApplication.setBounds(100, 100, 720, 540);
		frmPassengerApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPassengerApplication.getContentPane().setLayout(null);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cardPanel.setBounds(0, 0, 391, 501);
		frmPassengerApplication.getContentPane().add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		
		JPanel searchFlightPanel = new JPanel();
		cardPanel.add(searchFlightPanel, "name_5187118987893");
		searchFlightPanel.setLayout(null);
		
		JPanel bookFlightPanel = new JPanel();
		cardPanel.add(bookFlightPanel, "name_6185181549412");
		bookFlightPanel.setLayout(null);
		
		flightSearchField = new JTextField();
		flightSearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		flightSearchField.setBounds(10, 113, 232, 32);
		searchFlightPanel.add(flightSearchField);
		flightSearchField.setColumns(10);
		
		JComboBox cBoxFlightParam = new JComboBox();
		cBoxFlightParam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cBoxFlightParam.setModel(new DefaultComboBoxModel(new String[] {"Search by Departure Location", "Search by Destination Location", "Search by Date of Departure"}));
		cBoxFlightParam.setBounds(10, 59, 232, 32);
		cBoxFlightParam.setSelectedIndex(-1);
		searchFlightPanel.add(cBoxFlightParam);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 365, 285);
		searchFlightPanel.add(scrollPane);
		
		JList<Flight> flightList = new JList<Flight>();
		scrollPane.setViewportView(flightList);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = "good";
				if(cBoxFlightParam.getSelectedIndex() == -1 || flightSearchField.getText().equals("")){
					//String message = passengerBE.getFlights();
				}
				else if(cBoxFlightParam.getSelectedIndex() == 0){
					//String message = passengerBE.searchTickets("flightId", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 1){
					//String message = passengerBE.searchTickets("destination", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 2){
					//String message = passengerBE.searchFights("source", flightSearchField.getText());
				}
				if(message.equals("good")) {
					DefaultListModel<Flight> DLM = new DefaultListModel<Flight>();
					for(int i = 0; i < passengerBE.flights.size(); i++)
						DLM.addElement(passengerBE.flights.get(i));
					flightList.setModel(DLM);
				}
				else{
					JOptionPane.showMessageDialog(frmPassengerApplication.getComponent(0), message);
				}
			}
		});
		btnSearch.setBounds(252, 113, 123, 32);
		searchFlightPanel.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flightSearchField.setText("");
				cBoxFlightParam.setSelectedIndex(-1);
				flightList.removeAll();
			}
		});
		btnClear.setBounds(252, 59, 123, 32);
		searchFlightPanel.add(btnClear);
		
		JButton btnRefresh = new JButton("Refresh Flights");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefresh.setBounds(10, 452, 150, 32);
		searchFlightPanel.add(btnRefresh);
		
		JButton btnBookSelectedFlight = new JButton("Book Selected Flight");
		btnBookSelectedFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBookSelectedFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookFlightPanel.setVisible(true);
				searchFlightPanel.setVisible(false);
			}
		});
		btnBookSelectedFlight.setBounds(170, 452, 205, 32);
		searchFlightPanel.add(btnBookSelectedFlight);
		
		JLabel label_10 = new JLabel("Search for Flights by:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_10.setBounds(10, 11, 236, 35);
		searchFlightPanel.add(label_10);
		
		JLabel lblPleaseEnterFirst = new JLabel("Please Enter First Name");
		lblPleaseEnterFirst.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPleaseEnterFirst.setBounds(20, 85, 276, 40);
		bookFlightPanel.add(lblPleaseEnterFirst);
		
		JLabel lblBookingTicket = new JLabel("Booking Ticket");
		lblBookingTicket.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblBookingTicket.setBounds(20, 21, 287, 39);
		bookFlightPanel.add(lblBookingTicket);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameField.setBounds(20, 126, 265, 32);
		bookFlightPanel.add(firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lblPleaseEnterLast = new JLabel("Please Enter Last Name");
		lblPleaseEnterLast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPleaseEnterLast.setBounds(23, 169, 276, 40);
		bookFlightPanel.add(lblPleaseEnterLast);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameField.setBounds(23, 207, 265, 32);
		bookFlightPanel.add(lastNameField);
		lastNameField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchFlightPanel.setVisible(true);
				bookFlightPanel.setVisible(false);
			}
		});
		btnBack.setBounds(10, 452, 101, 32);
		bookFlightPanel.add(btnBack);
		
		JButton btnConfirmTicketPurchase = new JButton("Confirm Ticket Purchase");
		btnConfirmTicketPurchase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmTicketPurchase.setBounds(121, 452, 254, 32);
		bookFlightPanel.add(btnConfirmTicketPurchase);
		
		JButton btnClear_1 = new JButton("Clear Name Fields");
		btnClear_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNameField.setText("");
				lastNameField.setText("");
			}
		});
		btnClear_1.setBounds(23, 263, 265, 32);
		bookFlightPanel.add(btnClear_1);
		
		JTextPane txtpnConfirmMsg = new JTextPane();
		txtpnConfirmMsg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnConfirmMsg.setText("Please review your information entered above and the flight information on right to ensure it is the correct before confirming ticket purchase.");
		txtpnConfirmMsg.setBounds(25, 312, 323, 116);
		bookFlightPanel.add(txtpnConfirmMsg);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBounds(391, 0, 313, 501);
		frmPassengerApplication.getContentPane().add(panel);
		
		JLabel label = new JLabel("Flight ID Number");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 40, 157, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Destination");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(10, 80, 86, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Departure");
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 120, 86, 25);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Date of Departure");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 160, 140, 25);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Time of Departure");
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 200, 140, 25);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Duration of Flight");
		label_5.setVerticalAlignment(SwingConstants.BOTTOM);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 240, 140, 25);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Price");
		label_6.setVerticalAlignment(SwingConstants.BOTTOM);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 378, 48, 25);
		panel.add(label_6);
		
		flightIdField = new JTextField();
		flightIdField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightIdField.setEditable(false);
		flightIdField.setColumns(10);
		flightIdField.setBounds(216, 43, 84, 25);
		panel.add(flightIdField);
		
		destField = new JTextField();
		destField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		destField.setEditable(false);
		destField.setColumns(10);
		destField.setBounds(160, 83, 140, 25);
		panel.add(destField);
		
		departField = new JTextField();
		departField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departField.setEditable(false);
		departField.setColumns(10);
		departField.setBounds(160, 123, 140, 25);
		panel.add(departField);
		
		dateOfDepartField = new JTextField();
		dateOfDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateOfDepartField.setEditable(false);
		dateOfDepartField.setColumns(10);
		dateOfDepartField.setBounds(216, 163, 84, 25);
		panel.add(dateOfDepartField);
		
		durationOfFlightField = new JTextField();
		durationOfFlightField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		durationOfFlightField.setEditable(false);
		durationOfFlightField.setColumns(10);
		durationOfFlightField.setBounds(216, 243, 84, 25);
		panel.add(durationOfFlightField);
		
		totalSeatsField = new JTextField();
		totalSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalSeatsField.setEditable(false);
		totalSeatsField.setColumns(10);
		totalSeatsField.setBounds(216, 283, 84, 25);
		panel.add(totalSeatsField);
		
		JLabel label_7 = new JLabel("Price incl. tax");
		label_7.setVerticalAlignment(SwingConstants.BOTTOM);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(10, 414, 140, 25);
		panel.add(label_7);
		
		availableSeatsField = new JTextField();
		availableSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availableSeatsField.setEditable(false);
		availableSeatsField.setColumns(10);
		availableSeatsField.setBounds(216, 323, 84, 25);
		panel.add(availableSeatsField);
		
		timeOfDepartField = new JTextField();
		timeOfDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timeOfDepartField.setEditable(false);
		timeOfDepartField.setColumns(10);
		timeOfDepartField.setBounds(216, 203, 84, 25);
		panel.add(timeOfDepartField);
		
		JLabel label_8 = new JLabel("Total Seats");
		label_8.setVerticalAlignment(SwingConstants.BOTTOM);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(10, 280, 140, 25);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Available Seats");
		label_9.setVerticalAlignment(SwingConstants.BOTTOM);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(10, 320, 140, 25);
		panel.add(label_9);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setEditable(false);
		priceField.setColumns(10);
		priceField.setBounds(216, 381, 84, 25);
		panel.add(priceField);
		
		priceTaxField = new JTextField();
		priceTaxField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceTaxField.setEditable(false);
		priceTaxField.setColumns(10);
		priceTaxField.setBounds(216, 417, 84, 25);
		panel.add(priceTaxField);
	}
}
