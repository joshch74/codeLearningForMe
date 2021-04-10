package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;

public class PayView {
	public PayView() {
		System.out.println("\"결제하기\"입니다.");
		MovieDAO mdao = new MovieDAO();
	      UserDAO udao = new UserDAO();
	      Scanner sc = new Scanner(System.in);
	      System.out.println("\"결제하기\"입니다.");

	      while (true) {
	         System.out.println("결제 하시겠습니까?\n1. 네 2. 아니오");
	         int choice = sc.nextInt();

	         switch (choice) {
	         case 1:
	            // 결제하기
	            String userId = Session.get("session_id");
	            //--------장바구니 확인--------
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
					System.out.println("성인은"+price+"원 입니다.");
				}else {
					price = 5000;
					System.out.println("청소년은"+price+"원 입니다.");
				}		
	            // 영화 가격을 변수에 저장
	            // userdao에 결제하기 만들고 가져와서 변수를 빼주고 다시 db에 넣기
	        	 break;
	         case 2:
	             // 결제된 티켓 취소하기
	             new CancleView();
	             break;

	         }

	      }
	}
}
