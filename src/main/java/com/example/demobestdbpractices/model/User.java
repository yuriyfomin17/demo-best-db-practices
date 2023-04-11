package com.example.demobestdbpractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"reminders", "notes"})
@Entity
@Table(name = "persons")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, insertable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, name = "last_updated_at")
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.PRIVATE)
    private List<Reminder> reminders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.PRIVATE)
    private List<Note> notes = new ArrayList<>();

    public void addReminder(Reminder reminder){
        reminder.setUser(this);
        reminders.add(reminder);
    }
    public void removeReminder(Reminder reminder){
        reminder.setUser(null);
        reminders.remove(reminder);
    }

    public void addNote(Note note){
        note.setUser(this);
        notes.add(note);
    }

    public void removeNote(Note note){
        note.setUser(null);
        notes.remove(note);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User person)) return false;
        return id != null && id.equals(person.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}