package View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.PlayerController;
import Controller.QuizController;
import Controller.StatController;
import DAO.LoadDAO;
import DAO.PlayerDAO;
import DAO.StatDAO;
import DTO.ActionCount;
import DTO.PlayerDTO;
import DTO.QuizDTO;
import DTO.ResultStatus;
import DTO.StatDTO;

public class ProjectMain {
	static Scanner sc = new Scanner(System.in);
	static int choice = 0;
	String id = null;
	String pw = null;

	// [1] 메인 메소드
	public static void main(String[] args) throws SQLException {
		// menu -> start -> choice -> action

		// 어디서든지 접근 가능한 Controller 객체 만들기
		PlayerController control = new PlayerController();
		PlayerDTO playerDTO = new PlayerDTO();
		while (true) {
			System.out.println("[1]회원가입 [2]로그인하기 >> ");
			int menu = sc.nextInt();

			// (1)회원가입
			if (menu == 1) {
				System.out.println("====회원가입====");
				System.out.println("가입 id : ");
				String id = sc.next();
				System.out.println("가입 pw : ");
				String pw = sc.next();
				System.out.println("가입 name : ");
				String name = sc.next();

				// controller에 필요한 정보를 전달하자!
				int result = control.Con_join(menu, id, pw, name);

				if (result > 0) {
					System.out.println("회원가입성공");
				} else {
					System.out.println("실패");
				}

				// (2) 로그인하기
			} else if (menu == 2) {
				// System.out.println("====로그인하기====");
				// dao, dto로 구현하기
				while (true) {
					System.out.println("====로그인하기====");
					// dao, dto로 구현하기
					System.out.println("아이디: ");
					String id = sc.next();
					System.out.println("비밀번호: ");
					String pw = sc.next();

					playerDTO = control.Con_login(menu, id, pw);

					if (playerDTO.getId() != null) {
						// 로그인 성공 시 추가 작업
						break; // 로그인 성공했으므로 while 루프를 빠져나감
					} else {
						System.out.println("로그인 실패! 다시 시도해주세요.");
						System.out.println("[1]회원가입 [2]로그인하기 >> ");
						menu = sc.nextInt();
						if (menu == 1) {
							break; // 회원가입 선택 시 while 루프를 빠져나감
						}
					}
				}

				// 로그인 성공
//				while (true) {
				System.out.println("[1]게임 시작하기 [2]회원탈퇴하기 ");
				int start = sc.nextInt();
				System.out.println("===============ENDING===============");
				if (start == 1) {
					StatController statusControl = new StatController();
					StatDTO dto = statusControl.getStatus(playerDTO.getplayer_no());

					if (dto.calculateStatus() != ResultStatus.PROCESS) {
						System.out.println(dto.calculateStatus().getName());
						return;
					}
					ResultStatus resultStatus = ResultStatus.PROCESS;
					while (dto.getStage() <= 30 && resultStatus == ResultStatus.PROCESS) {
						resultStatus = showActionMenu(dto, playerDTO);
						statusControl.saveStatus(dto);
					}
					System.out.println(resultStatus.getName());

					// 회원 탈퇴하기
				} else if (start == 2) {
					System.out.println("회원 탈퇴하기");

					System.out.print("아이디: ");
					String idDele = sc.next();

					int deleid = control.Con_delete(idDele);

					if (deleid > 0) {
						System.out.println("회원탈퇴 성공");
					} else {
						System.out.println("회원탈퇴 실패");
					}
					break;

				} else {
					System.out.println("다시 번호를 입력해주세요");
					break;

				}
			}
			System.out.println("로그인 실패 관리자에게 문의하세요");

		}
	}

//	}

