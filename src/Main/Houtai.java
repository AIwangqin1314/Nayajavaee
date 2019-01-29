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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

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
	private String mysql_wish=null;
	private int mysql_user_id=0;
	private String mysql_phone=null;
	private String mysql_strName=null;
	private String mysql_strSex=null;
	private String mysql_BirthDay=null;
	private String mysql_strCardNo=null;
	private int pict_num=0;
	private int mysql_pict_num=0;
	private int brnum=0;
	private String find_phone=null;
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
		int jianca_gift;
		mysql_name=request.getParameter("strName");
		mysql_wish=request.getParameter("wish");
		mysql_phone=request.getParameter("phone");
		mysql_strSex=request.getParameter("strSex");
		mysql_BirthDay=request.getParameter("BirthDay");
		mysql_strCardNo=request.getParameter("strCardNo");
		find_phone=request.getParameter("head");
		System.out.println(mysql_phone.length());
		System.out.println("�õ�����"+mysql_name+mysql_wish+mysql_phone+mysql_strSex+mysql_BirthDay+mysql_strCardNo+find_phone);
		if("one".equals(find_phone)) {
			int[] b= {0,0};
			System.out.println("����");
			if(br_phone(mysql_phone,b)) {
				//if(date_phone(mysql_phone)) {
					String str=request.getQueryString();
					System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
					response.setHeader("Access-Control-Allow-Origin", "*");
					response.setHeader("content-type", "text/json; charset=UTF-8");
					PrintWriter out = response.getWriter();		    				
					//java������json����
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("picid",b[0]);
					jsonObject.put("num", b[1]);			
					jsonObject.put("status", "ok");
					
			        out.write(jsonObject.toString());
			        out.flush();
			        out.close();
//				}else {
//					String str=request.getQueryString();
//					System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
//					response.setHeader("Access-Control-Allow-Origin", "*");
//					response.setHeader("content-type", "text/json; charset=UTF-8");
//					PrintWriter out = response.getWriter();		    				
//					//java������json����
//					JSONObject jsonObject=new JSONObject();
//					jsonObject.put("picid","time");
//					jsonObject.put("num", "out");			
//					jsonObject.put("status", "theer");
//					
//			        out.write(jsonObject.toString());
//			        out.flush();
//			        out.close();
//			        }
			}else {
				String str=request.getQueryString();
				System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("content-type", "text/json; charset=UTF-8");
				PrintWriter out = response.getWriter();		    				
				//java������json����
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("picid","wu");
				jsonObject.put("num", "wu");			
				jsonObject.put("status", "no");
				
		        out.write(jsonObject.toString());
		        out.flush();
		        out.close();
			}
		}else if("twe".equals(find_phone)&&mysql_phone.length()==11) {

		jianca_gift=result_phone(mysql_phone);//����
		if(jianca_gift==0&&mysql_wish!=null) {
			int[] b= {0,0};
			//����ͼƬ
			brnum=count(mysql_BirthDay,"-");			
			mysql_pict_num++;
			hecheng(mysql_name,mysql_wish,mysql_pict_num,brnum);
			adddata(mysql_name,mysql_phone,mysql_wish,mysql_strSex,mysql_BirthDay,mysql_strCardNo,mysql_pict_num);
		
	
		String str=request.getQueryString();
		System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
      
		
		//java������json����
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id",mysql_pict_num);
		jsonObject.put("name", "wu");
		jsonObject.put("phone", "wu");
		jsonObject.put("gift", "wu");
		jsonObject.put("status", "ok");
		
			jsonObject.put("num", brnum);	
		

        out.write(jsonObject.toString());
        out.flush();
        out.close();
        
		} else if(jianca_gift>0&&mysql_wish!=null){
			int[] b= {0,0};
			int update_num=count(mysql_BirthDay,"-");
			System.out.println(update_num);
			hecheng(mysql_name,mysql_wish,jianca_gift,update_num);
			update_msg(mysql_phone,mysql_wish,mysql_BirthDay,mysql_name);
			//
			String str=request.getQueryString();
			System.out.println("����get���󡣡�������������������"+request.getParameter("key")+"\n"+str);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("content-type", "text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
	       
			
			//java������json����
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id",jianca_gift);
			jsonObject.put("name", "wu");
			jsonObject.put("phone", "wu");
			jsonObject.put("gift", "wu");
			jsonObject.put("status", "ok");
			jsonObject.put("num", update_num);	
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
			
			jsonObject.put("name", "no");
			jsonObject.put("phone", "no");
			jsonObject.put("status", "no");
			
	        out.write(jsonObject.toString());
	        out.flush();
	        out.close();
		}
		}
		else 

		response.getWriter().append("Served at: ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public int count(String br,String splmsg) {
		String[]  strs=br.split(splmsg);
		int count_num=100;
		for(int i=0;i<strs.length;i++){
			System.out.println(strs[i]);
				}
		int datex=Integer.parseInt(strs[2]);
		switch(strs[1]) {
		case "01":
			if(datex>19) {
				System.out.println("ˮƿ��");
				count_num=0;
			}
			else {
				System.out.println("Ħ����");
				count_num=11;
			}
			break;
		case "02":
			if(datex>18) {
				System.out.println("˫����");
				count_num=1;
			}
			else {
				System.out.println("ˮƿ��");
				count_num=0;
			}
			break;
		case "03":
			if(datex>20) {
				System.out.println("������");
				count_num=2;
			}
			else {
				System.out.println("˫����");
				count_num=1;
			}
			break;
		case "04":
			if(datex>19) {
				System.out.println("��ţ��");
				count_num=3;
			}
			else {
				System.out.println("������");
				count_num=2;
			}
			break;
		case "05":
			if(datex>20) {
				System.out.println("˫����");
				count_num=4;
			}
			else {
				System.out.println("��ţ��");
				count_num=3;
			}
			break;
		case "06":
			if(datex>21) {
				System.out.println("��з��");
				count_num=5;
			}
			else {
				System.out.println("˫����");
				count_num=4;
			}
			break;
		case "07":
			if(datex>22) {
				System.out.println("ʨ����");
				count_num=6;
			}
			else  {
				System.out.println("��з��");
				count_num=5;
			}
			
			break;
		case "08":
			if(datex>22) {
				System.out.println("��Ů��");
				count_num=7;
			}
			else {
				System.out.println("ʨ����");
				count_num=6;
			}
			break;
		case "09":
			if(datex>22) {
				System.out.println("��Ы��");
				count_num=8;
			}
			else {
				System.out.println("��Ů��");
				count_num=7;
			}
			break;
		case "10":
			if(datex>23) {
				System.out.println("������");
				count_num=9;
			}
			else {
				System.out.println("��Ы��");
				count_num=8;
			}
			break;
		case "11":
			if(datex>22) {
				System.out.println("Ħ����");
				count_num=10;
			}
			else {
				System.out.println("������");
				count_num=9;
			}
			break;
		case "12":
			if(datex>21) {
				System.out.println("ˮƿ��");
				count_num=11;
			}
			else {
				System.out.println("Ħ����");
				count_num=10;
			}
			break;
		}
		return count_num;
	}
	public void adddata(String name,String phone,String wish,String sex,String brthday,String number,int pict_id){
	
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
           
           String s=""+"insert into user(name,wish,phone,date,sex,cardno,brithday,pictid) values(?,?,?,?,?,?,?,?)";
                    PreparedStatement pst=con.prepareStatement(s);
//                   user_id=user_id+1;
                   pst.setString(1, name);
                   pst.setString(2, wish);
                   pst.setString(3, phone);
                   pst.setString(4, datemsg);
                   pst.setString(5, sex);
                   pst.setString(6, number);
                   pst.setString(7, brthday);
                   pst.setInt(8, pict_id);
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
	public void hecheng(String ming,String msg,int num,int brnum) {
		WaterMarkUtils he=new WaterMarkUtils();
		System.out.println(brnum);
		Font font = new Font("΢���ź�", Font.PLAIN, 65);                     //ˮӡ����
        String srcImgPath="/home/ubuntu/pic/root/ti"+brnum+".jpg"; //ԴͼƬ��ַ
        String tarImgPath="/home/ubuntu/www/pic/ny"+num+".jpg"; //���洢�ĵ�ַ
        //String srcImgPath="D:/pic/ti"+brnum+".jpg"; //ԴͼƬ��ַ
        //String tarImgPath="D:/pic/ny"+num+".jpg"; //���洢�ĵ�ַ
        String waterMarkContent=msg;  //ˮӡ����
        Color color=new Color(255,255,255,240);                               //ˮӡͼƬɫ���Լ�͸����
        he.addWaterMark(srcImgPath, tarImgPath,waterMarkContent ,color ,font,ming);
	}
//	@SuppressWarnings("finally")
	public void update_msg(String tel,String wish,String brithday,String name){
		Connection con;
		String gift_mysql=null;
		int br_mysql=100;
		try {
		    //������������
		    Class.forName(driver);
		    //1.getConnection()����������MySQL���ݿ⣡��
		    con = DriverManager.getConnection(url,user,password);
		    if(!con.isClosed())
		        System.out.println("Succeeded connecting to the Database!");
		    Statement statement = con.createStatement();
		    
		    String sql="update user set wish =? ,name =? ,brithday =?  where phone= ?" ;
		    
		    System.out.println(sql);

            PreparedStatement pst=con.prepareStatement(sql);

           pst.setString(1, wish);
           pst.setString(2, name);
           pst.setString(3, brithday);
           pst.setString(4, tel);
            pst.executeUpdate();        
            //�ر���Դ        
            pst.close();

		  System.out.println("����chengg");
		  
		  
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
		
		
		
	}
 	public Integer result_Set(String gift_getmsg){
		  //����Connection����
      Connection con;
      Integer id=0;
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
         
    }
     System.out.println("���ݿ�ȡֵ�ɹ���ȡ����");
     return id;
	
	}
	public int result_phone(String phone_getmsg){
		  //����Connection����
  Connection con;
 int gift_mysql=0;
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
    		gift_mysql=Integer.parseInt(rs.getString("pictid"));
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
 if(gift_mysql!=0) {
	   return gift_mysql;
 }
 else {return 0;}
	}
	public boolean br_phone(String tel,int[] num) {
			  //����Connection����
	  Connection con;
	 String gift_mysql=null;
	 int br_mysql=100;
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
	     String sql="select * from user where phone='"+tel+"'" ;
	     //String sql="select phone, count(*) from user group by phone";
	      //3.ResultSet�࣬������Ż�ȡ�Ľ��������
	     ResultSet rs = statement.executeQuery(sql);
	      System.out.println(sql);
	      

	    while(rs.next()){
	    	String msg=rs.getString("phone");
	    	System.out.println(msg);
	    	if(msg!=null){
	    		num[0]=Integer.parseInt(rs.getString("pictid"));
	    		num[1]=count(rs.getString("brithday"),"-");
	    		System.out.println("�绰�������"+num[0]+"����"+num[1]);
//	    		 return gift_mysql;
	    		gift_mysql="youshu";
	    		} else {
	    			System.out.println("���޴���"+msg);
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
//	       System.out.println("���ݿ�ȡֵ�ɹ���ȡ����");
//	       
	//  }
	
	if(gift_mysql!=null) {
		return true;
	}else {
		return false;
	}

	}
	public boolean date_phone(String tel) {
		  //����Connection����
		Connection con;
		String gift_mysql=null;
		int br_mysql=100;
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
		   String sql="select * from user where phone='"+tel+"'" ;
		   //String sql="select phone, count(*) from user group by phone";
		    //3.ResultSet�࣬������Ż�ȡ�Ľ��������
		   ResultSet rs = statement.executeQuery(sql);
		    System.out.println(sql);
		    
		
		  while(rs.next()){
		  	String msg=rs.getString("phone");
		  	System.out.println(msg);
		  	if(msg!=null){
		  		gift_mysql=rs.getString("iddate");
		  		System.out.println("�绰�������"+gift_mysql);
		//  		 return gift_mysql;
		  		} else {
		  			System.out.println("���޴���"+msg);
		  		}  
		  }
		  TestDate date = null ;
          String datemsg=date.getdate();
		  if(gift_mysql==null) {
			  sql="update user set iddate = '"+datemsg+"' where phone='"+tel+"'" ;
			  statement.executeUpdate(sql); 
			  System.out.println(sql);
			  br_mysql=1;
		  }else {
			  
	           System.out.println("ʱ��"+datemsg+"mysqlʱ��"+gift_mysql);
	       
	           java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	           java.util.Date s=format1.parse(datemsg);
	           java.util.Date m=format1.parse(gift_mysql);
	           System.out.println("��ʽ��ʱ��"+s);
	           System.out.println("��ʽʱ��"+m);
	           long diff = s.getTime() - m.getTime();//�����õ��Ĳ�ֵ�Ǻ��뼶�� 
	           long days = diff / (1000 * 60 * 60 * 24);  
	           if(days>0) {
	        	   br_mysql=1;
	        	   sql="update user set iddate = '"+datemsg+"' where phone='"+tel+"'" ;
	 		    	statement.executeUpdate(sql); 
	           }else {
	        	   long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);  
	        	   if(hours>0) {
	        		   br_mysql=1;
	        		   sql="update user set iddate = '"+datemsg+"' where phone='"+tel+"'" ;
	     			   statement.executeUpdate(sql); 
	        	   }else {
	        		   long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
	        		   System.out.println(minutes);
	        		   if(minutes>30) {
	        			   br_mysql=1;
	        			   sql="update user set iddate = '"+datemsg+"' where phone='"+tel+"'" ;
	        			   statement.executeUpdate(sql); 
	        		   }else {
	        			   //����0
	        			   
	        		   }
	        	   }
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
		//     System.out.println("���ݿ�ȡֵ�ɹ���ȡ����");
		//     
		//  }
		
		if(br_mysql==1) {
			return true;
		}else {
			return false;
		}
		}
	
}



 