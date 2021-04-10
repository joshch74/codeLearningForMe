package com.koreait.app.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberBean;
import com.koreait.app.member.dao.MemberDAO;

public class MemberJoinOkAction {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		//���Խ�Ű�� �ڵ�
		MemberDAO mdao = new MemberDAO();
		MemberBean member = new MemberBean();
		member.setUserid(request.getParameter("userid"));
		member.setUserpw(request.getParameter("userpw"));
		member.setUsergender(request.getParameter("usergender"));
		member.setUserphone(request.getParameter("userphone"));
		member.setUsername(request.getParameter("username"));
		member.setUseraddr(request.getParameter("useraddr"));
		member.setUserpostcode(request.getParameter("userpostcode"));
		member.setUseraddrdetail(request.getParameter("useraddrdetail"));
		member.setUseraddretc(request.getParameter("useraddretc"));
		if(mdao.join(member)) {
			Cookie cookie = new Cookie("joinid", member.getUserid());
			//��Ű�� ��� ������ ���� / ������ ��ο��� �� ����� �� �ֵ��� ����
			cookie.setPath("/");
			response.addCookie(cookie);
//			������ ���� ����
			forward.setRedirect(true);
			//Redirect : setPath�� ����� ��� ���� root ��ε� �������� ������
			//�ݵ�� request.getContextPath()�� �����ش�.
			//Forward : setPath�� ����ϸ� "/"�տ� root ��ΰ� �״�� ���޵Ǳ� ������
			//request.getContextPath()�� �����ָ� �ȵȴ�.
			forward.setPath(request.getContextPath()+"/app/member/loginview.jsp");
		}
		
		return forward;
		
		
	}
}




