package Q2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PhoneBookJFrame extends JFrame {

	private final JButton addRecordButton;
	private final JButton deleteRecordButton;
	private final JButton importRecordsButton;
	private final JButton searchButton;
	private final JButton editButton;
	private final JTable bookTable;
	private final JTextField searchField;

	private DefaultTableModel model;
	private JScrollPane tableContainer;

	private static boolean isImported = false;

	private static final String[] columns = { "Full Name", "Phone Number" };
	private static final int FULL_NAME_POSITION = 0;
	private static final int PHONE_NUMBER_POSITION = 1;
	private static final int JTEXTFIELD_SIZE = 30;

	private PhoneBook phoneBookTreeMap;

	public PhoneBookJFrame() {

		super("Phone Book");

		FlowLayout layout = new FlowLayout();
		setLayout(layout);
		layout.setAlignment(FlowLayout.RIGHT);

		searchField = new JTextField(JTEXTFIELD_SIZE);
		searchButton = new JButton("Search");
		addRecordButton = new JButton("Add");
		deleteRecordButton = new JButton("Delete");
		importRecordsButton = new JButton("Import");
		editButton = new JButton("Edit");
		bookTable = new JTable();

		add(searchField);
		add(searchButton);
		add(editButton);
		add(addRecordButton);
		add(deleteRecordButton);
		add(importRecordsButton);
		add(bookTable);

		ButtonHandler buttonHandler = new ButtonHandler();

		searchButton.addActionListener(buttonHandler);
		addRecordButton.addActionListener(buttonHandler);
		deleteRecordButton.addActionListener(buttonHandler);
		importRecordsButton.addActionListener(buttonHandler);
		editButton.addActionListener(buttonHandler);

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand() == "Import") {

				Path filePath = new ImportJFileChooserJFrame().getFilePath();
				if (filePath != null)
					populateTable(filePath);

			} else if (e.getActionCommand() == "Delete") {

				int selectedRow = bookTable.getSelectedRow();

				if (selectedRow != -1)
					deleteRow(selectedRow);

			}

			else if (e.getActionCommand() == "Search") {

				if (!isImported) {

					JOptionPane.showMessageDialog(null, "You must import from file first.");

				} else {

					String key = searchField.getText();
					if (key != null)
						search(key);

				}
			} else if (e.getActionCommand() == "Add") {

				if (!isImported) {

					JOptionPane.showMessageDialog(null, "You must import from file first.");

				} else
					addRow();

			} else if (e.getActionCommand() == "Edit") {
				int selectedRow = bookTable.getSelectedRow();

				if (selectedRow != -1) {
					editRecord(selectedRow);
				}
			}

		}

		private void editRecord(int selectedRow) {

			JTextField fullNameField = new JTextField(30);
			JTextField phoneNumberField = new JTextField(30);

			String fullName = model.getValueAt(selectedRow, FULL_NAME_POSITION).toString();
			String phoneNumber = model.getValueAt(selectedRow, PHONE_NUMBER_POSITION).toString();
			
			fullNameField.setText(fullName);
			phoneNumberField.setText(phoneNumber);
			
			Object[] message = { "Enter full name", fullNameField, "Enter phone number", phoneNumberField };

			int result = JOptionPane.showConfirmDialog(null, message);

			if (result == JOptionPane.OK_OPTION) {

				if (fullNameField.getText() != null && fullName.compareTo(fullNameField.getText()) == 0) {
					
					if (phoneNumberField.getText() != null) {
				
						String value = phoneNumberField.getText().toString();
						phoneBookTreeMap.getPhoneBook().put(fullName, value);
						model.setValueAt(value , selectedRow, PHONE_NUMBER_POSITION);
						
					}

				}

			}

		}

		private void search(String key) {

			String phoneNumber = phoneBookTreeMap.getPhoneBook().get(key);

			if (phoneNumber != null) {
				String message = "Full Name : " + key + " Phone Number: " + phoneNumber;
				JOptionPane.showMessageDialog(null, message);
			}

		}

		private void addRow() {

			JTextField fullNameField = new JTextField(30);
			JTextField phoneNumberField = new JTextField(30);

			Object[] message = { "Enter full name", fullNameField, "Enter phone number", phoneNumberField };

			int result = JOptionPane.showConfirmDialog(null, message);

			if (result == JOptionPane.OK_OPTION) {

				Object[] row = { fullNameField.getText(), phoneNumberField.getText() };

				if (!phoneBookTreeMap.getPhoneBook().containsKey(row[FULL_NAME_POSITION].toString())) {

					phoneBookTreeMap.getPhoneBook().put(row[FULL_NAME_POSITION].toString(),
							row[PHONE_NUMBER_POSITION].toString());
					model.addRow(row);
				}

				else
					JOptionPane.showMessageDialog(null, "The record is already added");
			}
		}

		private void deleteRow(int rowForDelete) {

			String key = model.getValueAt(rowForDelete, FULL_NAME_POSITION).toString();
			model.removeRow(rowForDelete);
			phoneBookTreeMap.getPhoneBook().remove(key);

		}

	}

	private void populateTable(Path filePath) {

		phoneBookTreeMap = new PhoneBook(filePath);

		model = new DefaultTableModel();

		for (String str : columns)
			model.addColumn(str);

		for (Map.Entry<String, String> entry : phoneBookTreeMap.getPhoneBook().entrySet()) {
			
			Object[] row = { entry.getKey(), entry.getValue()};
			model.addRow(row);
			
		}

		bookTable.setModel(model);
		tableContainer = new JScrollPane(bookTable);
		add(tableContainer);
		isImported = true;

	}

}
