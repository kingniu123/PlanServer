package plan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import db.DBDao;
import db.DBUtil;
import db.Plan;
import db.SubPlan;
import db.Users;

public class getPlans extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public getPlans() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("getPlans_doGet");   
		
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getPlans_doPost");   
		InputStream is = request.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
        String req = dis.readUTF();
        System.out.println(req);
        
        Gson gson = new Gson();
        Users user = gson.fromJson(req, Users.class);
        response.setCharacterEncoding("UTF-8"); 
		//获取数据库中该用户所有数据
		Connection conn = null;
        try {  
            conn = DBUtil.getConnect();
        	conn.setAutoCommit(false); // 开启事务
        	
        	List<Plan> pl = DBDao.getPlanForUsers(conn, user.getU_id());
        	String s = gson.toJson(pl);
        	DataOutputStream out = new DataOutputStream(response.getOutputStream());
        	out.writeUTF(s);
            
            conn.commit(); // 提交事务
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
