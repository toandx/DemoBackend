package toandx.render.demoBackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="note")
public class NoteEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_by")
    private Integer createdBy;

    public NoteEntity(String title, String content, Integer createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }
}
