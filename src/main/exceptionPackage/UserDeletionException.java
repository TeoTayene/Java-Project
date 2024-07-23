package main.exceptionPackage;

public class UserDeletionException extends Exception {
    private String error;

    public UserDeletionException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
            return "Erreur lors de la suppresion de l'utilisateur : " + error;
        }
}
