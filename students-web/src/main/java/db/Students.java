package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.Record;
import model.Student;

public class Students {
	Connection con = null;
	public void doConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false","root","password");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void addRecord(Record rd)
	{
		String sql = "INSERT INTO assigment(studentName, subject, assignment_category, dos, points) values (?,?,?,?,?)";
		try {
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, rd.getStudentName());
		pst.setString(2, rd.getSubjectName());
		pst.setString(3, rd.getAssignmentCategory());
		pst.setString(4, rd.getDateOfSubmission());
		pst.setFloat(5, rd.getPoints());
		pst.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void closeConnection()
	{
		try {
		if (con != null)
		{
			con.close();
			con = null;
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public String[][] getStudentData(String name) {
		String solution[][] = null;
		try
		{
			System.out.println(name + " ");
			System.out.println("Subject -- Test -- Quiz -- Project -- Lab -- Overall");
			String sqlGetSubjects = "SELECT distinct(subject) from assigment where studentName = '" +name+ "'";
			Statement st = con.createStatement();
			Set<String> subjects = new HashSet<String>();
			ResultSet rs = st.executeQuery(sqlGetSubjects);
			while(rs.next())
				subjects.add(rs.getString("subject"));
			int curInd = 0;
			solution = new String[subjects.size()][7];
			for(String s:subjects)
			{
				ArrayList<Float> t = new ArrayList<Float>();
				ArrayList<Float> q = new ArrayList<Float>();
				ArrayList<Float> p = new ArrayList<Float>();
				ArrayList<Float> l = new ArrayList<Float>();
				sqlGetSubjects = "SELECT * from mydb.assigment where studentName='"+name+"' and subject = '"+s+"'";
				rs = st.executeQuery(sqlGetSubjects);
				while (rs.next())
				{
					String ac = rs.getString("assignment_category");
					float pnt = rs.getFloat("points");
					if(ac.startsWith("test"))
						t.add(pnt);
					else if(ac.startsWith("lab"))
						l.add(pnt);
					else if(ac.startsWith("pro"))
						p.add(pnt);
					else
						q.add(pnt);
				}
				float ts = 0,qs = 0,ps = 0,ls = 0;
				for(float x:t)
					ts = ts + x;
				for(float x:q)
					qs = qs + x;
				for(float x:p)
					ps = ps + x;
				for(float x:l)
					ls = ls + x;
				ts = (float) ((ts/t.size())*0.4);
				ls = (float) ((ls/l.size())*0.1);
				ps = (float) ((ps/p.size())*0.3);
				qs = (float) ((qs/q.size())*0.2);
				ts = (float) (Math.round(ts * 100.0) / 100.0);
				qs = (float) (Math.round(qs * 100.0) / 100.0);
				ls = (float) (Math.round(ls * 100.0) / 100.0);
				ps = (float) (Math.round(ps * 100.0) / 100.0);
				float ov = ts+ls+ps+qs;
//				System.out.print(s + "--");
//				System.out.print(ts + "--");
//				System.out.print(qs + "--");
//				System.out.print(ps + "--");
//				System.out.print(ls + "--");
//				System.out.print(ov);
//				System.out.println();
				solution[curInd][0] = name;
				solution[curInd][1] = s;
				solution[curInd][2] = String.valueOf(ts);
				solution[curInd][3] = String.valueOf(qs);
				solution[curInd][4] = String.valueOf(ps);
				solution[curInd][5] = String.valueOf(ls);
				solution[curInd][6] = String.valueOf(ov);
				curInd++;
				}
//			return solution;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return solution;
	}
	public String[][] getSubjectData(String name) {
		String solution[][] = null;
		try
		{
			System.out.println(name + " ");
			System.out.println("Student Name -- Test -- Quiz -- Project -- Lab -- Overall");
			String sqlGetSubjects = "SELECT distinct(studentName) from assigment where subject = '" +name+ "'";
			Statement st = con.createStatement();
			Set<String> subjects = new HashSet<String>();
			ResultSet rs = st.executeQuery(sqlGetSubjects);
			while(rs.next())
				subjects.add(rs.getString("studentName"));
			int curInd = 0;
			solution = new String[subjects.size()][7];
			for(String s:subjects)
			{
				ArrayList<Float> t = new ArrayList<Float>();
				ArrayList<Float> q = new ArrayList<Float>();
				ArrayList<Float> p = new ArrayList<Float>();
				ArrayList<Float> l = new ArrayList<Float>();
				sqlGetSubjects = "SELECT * from mydb.assigment where subject='"+name+"' and studentName = '"+s+"'";
				rs = st.executeQuery(sqlGetSubjects);
				while (rs.next())
				{
					String ac = rs.getString("assignment_category");
					float pnt = rs.getFloat("points");
					if(ac.startsWith("test"))
						t.add(pnt);
					else if(ac.startsWith("lab"))
						l.add(pnt);
					else if(ac.startsWith("pro"))
						p.add(pnt);
					else
						q.add(pnt);
				}
				float ts = 0,qs = 0,ps = 0,ls = 0;
				for(float x:t)
					ts = ts + x;
				for(float x:q)
					qs = qs + x;
				for(float x:p)
					ps = ps + x;
				for(float x:l)
					ls = ls + x;
				ts = (float) ((ts/t.size())*0.4);
				ls = (float) ((ls/l.size())*0.2);
				ps = (float) ((ps/p.size())*0.3);
				qs = (float) ((qs/q.size())*0.1);
				ts = (float) (Math.round(ts * 100.0) / 100.0);
				qs = (float) (Math.round(qs * 100.0) / 100.0);
				ls = (float) (Math.round(ls * 100.0) / 100.0);
				ps = (float) (Math.round(ps * 100.0) / 100.0);
				float ov = ts+ls+ps+qs;
//				System.out.print(s + "--");
//				System.out.print(ts + "--");
//				System.out.print(qs + "--");
//				System.out.print(ps + "--");
//				System.out.print(ls + "--");
//				System.out.print(ov);
//				System.out.println();
				solution[curInd][0] = name;
				solution[curInd][1] = s;
				solution[curInd][2] = String.valueOf(ts);
				solution[curInd][3] = String.valueOf(qs);
				solution[curInd][4] = String.valueOf(ps);
				solution[curInd][5] = String.valueOf(ls);
				solution[curInd][6] = String.valueOf(ov);
				curInd++;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return solution;
	}
}
