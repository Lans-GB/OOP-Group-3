package LabExercises;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;

public class EmployeeOrderV2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;   // Employee Name
    private JTextField textField_1; // Employee ID
    private JTextField textField_2; // Travel Destination
    private JTextField textField_3; // Travel Date
    private JTextField textField_4; // Return Date
    private JTextField textField_5; // Employee Count
    private JTextArea textArea;     // Output area

    // Data storage
    private ArrayList<String> employeeNames = new ArrayList<>();
    private ArrayList<Integer> employeeIDs = new ArrayList<>();
    private ArrayList<String> destinations = new ArrayList<>();
    private ArrayList<Integer> orderNums = new ArrayList<>();
    private ArrayList<LocalDate> travelDates = new ArrayList<>();
    private ArrayList<LocalDate> returnDates = new ArrayList<>();

    private Random random = new Random();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private int employeeCount = 0;  // Total employees to be entered

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeOrderV2 frame = new EmployeeOrderV2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EmployeeOrderV2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setTitle("Employee Travel Order System");

        JLabel lblCount = new JLabel("Employee Count");
        lblCount.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblCount.setBounds(10, 11, 150, 14);
        contentPane.add(lblCount);

        textField_5 = new JTextField();
        textField_5.setBounds(180, 8, 80, 20);
        contentPane.add(textField_5);

        JLabel lblNewLabel = new JLabel("Employee Name");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNewLabel.setBounds(10, 36, 150, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Employee ID (5 digits)");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(10, 61, 150, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Travel Destination");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(10, 86, 150, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Travel Date (MM/DD/YYYY)");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNewLabel_3.setBounds(10, 111, 200, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Return Date (MM/DD/YYYY)");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNewLabel_4.setBounds(10, 136, 200, 14);
        contentPane.add(lblNewLabel_4);

        textField = new JTextField();
        textField.setBounds(180, 33, 150, 20);
        contentPane.add(textField);

        textField_1 = new JTextField();
        textField_1.setBounds(180, 58, 150, 20);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(180, 83, 150, 20);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setBounds(210, 108, 100, 20);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setBounds(210, 133, 100, 20);
        contentPane.add(textField_4);

        JButton btnSetCount = new JButton("Set Count");
        btnSetCount.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnSetCount.setBounds(280, 8, 100, 23);
        contentPane.add(btnSetCount);
        btnSetCount.addActionListener(e -> setEmployeeCount());

        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnAddEmployee.setBounds(10, 170, 150, 23);
        contentPane.add(btnAddEmployee);
        btnAddEmployee.addActionListener(e -> addEmployee());

        JButton btnDisplayDetails = new JButton("Display Details");
        btnDisplayDetails.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnDisplayDetails.setBounds(170, 170, 150, 23);
        contentPane.add(btnDisplayDetails);
        btnDisplayDetails.addActionListener(e -> displayDetails());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 210, 560, 260);
        contentPane.add(scrollPane);
    }

    // Sets the number of employees to be added
    private void setEmployeeCount() {
        try {
            int count = Integer.parseInt(textField_5.getText().trim());
            if (count <= 0) {
                JOptionPane.showMessageDialog(this, "Employee count must be greater than zero.");
                return;
            }
            employeeCount = count;
            JOptionPane.showMessageDialog(this, "Employee count set to " + count);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number for employee count.");
        }
    }

    // Adds employee information
    private void addEmployee() {
        if (employeeCount == 0) {
            JOptionPane.showMessageDialog(this, "Please set the Employee Count first.");
            return;
        }

        if (employeeNames.size() >= employeeCount) {
            JOptionPane.showMessageDialog(this, "You already reached the employee limit.");
            return;
        }

        String name = textField.getText().trim();
        String idText = textField_1.getText().trim();

        if (name.isEmpty() || idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Employee Name and ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            if (id < 10000 || id > 99999) {
                JOptionPane.showMessageDialog(this, "Employee ID must be a 5-digit number.");
                return;
            }

            if (employeeIDs.contains(id)) {
                JOptionPane.showMessageDialog(this, "Employee ID already exists.");
                return;
            }

            employeeNames.add(name);
            employeeIDs.add(id);
            JOptionPane.showMessageDialog(this, "Employee added successfully. (" + employeeNames.size() + "/" + employeeCount + ")");

            textField.setText("");
            textField_1.setText("");

            // Automatically request travel order entry when all employees are added
            if (employeeNames.size() == employeeCount) {
                JOptionPane.showMessageDialog(this, "All employees added. Now enter travel order details.");
                addTravelOrder();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Must be numeric.");
        }
    }

    // Creates a travel order for all employees after validation
    private void addTravelOrder() {
        String destination = textField_2.getText().trim();
        String travelStr = textField_3.getText().trim();
        String returnStr = textField_4.getText().trim();

        if (destination.isEmpty() || travelStr.isEmpty() || returnStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all travel details before generating the order.");
            return;
        }

        try {
            LocalDate travelDate = LocalDate.parse(travelStr, formatter);
            LocalDate returnDate = LocalDate.parse(returnStr, formatter);

            if (!returnDate.isAfter(travelDate)) {
                JOptionPane.showMessageDialog(this, "Return date must be after the travel date.");
                return;
            }

            destinations.add(destination);
            travelDates.add(travelDate);
            returnDates.add(returnDate);
            int orderNum = random.nextInt(9000) + 1000;
            orderNums.add(orderNum);

            JOptionPane.showMessageDialog(this, "Travel Order created successfully.");
            textField_2.setText("");
            textField_3.setText("");
            textField_4.setText("");

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use MM/DD/YYYY.");
        }
    }

    // Displays all employee and travel order details
    private void displayDetails() {
        if (employeeNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employee data available to display.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== DNS Corporation Travel Orders ===\n\n");

        for (int i = 0; i < employeeNames.size(); i++) {
            String passNum = "EP" + String.format("%05d", (i + 1));
            sb.append("Employee: ").append(employeeNames.get(i)).append("\n");
            sb.append("ID: ").append(employeeIDs.get(i)).append("\n");
            sb.append("Employment Pass: ").append(passNum).append("\n");

            if (!destinations.isEmpty()) {
                sb.append("Destination: ").append(destinations.get(0)).append("\n");
                sb.append("Order Number: ").append(orderNums.get(0)).append("\n");
                sb.append("Travel Date: ").append(travelDates.get(0).format(formatter)).append("\n");
                sb.append("Return Date: ").append(returnDates.get(0).format(formatter)).append("\n");
            } else {
                sb.append("No travel order recorded yet.\n");
            }

            sb.append("------------------------------------------\n");
        }

        textArea.setText(sb.toString());
    }
}
