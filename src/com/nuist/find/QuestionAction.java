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
 * Servlet implementation class QuestionAction
 */
//@WebServlet("/QuestionAction")
public class QuestionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionAction() {
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
		String id = "1";

		String result = "";
	

		String sql = "select * from qs_title order by rand() limit 5;";
//		String sql = "select * from qs_title where qs_id = '" + id +"'" ;
		Connection conn = DBbean.getConn();
		Statement stmt = DBbean.getStatement(conn);
		ResultSet rs = DBbean.getResultSet(stmt, sql);
		try {
			while(rs.next()){
				result += rs.getString("qs_tit")+"|";
			}
//			if (rs.next()) {
//				result = rs.getString("qs_tit");
//			}
//			else{
//				result = "Sorry,Ã‚ø‚÷–Œ¥’“µΩ°≠°≠";
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBbean.close(rs);
			DBbean.close(stmt);
			DBbean.close(conn);
		}
		out.write(result);
		
		out.flush();
		out.close();
	}

}
