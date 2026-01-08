package toandx.render.demoBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toandx.render.demoBackend.dto.Note;
import toandx.render.demoBackend.entity.NoteEntity;
import toandx.render.demoBackend.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepo;

    // Can select only 1 solution: Variable injection or Constructor injection, if use Variable inject and add empty construction, variable injection not work. noteRepo=null
    /*public NoteService() {

    }*/

    public Note addNote(Note note) {
        noteRepo.save(new NoteEntity(note.getTitle(), note.getContent()));
        return note;
    }

    public void clear() {
        noteRepo.deleteAll();
    }

    public List<NoteEntity> getAll() {
        return noteRepo.findAll();
    }

    public NoteEntity getById(Long id) {
        return noteRepo.findById(id).orElse(null);
    }
}
