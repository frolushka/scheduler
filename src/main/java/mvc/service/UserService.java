package mvc.service;

import mvc.model.user.Invite;
import mvc.model.user.Role;
import mvc.model.user.User;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    Invite findInvite(String invite);

    void saveUser(User user);
    void saveRole(Role role);
    void saveInvite(Invite invite);

    String generateInvite();

    List<User> getAll();
    List<Invite> getAllInvites();

}