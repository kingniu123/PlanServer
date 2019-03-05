package plan;

import java.io.IOException;
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

public class test extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public test() {
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
		System.out.println("test doGet()");
		Connection conn = null;
        try {  
            
            conn = DBUtil.getConnect();
        	conn.setAutoCommit(false); // 开启事务
//        	List<Users> list = DBDao.getUsers(conn);
//        	for(Users u: list){
//        		DBDao.printUsers(u);
//        	}
        	
//        	Gson gson = new Gson();
//        	Plan p = new Plan();
//        	p.setU_id("1234567");
//        	p.setTitle("测试");
//        	p.setTag(0);
//        	p.setColor(0);
//        	p.setP_start("2018年4月7日");
//        	p.setDuring("一个月");
//        	p.setFin_state(0);
//        	p.setRing(true);
//        	p.setInterval(1);
//        	p.setRingtime("10:00");
//        	p.setProgress(0.00);
//        	p.setState(0);
//        	String s = gson.toJson(p);
//        	System.out.println(s);
//        	int id = DBDao.CreatePlan(conn, p);
//        	System.out.println(id);
//        	Plan p = DBDao.getPlanFromId(conn, 1);
//        	DBDao.deletePlan(conn, p);
//        	List<Plan> pl = DBDao.getPlans(conn);
//        	for(Plan plan: pl){
//        		System.out.println(plan.getId() +" "+ plan.getTitle());
//        	}
//        	SubPlan sp = new SubPlan();
//        	sp.setP_id(2);
//        	sp.setTitle("subplan test");
//        	sp.setWeight(1);
//        	sp.setState(0);
//        	DBDao.CreateSubPlan(conn, sp);
//        	sp = DBDao.getSubPlanFromId(conn, 5);
//        	DBDao.deleteSubPlan(conn, sp);
//        	List<SubPlan> list = DBDao.getSubPlans(conn);
//        	for(SubPlan subplan: list){
//        		System.out.println("遍历："+subplan.getSub_id() + " p_id: "+ subplan.getP_id() + " tilte: "+ subplan.getTitle());
//        	}
        	boolean b = DBDao.ClearAllSubPlan(conn);
        	boolean a = DBDao.ClearAllPlan(conn);
        	
//        	List<Plan> list = DBDao.getPlanForUsers(conn, "1234567");
//        	String s1 = gson.toJson(list);
//        	String s2 = new String(s1.getBytes(),"UTF-8");
//        	System.out.println(s1);
        	System.out.println(""+a+" "+ b);
        	
            conn.commit(); // 提交事务
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
		
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
