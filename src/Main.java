import School.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        handleLoad();

        while (true) {
            printMenu();
            int choice = readInt(sc);
            switch (choice) {
                case 1 :
                    handleAddStudent(sc);
                    break;

                case 2 :
                    handleInputScore(sc);
                    break;

                case 3 :
                    Student student = findStudentById(sc);

                    if(student != null){
                        System.out.println(student);
                    }
                    break;
                case 4 :
                    handleList();
                    break;

                case 5:
                    handleSave();
                    break;

                case 6:
                    handleLoad();
                    break;

                case 7 :
                    System.out.println("프로그램 종료");
                    return;
            }
        }
    }
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력하세요\n");
            }
        }
    }
    private static void printMenu() {
        //메뉴 출력
        System.out.println("==== 학생 성적 관리 시스템 ====");
        System.out.println("1. 학생 등록");
        System.out.println("2. 성적 입력");
        System.out.println("3. 평균 및 등급 조회");
        System.out.println("4. 학생 목록 출력");
        System.out.println("5. 파일 저장");
        System.out.println("6. 파일 불러오기");
        System.out.println("7. 종료");
        System.out.println("선택 >>");
    }

    private static void handleAddStudent(Scanner sc) {
        //힉생 유형(HIGH, MIDDLE, ELEMENTARY) 선택 후 객체 생성
            System.out.println("학생 유형 선택 (1:고등, 2:중등, 3:초등)");
            int type = readInt(sc);

            System.out.print("학번 입력: ");
            String id = sc.nextLine();

            System.out.print("이름 입력: ");
            String name = sc.nextLine();

            Student student = null;

            if(type == 1){
                student = new HighStudent(id, name);
            }else if(type == 2){
                student = new MiddleStudent(id, name);
            }else if(type == 3){
                student = new ElementaryStudent(id, name);
            }else{
                System.out.println("잘못된 선택");
                return;
            }

            students.add(student);
            System.out.println("학생 등록 완료!");
        }

    private static Student findStudentById(Scanner sc) {
        //선형 탐색
        System.out.print("학번 입력 : ");
        String id = sc.nextLine();

        for(Student s : students){
            if(s.getStudentId().equals(id)){
                return s;
            }
        }

        System.out.println("학생을 찾을 수 없음");
        return null;
    }

    private static void handleInputScore(Scanner sc) {
            //평균 및 등급 조희
            Student student = findStudentById(sc);

            if(student == null){
                return;
            }

            for(int i = 0; i < 3; i++){
                System.out.print((i+1) + "번째 과목 점수 입력: ");
                int score = readInt(sc);
                student.setScore(i, score);
            }

            System.out.println("성적 입력 완료!");
        }

    private static void handleList() {
        //전체 학생 출력 
        if(students.isEmpty()){
            System.out.println("등록된 학생 없음");
            return;
        }

        for(Student s : students){
            System.out.println(s);
        }
    }
    private static void handleSave() {
        //파일 저장
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("student.txt"))) {
            for(Student student : students) {
                bw.write(student.toFileString());
                bw.newLine();
            }
            System.out.println("파일 저장 완료 (총 " + students.size() + "명");
        }catch (IOException e) {
            System.out.println("파일 저장 오류 : " + e.getMessage());
        }
    }

    private static void handleLoad() {
        //파일 불러오기
        File file = new File("student.txt");
        if(!file.exists()) {
            System.out.println("파일이 존재하지 않습니다");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            students.clear();
            String line;
            int count = 0;

            while((line = br.readLine()) != null) {
                String[]parts = line.split(",");
                if(parts.length < 3) continue;

                StudentType type = StudentType.valueOf(parts[0]);
                String studentId = parts[1];
                String name = parts[2];

                Student student = null;

                switch (type) {
                    case HIGH :
                        student = new HighStudent(studentId, name);
                        break;
                    case MIDDLE :
                        student = new MiddleStudent(studentId, name);
                        break;
                    case ELEMENTARY :
                        student = new ElementaryStudent(studentId,name);
                        break;
                }

                if(student != null) {
                    for(int i = 0; i < 3 && i + 3 < parts.length; i++) {
                        student.setScore(i, Integer.parseInt(parts[3+i]));
                    }
                    students.add(student);
                    count++;
                }
            }

            System.out.println("파일 불러오기 완료 (총" + count + "명");
        }
        catch (IOException e) {
            System.out.println("파일 읽기 오류 " + e.getMessage());
        }catch (NumberFormatException e) {
            System.out.println("파일 형식 오류 :" +e.getMessage());
        }catch (IllegalArgumentException e ) {
            System.out.println("잘못된 형식 유형 " +e.getMessage());
        }
    }
}
