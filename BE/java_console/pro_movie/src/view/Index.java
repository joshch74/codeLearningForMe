package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Index {
	public Index() {
		String start="";		
		Scanner sc= new Scanner(System.in);
		int choice = 0;
		
		while(true) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 영화\n4. 크롤링\n5. 종료");
			choice=sc.nextInt();
			
			//컨트롤러
			if(choice==5) {
				System.out.println("이용해주셔서 감사합니다.");
				break;
			}
			switch(choice) {
			case 1:
				//회원가입
				new JoinView();
				break;
			case 2:
				//로그인
				new LoginView(0);
				break;
			case 3:
				new MovieView();
				break;
			case 4:
				System.out.println("크롤링을 다시 하시겠습니까?\n1. 예\t2. 아니요");
				while(true) {
					choice = sc.nextInt();
					if (choice ==1) {
						new CrawLing(start);
						break;
					}else if(choice==2) {
						break;
					}else {
						System.out.println("오류발생 다시 입력해주시기 바랍니다.");
					}
				}
				break;
			}
		}
	}
}
