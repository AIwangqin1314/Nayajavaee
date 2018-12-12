package Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Chaxun
 */

public class Chaxun extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
     //URL指向要访问的数据库名mydata
     
     //MySQL配置时的用户名
	private static  String user = "root";
     //MySQL配置时的密码
	private static String password = "xishanNY$1215mysql";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chaxun() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		mysql_name=request.getParameter("name");
		mysql_gift=request.getParameter("gift");
		mysql_phone=request.getParameter("phone");
		System.out.println("得到请求"+mysql_name+mysql_gift+mysql_phone);
		String regist=result_Set(mysql_phone);
		
		String str=request.getQueryString();
		System.out.println("发送get请求。。。。。。。。。。。"+request.getParameter("key")+"\n"+str);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
       
		JSONObject jsonObject=new JSONObject();
	
		jsonObject.put("phone", mysql_phone);
		jsonObject.put("gift", regist);
	
		
        out.write(jsonObject.toString());
        out.flush();
        out.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
//	@SuppressWarnings("finally")
	public String result_Set(String phone_getmsg){
		  //声明Connection对象
    Connection con;
   String gift_mysql=null;
   try {
        //加载驱动程序
        Class.forName(driver);
        //1.getConnection()方法，连接MySQL数据库！！
        con = DriverManager.getConnection(url,user,password);
        if(!con.isClosed())
            System.out.println("Succeeded connecting to the Database!");
       //2.创建statement类对象，用来执行SQL语句！！
       Statement statement = con.createStatement();
        //要执行的SQL语句
       String sql="select * from user where phone='"+phone_getmsg+"'" ;
       //String sql="select phone, count(*) from user group by phone";
        //3.ResultSet类，用来存放获取的结果集！！
       ResultSet rs = statement.executeQuery(sql);
        System.out.println(sql);
        
 
      while(rs.next()){
      	String msg=rs.getString("phone");
      	System.out.println(msg);
      	if(msg!=null){
      		gift_mysql=rs.getString("gift");
      		System.out.println("电话号码存在"+gift_mysql);
//      		 return gift_mysql;
      		}     
      }
      System.out.println("结束");
      
       rs.close();
       con.close();
      
    } catch(ClassNotFoundException e) {   
  	 
       //数据库驱动类异常处理
       System.out.println("Sorry,can`t find the Driver!");   
        e.printStackTrace();   
       } catch(SQLException e) {
       //数据库连接失败异常处理
        e.printStackTrace();  
       }catch (Exception e) {
   // TODO: handle exception
        e.printStackTrace();
       
  }
//   finally{
//         System.out.println("数据库取值成功获取！！");
//         
//    }
   if(gift_mysql!=null) {
	   return gift_mysql;
   }
   else {return "";}
	}
}

