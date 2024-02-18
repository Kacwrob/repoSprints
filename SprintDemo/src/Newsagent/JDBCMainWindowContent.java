package Newsagent;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class JDBCMainWindowContent extends JInternalFrame implements ActionListener {
	String cmd = null;

	// DB Connectivity Attributes
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private Container content;

	private JPanel detailsPanel;
	private JScrollPane dbContentsPanel;

	private Border lineBorder;

	// Book labels and text fields
	private JLabel IDLabel = new JLabel("ID:                 ");
	private JLabel firstNameLabel = new JLabel("First Name:               ");
	private JLabel lastNameLabel = new JLabel("Last Name:      ");
	private JLabel usernameLabel = new JLabel("Username:               ");
	private JLabel passwordLabel = new JLabel("Password:      ");
	private JLabel emailLabel = new JLabel("Email:        ");
	private JLabel contactNumberLabel = new JLabel("Contact number:                 ");

	private JTextField IDTF = new JTextField(10);
	private JTextField firstNameTF = new JTextField(10);
	private JTextField lastNameTF = new JTextField(10);
	private JTextField usernameTF = new JTextField(10);
	private JPasswordField passwordTF = new JPasswordField(10);
	private JTextField emailTF = new JTextField(10);
	private JTextField contactNumberTF = new JTextField(10);

	// Creating Table Model objects
	private static NewsagentTableModel newsagentTableModel = new NewsagentTableModel();
	private JTable newsagentTable = new JTable(newsagentTableModel);

	// Buttons for inserting, and updating members
	// also a clear button to clear details panel
	private JButton insertButton = new JButton("Insert");
	private JButton deleteButton = new JButton("Delete");
	private JButton updateButton = new JButton("Update");
	private JButton clearButton = new JButton("Clear");

	public JDBCMainWindowContent(String aTitle) {
		// setting up the GUI
		super(aTitle, false, false, false, false);
		setEnabled(true);

		initiate_db_conn();
		// add the 'main' panel to the Internal Frame
		content = getContentPane();
		content.setLayout(null);
		content.setBackground(Color.lightGray);
		lineBorder = BorderFactory.createEtchedBorder(15, Color.red, Color.black);

		// setup details panel and add the components to it
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new GridLayout(7, 2));
		detailsPanel.setBackground(Color.lightGray);
		detailsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "CRUD Actions"));

		detailsPanel.add(IDLabel);
		detailsPanel.add(IDTF);
		detailsPanel.add(firstNameLabel);
		detailsPanel.add(firstNameTF);
		detailsPanel.add(lastNameLabel);
		detailsPanel.add(lastNameTF);
		detailsPanel.add(usernameLabel);
		detailsPanel.add(usernameTF);
		detailsPanel.add(passwordLabel);
		detailsPanel.add(passwordTF);
		detailsPanel.add(emailLabel);
		detailsPanel.add(emailTF);
		detailsPanel.add(contactNumberLabel);
		detailsPanel.add(contactNumberTF);

		insertButton.setSize(100, 30);
		updateButton.setSize(100, 30);
		deleteButton.setSize(100, 30);
		clearButton.setSize(100, 30);

		insertButton.setLocation(370, 29);
		updateButton.setLocation(370, 111);
		deleteButton.setLocation(370, 70);
		clearButton.setLocation(370, 152);

		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);

		content.add(insertButton);
		content.add(updateButton);
		content.add(deleteButton);
		content.add(clearButton);

		newsagentTable.setPreferredScrollableViewportSize(new Dimension(900, 300));

		dbContentsPanel = new JScrollPane(newsagentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dbContentsPanel.setBackground(Color.lightGray);
		dbContentsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Newsagent Table"));

		detailsPanel.setSize(360, 204);
		detailsPanel.setLocation(3, 0);
		dbContentsPanel.setSize(841, 202);
		dbContentsPanel.setLocation(480, 2);

		content.add(detailsPanel);
		content.add(dbContentsPanel);

		setSize(1347, 666);
		setVisible(true);

		newsagentTableModel.refreshNewsagentTableFromDB(stmt);
	}

	public void initiate_db_conn() {
		try {
			// Load the JConnector Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Specify the DB Name
			String url = "jdbc:mysql://localhost:3306/NewspaperDB";
			// Connect to DB using DB URL, Username and password
			con = DriverManager.getConnection(url, "root", "root");
			// Create a generic statement which is passed to the TestInternalFrame1
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println("Error: Failed to connect to database\n" + e.getMessage());
		}
	}

	// event handling
	@Override
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();

		// CRUD actions
		if (target == insertButton) {
			try {
				String updateTemp = "CALL UpsertNewsagent(?, ?, ?, ?, sha1(?), ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(updateTemp);

				pstmt.setNull(1, Types.INTEGER);
				pstmt.setString(2, Newsagent.validateFirstName(firstNameTF.getText()));
				pstmt.setString(3, Newsagent.validateLastName(lastNameTF.getText()));
				pstmt.setString(4, Newsagent.validateUserName(usernameTF.getText()));
				pstmt.setString(5, Newsagent.validatePassword(String.valueOf(passwordTF.getPassword())));
				pstmt.setString(6, Newsagent.validateEmail(emailTF.getText()));
				pstmt.setString(7, Newsagent.validateContactNumber(contactNumberTF.getText()));

				pstmt.executeUpdate();

			} catch (SQLException sqle) {
				System.err.println("Error with insert:\n" + sqle.toString());
			} catch (NewsagentExceptionHandler e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Insert Error", JOptionPane.ERROR_MESSAGE);
			} finally {
				newsagentTableModel.refreshNewsagentTableFromDB(stmt);
			}
		}

		if (target == deleteButton) {
			try {
				String updateTemp = "DELETE FROM Newsagents WHERE newsagent_id = " + IDTF.getText() + ";";

				stmt.executeUpdate(updateTemp);

			} catch (SQLException sqle) {
				System.err.println("Error with delete:\n" + sqle.toString());
			} finally {
				newsagentTableModel.refreshNewsagentTableFromDB(stmt);
			}
		}

		if (target == updateButton) {
			try {

				String updateTemp = "CALL UpsertNewsagent(?, ?, ?, ?, sha1(?), ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(updateTemp);

				pstmt.setInt(1, Integer.parseInt(IDTF.getText()));
				pstmt.setString(2, Newsagent.validateFirstName(firstNameTF.getText()));
				pstmt.setString(3, Newsagent.validateLastName(lastNameTF.getText()));
				pstmt.setString(4, Newsagent.validateUserName(usernameTF.getText()));
				pstmt.setString(5, Newsagent.validatePassword(String.valueOf(passwordTF.getPassword())));
				pstmt.setString(6, Newsagent.validateEmail(emailTF.getText()));
				pstmt.setString(7, Newsagent.validateContactNumber(contactNumberTF.getText()));

				pstmt.executeUpdate();

				// The table updates when we access the db.
				rs = stmt.executeQuery("SELECT * from Newsagents ");
				rs.next();
				rs.close();

			} catch (SQLException sqle) {
				System.err.println("Error with update:\n" + sqle.toString());
			} catch (NewsagentExceptionHandler e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Insert Error", JOptionPane.ERROR_MESSAGE);
			} finally {
				newsagentTableModel.refreshNewsagentTableFromDB(stmt);
			}
		}

		if (target == clearButton) {
			IDTF.setText("");
			firstNameTF.setText("");
			lastNameTF.setText("");
			usernameTF.setText("");
			passwordTF.setText("");
			emailTF.setText("");
			contactNumberTF.setText("");
		}
		// end of book CRUD actions

	}

}
