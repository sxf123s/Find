package com.nuist.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuist.util.DBbean;

/**
 * Servlet implementation class AnswerAction
 */
//@WebServlet("/AnswerAction")
public class AnswerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		//
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String question = request.getParameter("question");

		String result1 = "", result2 = "", result = "";
		String id = "";
	

		String sql = "select * from qs_title where qs_msg like '%" + question +"%'" ;
		Connection conn = DBbean.getConn();
		Statement stmt = DBbean.getStatement(conn);
		ResultSet rs = DBbean.getResultSet(stmt, sql);
		try {
			if(rs.next()){
				result1 += rs.getString("qs_msg");
				id = rs.getString("qs_id");
			}
//			if(!rs.next()){
//				result = "Sorry,题库中未找到……";
//			}
//			if (rs.next()) {
//				result = rs.getString("question");
//			}
//			else{
//				result = "Sorry,题库中未找到……";
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBbean.close(rs);
			DBbean.close(stmt);
			DBbean.close(conn);
		}
		
		if(result1.isEmpty()){
			result1 = "Sorry,题库中未找到……";
			id = "无";
		}
		Connection conn1 = DBbean.getConn();
		Statement stmt1 = DBbean.getStatement(conn1);
		String sql1 = "select * from answer where qs_id = '" + id +"'" ;
		ResultSet rs1 = DBbean.getResultSet(stmt1, sql1);
		try {
			if(rs1.next()){
				result2 += rs1.getString("as_msg")+"\n";
			}
//			if(!rs.next()){
//				result = "Sorry,题库中未找到……";
//			}
//			if (rs.next()) {
//				result = rs.getString("question");
//			}
//			else{
//				result = "Sorry,题库中未找到……";
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBbean.close(rs1);
			DBbean.close(stmt1);
			DBbean.close(conn1);
		}
		if(result2.isEmpty()){
			result2 = "Sorry,题库中未找到……";
		}
		result = result1 + "|" +id+"|"+result2;
//		result = result1 + "|" + id;
		out.write(result);		
		out.flush();
		out.close();
	}

}
