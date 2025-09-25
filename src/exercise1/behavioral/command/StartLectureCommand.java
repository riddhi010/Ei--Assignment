package exercise1.behavioral.command;

public class StartLectureCommand implements Command {
    private final Classroom classroom;

    public StartLectureCommand(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public void execute() throws Exception {
        classroom.startLecture();
    }
}
