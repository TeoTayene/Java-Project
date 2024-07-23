package main.exceptionPackage;

public class UserCreationException extends Exception {
    private String error;

    public UserCreationException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la création de l'utilisateur : " + error;
    }
}
