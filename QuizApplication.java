import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctOption; 

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public void display() {
        System.out.println("\n" + questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == (correctOption + 1);
    }
}

public class QuizApplication {
    static Scanner scanner = new Scanner(System.in);
    static List<Question> questions = new ArrayList<>();
    static int score = 0;
    static final int TIME_LIMIT_SECONDS = 10;

    public static void main(String[] args) {
        loadQuestions();

        System.out.println("Welcome to the Quiz App ");
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to answer each question.");

        for (Question q : questions) {
            q.display();

            Timer timer = new Timer();
            AnswerTask task = new AnswerTask();
            timer.schedule(task, TIME_LIMIT_SECONDS * 1000);

            System.out.print("Your answer (1-4): ");
            String input = null;

            try {
                input = scanner.nextLine();
                timer.cancel(); // stop timer if answered in time
            } catch (Exception e) {
                System.out.println("Time's up!");
            }

            if (!task.isTimeUp) {
                try {
                    int answer = Integer.parseInt(input.trim());
                    if (q.isCorrect(answer)) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect!");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                }
            } else {
                System.out.println("Time's up! Moving to next question.");
            }
        }

        System.out.println("\nQuiz Over! Your Score: " + score + "/" + questions.size());
    }

    static void loadQuestions() {
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"},
                2));

        questions.add(new Question(
                "Which language runs in a web browser?",
                new String[]{"Java", "C", "Python", "JavaScript"},
                3));

        questions.add(new Question(
                "What is 10 * 2?",
                new String[]{"20", "15", "25", "10"},
                0));
    }

    // Timer task to track timeout
    static class AnswerTask extends TimerTask {
        boolean isTimeUp = false;

        public void run() {
            isTimeUp = true;
            System.out.println("\nTime's up!");
        }
    }
}
