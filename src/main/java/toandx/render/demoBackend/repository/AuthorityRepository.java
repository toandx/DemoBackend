package toandx.render.demoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toandx.render.demoBackend.entity.Authority;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,String> {
    @Override
    Optional<Authority> findById(String name);
}
