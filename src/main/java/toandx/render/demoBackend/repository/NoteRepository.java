package toandx.render.demoBackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toandx.render.demoBackend.entity.NoteEntity;

import java.util.List;
import java.util.Map;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query(value = "SELECT n.id, n.title FROM note n WHERE n.created_by = ?1", nativeQuery = true)
    List<Map<String, Object>> findAllSummary(Integer userId); // Must use Map, use DTO cannot convert

}
