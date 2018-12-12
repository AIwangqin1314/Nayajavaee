package Main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import java.util.*;
import java.sql.*;


/**
 * Servlet implementation class houtai
 */
//@WebServlet("/houtai")
public class Houtai extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MyUtils = null;
	// String job = null;
	private String id = null;
	private String name=null;
	private String gift=null;
	private int user_id=0;
	private String phone=null;
	private String mysql_name=null;
	private String mysql_gift=null;
	private int mysql_user_id=0;
	private String mysql_phone=null;
	private static String url = "jdbc:mysql://172.16.0.9:3306/nanya?useSSL=false";
	//private static String url = "jdbc:mysql://cdb-reg07uth.gz.tencentcdb.com:10027/nanya?useSSL=false";
	private static String driver = "com.mysql.jdbc.Driver";
     //URLָ��Ҫ���ʵ����ݿ���mydata
     
     //MySQL����ʱ���û���
	private static  String user = "root";
     //MySQL����ʱ������
	private static String password = "xishanNY$1215mysql";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Houtai() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ��ȡget����
		int count_num=0;
		String jianca_gift;
		mysql_name=request.getParameter("name");
		mysql_gift=request.getParameter("gift");
		mysql_phone=request.getParameter("phone");
		System.out.println("�õ�����"+mysql_name+mysql_gift+mysql_phone);
		jianca_gift=result_phone(mysql_phone);
		if(jianca_gift==null) {
			adddata(mysql_name,mysql_phone,mysql_gift);
		
		count_num=result_Set(mysql_gift);
		//
		String str=request.getQueryString();
		System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("content-type", "text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
        //��:ServletOutputStream out = response.getOutputStream();
        //��������Ҫһ����!
		
		//java������json����
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id",user_id+"");
		jsonObject.put("name", name);
		jsonObject.put("phone", phone);
		jsonObject.put("gift", gift);
		jsonObject.put("count_num", count_num);
		
//		System.out.println(jsonObject.toString());
//		JSONArray array = new JSONArray();
//		array.put(jsonObject);
//		array.put(jsonObject);
//		System.out.println(array.toString());
      //��ȡjson����
        //JSONArray array = MyUtils.getJSONArray();
        //���json����
        out.write(jsonObject.toString());
        out.flush();
        out.close();
        
		}else {
			String str=request.getQueryString();
			System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("content-type", "text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
	       
			//java������json����
			JSONObject jsonObject=new JSONObject();
			
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			jsonObject.put("gift", jianca_gift);
			jsonObject.put("count_num", "��");
			
	        out.write(jsonObject.toString());
	        out.flush();
	        out.close();
		}
//		response.getWriter().append(number+"");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

public void adddata(String name,String phone,String gift){
	
        //����Connection����
        Connection con;
       //����������
    //������ѯ�����
       try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
           //2.����statement���������ִ��SQL��䣡
            TestDate date = null ;
            String datemsg=date.getdate();
           
           String s=""+"insert into user(name,gift,phone,date,sex,cardno,brithday) values(?,?,?,?,?,?,?)";
                    PreparedStatement pst=con.prepareStatement(s);
//                   user_id=user_id+1;
                   pst.setString(1, name);
                   pst.setString(2, gift);
                   pst.setString(3, phone);
                   pst.setString(4, datemsg);
                   pst.setString(5, "��");
                   pst.setString(6, "1526ffsf778ht");
                   pst.setString(7, datemsg);
                    pst.executeUpdate();        
                    //�ر���Դ        
                    pst.close();
            //Ҫִ�е�SQL���
           con.close();
        } catch(ClassNotFoundException e) {   
           //���ݿ��������쳣����
           System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
           } catch(SQLException e) {
           //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
           }catch (Exception e) {
       // TODO: handle exception
            e.printStackTrace();
      }finally{
             System.out.println("���ݿ����ݳɹ���ȡ����");
             
        }
       
   }

	public void mysqldata(){
		  //����Connection����
        Connection con;
//       //����������
//        String driver = "com.mysql.jdbc.Driver";
//       //URLָ��Ҫ���ʵ����ݿ���mydata
//       String url = "jdbc:mysql://172.16.0.9:3306/nanya?sueSSL=false";
//       //MySQL����ʱ���û���
//        String user = "root";
//       //MySQL����ʱ������
//       String password = "xishanNY$1215mysql";
    //������ѯ�����
       try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
           //2.����statement���������ִ��SQL��䣡��
           Statement statement = con.createStatement();
            //Ҫִ�е�SQL���
           String sql = "select * from user";
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
           ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
           System.out.println("ִ�н��������ʾ:");  
           System.out.println("-----------------");  
            System.out.println("����" + "\t" + "ְ��");  
          System.out.println("-----------------");  
              
      
            while(rs.next()){
           	 
               //��ȡstuname��������
               user_id = rs.getInt("uid");
               
              //��ȡstuid��������
                name = rs.getString("name");

                gift = rs.getString("gift");
                phone = rs.getString("phone");
               //������
              // System.out.println(id + "\t" + job);
               
           }
           rs.close();
           con.close();
        } catch(ClassNotFoundException e) {   
           //���ݿ��������쳣����
           System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
           } catch(SQLException e) {
           //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
           }catch (Exception e) {
       // TODO: handle exception
            e.printStackTrace();
      }finally{
             System.out.println("���ݿ����ݳɹ���ȡ����");
             
        }
	
	}
