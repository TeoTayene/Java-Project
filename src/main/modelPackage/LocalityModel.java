package main.modelPackage;

public class LocalityModel {
    private Integer code;
    private String name;
    private String city;
    private Integer zipCode;
    private Integer localisation;

    public LocalityModel(Integer code, String name, String city, Integer zipCode, Integer localisation) {
        setCode(code);
        setName(name);
        setCity(city);
        setZipCode(zipCode);
        setLocalisation(localisation);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Integer localisation) {
        this.localisation = localisation;
    }
}
