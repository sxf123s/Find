package com.nuist.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuist.util.DBbean;

/**
 * Servlet implementation class RegisterAction
 */
//@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAction() {
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
		
	
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		
		
			String sql = "insert into user(user,password,gender,email) values(?,?,?,?)";
			Connection conn = DBbean.getConn();
			PreparedStatement ps = DBbean.getPrepareStatement(conn, sql);
			try {
				ps.setString(1, name);
				ps.setString(2, pwd);
				ps.setString(3, gender);
				ps.setString(4, email);
				ps.executeUpdate();
				out.write("sucess");
			} catch (SQLException e) {
				out.write("此用户名已经被注册，请更换! ");
				e.printStackTrace();
			} finally {
				DBbean.close(ps);
				DBbean.close(conn);
			}
		
		
		out.flush();
		out.close();
		
		
	}
	

}
