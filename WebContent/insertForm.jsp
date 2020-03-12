<%@page import="com.sist.model.DeptDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 20/03/11 --%>
<%
	ArrayList<DeptDTO> dept = (ArrayList<DeptDTO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="orange" />
			<h3>EMP 테이블 레코드 추가 폼</h3>
		<hr width="50%" color="orange" />
		<br /><br />
		
		<form method="post" action="insertOK">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>사원번호</th>
					<td> <input type="text" name="no" /></td>
				</tr>
				<tr>
					<th>사원이름</th>
					<td> <input type="text" name="name" /></td>
				</tr>
				<tr>
					<th>담당업무</th>
					<td> <input type="text" name="job" /></td>
				</tr>
				<tr>
					<th>관리자 No</th>
					<td> <input type="text" name="mgrNo" /></td>
				</tr>
				<tr>
					<th>급여</th>
					<td> <input type="text" name="sal" /></td>
				</tr>
				<tr>
					<th>보너스</th>
					<td> <input type="text" name="comm" /></td>
				</tr>
				<tr>
					<th>부서번호</th>
					<td> 
						<select name="deptNo">
						<%
							if(dept.size() == 0){ %>
							
								<option value="">:::부서번호 없음:::</option>	
								
						<%	} else {	//데이터가 있는 경우
							
								for(int i = 0; i < dept.size(); i++){
									DeptDTO dto = dept.get(i); %>
									<option value="<%= dto.getDeptno()%>">
									<%= dto.getDname() %>[<%= dto.getDeptno() %>]</option>
									
							<%	}
							
							}%>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="추가" /> &nbsp;&nbsp;
						<input type="reset" value="취소"	/>					
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>