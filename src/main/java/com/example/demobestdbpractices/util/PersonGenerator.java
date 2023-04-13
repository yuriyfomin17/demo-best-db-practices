package com.example.demobestdbpractices.util;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.devskiller.jfairy.producer.text.TextProducer;
import com.example.demobestdbpractices.model.Note;
import com.example.demobestdbpractices.model.Profile;
import com.example.demobestdbpractices.model.Reminder;
import com.example.demobestdbpractices.model.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class PersonGenerator {
    private static final Fairy fairy = Fairy.create();
    private static final TextProducer textProducer = fairy.textProducer();

    public User generateUser(){
        Person person = fairy.person();
        var user = new User();
        user.setFirstName(person.getFirstName());
        user.setLastName(person.getLastName());
        user.setEmail(person.getEmail());
        var notesList = generateNotes(5000);
        var remindersList = generateReminders(5000);
        notesList.forEach(user::addNote);
        remindersList.forEach(user::addReminder);

        return user;
    }

    public Profile generateProfile(){
        var profile = new Profile();
        profile.setPictureUrl(textProducer.randomString(5));
        profile.setActive(true);
        return profile;
    }
    public Reminder generateReminder(){
        var reminder = new Reminder();
        reminder.setTodo(textProducer.sentence(2));
        reminder.setDeadline(LocalDateTime.now().plusDays(5));
        return reminder;
    }
    public Note generateNote(){
        var note = new Note();
        note.setBody(textProducer.sentence(2));
        return note;
    }

    public List<Note> generateNotes(int size){
        return Stream.generate(PersonGenerator::generateNote).limit(size).toList();
    }
    public List<Reminder> generateReminders(int size){
        return Stream.generate(PersonGenerator::generateReminder).limit(size).toList();
    }
}
