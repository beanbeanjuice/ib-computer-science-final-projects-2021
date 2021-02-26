package database;

public class User {

    private int id;
    private String name;
    private String password; // In an actual database, this will be the password hash

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
