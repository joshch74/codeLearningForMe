package com.koreait.app.board;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardBean;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FileBean;
import com.koreait.app.board.dao.FileDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		//파일이 저장될 경로
		String saveFolder = request.getServletContext().getRealPath("app/files");
		int size = 1024*1024*5;//5MB
		System.out.println(saveFolder);
		BoardDAO bdao = new BoardDAO();
		FileDAO fdao = new FileDAO();
		//게시글 내용을 BOARD 테이블에 저장을 잘 했는지 체크할 flag
		boolean bcheck=false;
		
		//파일의 정보(게시글번호, 실제이름, 시스템상이름) 저장을 잘 했는지 체크할 flag
		boolean fcheck1=false;
		boolean fcheck2=false;
		
		//게시글 내용들 DB에 넣기   99
		BoardBean board = new BoardBean();
		MultipartRequest multi = new MultipartRequest(request, saveFolder,size,"UTF-8",new DefaultFileRenamePolicy());
		board.setBoardtitle(multi.getParameter("boardtitle"));
		board.setBoardcontents(multi.getParameter("boardcontents"));
		board.setUserid(multi.getParameter("userid"));
		bcheck = bdao.insertBoard(board);
		
		//파일 내용 저장하기
		String filename1 = multi.getFilesystemName("file1");
		if(filename1 == null) { fcheck1 = true; }
		String orgname1 = multi.getOriginalFileName("file1");
		String filename2 = multi.getFilesystemName("file2");
		if(filename2 == null) { fcheck2 = true; }
		String orgname2 = multi.getOriginalFileName("file2");
		int boardnum = bdao.getBoardSeq();
		if(filename1 !=null && filename1 !="") {
			FileBean file = new FileBean();
			file.setBoardnum(boardnum);
			file.setFilename(filename1);
			file.setRealname(orgname1);
			fcheck1=fdao.insertFiles(file);
		}
		if(filename2 !=null && filename2 !="") {
			FileBean file = new FileBean();
			file.setBoardnum(boardnum);
			file.setFilename(filename2);
			file.setRealname(orgname2);
			fcheck2=fdao.insertFiles(file);
		}
		if(!bcheck || !fcheck1 || !fcheck2) {
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/board/BoardList.bo?result=false");
			return forward;
		}
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/board/BoardList.bo");
		return forward;
	}
}











