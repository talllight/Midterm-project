import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subject {
    String name;
    String grade;
    int credit;
    String category; // 전공 or 교양

    Subject(String name, String grade, int credit, String category) {
        this.name = name;
        this.grade = grade;
        this.credit = credit;
        this.category = category;
    }

    double getGradePoint() {
        switch (grade.toUpperCase()) {
            case "A+": return 4.5;
            case "A":  return 4.0;
            case "B+": return 3.5;
            case "B":  return 3.0;
            case "C+": return 2.5;
            case "C":  return 2.0;
            case "D+": return 1.5;
            case "D":  return 1.0;
            case "F":  return 0.0;
            default: return 0.0;
        }
    }

    double getWeightedGradePoint() {
        return getGradePoint() * credit;
    }
}

public class Main {
    static List<Subject> subjects = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== 대학생을 위한 학점 계산기 ====");

        while (true) {
            System.out.print("과목명 입력 (종료하려면 '끝' 입력): ");
            String name = scanner.nextLine();
            if (name.equals("끝")) break;

            System.out.print("성적 (A+, A, B+, B 등): ");
            String grade = scanner.nextLine();

            System.out.print("학점 수: ");
            int credit = Integer.parseInt(scanner.nextLine());

            System.out.print("과목 구분 (전공/교양): ");
            String category = scanner.nextLine();

            subjects.add(new Subject(name, grade, credit, category));
        }

        double totalWeightedScore = 0;
        int totalCredits = 0;

        for (Subject subject : subjects) {
            totalWeightedScore += subject.getWeightedGradePoint();
            totalCredits += subject.credit;
        }

        double gpa = totalCredits > 0 ? totalWeightedScore / totalCredits : 0.0;

        System.out.printf("총 학점: %d\n", totalCredits);
        System.out.printf("총 GPA: %.2f\n", gpa);

        scanner.close();
    }
}
