package main.modelPackage;

public class CommunityModel {
    private Integer id;
    private String name;
    private Integer creator;

    public CommunityModel(Integer id, String name, Integer creator) {
        setId(id);
        setName(name);
        setCreator(creator);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
