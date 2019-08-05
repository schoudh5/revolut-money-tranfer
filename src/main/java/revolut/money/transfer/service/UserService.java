package revolut.money.transfer.service;


public interface UserService {
    User findByUsername(String name);
    void create(User user);
    void delete(User user);
}
