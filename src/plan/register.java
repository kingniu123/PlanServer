package plan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import db.DBDao;
import db.DBUtil;
import db.Users;
import net.sf.json.JSONObject;

public class register extends HttpServlet {

	public register() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log

	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("register_doGet");
          
		Connection conn = null;
        try {  
            
            
            conn = DBUtil.getConnect();
        	conn.setAutoCommit(false); // 开启事务
        	
        	
            
            conn.commit(); // 提交事务
            conn.close();
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
          
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("register_doPost");
		//获取输入流
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
    	//查看数据库中所有信息
    	List<Users> list = DBDao.getUsers(conn);
    	System.out.println(list.size());
    	for(Users u:list){
    		DBDao.printUsers(u);
    	}
    	
    	if(DBDao.isExist(conn, user)){
    		//ID已存在
    		DataOutputStream out = new DataOutputStream(response.getOutputStream());
        	out.writeUTF(option_code.REG_DUPID);
    		System.out.println("ID已存在");
    	}else{
    		if(DBDao.createUsers(conn, user)){//注册成功
    			DataOutputStream out = new DataOutputStream(response.getOutputStream());
            	out.writeUTF(option_code.REG_OK);
    			System.out.println("注册成功");
    		}else {//创建失败
    			DataOutputStream out = new DataOutputStream(response.getOutputStream());
            	out.writeUTF(option_code.REG_CREATE_ERROR);
    			System.out.println("创建失败");
    		}
    	}
    	
    	conn.commit(); // 提交事务
        
        }catch(Exception e){
        	e.printStackTrace();
        }
       
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
