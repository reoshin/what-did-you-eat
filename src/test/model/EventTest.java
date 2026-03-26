package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import model.Event;

import java.util.Calendar;
import java.util.Date;

// Reference: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

public class EventTest {
    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("Event occured");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Event occured", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void textToString() {
        assertEquals(d.toString() + "\n" + "Event occured", e.toString());
    }
}