	// [2] showActionMenu 메소드 (캐릭터 키우기 관련)
	private static ResultStatus showActionMenu(StatDTO dto, PlayerDTO playerDTO) throws SQLException {
		ResultStatus resultStatus = ResultStatus.PROCESS;

		int week = dto.getStage();
		ActionCount actionCount = new ActionCount();

		System.out.println(week + "주차가 새로 시작되었습니다.\n");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		showStatus(playerDTO.getnickname(), dto.getPlayer_no());
		System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.println("[1]등원 [2]자체휴강");
		choice = sc.nextInt();
		// 등원
		if (choice == 1) {
			System.out.println("===================================================");
			System.out.println("\r\n" + "⠀⠀⠀⠀⠀⠀⢀⢳⡰⠔⠝⡒⢑⠙⠊⠓⢑⠒⠲⠤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⢀⠀⠀⢀⠤⢊⠊⡐⢀⠁⡂⠐⡀⠂⡁⠌⠠⠀⠅⠠⠠⠈⠓⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠁⡭⠫⠁⠡⠐⢀⠐⡀⠂⠐⠠⠐⠀⠄⠂⠄⠁⠂⡁⠄⠡⢨⢠⠘⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠊⠂⠂⡁⠂⡈⠄⠐⠀⠌⢀⠡⠀⠡⢀⠡⠀⡁⢁⠀⠂⢂⠈⠚⡀⠂⠱⣂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⢀⠁⢂⠀⠂⠄⠂⢁⠈⠄⠂⠠⠈⠠⢀⠐⣰⠚⢤⠂⠁⠄⠈⠄⠐⢈⠠⠱⡂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠠⠐⢀⠈⠄⢂⢈⠠⠐⠀⠌⠠⠈⠐⡀⠄⢳⠀⣺⠀⠡⢀⠡⡈⢈⠠⣢⠠⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠠⠈⠠⠐⠀⠵⢜⠀⠄⢁⠐⠠⢥⣂⣀⣂⣨⢻⠢⡬⠴⠔⢚⠈⡀⠄⠩⢀⢱⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠐⢈⠠⠈⠄⠂⠄⠂⡈⠠⢀⠡⠀⠄⠁⢌⠀⠄⠠⠀⣂⢈⠠⠀⠄⠂⠁⠄⢸⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠐⡀⢐⠐⢀⠁⡐⠀⢧⠣⢀⠐⠠⠈⡈⠓⠠⢈⢄⠁⡙⠀⠄⠂⠠⠁⠌⠐⢨⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠐⢀⠢⣊⠆⠠⠐⠈⡀⠂⠄⠂⢁⠐⠀⠌⢊⠋⡈⠓⠠⢈⠠⠈⠄⠁⠄⡁⢪⠂⠀⠀⠀⠀⠀⠀⡀⣀⣀⣀⣀⣀⣠⡠⡤⢤⡀⠀⠀⠀\r\n"
					+ "⢈⠠⠀⠌⢈⠀⢂⠁⡀⢂⠐⢈⠀⠄⠡⠈⠠⠠⢀⠡⠈⠠⠐⠈⡀⠡⠐⠠⡝⠀⢰⢒⢓⠍⡫⠩⡩⠊⢔⠡⢂⠅⡢⠨⡂⢵⠁⠀⠀⠀\r\n"
					+ "⠠⠐⢀⠡⠀⡈⠠⢀⠐⢠⠐⢀⠂⠁⠄⡁⠂⠂⠄⠐⡈⠠⠈⠄⠂⠐⡈⢸⠂⠀⡏⡢⢂⠕⡐⢅⠢⡑⡡⢌⠢⢑⠨⡂⠪⡝⡀⣀⡀⣀\r\n"
					+ "⠠⠈⠠⠐⢀⠐⡀⠂⠐⠈⠹⢔⡄⡡⣐⢄⣌⡔⡬⡔⡴⢔⠕⠦⠣⠳⢒⢓⠒⡯⡃⡢⢑⢐⠌⡂⢕⣎⢯⠢⢡⠑⢌⠢⡹⠅⠍⢂⠩⢐\r\n"
					+ "⠒⢎⠺⢘⠚⡂⡓⢥⢌⡈⡠⡠⡽⣰⢈⢐⠠⠂⡂⡂⢢⣢⡪⡬⠮⠮⡲⢲⢱⡝⡐⢌⠢⡑⠌⡌⢌⠪⢨⠨⡂⠕⡡⢊⡮⠑⠨⢐⠨⠠\r\n"
					+ "⢌⢐⠨⢐⠨⢀⠂⠅⡹⣜⣑⢍⡺⡼⡂⡐⠨⢐⠐⡠⢁⠪⠲⡬⣌⠪⡐⢅⡺⡂⡪⠐⢅⢊⠌⡢⢑⢌⠢⡑⢌⠪⡐⡵⠡⡁⠅⡂⢂⠅\r\n"
					+ "⠠⠂⢌⢐⢈⢐⠨⢐⠠⢂⠡⢉⠊⣕⠅⡂⠅⡂⠌⡐⠠⠨⢐⠨⢈⠳⢪⢢⢏⣔⢔⡥⣕⢤⢵⠬⡲⡢⠗⡚⠎⡋⠪⡈⡂⠔⢐⠠⠡⠨\r\n"
					+ "⡈⠌⡐⠠⢂⠂⢌⢐⠨⠐⠨⢐⠐⡘⠲⠲⠱⡊⠏⠪⢓⠵⠲⠜⠖⡜⠎⡂⠅⡂⠅⡂⡐⢐⠐⡈⠄⡂⢂⠂⠅⢂⠡⢐⢀⠊⠄⡂⠅⡊\r\n"
					+ "⠠⢁⠂⠅⡂⠌⡐⠠⠂⠅⡡⠂⠌⠄⠅⠅⡁⡂⠌⠌⠄⠌⠌⡈⡂⢂⢁⢂⠂⡂⠅⡐⠠⢁⠂⡂⠡⢐⠠⢁⢊⠐⡈⠄⡂⠌⡐⠠⢁⠔\r\n"
					+ "⠨⠐⠨⢐⠠⢁⠂⠅⠅⡁⡂⠅⡁⡊⠨⢐⢐⠠⢑⠨⢈⢐⢁⠂⠔⡐⢐⠐⠨⠐⡈⠄⡑⠠⢁⠂⠅⡂⠌⡐⡐⠨⢀⠅⢂⢁⠂⠅⡂⠌\r\n"
					+ "⢅⠡⢁⠢⠨⢐⠨⢈⠔⢐⠠⢁⠂⠌⠌⡐⠠⠨⢐⠐⡐⠠⠂⠌⡂⢂⠡⠨⠨⠐⠄⠅⡂⡑⠠⢁⠅⢂⢁⢂⠂⠅⡂⠌⡐⡀⡊⡐⠠⡁\r\n" + "");
			resultStatus = dto.attend();
			if (resultStatus != ResultStatus.PROCESS) {
				return resultStatus;
			}
			while (dto.getAction() > 0) {
				// 행동력을 다 소모하면 while문 break하고 다음주차로 넘어간다.
				System.out.println("=================================================");
				System.out.println("==========[1]문제풀기 [2]자습 [3]조퇴 [4]휴식==========");
				System.out.println("=================================================");
				int behavior = sc.nextInt();

				if (behavior == 1) {
					if (!actionCount.solving()) {
						System.out.println("문제풀기 횟수 초과!!!!");
						continue;
					}

					System.out.println("문제풀기");
					QuizController quizControl = new QuizController();
					ArrayList<QuizDTO> quizList = quizControl.getQuiz("normal");
					int listSize = quizList.size();

					Random ran = new Random();
					int randomNum = ran.nextInt(1, listSize + 1);

					String quizQuestion = quizList.get(randomNum).getQuestion();
					String quizAnswer = quizList.get(randomNum).getAnswer();
					System.out.println(quizQuestion);
					System.out.print("답을 입력해주세요 >> ");
					String playerAnswer = sc.next();

					if (playerAnswer.equals(quizAnswer)) {
						System.out.println("정답입니다~");
						resultStatus = dto.nomalPass();
						if (resultStatus != ResultStatus.PROCESS) {
							return resultStatus;
						}
					} else {
						System.out.println("틀렸습니다!!!");
						resultStatus = dto.fail();
						if (resultStatus != ResultStatus.PROCESS) {
							return resultStatus;
						}
					}
					continue;

				} else if (behavior == 2) {
					if (!actionCount.study()) {
						System.out.println("자습 횟수 초과!!!!");
						continue;
					}

					System.out.println("자습중...");
					resultStatus = dto.study();
					if (resultStatus != ResultStatus.PROCESS) {
						return resultStatus;
					}
					continue;

				} else if (behavior == 3) {
					if (!actionCount.leave()) {
						System.out.println("조퇴했잖아!!!!");
						continue;
					}
					System.out.println("조퇴는 늘 새로워! 짜릿해~ ♬");
					resultStatus = dto.leave();
					if (resultStatus != ResultStatus.PROCESS) {
						return resultStatus;
					}
					continue;
				} else if (behavior == 4) {
					if (!actionCount.rest()) {
						System.out.println("그만쉬어!!!");
						continue;
					}
					System.out.println("휴식중...");
					resultStatus = dto.rest();
					if (resultStatus != ResultStatus.PROCESS) {
						return resultStatus;
					}
					continue;
				}
			}
			// 자체 휴강
		} else if (choice == 2) {
			if (!actionCount.absence()) {
				System.out.println("니 오늘 학원안갔잖아!!!");
			}
			System.out.println("자체휴강");
			resultStatus = dto.absence();
			if (resultStatus != ResultStatus.PROCESS) {
				return resultStatus;
			}
		}

		dto.setStage(++week);
		dto.setAction(20);
		return resultStatus;

	}

//	// [4] save game 메서드화
//		public static void saveGame(String id, String pw, String nickname, int health, int action, int intelligence, int stress, int stage, int player_no) throws SQLException {
//
//		    // PlayerDTO 및 StatDTO 객체 생성
//		    PlayerDTO player = new PlayerDTO(id, pw, nickname);
//		    StatDTO stat = new StatDTO(health, action, intelligence, stress, stage);
//
//		    StatController stc = new StatController();
//		    stc.getStatus(player_no);
//		    
//		    
//		    // PlayerDAO 및 StatDAO 객체 생성
//		    PlayerDAO playerDAO = new PlayerDAO();
//		    StatDAO statDAO = new StatDAO();
//
//		    // 데이터 저장
//		    int result = playerDAO.join(player);
//		    result = statDAO.join(stat);
//
//		    // 예외 처리 및 로그 출력
//		    if (result > 0) {
//		        System.out.println("저장 성공!");
//		    } else {
//		        System.out.println("저장 실패!");
//		    }
//		}

	// [5] load game 메서드
	public static void showStatus(String nickname, int player_no) throws SQLException {

		StatDTO stat = new StatDTO();
		StatController stc = new StatController();

		stat = stc.getStatus(player_no);

		if (player_no != 0) {
			System.out.println(nickname + "님의 능력치");
			System.out.println(stat);
		} else {
			System.out.println("데이터 불러오기 실패!");
		}
	}
}
