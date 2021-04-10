package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class MainView {
	public MainView() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1. 마이페이지\n2. 영화\n3. 쿠폰 이벤트\n4. 로그아웃");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				new UserInfoView();
				break;
			case 2:
				new MovieView();
				break;
			case 3:
				
				break;
			case 4:
				new Index();
				break;
			}
			
		}
	}
}
