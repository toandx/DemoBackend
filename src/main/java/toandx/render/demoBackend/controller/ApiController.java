package toandx.render.demoBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import toandx.render.demoBackend.dto.Note;
import toandx.render.demoBackend.entity.NoteEntity;
import toandx.render.demoBackend.entity.User;
import toandx.render.demoBackend.service.NoteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note,getUserId());
    }

    @GetMapping("/note")
    public List<Map<String, Object>> getNoteSummary() {
        return noteService.getAllSummary(getUserId());
    }

    @GetMapping("/noteId")
    public NoteEntity getNoteById(@RequestParam("id") Long id) {
        return noteService.getById(id);
    }

    @PutMapping("/noteId")
    public NoteEntity updateNote(@RequestBody NoteEntity note) {
        return noteService.updateById(note);
    }

    @DeleteMapping("/noteId")
    public boolean deleteNoteId(@RequestParam("id") Long id) {
        return noteService.deleteById(id);
    }

    @DeleteMapping("/note")
    public void clearNote() {
        noteService.clear();
    }

    public Integer getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user.getId();
    }

}
