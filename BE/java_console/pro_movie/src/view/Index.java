package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Index {
	public Index() {
		String start="";		
		Scanner sc= new Scanner(System.in);
		int choice = 0;
		
		while(true) {
			System.out.println("1. ȸ������\n2. �α���\n3. ��ȭ\n4. ũ�Ѹ�\n5. ����");
			choice=sc.nextInt();
			
			//��Ʈ�ѷ�
			if(choice==5) {
				System.out.println("�̿����ּż� �����մϴ�.");
				break;
			}
			switch(choice) {
			case 1:
				//ȸ������
				new JoinView();
				break;
			case 2:
				//�α���
				new LoginView(0);
				break;
			case 3:
				new MovieView();
				break;
			case 4:
				System.out.println("ũ�Ѹ��� �ٽ� �Ͻðڽ��ϱ�?\n1. ��\t2. �ƴϿ�");
				while(true) {
					choice = sc.nextInt();
					if (choice ==1) {
						new CrawLing(start);
						break;
					}else if(choice==2) {
						break;
					}else {
						System.out.println("�����߻� �ٽ� �Է����ֽñ� �ٶ��ϴ�.");
					}
				}
				break;
			}
		}
	}
}
