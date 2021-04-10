package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;

public class PayView {
	public PayView() {
		System.out.println("\"�����ϱ�\"�Դϴ�.");
		MovieDAO mdao = new MovieDAO();
	      UserDAO udao = new UserDAO();
	      Scanner sc = new Scanner(System.in);
	      System.out.println("\"�����ϱ�\"�Դϴ�.");

	      while (true) {
	         System.out.println("���� �Ͻðڽ��ϱ�?\n1. �� 2. �ƴϿ�");
	         int choice = sc.nextInt();

	         switch (choice) {
	         case 1:
	            // �����ϱ�
	            String userId = Session.get("session_id");
	            //--------��ٱ��� Ȯ��--------
	            mdao.payCheck();
	            
	            String ticketAge = udao.findAge(userId);
	            String userAge = "";
	            int ticketNum = 0;
	            mdao.bringTicketNum();
	            int price = 0;
	            
	            for (int i = 0; i < 4; i++) {
					userAge+=ticketAge.charAt(i);
				}
	            int birthYear = Integer.parseInt(userAge);
	            if (birthYear <=2002) {
					price = 10000;
					System.out.println("������"+price+"�� �Դϴ�.");
				}else {
					price = 5000;
					System.out.println("û�ҳ���"+price+"�� �Դϴ�.");
				}		
	            // ��ȭ ������ ������ ����
	            // userdao�� �����ϱ� ����� �����ͼ� ������ ���ְ� �ٽ� db�� �ֱ�
	        	 break;
	         case 2:
	             // ������ Ƽ�� ����ϱ�
	             new CancleView();
	             break;

	         }

	      }
	}
}
