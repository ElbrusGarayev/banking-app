package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullname;

    private String username;

    private String password;

    private String roles;

    @Transient
    private final static String DELIMITER = ":";
    @Transient
    private final static String[] EMPTY = {};

    public Admin(String fullname, String username, String password, String... roles) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        setRoles(roles);
    }

    public String[] getRoles() {
        return roles == null || roles.isEmpty() ? EMPTY : roles.split(DELIMITER);
    }

    public void setRoles(String[] roles) {
        this.roles = String.join(DELIMITER, roles);
    }
}
