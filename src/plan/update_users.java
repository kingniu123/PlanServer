package plan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import db.DBDao;
import db.DBUtil;
import db.Users;

public class update_users extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public update_users() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("update_users doPost");
		// 获取输入流
		InputStream is = request.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
        String req = dis.readUTF();
		System.out.println(req);

		Gson gson = new Gson();
		Users user = gson.fromJson(req, Users.class);
		
		Connection conn = null;
        try {  
        conn = DBUtil.getConnect();
    	conn.setAutoCommit(false); // 开启事务
    	
    	if(DBDao.updateUsers(conn, user)){
    		DataOutputStream out = new DataOutputStream(response.getOutputStream());
        	out.writeUTF(option_code.UPDATE_OK);
    	}else {
    		DataOutputStream out = new DataOutputStream(response.getOutputStream());
        	out.writeUTF(option_code.UPDATE_ERROR);
    	}
    	Users u2 = DBDao.getUserFromId(conn, user.getU_id());
    	DBDao.printUsers(u2);
    	
    	conn.commit(); // 提交事务
        
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
