package main.exceptionPackage;

public class DirectMessageException extends Exception {
    private String error;

    public DirectMessageException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la récupération des messages privés : " + error;
    }
}
