package main.viewPackage;

import main.controllerPackage.UserController;
import main.exceptionPackage.*;
import main.modelPackage.NonEditableTableModel;
import main.modelPackage.UserModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListingPanel extends JPanel implements ActionListener {
    private MainWindow mainWindow;
    private JTable tableUsers;
    private ArrayList<UserModel> users;
    private List<String> columnNames;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonAdd;
    private JButton buttonList;
    private JPanel addUserPanel;
    private JScrollPane scrollPane;
    private UserController userController;
    private Boolean allDeleted = true;

    public ListingPanel(MainWindow mainWindow) throws ConnectionDataAccessException {
        this.mainWindow = mainWindow;
        userController = new UserController();

        setLayout(new BorderLayout());

        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(0, 20)); // 20 pixels height gap
        add(gapPanel, BorderLayout.CENTER);

        tableUsers = new JTable();
        scrollPane = new JScrollPane(tableUsers);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonList = new JButton("Afficher les utilisateurs");
        buttonList.addActionListener(this);
        buttonAdd = new JButton("Ajouter un utilisateur");
        buttonAdd.addActionListener(this);

        buttonUpdate = new JButton("Modifier un utilisateur");
        buttonUpdate.addActionListener(this);
        buttonDelete = new JButton("Supprimer un utilisateur");
        buttonDelete.addActionListener(this);

        buttonPanel.add(buttonList);
        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonUpdate);
        buttonPanel.add(buttonDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonList) {
            try {
                refreshUsersData();
            } catch (UserSearchException ex) {
                mainWindow.displayError(ex.toString());
            }
        }
        else if (e.getSource() == buttonAdd) {
            try {
                // reset le panel d'ajout d'utilisateur
                this.addUserPanel = new AddUserPanel(mainWindow);
                mainWindow.switchPanel(addUserPanel);
            } catch (CountriesDAOException | ConnectionDataAccessException | LocalityException ex) {
                mainWindow.displayError(ex.toString());
            }
        }
        else if (e.getSource() == buttonDelete) {
            int[] selectedRows = tableUsers.getSelectedRows();

            if (selectedRows.length == 0) {
                mainWindow.displayError("Veuillez sélectionner un ou plusieurs utilisateurs à supprimer");
            } else {
                int result = JOptionPane.showConfirmDialog(mainWindow,
                        "Êtes-vous sûr de vouloir supprimer cet utilisateur ? \n" +
                                "Cette action supprimera tous les posts, communautés, messages " +
                                "et likes associés à cet utilisateur.", "Confirmer la suppression",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result != JOptionPane.YES_OPTION)
                    return;

                try {
                    // reset la variable si elle a déjà été utilisée
                    if (!allDeleted) allDeleted = true;

                    for (int selectedRow : selectedRows) {
                        int userId = (int) tableUsers.getValueAt(selectedRow, 0);

                        try {
                            UserModel user = userController.getUser(userId);
                            if (!userController.deleteUser(user)) {
                                allDeleted = false;
                                mainWindow.displayError("Erreur lors de la suppression de l'utilisateur avec l'ID: " + userId);
                            }
                        } catch (UserSearchException | UserDeletionException ex) {
                            mainWindow.displayError(ex.toString());
                            allDeleted = false;
                        }
                    }

                    if (allDeleted) {
                        mainWindow.displayMessage("Utilisateur(s) supprimé(s) avec succès", "Suppression réussie");
                    }

                    refreshUsersData();
                } catch (UserSearchException ex) {
                    mainWindow.displayError(ex.toString());
                }
            }
        }
        else if (e.getSource() == buttonUpdate) {
            int selectedRow = tableUsers.getSelectedRow();
            if (selectedRow == -1) {
                mainWindow.displayError("Veuillez sélectionner un utilisateur à modifier");
            } else {
                int userId = (int) tableUsers.getValueAt(selectedRow, 0);
                try {
                    UserModel user = userController.getUser(userId);
                    this.addUserPanel = new AddUserPanel(mainWindow, user);
                    mainWindow.switchPanel(addUserPanel);
                } catch (UserSearchException | CountriesDAOException | ConnectionDataAccessException | LocalityException ex) {
                    mainWindow.displayError(ex.toString());
                }
            }
        }
    }

    public void refreshUsersData() throws UserSearchException {
        columnNames = userController.getColumnsNames();
        users = new ArrayList<>(userController.getAllUsers());
        tableUsers = updateTable(users, columnNames);
        scrollPane.setViewportView(tableUsers);
    }

    public JTable updateTable(List<UserModel> users, List<String> columnsNames) {
            String[] columnNames = {
                    "ID",
                    "Email",
                    "Username",
                    "Password",
                    "Date of Birth",
                    "Gender",
                    "Created At",
                    "Street and Number",
                    "Phone Number",
                    "Bio",
                    "Admin",
                    "Home"
            };

            AbstractTableModel model = new AbstractTableModel() {
                private final Class<?>[] columnClasses = {
                        Integer.class, String.class, String.class, String.class, Date.class,
                        String.class, Date.class, String.class, String.class, String.class,
                        Boolean.class, String.class
                };

                @Override
            public int getRowCount() {
                return users.size();
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                UserModel user = users.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return user.getId();
                    case 1:
                        return user.getEmail();
                    case 2:
                        return user.getUsername();
                    case 3:
                        return user.getPassword();
                    case 4:
                        return user.getDateOfBirth();
                    case 5:
                        return user.getGender();
                    case 6:
                        return user.getCreatedAt();
                    case 7:
                        return user.getStreetAndNumber();
                    case 8:
                        return user.getPhoneNumber();
                    case 9:
                        return user.getBio();
                    case 10:
                        return user.isAdmin();
                    case 11:
                        return user.getHome();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < model.getColumnCount(); i++) {
            Class<?> columnClass = model.getColumnClass(i);
            if (columnClass == Integer.class || columnClass == Float.class || columnClass == Double.class) {
                table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            } else if (columnClass == Boolean.class) {
                table.getColumnModel().getColumn(i).setCellRenderer(table.getDefaultRenderer(Boolean.class));
            } else {
                table.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
            }
        }
        return table;
    }
}
