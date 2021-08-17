package demo.hao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class MyUser {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    protected MyUser() {}

    public MyUser(String name, String password, List<String> roles) { // <3>
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
}
