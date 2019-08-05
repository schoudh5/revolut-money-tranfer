package revolut.money.transfer.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import revolut.money.transfer.service.User;
import revolut.money.transfer.service.UserService;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Singleton
public class MemoryUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(MemoryUserService.class);

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User findByUsername(String username) {
        requireNonNull(username, "username");
        return users.get(username);
    }

    @Override
    public void create(User user) {
        requireNonNull(user, "user is not supplied");
        if (users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("User " + user.getUsername() + " already exists");
        }
        users.put(user.getUsername(), user);
        LOG.debug("Created user: {}", user.getUsername());
    }

    @Override
    public void delete(User user) {
        requireNonNull(user, "user is not supplied");
        users.remove(user.getUsername());
        LOG.debug("Deleted user: {}", user.getUsername());
    }
}
