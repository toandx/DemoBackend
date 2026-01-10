package toandx.render.demoBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toandx.render.demoBackend.dto.Note;
import toandx.render.demoBackend.entity.NoteEntity;
import toandx.render.demoBackend.service.NoteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/note")
    public List<Map<String, Object>> getNote() {
        return noteService.getAllSummary();
    }

    @GetMapping("/noteId")
    public NoteEntity getNoteById(@RequestParam("id") Long id) {
        return noteService.getById(id);
    }

    @DeleteMapping("/note")
    public void clearNote() {
        noteService.clear();
    }

    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

}
