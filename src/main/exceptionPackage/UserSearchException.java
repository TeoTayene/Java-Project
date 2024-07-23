package main.exceptionPackage;

public class UserSearchException extends Exception {
    private String error;

    public UserSearchException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la recherche de l'utilisateur : " + error;
    }
}
