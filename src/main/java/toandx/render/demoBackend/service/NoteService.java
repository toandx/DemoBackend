package toandx.render.demoBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toandx.render.demoBackend.dto.Note;
import toandx.render.demoBackend.entity.NoteEntity;
import toandx.render.demoBackend.repository.NoteRepository;

import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepo;

    // Can select only 1 solution: Variable injection or Constructor injection, if use Variable inject and add empty construction, variable injection not work. noteRepo=null
    /*public NoteService() {

    }*/

    public Note addNote(Note note, Integer userId) {
        noteRepo.save(new NoteEntity(note.getTitle(), note.getContent(), userId));
        return note;
    }

    public void clear() {
        noteRepo.deleteAll();
    }

    public List<Map<String,Object>> getAllSummary(Integer userId) {
        return noteRepo.findAllSummary(userId);
    }

    public NoteEntity getById(Long id) {
        return noteRepo.findById(id).orElse(null);
    }

    public boolean deleteById(Long id) {
        noteRepo.deleteById(id);
        return true;
    }

    public NoteEntity updateById(NoteEntity note) {
        return noteRepo.save(note);
    }
}
