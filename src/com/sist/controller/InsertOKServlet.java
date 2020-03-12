package com.sist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.EmpDAO;
import com.sist.model.EmpDTO;


@WebServlet("/insertOK")
public class InsertOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public InsertOKServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 입력폼 창에서 넘어온 데이터들을 처리해주자.
		int empno = Integer.parseInt(request.getParameter("no"));
		String ename = request.getParameter("name");
		String job = request.getParameter("job");
		int mgr = Integer.parseInt(request.getParameter("mgrNo"));
		int sal = Integer.parseInt(request.getParameter("sal"));
		int comm = Integer.parseInt(request.getParameter("comm"));
		int deptno = Integer.parseInt(request.getParameter("deptNo")) ;
		
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(empno);
		dto.setEname(ename);
		dto.setJob(job);
		dto.setMgr(mgr);
		dto.setSal(sal);
		dto.setComm(comm);
		dto.setDeptno(deptno);
		
		EmpDAO dao = new EmpDAO();
		int res = dao.insert(dto);
		
		PrintWriter out = response.getWriter();
		if(res > 0) { // 레코드 추가 성공
			response.sendRedirect("select");
		} else {
			out.println("<script>");
			out.println("alert('레코드 추가 실패')");
			out.println("history.back()");	// 이전 페이지로 이동
			out.println("</script>");
		}
	}

}
