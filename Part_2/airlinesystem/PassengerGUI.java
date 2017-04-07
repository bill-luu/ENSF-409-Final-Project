package airlinesystem;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PassengerGUI {
	Passenger passengerBE;

	private JFrame frmPassengerApplication;
	private JTextField flightSearchField;
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
		passengerBE = new Passenger("localhost", 9090);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPassengerApplication = new JFrame();
		frmPassengerApplication.setResizable(false);
		frmPassengerApplication.setTitle("Passenger Application");
		frmPassengerApplication.setBounds(100, 100, 720, 540);
		frmPassengerApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPassengerApplication.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    		passengerBE.quitServer();
		            System.exit(0);
		        }
		    }
		);
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
		
		JComboBox<String> cBoxFlightParam = new JComboBox<String>();
		cBoxFlightParam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cBoxFlightParam.setModel(new DefaultComboBoxModel<String>(new String[] {"Search by Departure Location", "Search by Destination Location", "Search by Date of Departure"}));
		cBoxFlightParam.setBounds(10, 59, 232, 32);
		cBoxFlightParam.setSelectedIndex(-1);
		searchFlightPanel.add(cBoxFlightParam);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 365, 285);
		searchFlightPanel.add(scrollPane);
		
		JList<String> flightList = new JList<String>();
		flightList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(flightList.getSelectedIndex() == -1){
					flightIdField.setText("");
					destField.setText("");
					departField.setText("");
					dateOfDepartField.setText("");
					timeOfDepartField.setText("");
					durationOfFlightField.setText("");
					totalSeatsField.setText("");
					availableSeatsField.setText("");
					priceField.setText("");
					priceTaxField.setText("");
				}
				else{
					Flight flight = passengerBE.receivedFlights.get(flightList.getSelectedIndex());
					flightIdField.setText(flight.getFlightId());
					destField.setText(flight.getDest());
					departField.setText(flight.getSrc());
					dateOfDepartField.setText(flight.getDate());
					timeOfDepartField.setText(flight.getTime());
					durationOfFlightField.setText(flight.getDuration());
					totalSeatsField.setText(flight.getTotalSeats());
					availableSeatsField.setText(flight.getAvailableSeats());
					priceField.setText(flight.getPrice());
					priceTaxField.setText(flight.getTaxedPrice());
				}
			}
		});
		flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(flightList);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flightList.setSelectedIndex(-1);
				String message = null;
				if(cBoxFlightParam.getSelectedIndex() == -1 || flightSearchField.getText().length() == 0){
					message = passengerBE.getFlights();
				}
				else if(cBoxFlightParam.getSelectedIndex() == 0){
					message = passengerBE.searchFlights("flightId", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 1){
					message = passengerBE.searchFlights("destination", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 2){
					message = passengerBE.searchFlights("source", flightSearchField.getText());
				}
				if(message.equals("GOOD")) {
					DefaultListModel<String> DLM = new DefaultListModel<String>();
					for(int i = 0; i < passengerBE.receivedFlights.size(); i++)
						DLM.addElement(passengerBE.receivedFlights.get(i).toDisplay());
					flightList.setModel(DLM);
				}
				else{
					flightList.removeAll();
					passengerBE.receivedFlights.clear();
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
				flightList.setSelectedIndex(-1);
				flightList.removeAll();
				passengerBE.receivedFlights.clear();
			}
		});
		btnClear.setBounds(252, 59, 123, 32);
		searchFlightPanel.add(btnClear);
		
		JButton btnRefresh = new JButton("Refresh Flights");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Does the same thing as search, just here for user ease of use
				flightList.setSelectedIndex(-1);
				String message = null;
				if(cBoxFlightParam.getSelectedIndex() == -1 || flightSearchField.getText().length() == 0){
					message = passengerBE.getFlights();
				}
				else if(cBoxFlightParam.getSelectedIndex() == 0){
					message = passengerBE.searchFlights("flightId", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 1){
					message = passengerBE.searchFlights("destination", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 2){
					message = passengerBE.searchFlights("source", flightSearchField.getText());
				}
				if(message.equals("GOOD")) {
					DefaultListModel<String> DLM = new DefaultListModel<String>();
					for(int i = 0; i < passengerBE.receivedFlights.size(); i++)
						DLM.addElement(passengerBE.receivedFlights.get(i).toDisplay());
					flightList.setModel(DLM);
				}
				else{
					flightList.removeAll();
					passengerBE.receivedFlights.clear();
					//JOptionPane.showMessageDialog(frmPassengerApplication.getComponent(0), message);
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefresh.setBounds(10, 452, 150, 32);
		searchFlightPanel.add(btnRefresh);
		
		JButton btnBookSelectedFlight = new JButton("Book Selected Flight");
		btnBookSelectedFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBookSelectedFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flightList.getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(frmPassengerApplication.getComponent(0), "No flight selected");
				else{
					bookFlightPanel.setVisible(true);
					searchFlightPanel.setVisible(false);
				}
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
		btnConfirmTicketPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = passengerBE.bookTicket(firstNameField.getText(), lastNameField.getText(), flightIdField.getText());
				JOptionPane.showMessageDialog(frmPassengerApplication.getComponent(0), message);
				
			}
		});
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
		
		JPanel flightInfoPanel = new JPanel();
		flightInfoPanel.setLayout(null);
		flightInfoPanel.setBorder(null);
		flightInfoPanel.setBounds(391, 0, 313, 501);
		frmPassengerApplication.getContentPane().add(flightInfoPanel);
		
		JLabel label = new JLabel("Flight ID Number");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 40, 157, 25);
		flightInfoPanel.add(label);
		
		JLabel label_1 = new JLabel("Destination");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(10, 80, 86, 25);
		flightInfoPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Departure");
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 120, 86, 25);
		flightInfoPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Date of Departure");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 160, 140, 25);
		flightInfoPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Time of Departure");
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 200, 140, 25);
		flightInfoPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Duration of Flight");
		label_5.setVerticalAlignment(SwingConstants.BOTTOM);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 240, 140, 25);
		flightInfoPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Price");
		label_6.setVerticalAlignment(SwingConstants.BOTTOM);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 378, 48, 25);
		flightInfoPanel.add(label_6);
		
		flightIdField = new JTextField();
		flightIdField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightIdField.setEditable(false);
		flightIdField.setColumns(10);
		flightIdField.setBounds(216, 43, 84, 25);
		flightInfoPanel.add(flightIdField);
		
		destField = new JTextField();
		destField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		destField.setEditable(false);
		destField.setColumns(10);
		destField.setBounds(160, 83, 140, 25);
		flightInfoPanel.add(destField);
		
		departField = new JTextField();
		departField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departField.setEditable(false);
		departField.setColumns(10);
		departField.setBounds(160, 123, 140, 25);
		flightInfoPanel.add(departField);
		
		dateOfDepartField = new JTextField();
		dateOfDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateOfDepartField.setEditable(false);
		dateOfDepartField.setColumns(10);
		dateOfDepartField.setBounds(216, 163, 84, 25);
		flightInfoPanel.add(dateOfDepartField);
		
		durationOfFlightField = new JTextField();
		durationOfFlightField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		durationOfFlightField.setEditable(false);
		durationOfFlightField.setColumns(10);
		durationOfFlightField.setBounds(216, 243, 84, 25);
		flightInfoPanel.add(durationOfFlightField);
		
		totalSeatsField = new JTextField();
		totalSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalSeatsField.setEditable(false);
		totalSeatsField.setColumns(10);
		totalSeatsField.setBounds(216, 283, 84, 25);
		flightInfoPanel.add(totalSeatsField);
		
		JLabel label_7 = new JLabel("Price incl. tax");
		label_7.setVerticalAlignment(SwingConstants.BOTTOM);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(10, 414, 140, 25);
		flightInfoPanel.add(label_7);
		
		availableSeatsField = new JTextField();
		availableSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availableSeatsField.setEditable(false);
		availableSeatsField.setColumns(10);
		availableSeatsField.setBounds(216, 323, 84, 25);
		flightInfoPanel.add(availableSeatsField);
		
		timeOfDepartField = new JTextField();
		timeOfDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timeOfDepartField.setEditable(false);
		timeOfDepartField.setColumns(10);
		timeOfDepartField.setBounds(216, 203, 84, 25);
		flightInfoPanel.add(timeOfDepartField);
		
		JLabel label_8 = new JLabel("Total Seats");
		label_8.setVerticalAlignment(SwingConstants.BOTTOM);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(10, 280, 140, 25);
		flightInfoPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Available Seats");
		label_9.setVerticalAlignment(SwingConstants.BOTTOM);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(10, 320, 140, 25);
		flightInfoPanel.add(label_9);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setEditable(false);
		priceField.setColumns(10);
		priceField.setBounds(216, 381, 84, 25);
		flightInfoPanel.add(priceField);
		
		priceTaxField = new JTextField();
		priceTaxField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceTaxField.setEditable(false);
		priceTaxField.setColumns(10);
		priceTaxField.setBounds(216, 417, 84, 25);
		flightInfoPanel.add(priceTaxField);
	}
}
