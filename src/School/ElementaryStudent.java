package School;

public class ElementaryStudent extends Student {
    public ElementaryStudent(String studentId,String name) {
        super(studentId, name, StudentType.ELEMENTARY);
    }

    @Override
    public String calculateGrade() {
        double avg = calculateAverage();
        if(avg >= 90) return "A";
        else if(avg >= 80) return "B";
        else if(avg >= 70) return "C";
        else return  "D";
    }

}
