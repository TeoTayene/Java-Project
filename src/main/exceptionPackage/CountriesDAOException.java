package main.exceptionPackage;

public class CountriesDAOException extends Exception {
    private String error;

    public CountriesDAOException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la récupération des pays : " + error;
    }
}
