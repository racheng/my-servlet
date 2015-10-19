package com.ra.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String USER_FILE = "d:/leicheng/×ÊÁÏ/users";

	private static final String LOGIN_VIEW = "login.view";

	private static final String USER_VIEW = "user.view";
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doLogin(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doLogin(request, response);
	}
	
	protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException {
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		boolean checkLogin = checkLogin(name, pw);
		if(checkLogin){
			request.getRequestDispatcher(USER_VIEW).forward(request, response);
		} else {
			// request.getRequestDispatcher(LOGIN_VIEW).forward(request,
			// response);
			response.sendRedirect(LOGIN_VIEW);
		}

	}

	private boolean checkLogin(String name, String pw) throws IOException {
		if (name != null && pw != null) {
			for (String fileStr : new File(USER_FILE).list()) {
				if (fileStr.equals(name)) {
					BufferedReader bf = new BufferedReader(new FileReader(USER_FILE + "/" + name));
					String[] userInfoStr = bf.readLine().split(";");
					bf.close();
					if (pw.equals(userInfoStr[1])) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
