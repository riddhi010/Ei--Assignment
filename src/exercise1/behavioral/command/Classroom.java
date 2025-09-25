package exercise1.behavioral.command;

import java.util.logging.Logger;

public class Classroom {
    private static final Logger logger = Logger.getLogger(Classroom.class.getName());
    private boolean lectureOngoing = false;

    public void startLecture() throws Exception {
        if (lectureOngoing) {
            throw new Exception("Lecture already started.");
        }
        lectureOngoing = true;
        System.out.println("\u001B[32mLecture started successfully.\u001B[0m");
        logger.info("Lecture started.");
    }

    public void endLecture() throws Exception {
        if (!lectureOngoing) {
            throw new Exception("No lecture is ongoing.");
        }
        lectureOngoing = false;
        System.out.println("\u001B[31mLecture ended.\u001B[0m");
        logger.info("Lecture ended.");
    }

    public void takeAttendance() throws Exception {
        if (!lectureOngoing) {
            throw new Exception("Cannot take attendance. Lecture not started.");
        }
        System.out.println("\u001B[34mAttendance recorded successfully.\u001B[0m");
        logger.info("Attendance taken.");
    }
}
