package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountriesDBAccess implements CountriesDataAccess {


    public CountriesDBAccess() throws ConnectionDataAccessException {

    }

    @Override
    public List<String> getCountries() throws CountriesDAOException {
        List<String> countries;
        try {
            String sql = "SELECT name FROM country";
            Connection connection = ConnectionDataAccess.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            countries = new ArrayList<>();

            while (resultSet.next()) {
                countries.add(resultSet.getString("name"));
            }
        } catch (SQLException | ConnectionDataAccessException e) {
            throw new CountriesDAOException(e.getMessage());
        }
        return countries;
    }
}
