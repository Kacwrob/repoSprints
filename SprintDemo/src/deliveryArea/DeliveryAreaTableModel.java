package deliveryArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class DeliveryAreaTableModel extends AbstractTableModel {
	Vector modelData; // will hold String[] objects
	int colCount;
	String[] headers = new String[0];
	Connection con;
	Statement stmt = null;
	String[] record;
	ResultSet rsNewsagents = null;

	public DeliveryAreaTableModel() {
		modelData = new Vector();
	}// end constructor QueryTableModel

	@Override
	public int getColumnCount() {
		return colCount;
	}

	@Override
	public String getColumnName(int i) {
		return headers[i];
	}

	@Override
	public int getRowCount() {
		return modelData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return ((String[]) modelData.elementAt(row))[col];
	}

	public void refreshDeliveryAreaTableFromDB(Statement stmt1) {
		modelData = new Vector();
		stmt = stmt1;

		try {
			rsNewsagents = stmt.executeQuery("SELECT * FROM delivery_area");
			ResultSetMetaData meta = rsNewsagents.getMetaData();

			// to get the number of columns
			colCount = meta.getColumnCount();
			// Now must rebuild the headers array with the new column names
			headers = new String[colCount];

			for (int h = 0; h < colCount; h++) {
				headers[h] = meta.getColumnName(h + 1);
			} // end for loop

			// fill the cache with the records from the query, ie get all the rows
			while (rsNewsagents.next()) {
				record = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					record[i] = rsNewsagents.getString(i + 1);
				} // end for loop
				modelData.addElement(record);
			} // end while loop

			fireTableChanged(null); // Tell the listeners a new table has arrived.
		}
		// end try clause
		catch (Exception e) {
			System.out.println("Error with refreshFromDB Method\n" + e.toString());
		} // end catch clause to query table
	}

}// end class QueryTableModel
