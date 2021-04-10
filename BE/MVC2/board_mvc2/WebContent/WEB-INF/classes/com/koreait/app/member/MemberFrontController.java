package com.koreait.app.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.ActionForward;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
//	JVM의 메모리에만 머물러 있던 객체를 시스템이 종료되거나 네트워크로 통신을 할 때에도
//	그대로 사용할 수 있도록 영구화 목적으로 직렬화를 사용한다.
//	직렬화된 객체는 byte형태로 변환되어 있으며, 다시 사용하고 싶을 때에는 역직렬화를 통해서
//	객체로 변환한다. 이때 SUID(serialVersionUID)라는 값을 고정시켜 구분점으로 사용해서
//	사용자가 원하는 객체가 맞는지 판단하게 된다.
//	자주 변경되는 클래스 객체에는 사용하지 않는 것이 좋다.
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		if(command.equals("/member/MemberJoinOk.me")) {
			//~~~~ 처리
			try {
				forward = new MemberJoinOkAction().execute(request,response);
			} catch (Exception e) {
				System.out.println("/member/MemberJoinOk 오류");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입 실패!')");
				out.println("</script>");
				out.close();
			}
		}else if(command.equals("/member/MemberLogin.me")){
			forward = new ActionForward();
			forward.setPath("/app/member/loginview.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/member/MemberLoginOk.me")) {
			try {
				forward = new MemberLoginOkAction().execute(request,response);
			} catch (Exception e) {
				System.out.println("/member/MemberLoginOk 오류");
				System.out.println(e);
			}
		}else if(command.equals("/member/CheckId.me")) {
			try {
				forward = new CheckIdAction().execute(request,response);
			} catch (Exception e) {
				System.out.println("/member/CheckId 오류");
			}
		}else if(command.equals("/member/MemberLogout.me")){
			request.getSession().invalidate();
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/");
		}else {
			forward = new ActionForward();
			forward.setPath("/app/error/404.jsp");
			forward.setRedirect(false);
		}
		//execute()에서 리턴받은 forward로 응답 설정 후 응답하기
		if(forward!=null) {
			if(forward.isRedirect()) {
				//redirect 방식
				response.sendRedirect(forward.getPath());
			}else {
				//forward 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}

}







