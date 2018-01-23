package com.johanneslee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.johanneslee.util.DbUtil;
import com.johanneslee.util.JsoupUtil;

/**
 * Servlet implementation class UpdateNums
 */
@WebServlet("/api/update")
public class UpdateNums extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNums() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JsoupUtil jsoupUtil = new JsoupUtil();
		DbUtil dbUtil = new DbUtil();
		List<String> list = new ArrayList<>();
		
		int i = 1;
		while(true) {
			list = jsoupUtil.getNumbers(i);
			if(list.get(0) == "")
				break;
			dbUtil.insertList(i, list);
			System.out.println(i + " is done.");
			i++;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
