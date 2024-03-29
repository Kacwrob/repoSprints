package Newsagent;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JTextField passwordTF = new JTextField(10);
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
		dbContentsPanel.setSize(550, 202);
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
				String updateTemp = "INSERT INTO Newsagents VALUES(" + null + ",'" + firstNameTF.getText() + "', '"
						+ lastNameTF.getText() + "', '" + usernameTF.getText() + "', sha1('" + passwordTF.getText()
						+ "'), '" + emailTF.getText() + "', '" + contactNumberTF.getText() + "');";

				stmt.executeUpdate(updateTemp);

			} catch (SQLException sqle) {
				System.err.println("Error with insert:\n" + sqle.toString());
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

				String updateTemp = "UPDATE Newsagents SET firstName = '" + firstNameTF.getText() + "', lastName = '"
						+ lastNameTF.getText() + "', username = '" + usernameTF.getText() + "', password ='"
						+ passwordTF.getText() + "', email = '" + emailTF.getText() + "', contactNo = '"
						+ contactNumberTF.getText() + "' where newsagent_id = " + IDTF.getText();

				stmt.executeUpdate(updateTemp);

				// The table updates when we access the db.
				rs = stmt.executeQuery("SELECT * from Newsagents ");
				rs.next();
				rs.close();

			} catch (SQLException sqle) {
				System.err.println("Error with  update:\n" + sqle.toString());
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

	///////////////////////////////////////////////////////////////////////////

	private void writeToFile(ResultSet rs) {
		try {
			System.out.println("In writeToFile");
			FileWriter outputFile = new FileWriter("output.csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			for (int i = 0; i < numColumns; i++) {
				printWriter.print(rsmd.getColumnLabel(i + 1) + ",");
			}
			printWriter.print("\n");

			while (rs.next()) {
				for (int i = 0; i < numColumns; i++) {
					printWriter.print(rs.getString(i + 1) + ",");
				}
				printWriter.print("\n");
				printWriter.flush();
			}
			printWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
