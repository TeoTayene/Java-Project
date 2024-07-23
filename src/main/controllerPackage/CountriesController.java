package main.controllerPackage;

import main.businessPackage.CountriesManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import java.util.List;

public class CountriesController {
    private CountriesManager countriesManager;

    public CountriesController() throws ConnectionDataAccessException {
        countriesManager = new CountriesManager();
    }

    public List<String> getCountries() throws CountriesDAOException {
        return countriesManager.getCountries();
    }
}
