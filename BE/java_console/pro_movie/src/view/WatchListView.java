package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class WatchListView {
	public WatchListView() {
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("1. ���ϱ�\n2. �ڷΰ���");
			int choice = sc.nextInt();
			
			if (choice==1) {
				if(Session.get("session_id")==null) {
					new LoginView(-1);
				}else {
					System.out.println("���ϱ⿡ �߰��� ��ȭ������ �Է����ּ���.");
					sc.nextLine();
					String title = sc.nextLine();
					mdao.create(title);	
					//**���ϱ� �Ϸ�! �� �Ŀ� �ٸ� ������ ���� ������?
				}
				break;
			}else if (choice==2) {
				//MovieView�� ���ư���
				break;
			}else {
				System.out.println("�ٽ� �Է��� �ּ���.\n");
			}
		}
	}
}