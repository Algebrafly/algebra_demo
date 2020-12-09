package com.algebra.authentication.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ImageTest {
    public static void main(String args[]) {
        new MyFrame();
    }
}

class MyFrame extends JFrame{
    JPanel panel = null;
    JLabel label = null;

    JPanel panel2 = null;
    JLabel label2 = null;

    public MyFrame(){
        super("测试图片灰度值的熵值");
        this.setBounds(400,100, 400, 400);
        this.setBackground(Color.gray);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });

        JMenu fileMenu = new JMenu("选择图片");
        JMenuItem openItem = new JMenuItem("打开");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        }) ;

        fileMenu.add(openItem);
        JMenuBar bar = new JMenuBar();
        bar.add(fileMenu);
        this.setJMenuBar(bar);

        this.panel = new JPanel();
        this.label = new JLabel();
        label.setText("请选择一张图片！");
        panel.add(label);

        this.panel2 = new JPanel();
        this.label2 = new JLabel();
        panel2.add(label2);

        this.add(panel, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setResizable(true);
    }

    public void openFile() {
        JFileChooser  chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
        //文件是否存在或者是否选择
        if(f == null) {
            return;
        }

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(f);
			/*获取文件是否为图片，如果能够正常的获取到一张图片的宽高属性，
			那肯定这是一张图片，因为非图片文件我们是获取不到它的宽高属性的*/
            if(bi == null || bi.getHeight() <=0 || bi.getWidth() <=0){
                label.setText("您选择的不是一张图片，请从新选择！");
                return;
            } else {
                calculate(bi);
                String path = f.getPath();
                ImageIcon image = new ImageIcon(path);
                label.setIcon(image);			//设置JLabel的显示图片
                label.setText("");
                this.pack();
                validate();				//使有效
            }
        } catch (IOException e) {
//			e.printStackTrace();
            return;
        }

    }

    public void calculate(BufferedImage bi) {
        int sgray[] = new int[256];
        for(int i=0; i<256; i++) {
            sgray[i] = 0;
        }

        double sum = 0;

        int width = bi.getWidth();
        int height = bi.getHeight();

        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                int rgb = bi.getRGB(i, j);
				
				/*应为使用getRGB(i,j)获取的该点的颜色值是ARGB，
				而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，
				即bufImg.getRGB(i, j) & 0xFFFFFF。*/
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0xff00) >> 8;
                int b = (rgb & 0xff);
                int gray = (int)(r * 0.3 + g * 0.59 + b * 0.11);	//计算灰度值
                sgray[gray] ++;
            }
        }

        System.out.println(Arrays.toString(sgray));

        double[] grayP = new double[256];
        for(int i=0; i<256; i++) {
            if(sgray[i] != 0) {
                double p = sgray[i] * 1.0 / (width * height);	//每一灰度值出现的概率
                grayP[i] = p;
                sum += p * (Math.log(1/p) / Math.log(2));		//熵
            }
        }

        System.out.println(Arrays.toString(grayP));

        String result = "该图片的灰度值的熵为：" + sum;
        label2.setText(result);				//设置JLabel的显示文字
        validate();
    }


    public void calculate2(BufferedImage bi) {
        int sgray[] = new int[256];
        for (int i = 0; i < 256; i++) {
            sgray[i] = 0;
        }

        double sum = 0;

        int width = bi.getWidth();
        int height = bi.getHeight();

        Raster ra = bi.getData();
		/*图像的类型，看看它是多少位的.如果是32位
		的要考虑aphal值通道,通过Raster对象来读取/写入像素，
		它自动帮你处理成为32位的. */

		/*Rectangle rect = ra.getBounds();
		int w = (int) rect.getWidth();
		int h = (int) rect.getHeight();*/
//System.out.println(width + ":" + height);
//System.out.println(w + ":" + h);

        int pixels[] = new int[width * height];
        pixels = ra.getPixels(0, 0, width, height, pixels); //获得图片每个点的像素

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //int rgb = bi.getRGB(i, j);
                int rgb = pixels[i * j];

				/*应为使用getRGB(i,j)获取的该点的颜色值是ARGB，
				而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，
				即bufImg.getRGB(i, j) & 0xFFFFFF。*/
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0xff00) >> 8;
                int b = (rgb & 0xff);
//                int gray = (int)(r * 0.3 + g * 0.59 + b * 0.11);	//计算灰度值
                int gray = (int) (r * 0.3 + g * 0.59 + b * 0.11);    //计算灰度值
                sgray[gray]++;
            }
        }
    }
}