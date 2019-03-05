package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/*
 * 数据库数据处理类
 */

public class DBDao {
	public static boolean ClearAllPlan(Connection connect)throws SQLException {
		//清空所有Plan
		String sql = "delete from plan where 1 = 1";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);

		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}
	
	public static boolean ClearAllSubPlan(Connection connect)throws SQLException {
		//清空所有subplan
		String sql = "delete from subplan where 1 = 1";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);

		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}
	public static List<SubPlan> getSubPlans(Connection connect) throws SQLException {
		// 读取所有子计划信息
		List<SubPlan> list = new ArrayList<>();

		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from subplan";
		result = statement.executeQuery(sql);
		while (result.next()) {
			list.add(setSubPlan(result));
		}

		return list;
	}

	public static List<SubPlan> getSubPlanForPlan(Connection connect, int p_id) throws SQLException {
		// 读取某个计划的子计划
		List<SubPlan> list = new ArrayList<>();
		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from subplan where p_id='" + p_id + "';";
		result = statement.executeQuery(sql);
		while (result.next()) {
			list.add(setSubPlan(result));
		}
		return list;
	}

	public static int CreateSubPlan(Connection connect, SubPlan sp) throws SQLException {
		// 插入子计划
		int id = -1;
		String sql = "insert into subplan values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, sp.getSub_id());
		ps.setInt(2, sp.getP_id());
		ps.setString(3, sp.getTitle());
		ps.setInt(4, sp.getWeight());
		ps.setString(5, sp.getStart());
		ps.setString(6, sp.getEnd());
		ps.setString(7, sp.getDescribe());
		ps.setInt(8, sp.getState());

		ps.executeUpdate();
			
		ResultSet rs = ps.getGeneratedKeys(); //获取结果   
		if (rs.next()) {
			id = rs.getInt(1);//取得ID
		} 
		return id;
	}

	public static SubPlan getSubPlanFromId(Connection connect, int id) throws SQLException {
		// 根据ID查询subplan
		SubPlan subplan = new SubPlan();
		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from subplan where sub_id='" + id + "';";
		result = statement.executeQuery(sql);
		if (result.next()) {
			subplan = setSubPlan(result);
		}

		return subplan;
	}

	public static boolean updateSubPlan(Connection connect, SubPlan sp) throws SQLException {
		// 更新子计划
		String sql = "update subplan set p_id=?, sub_title=?, sub_weight=?, sub_start=?, sub_end=?, sub_describe=?, sub_state=? "
				+ "where sub_id = ?";

		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setInt(1, sp.getP_id());
		ps.setString(2, sp.getTitle());
		ps.setInt(3, sp.getWeight());
		ps.setString(4, sp.getStart());
		ps.setString(5, sp.getEnd());
		ps.setString(6, sp.getDescribe());
		ps.setInt(7, sp.getState());
		ps.setInt(8, sp.getSub_id());
		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static boolean deleteSubPlan(Connection connect, SubPlan sp) throws SQLException {
		// 删除子计划
		String sql = "delete from subplan where sub_id = ?";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setInt(1, sp.getSub_id());

		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static List<Plan> getPlans(Connection connect) throws SQLException {
		// 读取所有计划信息
		List<Plan> list = new ArrayList<>();

		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from plan";
		result = statement.executeQuery(sql);
		while (result.next()) {
			list.add(setPlan(result));
		}

		return list;
	}

	public static List<Plan> getPlanForUsers(Connection connect, String u_id) throws SQLException {
		// 读取某个用户的计划
		List<Plan> list = new ArrayList<>();
		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from plan where u_id='" + u_id + "';";
		result = statement.executeQuery(sql);
		while (result.next()) {
			list.add(setPlan(result));
		}
		return list;
	}

	public static Plan getPlanFromId(Connection connect, int i) throws SQLException {
		// 根据ID查询plan
		Plan plan = new Plan();
		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from plan where p_id='" + i + "';";
		result = statement.executeQuery(sql);
		if (result.next()) {
			plan = setPlan(result);
		}

		return plan;
	}

	public static int CreatePlan(Connection connect, Plan plan) throws SQLException {
		// 创建plan
		int id = -1;
		String sql = "insert into plan(u_id, p_title, p_tag, p_color, p_planstart, p_during, p_start, p_end, p_ring, p_interval,"
				+ " p_ringtime, p_describe, p_state, p_fin_state) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, plan.getU_id());
		ps.setString(2, plan.getTitle());
		ps.setInt(3, plan.getTag());
		ps.setInt(4, plan.getColor());
		ps.setString(5, plan.getP_start());
		ps.setString(6, plan.getDuring());
		ps.setString(7, plan.getStart());
		ps.setString(8, plan.getEnd());
		ps.setString(9, String.valueOf(plan.isRing()));
		ps.setInt(10, plan.getInterval());
		ps.setString(11, plan.getRingtime());
		ps.setString(12, plan.getDescribe());
		ps.setInt(13, plan.getState());
		ps.setInt(14, plan.getFin_state());

		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys(); //获取结果   
		if (rs.next()) {
			id = rs.getInt(1);//取得ID
		} 
		return id;
	}

	public static boolean updatePlan(Connection connect, Plan plan) throws SQLException {
		// 更新plan
		String sql = "update plan set u_id=?, p_title=?, p_tag=?, p_color=?, p_planstart=?, p_during=?, "
				+ "p_start=?, p_end=?, p_ring=?, p_interval=?, p_ringtime=?, p_describe=?, p_state=?, p_fin_state=? "
				+ "where p_id = ?";

		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setString(1, plan.getU_id());
		ps.setString(2, plan.getTitle());
		ps.setInt(3, plan.getTag());
		ps.setInt(4, plan.getColor());
		ps.setString(5, plan.getP_start());
		ps.setString(6, plan.getDuring());
		ps.setString(7, plan.getStart());
		ps.setString(8, plan.getEnd());
		ps.setString(9, String.valueOf(plan.isRing()));
		ps.setInt(10, plan.getInterval());
		ps.setString(11, plan.getRingtime());
		ps.setString(12, plan.getDescribe());
		ps.setInt(13, plan.getState());
		ps.setInt(14, plan.getFin_state());
		ps.setInt(15, plan.getId());
		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static boolean deletePlan(Connection connect, Plan plan) throws SQLException {
		// 删除计划
		String sql = "delete from plan where p_id = ?";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setInt(1, plan.getId());

		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static List<Users> getUsers(Connection connect) throws SQLException {
		// 读取所有用户信息
		List<Users> list = new ArrayList<>();

		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from users";
		result = statement.executeQuery(sql);
		while (result.next()) {
			list.add(setUsers(result));
		}

		return list;
	}

	public static Users getUserFromId(Connection connect, String id) throws SQLException {
		// 根据 ID查询用户
		Users user = new Users();

		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from users where u_id='" + id + "';";
		result = statement.executeQuery(sql);
		if (result.next()) {
			user = setUsers(result);
		}

		return user;
	}

	public static Users getUserFromNickname(Connection connect, String nickname) throws SQLException {
		// 根据昵称查询用户
		Users user = new Users();

		Statement statement = (Statement) connect.createStatement();
		ResultSet result;

		String sql = "select* from users where u_nickname='" + nickname + "';";
		result = statement.executeQuery(sql);
		if (result.next()) {
			user = setUsers(result);
		}

		return user;
	}

	public static boolean isExist(Connection conn, Users user) throws SQLException {
		// 根据ID查询用户是否存在
		Users u = getUserFromId(conn, user.getU_id());
		if (u.getU_id() != null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean createUsers(Connection connect, Users u) throws SQLException {
		// 插入一个Users对象，插入成功返回true，反之亦然
		String sql = "insert into users values(?,?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setString(1, u.getU_id());
		ps.setString(2, u.getU_psd());
		ps.setString(3, u.getU_nickname());
		ps.setString(4, u.getU_phone());
		ps.setString(5, u.getU_email());
		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static boolean updateUsers(Connection connect, Users u) throws SQLException {
		// 更新Users对象
		String sql = "update users set u_psd=?, u_nickname=?, u_phone=?, u_email=? where u_id = ?";

		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setString(1, u.getU_psd());
		ps.setString(2, u.getU_nickname());
		ps.setString(3, u.getU_phone());
		ps.setString(4, u.getU_email());
		ps.setString(5, u.getU_id());
		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static boolean deleteUsers(Connection connect, Users u) throws SQLException {
		// 删除Users
		String sql = "delete from users where u_id = ?";
		PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setString(1, u.getU_id());

		if (ps.executeUpdate() == 0)
			return false;
		else
			return true;
	}

	public static void printUsers(Users u) {
		// 在控制台输出users数据
		System.out.print("id: " + u.getU_id());
		System.out.print(" psd: " + u.getU_psd());
		System.out.print(" nickname: " + u.getU_nickname());
		System.out.print(" phone: " + u.getU_phone());
		System.out.print(" email: " + u.getU_email());
		System.out.println();
	}

	private static Users setUsers(ResultSet result) throws SQLException {
		// 将result中数据赋给users
		Users user = new Users();
		user.setU_id(result.getString("u_id"));
		user.setU_psd(result.getString("u_psd"));
		user.setU_nickname(result.getString("u_nickname"));
		user.setU_phone(result.getString("u_phone"));
		user.setU_email(result.getString("u_email"));
		return user;
	}

	private static Plan setPlan(ResultSet result) throws SQLException {
		// 将result中数据赋给plan
		Plan p = new Plan();
		p.setId(result.getInt("p_id"));
		p.setU_id(result.getString("u_id"));
		p.setTitle(result.getString("p_title"));
		p.setTag(result.getInt("p_tag"));
		p.setColor(result.getInt("p_color"));
		p.setP_start(result.getString("p_planstart"));
		p.setDuring(result.getString("p_during"));
		p.setStart(result.getString("p_start"));
		p.setEnd(result.getString("p_end"));
		p.setRing(Boolean.valueOf(result.getString("p_ring")));
		p.setInterval(result.getInt("p_interval"));
		p.setRingtime(result.getString("p_ringtime"));
		p.setDescribe(result.getString("p_describe"));
		p.setProgress(result.getDouble("p_progress"));
		p.setState(result.getInt("p_state"));
		p.setFin_state(result.getInt("p_fin_state"));
		return p;
	}

	private static SubPlan setSubPlan(ResultSet result) throws SQLException {
		// 将result中数据赋给subplan
		SubPlan sp = new SubPlan();
		sp.setSub_id(result.getInt("sub_id"));
		sp.setP_id(result.getInt("p_id"));
		sp.setTitle(result.getString("sub_title"));
		sp.setWeight(result.getInt("sub_weight"));
		sp.setStart(result.getString("sub_start"));
		sp.setEnd(result.getString("sub_end"));
		sp.setDescribe(result.getString("sub_describe"));
		sp.setState(result.getInt("sub_state"));
		return sp;
	}
}
