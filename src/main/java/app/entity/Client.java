package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullname;

    private int age;

    private String fin;

    @JsonIgnore
    private String roles;

    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    private Fee fee;

    public Client(String fullname, int age, String fin, String... roles) {
        this.fullname = fullname;
        this.age = age;
        this.fin = fin;
        setRoles(roles);
    }

    @Transient
    private final static String DELIMITER = ":";
    @Transient
    private final static String[] EMPTY = {};

    public String[] getRoles() {
        return roles == null || roles.isEmpty() ? EMPTY : roles.split(DELIMITER);
    }

    public void setRoles(String[] roles) {
        this.roles = String.join(DELIMITER, roles);
    }
}
