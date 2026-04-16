package School;

public class MiddleStudent extends Student{
    public MiddleStudent(String studentId,String name) {
        super(studentId, name, StudentType.MiddleStudent);
    }

    @Override
    public String calculateGrade() {
        double avg = calculateAverage();
        if(avg >= 85) return "A";
        else if(avg >= 75) return "B";
        else if(avg >= 65) return "C";
        else return  "D";
    }
}
