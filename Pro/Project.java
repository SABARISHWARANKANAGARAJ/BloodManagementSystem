package Pro;
import java.util.*;
import java.util.regex.*;    
import java.sql.*;
public class Project {
	static void search(String s,String s1,int id)
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Sabari@057");
		Statement st= con.createStatement();
		String str="select * from details where address='"+s+"' and blood='"+s1+"'";
		ResultSet rs=st.executeQuery(str);
		boolean donorsavailable = false;
		while(rs.next()) {
			int k=Integer.parseInt(rs.getString("sno"));
			if(id!=k)
			{
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("           Donor Name     : "+rs.getString("blood"));
			System.out.println("           Email-Id       : "+rs.getString("name"));
			System.out.println("           Donor City     : "+rs.getString("address"));
			System.out.println("           Donor Age      : "+rs.getString("age"));
			System.out.println("           Donor Contact  : "+rs.getString("phone"));
			System.out.println("-------------------------------------------------------------------------------");
			donorsavailable = true;
			}
		}
		if(!donorsavailable)
			System.out.println("-----No donors in this Area-----\n-----Search Another Nearest Area------");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	static void add(String n,String eid,String ad,String city,String bg,String pno,int a)
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Sabari@057");
		Statement st= con.createStatement();
		String str="insert into details(name,email,password,age,phone,address,blood) values('"+n+"','"+eid+"','"+ad+"','"+a+"','"+pno+"','"+city+"','"+bg+"')";
		int cnt=st.executeUpdate(str);
		System.out.println("Successfully Registered !!!!");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	static void request(String bg,String phone,String name,String city,String date,int id)
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Sabari@057");
		Statement st= con.createStatement();
		String str="insert into requests(blood,phone,name,city,requested_date,id) values('"+bg+"','"+phone+"','"+name+"','"+city+"','"+date+"','"+id+"')";
		int cnt=st.executeUpdate(str);
		System.out.println("Your Request Have Been SuccessFully Posted !!!!");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	static void view()
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Sabari@057");
		Statement st= con.createStatement();
		String str="Select * from requests ";
		ResultSet rs=st.executeQuery(str);
		while(rs.next())
		{
			
			System.out.println("EMERGENCY");
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("           Blood Required : "+rs.getString("blood"));
			System.out.println("           Person Name    : "+rs.getString("name"));
			System.out.println("           Person City    : "+rs.getString("city"));
			System.out.println("           Person Contact : "+rs.getString("phone"));
			System.out.println("           Requested Date : "+rs.getString("requested_date"));
			System.out.println("-------------------------------------------------------------------------------");
			
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	static int val(String eid,String pass)
	{
		try
		{
			int flag=0,id=0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Sabari@057");
		Statement st= con.createStatement();
		String str="select * from details where email='"+eid+"'";
		ResultSet rs=st.executeQuery(str);
		while(rs.next())
		{
			if(pass.equals(rs.getString("password")))
			{
				id=Integer.parseInt(rs.getString("sno"));
				flag=1;
			}
			else
				flag=0;
		}
		if(flag==1)
			return id;
		else
			return 0;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int i=0,id=0,j=0;
		do
		{
			System.out.println("1).Login\n2). Register");
			j=sc.nextInt();
			switch(j)
			{
			case 1:{
				System.out.println("Enter your Email-Id");
				String em=sc.next();
				System.out.println("Enter Your Password");
				String p=sc.next();
				id=val(em,p);
				if(id==0)
					System.out.println("Login Failed!!!");
				break;
			}
			case 2:
			{
				System.out.println("Enter Your Name : ");
				sc.nextLine();
				String n=sc.nextLine();
				
				System.out.println("Enter Your Email : ");
				String eid=sc.nextLine();
				Matcher matcher;
				boolean flag = true;
				do {
						if(!flag) {
							System.out.println("Invalid email.");
							System.out.println("Enter valid2 Email : ");
							eid = sc.nextLine();
						}
					  	String regex = "^(.+)@(.+)$";  
				        Pattern pattern = Pattern.compile(regex);  
				        matcher = pattern.matcher(eid);  
				        flag = matcher.matches();
				}while(!matcher.matches());
				System.out.println("Enter Your Password : ");
				String pass=sc.nextLine();
				System.out.println("Enter Your city : ");
				String city=sc.nextLine();
				System.out.println("Enter Your Blood Group : ");
				String bg=sc.nextLine();
				System.out.println("Enter Your Phone Number : ");
				String pno=sc.next();
				System.out.println("Enter Your Age : ");
				int a=sc.nextInt();
				add(n, eid, pass, city, bg, pno, a);
				break;
			}
			}
		}while(j>0 && j<=2 &&id==0);
		
		if(id>0)
		{
		do
		{
			System.out.println("Enter your Choice : \n1).Search Donor\n2).Request Donor\n3).View Request's\n \n  Enter Any Key To Exit");
			i=sc.nextInt();
			sc.nextLine();
			switch(i)
			{
			case 1:{
				System.out.println("Enter the City And Blood group : ");
				String s=sc.nextLine();
				String s1=sc.nextLine();
				search(s,s1,id);
				break;
			}
			case 2:{
				System.out.println("Enter The Blood group : ");
				String bg=sc.nextLine();
				System.out.println("Name Of The Person To  Be Contacted : ");
				String name=sc.nextLine();
				System.out.println("Enter The Contact Number : ");
				String ph=sc.nextLine();
				System.out.println("Enter The City : ");
				String city=sc.nextLine();
				System.out.println("Enter Date(dd/mm/yyyy) : ");
				String date=sc.nextLine();
				request(bg,ph,name,city,date,id);
				break;
			}
			case 3:
			{
				view();
				break;
			}
			default:System.out.println("Thank You!! Have A Nice Day");
			}
			System.out.println();
		}
		while(i<4);
		}
	}
}