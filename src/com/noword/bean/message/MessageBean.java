package com.noword.bean.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * ������Ϣ������
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
	 * �������е�������Ϣ,�����Ա�����List��.
	 * 
	 * @return
	 */
	public List<Message> findAll() {
		// ��ȡ���ݿ�����
		conn = DBConn.getConnection();
		// �½�һ��List����,������Ž��
		List<Message> list = new ArrayList<Message>();
		// ��������������Ϣ��SQL���
		String sql = "select * from t_messageboard order by id desc";
		//ִ�в��Ҳ���
		try {
			// ͨ�����ݿ����Ӷ��������ݿ��������
			stmt = conn.createStatement();
			// ִ�в�ѯ���,��ý����
			rs = stmt.executeQuery(sql);
			// ѭ�������,���������Message��װ�����List��
			while (rs.next()) {
				Message message = new Message();
				// ����Message ����
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

				// ���������list
				list.add(message);
			}
		} catch (Exception e) {
			// ִ�����ݿ�����п��ܳ���,���������жϵ�,��ӡ������켣,������ٴ���
			e.printStackTrace();
		} finally {
			// �ر����ݿ�����,�ͷ���Դ
			closeDB();
		}
		// ���ؽ��
		return list;
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param message
	 */
	public void saveMessage(Message message) {
		// ������ݿ�����
		conn = DBConn.getConnection();
		// �������������Ϣ��SQL���,����"?"��ֵ��pstmt��̬����.
		String sql = "insert into t_messageboard"
				+ "(title,guestname,qq,email,homepageUrl,sex,pic,leaveWord,ip,createTime)"
				+ " values(?,?,?,?,?,?,?,?,?,now())";
		//ִ�б������
		try {
			// ��ȡ���ݿ��������pstmt,��stmt��ͬ����:stmt����ִ���Ѿ�д�õ�SQL���,��pstmt��������ò���
			pstmt = conn.prepareStatement(sql);
			// ���ò���,����SQL����"?"
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
	 * ����idɾ��������Ϣ
	 * 
	 * @param id
	 */
	public void deleteMessage(int id) {
		// ������ݿ�����
		conn = DBConn.getConnection();
		// ɾ��������Ϣ��SQL���
		String sql = "delete from t_messageboard where id=?";
		
		//ִ��ɾ������
		try {
			pstmt = conn.prepareStatement(sql);
			// ����������id����SQL���
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
	 * �ر����ݿ���Դ��ͨ�÷���
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
