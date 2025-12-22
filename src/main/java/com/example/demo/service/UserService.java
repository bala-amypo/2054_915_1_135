public interface UserService {
    User register(RegisterRequest request);
    User login(LoginRequest request);
    List<User> getAllUsers();
    User findById(Long id);
    User findByEmail(String email);
}
