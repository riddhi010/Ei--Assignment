package exercise1.behavioral.command;

public class EndLectureCommand implements Command {
    private final Classroom classroom;

    public EndLectureCommand(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public void execute() throws Exception {
        classroom.endLecture();
    }
}
