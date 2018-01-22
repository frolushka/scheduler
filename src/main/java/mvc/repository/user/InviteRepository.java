package mvc.repository.user;

import mvc.model.user.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    Invite findByUsername(String username);

    Invite findByInvite(String invite);

}
