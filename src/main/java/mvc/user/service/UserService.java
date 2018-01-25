package mvc.user.service;

import mvc.user.model.Invite;
import mvc.user.model.Role;
import mvc.user.model.User;

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