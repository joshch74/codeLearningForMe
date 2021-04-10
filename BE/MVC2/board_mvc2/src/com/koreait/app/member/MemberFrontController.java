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
//	JVM�� �޸𸮿��� �ӹ��� �ִ� ��ü�� �ý����� ����ǰų� ��Ʈ��ũ�� ����� �� ������
//	�״�� ����� �� �ֵ��� ����ȭ �������� ����ȭ�� ����Ѵ�.
//	����ȭ�� ��ü�� byte���·� ��ȯ�Ǿ� ������, �ٽ� ����ϰ� ���� ������ ������ȭ�� ���ؼ�
//	��ü�� ��ȯ�Ѵ�. �̶� SUID(serialVersionUID)��� ���� �������� ���������� ����ؼ�
//	����ڰ� ���ϴ� ��ü�� �´��� �Ǵ��ϰ� �ȴ�.
//	���� ����Ǵ� Ŭ���� ��ü���� ������� �ʴ� ���� ����.
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
			//~~~~ ó��
			try {
				forward = new MemberJoinOkAction().execute(request,response);
			} catch (Exception e) {
				System.out.println("/member/MemberJoinOk ����");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('ȸ������ ����!')");
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
				System.out.println("/member/MemberLoginOk ����");
				System.out.println(e);
			}
		}else if(command.equals("/member/CheckId.me")) {
			try {
				forward = new CheckIdAction().execute(request,response);
			} catch (Exception e) {
				System.out.println("/member/CheckId ����");
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
		//execute()���� ���Ϲ��� forward�� ���� ���� �� �����ϱ�
		if(forward!=null) {
			if(forward.isRedirect()) {
				//redirect ���
				response.sendRedirect(forward.getPath());
			}else {
				//forward ���
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}

}




