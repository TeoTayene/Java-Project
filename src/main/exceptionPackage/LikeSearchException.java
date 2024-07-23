package main.exceptionPackage;

public class LikeSearchException extends Exception {
    private String error;

    public LikeSearchException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la recherche des likes : " + error;
    }
}
