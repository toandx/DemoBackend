package toandx.render.demoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toandx.render.demoBackend.entity.User;

// Step 3. Create repository to query User entity.
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}