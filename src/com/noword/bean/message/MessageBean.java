package com.noword.bean.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * 留言信息处理类
 * 
 * @author EsonLiu
 * 
 */

public class MessageBean {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 查找所有的留言信息,将留言保存在List中.
	 * 
	 * @return
	 */
	public List<Message> findAll() {
		// 获取数据库连接
		conn = DBConn.getConnection();
		// 新建一个List对象,用来存放结果
		List<Message> list = new ArrayList<Message>();
		// 查找所有留言信息的SQL语句
		String sql = "select * from t_messageboard order by id desc";
		//执行查找操作
		try {
			// 通过数据库连接对象获得数据库操作对象
			stmt = conn.createStatement();
			// 执行查询语句,获得结果集
			rs = stmt.executeQuery(sql);
			// 循环结果集,并将结果用Message封装后放入List中
			while (rs.next()) {
				Message message = new Message();
				// 构造Message 对象
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				message.setGuestName(rs.getString("guestName"));
				message.setQq(rs.getString("qq"));
				message.setEmail(rs.getString("email"));
				message.setHomepageUrl(rs.getString("homepageUrl"));
				message.setSex(rs.getString("sex"));
				message.setPic(rs.getString("pic"));
				message.setLeaveWord(rs.getString("leaveWord"));
				message.setCreateTime(rs.getDate("createtime"));
				message.setIp(rs.getString("ip"));

				// 将对象放入list
				list.add(message);
			}
		} catch (Exception e) {
			// 执行数据库操作有可能出错,比如连接中断等,打印出错误轨迹,方便跟踪错误
			e.printStackTrace();
		} finally {
			// 关闭数据库连接,释放资源
			closeDB();
		}
		// 返回结果
		return list;
	}

	/**
	 * 保存留言信息
	 * 
	 * @param message
	 */
	public void saveMessage(Message message) {
		// 获得数据库连接
		conn = DBConn.getConnection();
		// 构造插入留言信息的SQL语句,其中"?"的值由pstmt动态赋予.
		String sql = "insert into t_messageboard"
				+ "(title,guestname,qq,email,homepageUrl,sex,pic,leaveWord,ip,createTime)"
				+ " values(?,?,?,?,?,?,?,?,?,now())";
		//执行保存操作
		try {
			// 获取数据库操作对象pstmt,和stmt不同的是:stmt必须执行已经写好的SQL语句,而pstmt则可以设置参数
			pstmt = conn.prepareStatement(sql);
			// 设置参数,填满SQL语句的"?"
			pstmt.setString(1, message.getTitle());
			pstmt.setString(2, message.getGuestName());
			pstmt.setString(3, message.getQq());
			pstmt.setString(4, message.getEmail());
			pstmt.setString(5, message.getHomepageUrl());
			pstmt.setString(6, message.getSex());
			pstmt.setString(7, message.getPic());
			pstmt.setString(8, message.getLeaveWord());
			pstmt.setString(9, message.getIp());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	/**
	 * 根据id删除留言信息
	 * 
	 * @param id
	 */
	public void deleteMessage(int id) {
		// 获得数据库连接
		conn = DBConn.getConnection();
		// 删除留言信息的SQL语句
		String sql = "delete from t_messageboard where id=?";
		
		//执行删除操作
		try {
			pstmt = conn.prepareStatement(sql);
			// 将传进来的id填满SQL语句
			pstmt.setInt(1, id);
			
			//
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	/**
	 * 关闭数据库资源的通用方法
	 */
	private void closeDB() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
