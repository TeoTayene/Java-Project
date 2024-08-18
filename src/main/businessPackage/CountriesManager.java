package main.businessPackage;

import main.dataAccessPackage.CountriesDataAccess;
import main.dataAccessPackage.CountriesDBAccess;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import java.util.List;

public class CountriesManager {
    private CountriesDataAccess countriesDataAccess;

    public CountriesManager() throws ConnectionDataAccessException {
        setCountriesDAO(new CountriesDBAccess());
    }

    public void setCountriesDAO(CountriesDataAccess countriesDataAccess) {
        this.countriesDataAccess = countriesDataAccess;
    }

    public List<String> getCountries() throws CountriesDAOException, ConnectionDataAccessException {
        return countriesDataAccess.getCountries();
    }

    public Boolean countryExists(String country) throws CountriesDAOException, ConnectionDataAccessException {
        List<String> countries = getCountries();
        countries.replaceAll(String::toLowerCase);

        return countries.contains(country.toLowerCase());
    }
}
