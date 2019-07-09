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

import com.nuist.util.DBbean;;

/**
 * Servlet implementation class LoginAction
 */
//@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
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
		//设置客户端的解码方式为utf-8
		
		doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		//
		response.setCharacterEncoding("UTF-8");		
		PrintWriter out = response.getWriter();
		
		String result = "";		
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		LoginCheck lc = new LoginCheck();
		if(lc.CheckUser(name, pwd)==1){
			result = "success";
		}
		else{
			result = "fail";
		}
				
//		System.out.println("name="+name);
//		System.out.println("password="+pwd);
//		if (name.equals("sxf123s")&&pwd.equals("123456")) {
//			result = "success";
//		}
//		else{
//			result = "fail";
//		}
		out.write(result);
		out.flush();
		out.close();
		
	}
	public class LoginCheck{
		public int CheckUser(String username, String password){
			String sql = "select * from user where user='" + username + "'" + " "
					+ "and" + " " + "password='" + password + "'";
			Connection conn = DBbean.getConn();
			Statement stmt = DBbean.getStatement(conn);
			ResultSet rs = DBbean.getResultSet(stmt, sql);
			try {
				if (rs.next()) {
					return 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				DBbean.close(rs);
				DBbean.close(stmt);
				DBbean.close(conn);
			}
			return 0;
		}	
	}

}
