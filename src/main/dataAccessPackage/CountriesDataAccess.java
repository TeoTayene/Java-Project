package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;
import java.util.List;

public interface CountriesDataAccess {
    public List<String> getCountries() throws CountriesDAOException , ConnectionDataAccessException;
}
