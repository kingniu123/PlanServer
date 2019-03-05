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
import net.sf.json.JSONObject;

public class login extends HttpServlet {

	public login() {
		super();
		System.out.println("login()");
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("login_doGet");   
		
      
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login_doPost"); 
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
    	
    	DataOutputStream out = new DataOutputStream(response.getOutputStream());
        
    	if(DBDao.isExist(conn, user)){
    		Users dbuser = DBDao.getUserFromId(conn, user.getU_id());
    		if(user.getU_psd().equals(dbuser.getU_psd())){//登录成功

    			out.writeUTF(option_code.LOGIN_OK);
    			System.out.println("登录成功");
    		}else{//密码错误
    			out.writeUTF(option_code.LOGIN_WRONG_PSD);
    			System.out.println("密码错误");
    		}
    		
    	}else{//用户不存在
    		out.writeUTF(option_code.LOGIN_WRONG_ID);
    		System.out.println("用户不存在");
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
