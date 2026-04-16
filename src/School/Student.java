package School;

public abstract class Student {
    protected String studentId;
    protected String name;
    protected StudentType type; //학생 유형 (enum 사용)
    protected int[] scores; // 국어,영어,수학

    public Student(String studentId, String name, StudentType type){
        if(studentId == null || studentId.isEmpty()) {
            System.out.println("학번은 비어 있을 수 없습니다");
        }
        this.studentId = studentId;
        this.name = name;
        this.type = type;
        this.scores = new int[3];
    }

    public void setScore(int index, int score) {
        //특정 과목 점수 설정
        this.scores[index] = score;

    }

    public double calculateAverage(){
        // 평균 점수 계산
        int sum = 0;
        for(int value : scores) sum += value;

        return sum / 3.0;
    }

    public abstract String calculateGrade();

    public String toFileString() {
        //파일 저장용 문자열 반환
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.name()).append(",").append(studentId).append(",").append(name);

        for(int score : scores) {
            sb.append(",").append(score);
        }

        return sb.toString();
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return "학번: " + studentId +
                ", 이름: " + name +
                ", 평균: " + calculateAverage() +
                ", 등급: " + calculateGrade();
    }
}
