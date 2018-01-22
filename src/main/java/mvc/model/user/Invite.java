package mvc.model.user;

import mvc.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Locale;

@Entity
@Table(name = "user_invites")
public class Invite implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invite_id")
    private String id;

    @Column(name = "invite")
    @NotEmpty
    private String invite;

    @Column(name = "username")
    private String username;

    protected Invite() {

    }

    public Invite(String invite, String username) {
        this.invite = invite;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getInvite() {
        return invite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
