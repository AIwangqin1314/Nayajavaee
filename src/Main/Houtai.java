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
@WebServlet("/houtai")
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
		//mysqldata();
		mysql_name=request.getParameter("name");
		mysql_gift=request.getParameter("gift");
		mysql_phone=request.getParameter("phone");
		System.out.println("�õ�����"+mysql_name+mysql_gift+mysql_phone);
		adddata(mysql_name,mysql_phone,mysql_gift);
		String str=request.getQueryString();
		System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/json; charset=UTF-8");
		int number= (int) (Math.random()*7);
		System.out.println(number+"");
		PrintWriter out = response.getWriter();
        //��:ServletOutputStream out = response.getOutputStream();
        //��������Ҫһ����!
//        out.write("{\"id\":1,\"name\":{\"hujg\",\"������\"}}");
//		//out.write("{\"name\":\"fly\",\"type\":\"����\"}");
//        out.flush();
//        out.close();  
		
		//java������json����
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id",user_id+"");
		jsonObject.put("name", name);
		jsonObject.put("phone", phone);
		jsonObject.put("gift", gift);
		
		System.out.println(jsonObject.toString());
		JSONArray array = new JSONArray();
		array.put(jsonObject);
		array.put(jsonObject);
		System.out.println(array.toString());
      //��ȡjson����
        //JSONArray array = MyUtils.getJSONArray();
        //���json����
        out.write(jsonObject.toString());
        out.flush();
        out.close();
//		response.getWriter().append(number+"");
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
        String driver = "com.mysql.jdbc.Driver";
       //URLָ��Ҫ���ʵ����ݿ���mydata
       String url = "jdbc:mysql://172.16.0.9:3306/nanya?useSSL=false";
       //MySQL����ʱ���û���
        String user = "root";
       //MySQL����ʱ������
       String password = "xishanNY$1215mysql";
    //������ѯ�����
       try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
           //2.����statement���������ִ��SQL��䣡
           String s=""+"insert into user(uid,name,gift,phone) values("+"1,?,?,?)";
                    PreparedStatement pst=con.prepareStatement(s);
                   
                   pst.setString(1, name);
                   pst.setString(2, gift);
                   pst.setString(3, phone);
                 
                    pst.execute();        
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
       //����������
        String driver = "com.mysql.jdbc.Driver";
       //URLָ��Ҫ���ʵ����ݿ���mydata
       String url = "jdbc:mysql://172.16.0.9:3306/nanya?sueSSL=false";
       //MySQL����ʱ���û���
        String user = "root";
       //MySQL����ʱ������
       String password = "xishanNY$1215mysql";
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
              
//            String job = null;
//            String id = null;
      
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
}



 