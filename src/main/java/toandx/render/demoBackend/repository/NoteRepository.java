package toandx.render.demoBackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toandx.render.demoBackend.entity.NoteEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
