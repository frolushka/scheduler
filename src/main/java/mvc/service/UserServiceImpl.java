package mvc.service;

import mvc.model.user.Invite;
import mvc.model.user.Role;
import mvc.model.user.User;
import mvc.repository.user.InviteRepository;
import mvc.repository.user.RoleRepository;
import mvc.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        saveRole(new Role(user.getUsername(), "ROLE_USER"));

        Invite toDelete = inviteRepository.findByInvite(user.getInvite());
        inviteRepository.delete(toDelete);

        saveInvite(new Invite(user.getInvite(), user.getUsername()));
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveInvite(Invite invite) {
        inviteRepository.save(invite);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Invite findInvite(String invite) {
        return inviteRepository.findByInvite(invite);
    }

    @Override
    public List<User> getAll() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(userRepository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .collect(toList());
    }

    @Override
    public List<Invite> getAllInvites() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(inviteRepository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .collect(toList());
    }

    public String generateInvite(int length) {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(alphanum.charAt(random.nextInt(alphanum.length())));
        }
        String invite = sb.toString();
        saveInvite(new Invite(invite, null));
        return invite;
    }

    public String generateInvite() {
        return generateInvite(19);
    }

}
