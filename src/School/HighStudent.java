    package School;

    public class HighStudent extends Student{
        public HighStudent(String studentId,String name) {
            super(studentId, name, StudentType.HighStudent);
        }

        @Override
        public String calculateGrade() {
            double avg = calculateAverage();
            if(avg >= 90) return "A";
            else if(avg >= 80) return "B";
            else if(avg >= 70) return "C";
            else if(avg >= 60) return "D";
            else return "F";
        }
    }