//	@SuppressWarnings("finally")
	public Integer result_Set(String gift_getmsg){
		  //����Connection����
      Connection con;
      Integer id=0;
     //����������
//      String driver = "com.mysql.jdbc.Driver";
//     //URLָ��Ҫ���ʵ����ݿ���mydata
//     String url = "jdbc:mysql://172.16.0.9:3306/nanya?sueSSL=false";
//     //MySQL����ʱ���û���
//      String user = "root";
//     //MySQL����ʱ������
//     String password = "xishanNY$1215mysql";
  //������ѯ�����
     try {
          //������������
          Class.forName(driver);
          //1.getConnection()����������MySQL���ݿ⣡��
          con = DriverManager.getConnection(url,user,password);
          if(!con.isClosed())
              System.out.println("Succeeded connecting to the Database!");
         //2.����statement���������ִ��SQL��䣡��
         Statement statement = con.createStatement();
          //Ҫִ�е�SQL���
        
         String sql="select gift, count(*) from user group by gift";
          //3.ResultSet�࣬������Ż�ȡ�Ľ��������
         ResultSet rs = statement.executeQuery(sql);
          System.out.println(sql);
          
   
        while(rs.next()){
        	String msg=rs.getString("gift");
        	if(msg.equals(gift_getmsg)){
        		id=rs.getInt(2);
        		System.out.println(msg+id);
        		}     
        }
         rs.close();
         con.close();
      } catch(ClassNotFoundException e) {   
    	 
         //���ݿ��������쳣����
         System.out.println("Sorry,can`t find the Driver!");   
          e.printStackTrace();   
         } catch(SQLException e) {
         //���ݿ�����ʧ���쳣����
          e.printStackTrace();  
         }catch (Exception e) {
     // TODO: handle exception
          e.printStackTrace();
         
    }finally{
//           System.out.println("���ݿ�ȡֵ�ɹ���ȡ����");
//           return id;
      }
     System.out.println("���ݿ�ȡֵ�ɹ���ȡ����");
     return id;
	
	}
	public String result_phone(String phone_getmsg){
		  //����Connection����
  Connection con;
 String gift_mysql=null;
 try {
      //������������
      Class.forName(driver);
      //1.getConnection()����������MySQL���ݿ⣡��
      con = DriverManager.getConnection(url,user,password);
      if(!con.isClosed())
          System.out.println("Succeeded connecting to the Database!");
     //2.����statement���������ִ��SQL��䣡��
     Statement statement = con.createStatement();
      //Ҫִ�е�SQL���
     String sql="select * from user where phone='"+phone_getmsg+"'" ;
     //String sql="select phone, count(*) from user group by phone";
      //3.ResultSet�࣬������Ż�ȡ�Ľ��������
     ResultSet rs = statement.executeQuery(sql);
      System.out.println(sql);
      

    while(rs.next()){
    	String msg=rs.getString("phone");
    	System.out.println(msg);
    	if(msg!=null){
    		gift_mysql=rs.getString("gift");
    		System.out.println("�绰�������"+gift_mysql);
//    		 return gift_mysql;
    		}     
    }
    System.out.println("����");
    
     rs.close();
     con.close();
    
  } catch(ClassNotFoundException e) {   
	 
     //���ݿ��������쳣����
     System.out.println("Sorry,can`t find the Driver!");   
      e.printStackTrace();   
     } catch(SQLException e) {
     //���ݿ�����ʧ���쳣����
      e.printStackTrace();  
     }catch (Exception e) {
 // TODO: handle exception
      e.printStackTrace();
     
}
// finally{
//       System.out.println("���ݿ�ȡֵ�ɹ���ȡ����");
//       
//  }
 if(gift_mysql!=null) {
	   return gift_mysql;
 }
 else {return null;}
	}
}



 