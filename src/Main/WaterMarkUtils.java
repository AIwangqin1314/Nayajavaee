package Main;

	import java.awt.Color;
	import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.FileOutputStream;

	import javax.imageio.ImageIO;

	
	public class WaterMarkUtils {

   
    public void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent,Color markContentColor,Font font,String ming) {

        try {
            // ��ȡԭͼƬ��Ϣ
            File srcImgFile = new File(srcImgPath);//�õ��ļ�
            Image srcImg = ImageIO.read(srcImgFile);//�ļ�ת��ΪͼƬ
            int srcImgWidth = srcImg.getWidth(null);//��ȡͼƬ�Ŀ�
            int srcImgHeight = srcImg.getHeight(null);//��ȡͼƬ�ĸ�
            // ��ˮӡ
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //����ͼƬ�ı�������ˮӡ��ɫ
            g.setFont(font);              //��������
             
            //����ˮӡ������
            Font fontm = new Font("΢���ź�", Font.PLAIN, 100);   //yu         
            g.setFont(fontm); //yu
            int m = getWatermarkLength(ming, g);//centen  
            int x = 165; //left
            int y = (srcImgHeight*6)/10 ;//- 2*getWatermarkLength(waterMarkContent, g);           
            g.drawString(ming, x, y);  //����ˮӡ
            
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font); 
            g.setFont(font); //yu
            g.drawString("��������Ը:", 165+m, y);  //����ˮӡ
            
            g.drawString(waterMarkContent, (srcImgWidth-getWatermarkLength(waterMarkContent, g))/2, y+fm.getHeight()+50);  //����ˮӡ
            g.dispose();  
            // ���ͼƬ  
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("���ˮӡ���");  
            outImgStream.flush();  
            outImgStream.close();  

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());  
    }   
	}
    