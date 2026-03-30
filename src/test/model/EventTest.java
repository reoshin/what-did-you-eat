package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import model.Event;

import java.util.Calendar;
import java.util.Date;

// Reference: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

public class EventTest {
    private Event event;
    private Date date;

    @BeforeEach
    public void runBefore() {
        event = new Event("Event occured");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Event occured", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void textToString() {
        assertEquals(date.toString() + "\n" + "Event occured", event.toString());
    }
}
