package Main;

import java.util.Date; 
import java.text.SimpleDateFormat; 

public class TestDate{ 
	public static String getdate(){ 
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ
		
		
		 String datew= dateFormat.format( now ); 
		
		System.out.println(datew); 
		return datew;
		
//		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
//		
//		 
//		
//		
//		int year = c.get(Calendar.YEAR); 
//		int month = c.get(Calendar.MONTH); 
//		int date = c.get(Calendar.DATE); 
//		int hour = c.get(Calendar.HOUR_OF_DAY); 
//		int minute = c.get(Calendar.MINUTE); 
//		int second = c.get(Calendar.SECOND); 
//		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second); 
	} 
}
