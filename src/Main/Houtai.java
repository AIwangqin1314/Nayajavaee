package Main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class houtai
 */
@WebServlet("/houtai")
public class Houtai extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String job=null;
	// String job = null;
	private String id = null;
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
//		mysqldata();
		String str=request.getQueryString();
		System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/json; charset=UTF-8");
		int number= (int) (Math.random()*7);
		System.out.println(number+"");
		PrintWriter out = response.getWriter();
        //��:ServletOutputStream out = response.getOutputStream();
        //��������Ҫһ����!
        out.write("{\"id\":\"1\",\"name\":{\"hujg\",\"������\"}}");
		//out.write("{\"name\":\"fly\",\"type\":\"����\"}");
        out.flush();
        out.close();  
      //��ȡjson����
        //JSONArray array = MyUtils.getJSONArray();
        //���json����
        //out.write(array.toString());
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
//public void mysqldata(){
//		
//        //����Connection����
//        Connection con;
//       //����������
//        String driver = "com.mysql.jdbc.Driver";
//       //URLָ��Ҫ���ʵ����ݿ���mydata
//       String url = "jdbc:mysql://localhost:3306/yuyu?sueSSL=false";
//       //MySQL����ʱ���û���
//        String user = "root";
//       //MySQL����ʱ������
//       String password = "yulin1117wangqin.14";
//    //������ѯ�����
//       try {
//            //������������
//            Class.forName(driver);
//            //1.getConnection()����������MySQL���ݿ⣡��
//            con = DriverManager.getConnection(url,user,password);
//            if(!con.isClosed())
//                System.out.println("Succeeded connecting to the Database!");
//           //2.����statement���������ִ��SQL��䣡��
//           Statement statement = con.createStatement();
//            //Ҫִ�е�SQL���
//           String sql = "select * from user";
//            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
//           ResultSet rs = statement.executeQuery(sql);
//            System.out.println("-----------------");
//           System.out.println("ִ�н��������ʾ:");  
//           System.out.println("-----------------");  
//            System.out.println("����" + "\t" + "ְ��");  
//          System.out.println("-----------------");  
//              
////            String job = null;
////            String id = null;
//      
//            while(rs.next()){
//           	 
//               //��ȡstuname��������
//               job = rs.getString("job");
//              //��ȡstuid��������
//                id = rs.getString("ename");
//
//               //������
//               System.out.println(id + "\t" + job);
//               
//           }
//           rs.close();
//           con.close();
//        } catch(ClassNotFoundException e) {   
//           //���ݿ��������쳣����
//           System.out.println("Sorry,can`t find the Driver!");   
//            e.printStackTrace();   
//           } catch(SQLException e) {
//           //���ݿ�����ʧ���쳣����
//            e.printStackTrace();  
//           }catch (Exception e) {
//       // TODO: handle exception
//            e.printStackTrace();
//      }finally{
//             System.out.println("���ݿ����ݳɹ���ȡ����");
//        }
//   }
}



 