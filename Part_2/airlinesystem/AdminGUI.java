package Final_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
//import javax.swing.JSplitPane;
import javax.swing.JPanel;
//import javax.swing.JInternalFrame;
//import javax.swing.JDesktopPane;
//import javax.swing.JSeparator;
//import java.awt.FlowLayout;
//import javax.swing.BoxLayout;
//import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
//import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.border.BevelBorder;
//import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
//import javax.swing.DropMode;
//import javax.swing.SpringLayout;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
//import javax.swing.border.LineBorder;
//import javax.swing.border.SoftBevelBorder;
//import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class AdminGUI {
	private Admin adminBE;

	private JFrame frmAdminApplication;
	private JTextField flightSearchField;
	private JTextField flightIdNumberField;
	private JTextField destField;
	private JTextField departureField;
	private JTextField dateField;
	private JTextField durationField;
	private JTextField totalSeatsField;
	private JTextField availableSeatsField;
	private JTextField timeField;
	private JTextField priceField;
	private JTextField priceTaxField;
	private JTextField fileNameField;
	private JTextField ticketSearchField;
	private JTextField addDestField;
	private JTextField addDepartField;
	private JTextField addDoDField;
	private JTextField addDoFField;
	private JTextField addSeatsField;
	private JTextField addToDField;
	private JTextField addPriceField;
	private JTextField tTicketIdNumberField;
	private JTextField tDestField;
	private JTextField tDepartField;
	private JTextField tDoFField;
	private JTextField tTotalSeatsField;
	private JTextField tLastNameField;
	private JTextField tToDField;
	private JTextField tPriceField;
	private JTextField tPriceTaxField;
	private JTextField tFirstNameField;
	private JTextField tFlightIdNumberField;
	private JTextField tDoDField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField bFlightIdNumberField;
	private JTextField bDestField;
	private JTextField bDepartField;
	private JTextField bDateField;
	private JTextField bDurationField;
	private JTextField bTotalSeatsField;
	private JTextField bAvailableSeatsField;
	private JTextField bTimeField;
	private JTextField bPriceField;
	private JTextField bPriceTaxField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI window = new AdminGUI();
					window.frmAdminApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminGUI() {
		adminBE = new Admin("localhost", 9595);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmAdminApplication = new JFrame();
		frmAdminApplication.setResizable(false);
		frmAdminApplication.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAdminApplication.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    		//adminBE.quitServer();
		            System.exit(0);
		        }
		    }
		);
		frmAdminApplication.setTitle("Admin Application ");
		frmAdminApplication.setBounds(100, 100, 720, 540);
		frmAdminApplication.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		frmAdminApplication.getContentPane().add(mainPanel, "name_6911322877862");
		
		JPanel browseFlightPanel = new JPanel();
		frmAdminApplication.getContentPane().add(browseFlightPanel, "name_8333924157336");
		browseFlightPanel.setLayout(null);
		
		JPanel browseFlightInteriorPanel = new JPanel();
		browseFlightInteriorPanel.setBounds(384, 0, 310, 413);
		browseFlightPanel.add(browseFlightInteriorPanel);
		browseFlightInteriorPanel.setBorder(null);
		browseFlightInteriorPanel.setLayout(null);
		
		JPanel addFlightPanel = new JPanel();
		frmAdminApplication.getContentPane().add(addFlightPanel, "name_8926398173983");
		addFlightPanel.setLayout(null);
		
		JPanel browseTicketPanel = new JPanel();
		browseTicketPanel.setLayout(null);
		frmAdminApplication.getContentPane().add(browseTicketPanel, "name_11471501033299");
		
		JPanel LFFFPanel = new JPanel();
		frmAdminApplication.getContentPane().add(LFFFPanel, "name_10731868007654");
		LFFFPanel.setLayout(null);
		
		JPanel bookTicketPanel = new JPanel();
		bookTicketPanel.setLayout(null);
		frmAdminApplication.getContentPane().add(bookTicketPanel, "name_19348206394285");
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBounds(384, 21, 310, 413);
		bookTicketPanel.add(panel_1);
		
		JButton btnBrowseFlights = new JButton("Browse Flights");
		btnBrowseFlights.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnBrowseFlights.setBounds(136, 174, 409, 91);
		btnBrowseFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				browseFlightPanel.setVisible(true);
				mainPanel.setVisible(false);
				
			}
		});
		mainPanel.setLayout(null);
		mainPanel.add(btnBrowseFlights);
		
		JButton btnBrowseTickets = new JButton("Browse Tickets");
		btnBrowseTickets.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnBrowseTickets.setBounds(136, 320, 409, 91);
		btnBrowseTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseTicketPanel.setVisible(true);
				mainPanel.setVisible(false);
			}
		});
		mainPanel.add(btnBrowseTickets);
		
		JLabel lblWelcomeAdministratorPlease = new JLabel("Welcome Administrator!");
		lblWelcomeAdministratorPlease.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblWelcomeAdministratorPlease.setBounds(155, 47, 400, 72);
		mainPanel.add(lblWelcomeAdministratorPlease);
		
		JLabel lblFlightIdNumber = new JLabel("Flight ID Number");
		lblFlightIdNumber.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFlightIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFlightIdNumber.setBounds(10, 8, 157, 25);
		browseFlightInteriorPanel.add(lblFlightIdNumber);
		
		JLabel lblDestinationLocation = new JLabel("Destination");
		lblDestinationLocation.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDestinationLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDestinationLocation.setBounds(10, 50, 86, 25);
		browseFlightInteriorPanel.add(lblDestinationLocation);
		
		JLabel lblDepartureLocation = new JLabel("Departure");
		lblDepartureLocation.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDepartureLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartureLocation.setBounds(10, 90, 86, 25);
		browseFlightInteriorPanel.add(lblDepartureLocation);
		
		JLabel lblDateOfDeparture = new JLabel("Date of Departure");
		lblDateOfDeparture.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDateOfDeparture.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateOfDeparture.setBounds(10, 130, 140, 25);
		browseFlightInteriorPanel.add(lblDateOfDeparture);
		
		JLabel lblTimeOfDeparture = new JLabel("Time of Departure");
		lblTimeOfDeparture.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTimeOfDeparture.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimeOfDeparture.setBounds(10, 170, 140, 25);
		browseFlightInteriorPanel.add(lblTimeOfDeparture);
		
		JLabel lblDurationOfFlight = new JLabel("Duration of Flight");
		lblDurationOfFlight.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDurationOfFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDurationOfFlight.setBounds(10, 210, 140, 25);
		browseFlightInteriorPanel.add(lblDurationOfFlight);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(10, 330, 48, 25);
		browseFlightInteriorPanel.add(lblPrice);
		
		flightIdNumberField = new JTextField();
		flightIdNumberField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightIdNumberField.setEditable(false);
		flightIdNumberField.setBounds(216, 10, 84, 25);
		browseFlightInteriorPanel.add(flightIdNumberField);
		flightIdNumberField.setColumns(10);
		
		destField = new JTextField();
		destField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		destField.setEditable(false);
		destField.setColumns(10);
		destField.setBounds(160, 50, 140, 25);
		browseFlightInteriorPanel.add(destField);
		
		departureField = new JTextField();
		departureField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departureField.setEditable(false);
		departureField.setColumns(10);
		departureField.setBounds(160, 90, 140, 25);
		browseFlightInteriorPanel.add(departureField);
		
		dateField = new JTextField();
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateField.setEditable(false);
		dateField.setColumns(10);
		dateField.setBounds(216, 130, 84, 25);
		browseFlightInteriorPanel.add(dateField);
		
		durationField = new JTextField();
		durationField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		durationField.setEditable(false);
		durationField.setColumns(10);
		durationField.setBounds(216, 210, 84, 25);
		browseFlightInteriorPanel.add(durationField);
		
		totalSeatsField = new JTextField();
		totalSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalSeatsField.setEditable(false);
		totalSeatsField.setColumns(10);
		totalSeatsField.setBounds(216, 250, 84, 25);
		browseFlightInteriorPanel.add(totalSeatsField);
		
		JLabel lblPriceInclTax = new JLabel("Price incl. tax");
		lblPriceInclTax.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPriceInclTax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPriceInclTax.setBounds(10, 370, 140, 25);
		browseFlightInteriorPanel.add(lblPriceInclTax);
		
		availableSeatsField = new JTextField();
		availableSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availableSeatsField.setEditable(false);
		availableSeatsField.setColumns(10);
		availableSeatsField.setBounds(216, 290, 84, 25);
		browseFlightInteriorPanel.add(availableSeatsField);
		
		timeField = new JTextField();
		timeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timeField.setEditable(false);
		timeField.setColumns(10);
		timeField.setBounds(216, 170, 84, 25);
		browseFlightInteriorPanel.add(timeField);
		
		JLabel lblTotalSeats = new JLabel("Total Seats");
		lblTotalSeats.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTotalSeats.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalSeats.setBounds(10, 250, 140, 25);
		browseFlightInteriorPanel.add(lblTotalSeats);
		
		JLabel lblAvailableSeats = new JLabel("Available Seats");
		lblAvailableSeats.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAvailableSeats.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAvailableSeats.setBounds(10, 290, 140, 25);
		browseFlightInteriorPanel.add(lblAvailableSeats);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setEditable(false);
		priceField.setColumns(10);
		priceField.setBounds(216, 330, 84, 25);
		browseFlightInteriorPanel.add(priceField);
		
		priceTaxField = new JTextField();
		priceTaxField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceTaxField.setEditable(false);
		priceTaxField.setColumns(10);
		priceTaxField.setBounds(216, 370, 84, 25);
		browseFlightInteriorPanel.add(priceTaxField);
		
		JLabel lblSearchForFlights = new JLabel("Search for Flights by:");
		lblSearchForFlights.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchForFlights.setBounds(10, 11, 282, 35);
		browseFlightPanel.add(lblSearchForFlights);
		
		flightSearchField = new JTextField();
		flightSearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		flightSearchField.setBounds(10, 111, 236, 32);
		browseFlightPanel.add(flightSearchField);
		flightSearchField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 364, 279);
		browseFlightPanel.add(scrollPane);
		
		JList<Flight> flightList = new JList<Flight>();
		flightList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(flightList.getSelectedIndex() == -1){
					flightIdNumberField.setText("");
					destField.setText("");
					departureField.setText("");
					dateField.setText("");
					timeField.setText("");
					durationField.setText("");
					totalSeatsField.setText("");
					availableSeatsField.setText("");
					priceField.setText("");
					priceTaxField.setText("");
				}
				else{
					Flight flight = flightList.getSelectedValue();
					flightIdNumberField.setText(flight.getFlightId());
					destField.setText(flight.getDest());
					departureField.setText(flight.getSrc());
					dateField.setText(flight.getDate());
					timeField.setText(flight.getTime());
					durationField.setText(flight.getDuration());
					totalSeatsField.setText(flight.getTotalSeats());
					availableSeatsField.setText(flight.getAvailableSeats());
					priceField.setText(flight.getPrice());
					priceTaxField.setText(flight.getTaxedPrice());
				}
			}
		});
		flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(flightList);
		
		JComboBox<String> cBoxFlightParam = new JComboBox<String>();
		cBoxFlightParam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cBoxFlightParam.setModel(new DefaultComboBoxModel<String>(new String[] {"Departure Location", "Destination Location", "Date of Departure"}));
		cBoxFlightParam.setBounds(10, 57, 236, 32);
		cBoxFlightParam.setSelectedIndex(-1);
		browseFlightPanel.add(cBoxFlightParam);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flightList.setSelectedIndex(-1);
				String message = null;
				if(cBoxFlightParam.getSelectedIndex() == -1 || flightSearchField.getText().length() == 0){
					message = adminBE.getTickets();
				}
				else if(cBoxFlightParam.getSelectedIndex() == 0){
					message = adminBE.searchFlights("flightId", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 1){
					message = adminBE.searchFlights("destination", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 2){
					message = adminBE.searchFlights("source", flightSearchField.getText());
				}
				if(message.equals("GOOD")) {
					DefaultListModel<Flight> DLM = new DefaultListModel<Flight>();
					for(int i = 0; i < adminBE.receivedFlights.size(); i++)
						DLM.addElement(adminBE.receivedFlights.get(i));
					flightList.setModel(DLM);
				}
				else{
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
				}
			}
		});
		btnSearch.setBounds(256, 111, 118, 32);
		browseFlightPanel.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flightSearchField.setText("");
				cBoxFlightParam.setSelectedIndex(-1);
				flightList.setSelectedIndex(-1);
				flightList.removeAll();

			}
		});
		btnClear.setBounds(256, 58, 118, 32);
		browseFlightPanel.add(btnClear);
		
		JButton btnRefresh = new JButton("Refresh Flights");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flightList.setSelectedIndex(-1);
				String message = null;
				if(cBoxFlightParam.getSelectedIndex() == -1 || flightSearchField.getText().length() == 0){
					message = adminBE.getTickets();
				}
				else if(cBoxFlightParam.getSelectedIndex() == 0){
					message = adminBE.searchFlights("flightId", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 1){
					message = adminBE.searchFlights("destination", flightSearchField.getText());
				}
				else if(cBoxFlightParam.getSelectedIndex() == 2){
					message = adminBE.searchFlights("source", flightSearchField.getText());
				}
				if(message.equals("GOOD")) {
					DefaultListModel<Flight> DLM = new DefaultListModel<Flight>();
					for(int i = 0; i < adminBE.receivedFlights.size(); i++)
						DLM.addElement(adminBE.receivedFlights.get(i));
					flightList.setModel(DLM);
				}
				else{
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefresh.setBounds(125, 458, 155, 32);
		browseFlightPanel.add(btnRefresh);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				browseFlightPanel.setVisible(false);
			}
		});
		btnBack_1.setBounds(10, 458, 105, 32);
		browseFlightPanel.add(btnBack_1);
		
		JButton btnAddNewFlight = new JButton("Add New Flight");
		btnAddNewFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddNewFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFlightPanel.setVisible(true);
				browseFlightPanel.setVisible(false);
			}
		});
		btnAddNewFlight.setBounds(530, 458, 164, 32);
		browseFlightPanel.add(btnAddNewFlight);
		
		JButton btnLoadFlightsFrom = new JButton("Load Flights From File");
		btnLoadFlightsFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoadFlightsFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LFFFPanel.setVisible(true);
				browseFlightPanel.setVisible(false);
			}
		});
		btnLoadFlightsFrom.setBounds(290, 458, 230, 32);
		browseFlightPanel.add(btnLoadFlightsFrom);
		
		JButton button = new JButton("Book Selected Ticket");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flightList.getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), "No flight selected");
				
				else{
					bFlightIdNumberField.setText(flightIdNumberField.getText());
					bDestField.setText(destField.getText());
					bDepartField.setText(departureField.getText());
					bDateField.setText(dateField.getText());
					bTimeField.setText(timeField.getText());
					bDurationField.setText(durationField.getText());
					bTotalSeatsField.setText(totalSeatsField.getText());
					bAvailableSeatsField.setText(availableSeatsField.getText());
					bPriceField.setText(priceField.getText());
					bPriceTaxField.setText(priceTaxField.getText());
					bookTicketPanel.setVisible(true);
					browseFlightPanel.setVisible(false);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(453, 412, 187, 32);
		browseFlightPanel.add(button);
		
		JLabel lblPleaseEnterInformation = new JLabel("Please enter information for flight to be added");
		lblPleaseEnterInformation.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPleaseEnterInformation.setBounds(55, 11, 590, 44);
		addFlightPanel.add(lblPleaseEnterInformation);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseFlightPanel.setVisible(true);
				addFlightPanel.setVisible(false);
			}
		});
		btnBack.setBounds(157, 455, 100, 32);
		addFlightPanel.add(btnBack);
		
		JButton btnAddFlight = new JButton("Add Flight");
		btnAddFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Flight flightToAdd = new Flight(addDestField.getText(), addDepartField.getText(), addDoDField.getText(), addToDField.getText(), addDoFField.getText(), addSeatsField.getText(), addPriceField.getText());
				String messages = adminBE.addFlight(flightToAdd);
				if(messages.contains("ERROR")){
					String[] str = messages.split("_");
					for(int i = 1; i < str.length; i++){
						JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), str[i]);
					}
				}
				else{
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), messages);
				}
			}
		});
		btnAddFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddFlight.setBounds(409, 455, 130, 32);
		addFlightPanel.add(btnAddFlight);
		
		JButton btnClearFields = new JButton("Clear Fields");
		btnClearFields.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDestField.setText("");
				addDepartField.setText("");
				addDoDField.setText("");
				addToDField.setText("");
				addDoFField.setText("");
				addSeatsField.setText("");
				addPriceField.setText("");
			}
		});
		btnClearFields.setBounds(267, 455, 130, 32);
		addFlightPanel.add(btnClearFields);
		
		JPanel addFlightInteriorPanel = new JPanel();
		addFlightInteriorPanel.setLayout(null);
		addFlightInteriorPanel.setBorder(null);
		addFlightInteriorPanel.setBounds(157, 89, 382, 323);
		addFlightPanel.add(addFlightInteriorPanel);
		
		JLabel lblDestinationLocation_1 = new JLabel("Destination Location");
		lblDestinationLocation_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDestinationLocation_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDestinationLocation_1.setBounds(10, 50, 212, 25);
		addFlightInteriorPanel.add(lblDestinationLocation_1);
		
		JLabel lblDepartureLocation_1 = new JLabel("Departure Location");
		lblDepartureLocation_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDepartureLocation_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartureLocation_1.setBounds(10, 90, 212, 25);
		addFlightInteriorPanel.add(lblDepartureLocation_1);
		
		JLabel lblDateOfDepartureddmmyyyy = new JLabel("Date of Departure (dd/MM/yyyy)");
		lblDateOfDepartureddmmyyyy.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDateOfDepartureddmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateOfDepartureddmmyyyy.setBounds(10, 130, 268, 25);
		addFlightInteriorPanel.add(lblDateOfDepartureddmmyyyy);
		
		JLabel lblTimeOfDeparture_1 = new JLabel("Time of Departure (hh:mm)");
		lblTimeOfDeparture_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTimeOfDeparture_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimeOfDeparture_1.setBounds(10, 170, 268, 25);
		addFlightInteriorPanel.add(lblTimeOfDeparture_1);
		
		JLabel lblDurationOfFlight_1 = new JLabel("Duration of Flight (ddd:hh:mm)");
		lblDurationOfFlight_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDurationOfFlight_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDurationOfFlight_1.setBounds(10, 210, 268, 25);
		addFlightInteriorPanel.add(lblDurationOfFlight_1);
		
		JLabel label_6 = new JLabel("Price");
		label_6.setVerticalAlignment(SwingConstants.BOTTOM);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 286, 48, 25);
		addFlightInteriorPanel.add(label_6);
		
		addDestField = new JTextField();
		addDestField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addDestField.setColumns(10);
		addDestField.setBounds(232, 53, 140, 25);
		addFlightInteriorPanel.add(addDestField);
		
		addDepartField = new JTextField();
		addDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addDepartField.setColumns(10);
		addDepartField.setBounds(232, 93, 140, 25);
		addFlightInteriorPanel.add(addDepartField);
		
		addDoDField = new JTextField();
		addDoDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addDoDField.setColumns(10);
		addDoDField.setBounds(288, 133, 84, 25);
		addFlightInteriorPanel.add(addDoDField);
		
		addDoFField = new JTextField();
		addDoFField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addDoFField.setColumns(10);
		addDoFField.setBounds(288, 213, 84, 25);
		addFlightInteriorPanel.add(addDoFField);
		
		addSeatsField = new JTextField();
		addSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addSeatsField.setColumns(10);
		addSeatsField.setBounds(288, 253, 84, 25);
		addFlightInteriorPanel.add(addSeatsField);
		
		addToDField = new JTextField();
		addToDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addToDField.setColumns(10);
		addToDField.setBounds(288, 173, 84, 25);
		addFlightInteriorPanel.add(addToDField);
		
		JLabel label_9 = new JLabel("Total Seats");
		label_9.setVerticalAlignment(SwingConstants.BOTTOM);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(10, 250, 140, 25);
		addFlightInteriorPanel.add(label_9);
		
		addPriceField = new JTextField();
		addPriceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addPriceField.setColumns(10);
		addPriceField.setBounds(288, 289, 84, 25);
		addFlightInteriorPanel.add(addPriceField);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseFlightPanel.setVisible(true);
				LFFFPanel.setVisible(false);
			}
		});
		btnBack_2.setBounds(142, 458, 137, 32);
		LFFFPanel.add(btnBack_2);
		
		JButton btnLoadFlights = new JButton("Load Flights");
		btnLoadFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = adminBE.loadFlights(fileNameField.getText());
				JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
			}
		});
		btnLoadFlights.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoadFlights.setBounds(339, 458, 230, 32);
		LFFFPanel.add(btnLoadFlights);
		
		JLabel lblPleaseEnterFile = new JLabel("Please enter file name to load flights from:");
		lblPleaseEnterFile.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPleaseEnterFile.setBounds(99, 154, 568, 59);
		LFFFPanel.add(lblPleaseEnterFile);
		
		fileNameField = new JTextField();
		fileNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fileNameField.setBounds(142, 240, 427, 32);
		LFFFPanel.add(fileNameField);
		fileNameField.setColumns(10);
		
		ticketSearchField = new JTextField();
		ticketSearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ticketSearchField.setColumns(10);
		ticketSearchField.setBounds(10, 110, 236, 32);
		browseTicketPanel.add(ticketSearchField);
		
		JComboBox<String> cBoxTicketParam = new JComboBox<String>();
		cBoxTicketParam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cBoxTicketParam.setModel(new DefaultComboBoxModel<String>(new String[] {"Ticket ID Number", "Flight ID Number", "Ticket Holder Last Name"}));
		cBoxTicketParam.setSelectedIndex(-1);
		cBoxTicketParam.setBounds(10, 67, 236, 32);
		browseTicketPanel.add(cBoxTicketParam);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 153, 374, 287);
		browseTicketPanel.add(scrollPane_1);
		
		JList<Ticket> ticketList = new JList<Ticket>();
		ticketList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(ticketList.getSelectedIndex() == -1){
					tTicketIdNumberField.setText("");
					tFlightIdNumberField.setText("");
					tDestField.setText("");
					tDepartField.setText("");
					tDoDField.setText("");
					tToDField.setText("");
					tDoFField.setText("");
					tTotalSeatsField.setText("");
					tFirstNameField.setText("");
					tLastNameField.setText("");
					tPriceField.setText("");
					tPriceTaxField.setText("");
				}
				else{
					Ticket ticket = ticketList.getSelectedValue();
					tTicketIdNumberField.setText(ticket.getTicketId());
					tFlightIdNumberField.setText(ticket.getFlightId());
					tDestField.setText(ticket.getDest());
					tDepartField.setText(ticket.getSrc());
					tDoDField.setText(ticket.getDate());
					tToDField.setText(ticket.getTime());
					tDoFField.setText(ticket.getDuration());
					tTotalSeatsField.setText(ticket.getTotalSeats());
					tFirstNameField.setText(ticket.getFirstName());
					tLastNameField.setText(ticket.getLastName());
					tPriceField.setText(ticket.getPrice());
					tPriceTaxField.setText(ticket.getTaxedPrice());
				}	
			}
		});
		ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(ticketList);
		
		JButton btnTSearch = new JButton("Search");
		btnTSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticketList.setSelectedIndex(-1);
				String message = null;
				if(cBoxTicketParam.getSelectedIndex() == -1 || ticketSearchField.getText().length() == 0){
					message = adminBE.getTickets();
				}
				else if(cBoxTicketParam.getSelectedIndex() == 0){
					message = adminBE.searchTickets("ticketId", ticketSearchField.getText());
				}
				else if(cBoxTicketParam.getSelectedIndex() == 1){
					message = adminBE.searchTickets("flightId", ticketSearchField.getText());
				}
				else if(cBoxTicketParam.getSelectedIndex() == 2){
					message = adminBE.searchTickets("lastName", ticketSearchField.getText());
				}
				if(message.equals("GOOD")) {
					DefaultListModel<Ticket> DLM = new DefaultListModel<Ticket>();
					for(int i = 0; i < adminBE.receivedTickets.size(); i++)
						DLM.addElement(adminBE.receivedTickets.get(i));
					ticketList.setModel(DLM);
				}
				else{
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
				}
			}
		});
		btnTSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTSearch.setBounds(256, 110, 109, 32);
		browseTicketPanel.add(btnTSearch);
		
		JButton btnTClear = new JButton("Clear");
		btnTClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticketSearchField.setText("");
				cBoxTicketParam.setSelectedIndex(-1);
				ticketList.setSelectedIndex(-1);
				ticketList.removeAll();
			}
		});
		btnTClear.setBounds(256, 67, 109, 32);
		browseTicketPanel.add(btnTClear);
		
		JButton btnRefreshTickets = new JButton("Refresh Tickets");
		btnRefreshTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticketList.setSelectedIndex(-1);
				String message = null;
				if(cBoxTicketParam.getSelectedIndex() == -1 || ticketSearchField.getText().length() == 0){
					message = adminBE.getTickets();
				}
				else if(cBoxTicketParam.getSelectedIndex() == 0){
					message = adminBE.searchTickets("ticketId", ticketSearchField.getText());
				}
				else if(cBoxTicketParam.getSelectedIndex() == 1){
					message = adminBE.searchTickets("flightId", ticketSearchField.getText());
				}
				else if(cBoxTicketParam.getSelectedIndex() == 2){
					message = adminBE.searchTickets("lastName", ticketSearchField.getText());
				}
				if(message.equals("GOOD")) {
					DefaultListModel<Ticket> DLM = new DefaultListModel<Ticket>();
					for(int i = 0; i < adminBE.receivedTickets.size(); i++)
						DLM.addElement(adminBE.receivedTickets.get(i));
					ticketList.setModel(DLM);
				}
				else{
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
				}
			}
		});
		btnRefreshTickets.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefreshTickets.setBounds(244, 458, 150, 32);
		browseTicketPanel.add(btnRefreshTickets);
		
		JButton btnTBack = new JButton("Back");
		btnTBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				browseTicketPanel.setVisible(false);
			}
		});
		btnTBack.setBounds(118, 458, 116, 32);
		browseTicketPanel.add(btnTBack);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBounds(394, 0, 310, 439);
		browseTicketPanel.add(panel);
		
		JLabel label_7 = new JLabel("Flight ID Number");
		label_7.setVerticalAlignment(SwingConstants.BOTTOM);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(10, 45, 157, 25);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Destination");
		label_8.setVerticalAlignment(SwingConstants.BOTTOM);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(10, 80, 86, 25);
		panel.add(label_8);
		
		JLabel label_10 = new JLabel("Departure");
		label_10.setVerticalAlignment(SwingConstants.BOTTOM);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(10, 115, 86, 25);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("Date of Departure");
		label_11.setVerticalAlignment(SwingConstants.BOTTOM);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(10, 150, 140, 25);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("Time of Departure");
		label_12.setVerticalAlignment(SwingConstants.BOTTOM);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(10, 185, 140, 25);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("Duration of Flight");
		label_13.setVerticalAlignment(SwingConstants.BOTTOM);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_13.setBounds(10, 220, 140, 25);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("Price");
		label_14.setVerticalAlignment(SwingConstants.BOTTOM);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_14.setBounds(10, 360, 48, 25);
		panel.add(label_14);
		
		tTicketIdNumberField = new JTextField();
		tTicketIdNumberField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tTicketIdNumberField.setEditable(false);
		tTicketIdNumberField.setColumns(10);
		tTicketIdNumberField.setBounds(214, 10, 86, 25);
		panel.add(tTicketIdNumberField);
		
		tDestField = new JTextField();
		tDestField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDestField.setEditable(false);
		tDestField.setColumns(10);
		tDestField.setBounds(160, 80, 140, 25);
		panel.add(tDestField);
		
		tDepartField = new JTextField();
		tDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDepartField.setEditable(false);
		tDepartField.setColumns(10);
		tDepartField.setBounds(160, 115, 140, 25);
		panel.add(tDepartField);
		
		tDoDField = new JTextField();
		tDoDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDoDField.setEditable(false);
		tDoDField.setColumns(10);
		tDoDField.setBounds(216, 150, 84, 25);
		panel.add(tDoDField);
		
		tDoFField = new JTextField();
		tDoFField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDoFField.setEditable(false);
		tDoFField.setColumns(10);
		tDoFField.setBounds(216, 220, 84, 25);
		panel.add(tDoFField);
		
		tTotalSeatsField = new JTextField();
		tTotalSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tTotalSeatsField.setEditable(false);
		tTotalSeatsField.setColumns(10);
		tTotalSeatsField.setBounds(216, 255, 84, 25);
		panel.add(tTotalSeatsField);
		
		JLabel label_15 = new JLabel("Price incl. tax");
		label_15.setVerticalAlignment(SwingConstants.BOTTOM);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_15.setBounds(10, 395, 140, 25);
		panel.add(label_15);
		
		tLastNameField = new JTextField();
		tLastNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tLastNameField.setEditable(false);
		tLastNameField.setColumns(10);
		tLastNameField.setBounds(216, 325, 84, 25);
		panel.add(tLastNameField);
		
		tToDField = new JTextField();
		tToDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tToDField.setEditable(false);
		tToDField.setColumns(10);
		tToDField.setBounds(214, 185, 84, 25);
		panel.add(tToDField);
		
		JLabel label_16 = new JLabel("Total Seats");
		label_16.setVerticalAlignment(SwingConstants.BOTTOM);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_16.setBounds(10, 255, 140, 25);
		panel.add(label_16);
		
		JLabel lblLastName = new JLabel("Ticket Holder's Last Name");
		lblLastName.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(10, 325, 196, 25);
		panel.add(lblLastName);
		
		tPriceField = new JTextField();
		tPriceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPriceField.setEditable(false);
		tPriceField.setColumns(10);
		tPriceField.setBounds(216, 360, 84, 25);
		panel.add(tPriceField);
		
		tPriceTaxField = new JTextField();
		tPriceTaxField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tPriceTaxField.setEditable(false);
		tPriceTaxField.setColumns(10);
		tPriceTaxField.setBounds(216, 395, 84, 25);
		panel.add(tPriceTaxField);
		
		JLabel lblAvailableSeats_1 = new JLabel("Ticket Holder's First Name");
		lblAvailableSeats_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAvailableSeats_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAvailableSeats_1.setBounds(10, 290, 196, 25);
		panel.add(lblAvailableSeats_1);
		
		tFirstNameField = new JTextField();
		tFirstNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFirstNameField.setEditable(false);
		tFirstNameField.setColumns(10);
		tFirstNameField.setBounds(216, 290, 84, 25);
		panel.add(tFirstNameField);
		
		JLabel lblTicketIdNumber = new JLabel("Ticket ID Number");
		lblTicketIdNumber.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTicketIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTicketIdNumber.setBounds(10, 10, 157, 25);
		panel.add(lblTicketIdNumber);
		
		tFlightIdNumberField = new JTextField();
		tFlightIdNumberField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tFlightIdNumberField.setEditable(false);
		tFlightIdNumberField.setColumns(10);
		tFlightIdNumberField.setBounds(214, 45, 86, 25);
		panel.add(tFlightIdNumberField);
		
		JLabel lblSearchForTickets = new JLabel("Search for Tickets by:");
		lblSearchForTickets.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchForTickets.setBounds(10, 11, 310, 35);
		browseTicketPanel.add(lblSearchForTickets);
		
		JButton btnDeleteSelectedTicket = new JButton("Delete Selected Ticket");
		btnDeleteSelectedTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ticketList.getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), "No Ticket Selected");
				else{
					String message = adminBE.deleteTicket(tTicketIdNumberField.getText());
					JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
				}
			}
		});
		btnDeleteSelectedTicket.setBounds(404, 458, 220, 32);
		browseTicketPanel.add(btnDeleteSelectedTicket);
		btnDeleteSelectedTicket.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel label = new JLabel("Please Enter First Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(20, 85, 276, 40);
		bookTicketPanel.add(label);
		
		JLabel label_1 = new JLabel("Booking Ticket");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_1.setBounds(20, 21, 287, 39);
		bookTicketPanel.add(label_1);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameField.setColumns(10);
		firstNameField.setBounds(20, 126, 265, 32);
		bookTicketPanel.add(firstNameField);
		
		JLabel label_2 = new JLabel("Please Enter Last Name");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(23, 169, 276, 40);
		bookTicketPanel.add(label_2);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameField.setColumns(10);
		lastNameField.setBounds(23, 207, 265, 32);
		bookTicketPanel.add(lastNameField);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseFlightPanel.setVisible(true);
				bookTicketPanel.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBounds(184, 452, 101, 32);
		bookTicketPanel.add(button_1);
		
		JButton button_3 = new JButton("Clear Name Fields");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				firstNameField.setText("");
				lastNameField.setText("");
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_3.setBounds(23, 263, 265, 32);
		bookTicketPanel.add(button_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Please review your information entered above and the flight information on right to ensure it is the correct before confirming ticket purchase.");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPane.setBounds(25, 312, 323, 116);
		bookTicketPanel.add(textPane);
		
		JLabel label_3 = new JLabel("Flight ID Number");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 8, 157, 25);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Destination");
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 50, 86, 25);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Departure");
		label_5.setVerticalAlignment(SwingConstants.BOTTOM);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 90, 86, 25);
		panel_1.add(label_5);
		
		JLabel label_17 = new JLabel("Date of Departure");
		label_17.setVerticalAlignment(SwingConstants.BOTTOM);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setBounds(10, 130, 140, 25);
		panel_1.add(label_17);
		
		JLabel label_18 = new JLabel("Time of Departure");
		label_18.setVerticalAlignment(SwingConstants.BOTTOM);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_18.setBounds(10, 170, 140, 25);
		panel_1.add(label_18);
		
		JLabel label_19 = new JLabel("Duration of Flight");
		label_19.setVerticalAlignment(SwingConstants.BOTTOM);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_19.setBounds(10, 210, 140, 25);
		panel_1.add(label_19);
		
		JLabel label_20 = new JLabel("Price");
		label_20.setVerticalAlignment(SwingConstants.BOTTOM);
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_20.setBounds(10, 330, 48, 25);
		panel_1.add(label_20);
		
		bFlightIdNumberField = new JTextField();
		bFlightIdNumberField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bFlightIdNumberField.setEditable(false);
		bFlightIdNumberField.setColumns(10);
		bFlightIdNumberField.setBounds(216, 10, 84, 25);
		panel_1.add(bFlightIdNumberField);
		
		bDestField = new JTextField();
		bDestField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bDestField.setEditable(false);
		bDestField.setColumns(10);
		bDestField.setBounds(160, 50, 140, 25);
		panel_1.add(bDestField);
		
		bDepartField = new JTextField();
		bDepartField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bDepartField.setEditable(false);
		bDepartField.setColumns(10);
		bDepartField.setBounds(160, 90, 140, 25);
		panel_1.add(bDepartField);
		
		bDateField = new JTextField();
		bDateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bDateField.setEditable(false);
		bDateField.setColumns(10);
		bDateField.setBounds(216, 130, 84, 25);
		panel_1.add(bDateField);
		
		bDurationField = new JTextField();
		bDurationField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bDurationField.setEditable(false);
		bDurationField.setColumns(10);
		bDurationField.setBounds(216, 210, 84, 25);
		panel_1.add(bDurationField);
		
		bTotalSeatsField = new JTextField();
		bTotalSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bTotalSeatsField.setEditable(false);
		bTotalSeatsField.setColumns(10);
		bTotalSeatsField.setBounds(216, 250, 84, 25);
		panel_1.add(bTotalSeatsField);
		
		JLabel label_21 = new JLabel("Price incl. tax");
		label_21.setVerticalAlignment(SwingConstants.BOTTOM);
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_21.setBounds(10, 370, 140, 25);
		panel_1.add(label_21);
		
		bAvailableSeatsField = new JTextField();
		bAvailableSeatsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bAvailableSeatsField.setEditable(false);
		bAvailableSeatsField.setColumns(10);
		bAvailableSeatsField.setBounds(216, 290, 84, 25);
		panel_1.add(bAvailableSeatsField);
		
		bTimeField = new JTextField();
		bTimeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bTimeField.setEditable(false);
		bTimeField.setColumns(10);
		bTimeField.setBounds(216, 170, 84, 25);
		panel_1.add(bTimeField);
		
		JLabel label_22 = new JLabel("Total Seats");
		label_22.setVerticalAlignment(SwingConstants.BOTTOM);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_22.setBounds(10, 250, 140, 25);
		panel_1.add(label_22);
		
		JLabel label_23 = new JLabel("Available Seats");
		label_23.setVerticalAlignment(SwingConstants.BOTTOM);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_23.setBounds(10, 290, 140, 25);
		panel_1.add(label_23);
		
		bPriceField = new JTextField();
		bPriceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bPriceField.setEditable(false);
		bPriceField.setColumns(10);
		bPriceField.setBounds(216, 330, 84, 25);
		panel_1.add(bPriceField);
		
		bPriceTaxField = new JTextField();
		bPriceTaxField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bPriceTaxField.setEditable(false);
		bPriceTaxField.setColumns(10);
		bPriceTaxField.setBounds(216, 370, 84, 25);
		panel_1.add(bPriceTaxField);
		
		JButton button_2 = new JButton("Confirm Ticket Purchase");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = adminBE.bookTicket(firstNameField.getText(), lastNameField.getText(), bFlightIdNumberField.getText());
				JOptionPane.showMessageDialog(frmAdminApplication.getComponent(0), message);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_2.setBounds(317, 452, 254, 32);
		bookTicketPanel.add(button_2);
	}
}
