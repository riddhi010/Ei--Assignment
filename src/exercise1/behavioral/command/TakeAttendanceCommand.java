package exercise1.behavioral.command;

public class TakeAttendanceCommand implements Command {
    private final Classroom classroom;

    public TakeAttendanceCommand(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public void execute() throws Exception {
        classroom.takeAttendance();
    }
}
