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
		System.out.println("得到请求"+mysql_name+mysql_gift+mysql_phone);
		adddata(mysql_name,mysql_phone,mysql_gift);
		String str=request.getQueryString();
		System.out.println("发送get请求。。。。。。。。。。。"+request.getParameter("key")+"\n"+str);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/json; charset=UTF-8");
		int number= (int) (Math.random()*7);
		System.out.println(number+"");
		PrintWriter out = response.getWriter();
        //或:ServletOutputStream out = response.getOutputStream();
        //但两个不要一起用!
//        out.write("{\"id\":1,\"name\":{\"hujg\",\"我予以\"}}");
//		//out.write("{\"name\":\"fly\",\"type\":\"虫子\"}");
//        out.flush();
//        out.close();  
		
		//java对象变成json对象
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
      //获取json数据
        //JSONArray array = MyUtils.getJSONArray();
        //输出json数据
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
		
        //声明Connection对象
        Connection con;
       //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
       //URL指向要访问的数据库名mydata
       String url = "jdbc:mysql://172.16.0.9:3306/nanya?useSSL=false";
       //MySQL配置时的用户名
        String user = "root";
       //MySQL配置时的密码
       String password = "xishanNY$1215mysql";
    //遍历查询结果集
       try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
           //2.创建statement类对象，用来执行SQL语句！
           String s=""+"insert into user(uid,name,gift,phone) values("+"1,?,?,?)";
                    PreparedStatement pst=con.prepareStatement(s);
                   
                   pst.setString(1, name);
                   pst.setString(2, gift);
                   pst.setString(3, phone);
                 
                    pst.execute();        
                    //关闭资源        
                    pst.close();
            //要执行的SQL语句
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
      }finally{
             System.out.println("数据库数据成功获取！！");
             
        }
       
   }

	public void mysqldata(){
		  //声明Connection对象
        Connection con;
       //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
       //URL指向要访问的数据库名mydata
       String url = "jdbc:mysql://172.16.0.9:3306/nanya?sueSSL=false";
       //MySQL配置时的用户名
        String user = "root";
       //MySQL配置时的密码
       String password = "xishanNY$1215mysql";
    //遍历查询结果集
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
           String sql = "select * from user";
            //3.ResultSet类，用来存放获取的结果集！！
           ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
           System.out.println("执行结果如下所示:");  
           System.out.println("-----------------");  
            System.out.println("姓名" + "\t" + "职称");  
          System.out.println("-----------------");  
              
//            String job = null;
//            String id = null;
      
            while(rs.next()){
           	 
               //获取stuname这列数据
               user_id = rs.getInt("uid");
               
              //获取stuid这列数据
                name = rs.getString("name");

                gift = rs.getString("gift");
                phone = rs.getString("phone");
               //输出结果
              // System.out.println(id + "\t" + job);
               
           }
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
      }finally{
             System.out.println("数据库数据成功获取！！");
             
        }
	
	}
}



 