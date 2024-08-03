package JDBCProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import CustomException.stringNotVowel;
import OOP.thissuprt;
import SuperKeyword.superDemo;

public class JDBCMiniProject1
{
	static Connection con;
	public static void connection()
	{
		
		try 
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_mini_project","root","root");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void insert()
	{
		System.out.println("insert data.............");
		try 
		{
		String sql="insert into student values(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		while(true)
		{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter id");
		int id=sc.nextInt();
		ps.setInt(1, id);
		
		System.out.println("Enter name");
		String name=sc.next();
		ps.setString(2, name);
		
		System.out.println("Enter Course");
		String course=sc.next();
		ps.setString(3, course);
	
		
		
		System.out.println("Enter Totol Marks");
		int marks=sc.nextInt();
		ps.setInt(4, marks);
		
		System.out.println("Enter Phone no");
		String P_no=sc.next();
		ps.setString(5, P_no);
		
		System.out.println("Enter City");
		String city=sc.next();
		ps.setString(6, city);
		
		
		ps.addBatch();
		System.out.println("Do you want continue preass(y) or No(n):");
		char c=sc.next().charAt(0);
		if(c=='n')
		break;
	}	
		ps.executeBatch();
		
		System.out.println("data insert Sucessfully.......");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void update()
	{
		System.out.println("Update data.............");
		try {
			String sql="update student set name=? where id=?";
			PreparedStatement ps=con.prepareStatement(sql);
			
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Enter name");
			String name=sc.next();
			ps.setString(1, name);
			
			System.out.println("Enter id");
			int id=sc.nextInt();
			ps.setInt(2, id);
			
			
			ps.executeUpdate();
			
			System.out.println("Data update sucessfully.................");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public static void view()
	{
		//System.out.println("View data.............");
			try 
			{
				String sql="Select * from student";
				PreparedStatement ps=con.prepareStatement(sql);
				
				ResultSet rs=ps.executeQuery();	
				System.out.println("id | name | course | totalmarks | phoneno | city ");
				System.out.println("---+------+--------+------------+---------+------");	

				while(rs.next())
				{ 
					System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getInt(4)+" | "+rs.getInt(5)+" | "+rs.getString(6));
				}
				System.out.println("---+------+--------+------------+---------+------");	
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	}
	
	
	public static void delete()
	{
		System.out.println("delete data.............");
		try 
		{
			
			String s="delete from student where id=?";
			
			PreparedStatement ps=con.prepareStatement(s);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Id which you want to delete record for that id ");
			int id=sc.nextInt();
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			System.out.println("Data Delete Sucessfully...........");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		connection();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your choice");
		int ch=1;
		while(ch!=0)
		{
			System.out.print(""
					+ "-----------------------------------\n"
					+ "1.Insert student information:\n"
					+ "2.View the list of student:\n"
					+ "3.Edit the student information:\n"
					+ "4.Delete the student information:\n"
					+ "5.Terminate the loop:\n");
			ch=sc.nextInt();

		switch(ch)
		{
		case 1:insert();
		break;
		case 2:view();
		break;
		case 3:update();
		break;
		case 4:delete();
		break;
		default:System.out.println("you dont have another method...........");
		}
		}
	}

}
