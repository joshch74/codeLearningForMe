package com.koreait.app.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.ActionForward;
import com.koreait.app.member.CheckIdAction;
import com.koreait.app.member.MemberJoinOkAction;
import com.koreait.app.member.MemberLoginOkAction;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();//
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());//  /board/BoardReplyDeleteOk.bo
		ActionForward forward = null;
		System.out.println(command);
		if(command.equals("/board/BoardList.bo")) {
			try {
				forward = new BoardListAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardList 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setPath("/app/board/boardWrite.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/board/BoardWriteOk.bo")) {
			try {
				forward = new BoardWriteOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardWriteOk 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardView.bo")) {
			try {
				forward = new BoardViewAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardView 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/FileDownload.bo")) {
			try {
				forward = new FileDownloadAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/FileDownload 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardModify.bo")) {
			try {
				forward = new BoardModifyAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardModify 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardModifyOk.bo")) {
			try {
				forward = new BoardModifyOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardModifyOk 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardDelete.bo")) {
			try {
				forward = new BoardDeleteAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardDelete 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardReplyOk.bo")) {
			try {
				forward = new BoardReplyOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyOk 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardReplyModifyOk.bo")) {
			try {
				forward = new BoardReplyModifyOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyModifyOk 오류");
				System.out.println(e);
			}
		}else if(command.equals("/board/BoardReplyDeleteOk.bo")) {
			try {
				forward = new BoardReplyDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyDeleteOk 오류");
				System.out.println(e);
			}
		}else {
			forward = new ActionForward();
			forward.setPath("/app/error/404.jsp");
			forward.setRedirect(false);
		}
		System.out.println(forward);
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
