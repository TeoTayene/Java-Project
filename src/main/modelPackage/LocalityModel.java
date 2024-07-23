package main.modelPackage;

public class LocalityModel {
    private int code;
    private String name;
    private String city;
    private int zipCode;
    private int localisation;

    public LocalityModel(int code, String name, String city, int zipCode, int localisation) {
        setCode(code);
        setName(name);
        setCity(city);
        setZipCode(zipCode);
        setLocalisation(localisation);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getLocalisation() {
        return localisation;
    }

    public void setLocalisation(int localisation) {
        this.localisation = localisation;
    }
}
