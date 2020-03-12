package com.sist.model;

import java.sql.*;
import java.util.*;

// JDBC방식은 데이터에 직접 접근하는 방식이어서 부하가 많이 걸리게 됨.
// 나중에는 JDCP(Java Database Connect Pool) 방식을 사용.


// DAO(Data Access Object) : 데이터 접근 객체 - DB에 접속(연동)하는 객체
public class EmpDAO {
	
	Connection con = null;				// DB연결 객체
	PreparedStatement pstmt = null;		// DB에 SQL문을 전송하는 객체
	ResultSet rs = null;				// SQL문을 실행 후 결과 값을 가진 객체
	
	public EmpDAO() { // 나중에는 static으로 상주시킨다(싱글턴). memory관점으로..
	// 기본 생성자
		String driver="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "genie";
		String password = "1234";
		
		try {
			// 1. 드라이버 로딩
			Class.forName(driver);
			
			// 2. DB와 연결
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}// 생성자 end
	
	// EMP 테이블의 전체레코드를 조회하는 메솓,
	public List<EmpDTO> select(){
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		
		try {
			String sql = "select * from emp order by empno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();	// 실제로 DB에서 sql문을 실행하는 메소드
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				list.add(dto);
			}
			// open 객체 닫기
			rs.close(); pstmt.close(); con.close();
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	//select() 메소드 end
	
	// DEPT 테이블의 전체 목록을 조회하는 메소드
	public ArrayList<DeptDTO> dept() {
		ArrayList<DeptDTO> list = new ArrayList<DeptDTO>();
		
		try {
			String sql = "select * from dept order by deptno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				list.add(dto);
				
				
			}
			//open 객체 닫기
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	// dept() 메소드 end
	
	// EMP 테이블에 레코드를 추가하는 메소드
	public int insert(EmpDTO dto) {
		int result = 0;
		
		try {
			String sql = "insert into emp values(?,?,?,?,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			pstmt.setInt(5, dto.getSal());
			pstmt.setInt(6, dto.getComm());
			pstmt.setInt(7, dto.getDeptno());
			
			result = pstmt.executeUpdate();
			
			// open 객체 닫기
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}// insert() 메소드 end
	
	
}
