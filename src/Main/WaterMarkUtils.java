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
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体
             
            //设置水印的坐标
            Font fontm = new Font("微软雅黑", Font.PLAIN, 100);   //yu         
            g.setFont(fontm); //yu
            int m = getWatermarkLength(ming, g);//centen  
            int x = 165; //left
            int y = (srcImgHeight*6)/10 ;//- 2*getWatermarkLength(waterMarkContent, g);           
            g.drawString(ming, x, y);  //画出水印
            
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font); 
            g.setFont(font); //yu
            g.drawString("的新年心愿:", 165+m, y);  //画出水印
            
            g.drawString(waterMarkContent, (srcImgWidth-getWatermarkLength(waterMarkContent, g))/2, y+fm.getHeight()+50);  //画出水印
            g.dispose();  
            // 输出图片  
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");  
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
    