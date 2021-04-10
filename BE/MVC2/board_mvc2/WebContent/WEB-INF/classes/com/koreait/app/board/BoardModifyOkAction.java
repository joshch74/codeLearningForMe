package com.koreait.app.board;

import java.io.File;
import java.util.List;

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

public class BoardModifyOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BoardBean board = new BoardBean();
		BoardDAO bdao = new BoardDAO();
		FileDAO fdao = new FileDAO();
		ActionForward forward = new ActionForward();
		String saveFolder = request.getServletContext().getRealPath("app/files");
		int size = 1024 * 1024 * 5;// 5MB
		MultipartRequest multi = new MultipartRequest(request, saveFolder, size, "UTF-8",
				new DefaultFileRenamePolicy());

		int boardnum = Integer.parseInt(multi.getParameter("boardnum"));
		board.setBoardnum(boardnum);
		board.setBoardtitle(multi.getParameter("boardtitle"));
		board.setBoardcontents(multi.getParameter("boardcontents"));
		board.setUserid(multi.getParameter("userid"));
		boolean bcheck = bdao.modifyBoard(board);
		
		//방금 첨부를 통해 올린 파일의 시스템 이름 배열
		String[] filename = { multi.getFilesystemName("file1"), multi.getFilesystemName("file2") };
		//방금 첨부를 통해 올린 파일의 원래 이름 배열
		String[] realname = { multi.getOriginalFileName("file1"), multi.getOriginalFileName("file2") };
		//기존에 게시글에 올라와 있던 파일들의 리스트
		List<FileBean> files = fdao.getDetail(boardnum);
		//올린 파일들의 이름 배열
		String[] filenames = multi.getParameterValues("filename");
		
		//올린 파일들의 개수를 파악하기 위한 로직
		int cnt=0;
		for (int i = 0; i < filenames.length; i++) {
			if(filenames[i] != null && !filenames[i].equals("")) {
				cnt++;
			}
		}
		
		System.out.println("cnt : "+cnt);
		//올린 파일의 개수만큼 반복 하면서
		for (int i = 0; i < cnt; i++) {
			//첨부를 통해 올린 파일 이름이 null 로 날라왔다면 첨부하기를 누르지 않았다는 뜻
			if (filename[i] == null) {
				continue;
			}
			//기존에 등록된 파일의 개수보다 cnt가 더 크다는 말은 수정을 통해 새로운 파일을 등록했다는 뜻이다
			//따라서 files.size()가 cnt보다 크거나 같다면, 수정을 통해서 파일을 새로 등록하지 않았다는 뜻이다.
			if (files.size()>=cnt) {
				//기존에 존재하던 파일의 이름 받아서
				File f = new File(saveFolder, files.get(i).getFilename());
				//파일 객체 생성 후 존재한다면
				if (f.exists()) {
					//삭제
					f.delete();
				}
				//시스템상 이름으로 실제 DB에서도 정보를 지워준다.
				fdao.deleteFileByName(files.get(i).getFilename());
			}
			//DB에 새로 올린 파일을 등록하는 과정
			FileBean newfile = new FileBean();
			newfile.setBoardnum(boardnum);
			newfile.setFilename(filename[i]);
			newfile.setRealname(realname[i]);
			fdao.insertFiles(newfile);
		}
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardView.bo?num=" + boardnum);
		return forward;
	}
}
