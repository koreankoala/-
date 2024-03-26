package DTO;

// 문제풀이용 클래스
public class QuizDTO {
	// QuizDTO 필드
	private int quiz_no;
	private String quiz_type;
	private String question;
	private String answer;
	
	// QuizDTO 생성자
	private QuizDTO(int quiz_no, String quiz_type, String question, String answer) {
		this.quiz_no = quiz_no;
		this.quiz_type = quiz_type;
		this.question = question;
		this.answer = answer;
	}
	public static QuizDTO getQuiz (int quiz_no, String quiz_type, String question, String answer) {
		return new QuizDTO(quiz_no, quiz_type, question, answer);
	}
	
	//QuizDTO getter setter
	public int getQuiz_no() {
		return quiz_no;
	}
	public void setQuiz_no(int quiz_no) {
		this.quiz_no = quiz_no;
	}
	public String getQuiz_type() {
		return quiz_type;
	}
	public void setQuiz_type(String quiz_type) {
		this.quiz_type = quiz_type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
