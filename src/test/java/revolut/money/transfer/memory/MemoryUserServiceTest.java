package revolut.money.transfer.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revolut.money.transfer.service.User;
import revolut.money.transfer.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MemoryUserServiceTest {
    private static final String USERNAME = "DUMMY_USERNAME";

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new MemoryUserService();
    }

    @Test
    void findByUsername_whenUserExistsWithUsername_thenReturnUser() {
        User newUser = new User(USERNAME);
        userService.create(newUser);
        assertEquals(newUser, userService.findByUsername(USERNAME));
    }

    @Test
    void create_whenCalledWithNewUser_thenUserIsCreated() {
        User newUser = new User(USERNAME);
        userService.create(newUser);
        assertEquals(newUser, userService.findByUsername(USERNAME));
    }

    @Test
    void delete_whenCalledWithExistingUser_thenUserIsDeleted() {
        User newUser = new User(USERNAME);
        userService.create(newUser);
        userService.delete(newUser);
        assertNull(userService.findByUsername(USERNAME));
    }
}