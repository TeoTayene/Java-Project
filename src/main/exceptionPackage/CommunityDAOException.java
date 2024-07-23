package main.exceptionPackage;

public class CommunityDAOException extends Exception {
    private String error;

    public CommunityDAOException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la récupération des communautés : " + error;
    }
}
