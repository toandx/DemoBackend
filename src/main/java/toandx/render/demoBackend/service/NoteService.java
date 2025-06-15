package toandx.render.demoBackend.service;

import org.springframework.stereotype.Service;
import toandx.render.demoBackend.dto.Note;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private List<Note> listNote = new ArrayList<>();

    public NoteService() {
        listNote.add(new Note("Dat","My Brother"));
        listNote.add(new Note("Oanh","My Sister"));
    }

    public Note addNote(Note note) {
        listNote.add(note);
        return note;
    }

    public void clear() {
        listNote.clear();
    }

    public List<Note> getAll() {
        return listNote;
    }
}
