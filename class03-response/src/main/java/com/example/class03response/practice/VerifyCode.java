package com.example.class03response.practice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

@WebServlet("/verifyCode")
public class VerifyCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         int width = 160;
         int height = 45;

         //1.创建验证码图片对象
        BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2.美化图片，添加一个画笔
        Graphics g = image.getGraphics();

        //3.设置画笔颜色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        String str="1234567890qwertuASDFGHJK";
        //4. 生成随机角标
        Random random = new Random();
        for(int i=0; i<=5; i++){
            int index = random.nextInt(str.length());
            //获取随机字符
            char ch = str.charAt(index);
            //设置字体颜色
            Color color = new Color(random.nextInt(256), random.nextInt(256),random.nextInt(256));
            g.setColor(color);
            Font font=new Font("Dialog",Font.BOLD,20);
            g.setFont(font);
            //填写验证码
            g.drawString(ch+" ",width/5*i,height/2);

        }




        //随机生成坐标点
        for (int i=0;i<10;i++){
            int x1=random.nextInt(width);
            int x2=random.nextInt(width);
            int y1=random.nextInt(height);
            int y2=random.nextInt(height);
            Color color=new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));       //生成干扰线
            g.setColor(color);       //生成干扰线颜色随机
            g.drawLine(x1,y1,x2,y2);
        }
        //将验证码图片展示到页面上
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }
}
