package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class MainView {
	public MainView() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1. ����������\n2. ��ȭ\n3. ���� �̺�Ʈ\n4. �α׾ƿ�");
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
