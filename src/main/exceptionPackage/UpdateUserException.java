package main.exceptionPackage;

public class UpdateUserException extends Exception{
    private String error;

    public UpdateUserException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la mise à jour de l'utilisateur : " + error;
    }
}
