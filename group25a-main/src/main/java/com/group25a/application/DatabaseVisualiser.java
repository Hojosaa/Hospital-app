package com.comp5320.experimental;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DatabaseVisualiser extends JPanel {

    private Connection connection;
    private String databaseName;
    private JTable table;

    private DatabaseVisualiser(Connection connection, String databaseName) {
        this.connection = connection;
        this.databaseName = databaseName;
        initComponents();
    }

    public static void create(Connection connection, String databaseName) {
        DatabaseVisualiser visualiser = new DatabaseVisualiser(connection, databaseName);

        JFrame frame = new JFrame("Database Visualiser");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(visualiser);
        frame.pack();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(databaseName);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(tableName);
                root.add(tableNode);

                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    DefaultMutableTreeNode columnNode = new DefaultMutableTreeNode(columnName);
                    tableNode.add(columnNode);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null && node.getLevel() == 1) {
                    String tableName = node.getUserObject().toString();
                    displayTableData(tableName);
                }
            }
        });

        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setPreferredSize(new Dimension(200, 0));

        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, tableScrollPane);
        add(splitPane, BorderLayout.CENTER);
    }

    private void displayTableData(String tableName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }

            table.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}