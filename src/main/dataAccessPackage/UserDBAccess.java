package main.dataAccessPackage;

import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;
import main.utilPackage.Encryption;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBAccess implements UserDataAccess {

    public UserDBAccess() throws ConnectionDataAccessException {
    }

    @Override
    public Boolean createUser(UserModel user) throws UserCreationException {
        int lines = userInsertionOrUpdate(user, true);
        if (lines == 0) throw new UserCreationException("L'utilisateur n'a pas pu être créé");
        return true;
    }

    @Override
    public Boolean updateUser(UserModel user) throws UserCreationException {
        int updated = userInsertionOrUpdate(user, false);
        if (updated == 0) throw new UserCreationException("L'utilisateur n'a pas pu être mis à jour");
        return true;
    }

    private int userInsertionOrUpdate(UserModel user, Boolean create) throws UserCreationException {
        // creation du user = creation de created_at
        String sqlInsert = "INSERT INTO user " +
                "(email, username, password, date_of_birth, gender, street_and_number," +
                " phone_number, biography, is_admin, home, created_at)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // update du user = pas de modif de created_at
        String sqlUpdate = "UPDATE user SET " +
                "email = ?, username = ?, password = ?, date_of_birth = ?, gender = ?, " +
                "street_and_number = ?, phone_number = ?, biography = ?, is_admin = ?, home = ? " +
                "WHERE id = ?";
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement statement = connection.prepareStatement(create ? sqlInsert : sqlUpdate);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, Encryption.createDBPassword(user.getPassword()));
            statement.setDate(4, new Date(user.getDateOfBirth().getTime()));
            statement.setString(5, String.valueOf(user.getGender()));
            statement.setString(6, user.getStreetAndNumber());

            String phoneNumber = user.getPhoneNumber();
            if (phoneNumber == null) statement.setNull(7, Types.VARCHAR);
            else statement.setString(7, phoneNumber);

            String bio = user.getBio();
            if (bio == null) statement.setNull(8, Types.VARCHAR);
            else statement.setString(8, bio);

            statement.setBoolean(9, user.isAdmin());
            statement.setInt(10, user.getHome());

            if (create) {
                statement.setDate(11, new Date(System.currentTimeMillis()));
            } else {
                statement.setInt(11, user.getId());
            }

            return statement.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e){
            throw new UserCreationException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteUser(UserModel user) throws UserDeletionException {
        try {
            if (user == null) throw new UserDeletionException("L'utilisateur n'existe pas");
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, user.getId());

            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new UserDeletionException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserModel> getAllUsers() throws UserSearchException {
        try {
            String sql = "SELECT * FROM user";
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<UserModel> users = new ArrayList<>();
            while (rs.next()) {
                users.add(fillUser(rs));
            }

            return users;
        } catch (SQLException e) {
            throw new UserSearchException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel getUser(int id) throws UserSearchException {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return fillUser(rs);
            }
        } catch (SQLException  e) {
            throw new UserSearchException("Erreur lors de la récupération de l'utilisateur");
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private UserModel fillUser(ResultSet rs) throws SQLException {
        UserModel user = new UserModel();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setGender(rs.getString("gender").charAt(0));
        user.setCreatedAt(rs.getDate("created_at"));
        user.setStreetAndNumber(rs.getString("street_and_number"));
        
        if (rs.getString("phone_number") != null)
            user.setPhoneNumber(rs.getString("phone_number"));

        if (rs.getString("biography") != null)
            user.setBio(rs.getString("biography"));

        user.setAdmin(rs.getBoolean("is_admin"));
        user.setHome(rs.getInt("home"));
        
        return user;
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        List<LocalityModel> localities;
        try {
            String sql = "SELECT * FROM locality l " +
                    "WHERE l.localisation = (select c.id from country c where c.name = ?)";
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            localities = new ArrayList<>();

            while (resultSet.next()) {
                LocalityModel locality = new LocalityModel(
                    resultSet.getInt("code"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code"),
                    resultSet.getInt("localisation")
                );
                localities.add(locality);
            }
        } catch (SQLException e) {
            throw new LocalityException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
        return localities;
    }

    public List<String> getColumnsNames() throws UserSearchException {
        List<String> columnNames;
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metadata.getColumnName(i));
            }
        } catch (SQLException e) {
            throw new UserSearchException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
        return columnNames;
    }

    @Override
    public String getCountryNameByHome(int userId) throws CountriesDAOException {
        try {
            String sql = "SELECT c.name FROM user u " +
                    "JOIN locality l ON u.home = l.code " +
                    "JOIN country c ON l.localisation = c.id " +
                    "WHERE u.id = ?";
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
            throw new CountriesDAOException("Pays non trouvé");
        } catch (SQLException e) {
            throw new CountriesDAOException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNbUser() throws UserSearchException {
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            String sql = "SELECT COUNT(*) FROM user";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new UserSearchException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<UserModel> getUsersByCountry(String name) throws UserSearchException {
        List<UserModel> users = new ArrayList<>();
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            String sql = "SELECT * FROM user u " +
                    "JOIN locality l ON u.home = l.code " +
                    "JOIN country c ON l.localisation = c.id " +
                    "WHERE c.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(fillUser(rs));
            }
        } catch (SQLException e) {
            throw new UserSearchException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<UserModel> getUsersByAge(Date startDateOfBirth , Date endDateOfBirth) throws UserSearchException {
        List<UserModel> users = new ArrayList<>();
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            String sql = "SELECT * FROM user WHERE date_of_birth BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, startDateOfBirth);
            statement.setDate(2, endDateOfBirth);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(fillUser(rs));
            }
        } catch (SQLException e) {
            throw new UserSearchException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Boolean login(int id, String email, String password) throws LoginException {
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            String sql = "SELECT email, password FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String storedEmail = rs.getString("email");
                String storedPassword = rs.getString("password");
                Boolean emailMatch = storedEmail.equals(email);
                Boolean passwordMatch = Encryption.verifyPassword(password, storedPassword);
                return emailMatch && passwordMatch;
            }
            return false;
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new LoginException(e.getMessage());
        } catch (ConnectionDataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
