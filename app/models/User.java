package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Brams
 * Date: 24/03/14
 * Time: 09:20
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User extends Model {
    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

}
