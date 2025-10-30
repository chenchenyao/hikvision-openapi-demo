package com.example.javaproject.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author admin
 * @Description
 * @create 2025-05-12 13:42
 */
public class test1 {
    public static void main(String[] args) throws ServletException, IOException {
        img(null, null);
    }

    protected static void img(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File headFile = new File("D:\\tupian\\0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg");
        File tailFile = new File("D:\\tupian\\0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg");
        if(!headFile.exists() || !tailFile.exists()){
            // 图片缺失, 直接返回空
            return;
        }
//        Mat matHead = imread(headPath);
//        Mat matTail = imread(tailPath);
        BufferedImage matHead = ImageIO.read(headFile);
        BufferedImage matTail = ImageIO.read(tailFile);
        try {
//            Mat mat = mergeImg(matHead, matTail);// 对输入图片到格式没有限制
//            BufferedImage bufferedImage = matToBuffer(".jpg", mat);// 以jpg格式将矩阵转为图片缓存
//            boolean write = ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());// 将图片缓存写入response
            BufferedImage mat = mergeImg(matHead, matTail);
            boolean write = ImageIO.write(mat, "jpg", resp.getOutputStream());
        } catch (Exception e) {
        }
    }
    public static BufferedImage mergeImg(BufferedImage headImage, BufferedImage tailImage) {
        // 计算合并后图片的宽度和高度
        int width = Math.max(headImage.getWidth(), tailImage.getWidth());
        int height = headImage.getHeight() + tailImage.getHeight();

        // 创建一个新的BufferedImage对象
        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combined.createGraphics();

        // 先绘制头部图片
        g2d.drawImage(headImage, 0, 0, null);
        // 再在头部图片下方绘制尾部图片
        g2d.drawImage(tailImage, 0, headImage.getHeight(), null);

        // 释放资源
        g2d.dispose();

        return combined;
    }
}
