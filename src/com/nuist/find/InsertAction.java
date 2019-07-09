package com.nuist.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuist.util.DBbean;

/**
 * Servlet implementation class InsertAction
 */
//@WebServlet("/InsertAction")
public class InsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAction() {
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
		
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	
		String qs_id = request.getParameter("id");
		String as_msg = request.getParameter("text");
		String as_id = df.format(day);
		String sql = "insert into answer(as_id,as_msg,qs_id) values(?,?,?)";
		Connection conn = DBbean.getConn();
		PreparedStatement ps = DBbean.getPrepareStatement(conn, sql);
		try {
			ps.setString(1, as_id);
			ps.setString(2, as_msg);
			ps.setString(3, qs_id);
			ps.executeUpdate();
			out.write("sucess");
		} catch (SQLException e) {
			out.write("fail");
			e.printStackTrace();
		} finally {
			DBbean.close(ps);
			DBbean.close(conn);
		}
		
		out.flush();
		out.close();
	}

}
