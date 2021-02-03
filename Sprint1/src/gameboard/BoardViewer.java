package gameboard;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;

import IO.IO;
import userinput.UserInput;

public class BoardViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1800,900);
		frame.setTitle("Risk Game Board");
		
		/*
		BoardComponent component = new BoardComponent();
		IO io = new IO();
		UserInput ui = new UserInput();
		
		frame.add(component,BorderLayout.CENTER);
		
		Box b1=Box.createHorizontalBox();    //创建横向Box容器
        Box b2=Box.createVerticalBox();    //创建纵向Box容器
        
        frame.add(b1);    //将外层横向Box添加进窗体
        b1.add(Box.createVerticalStrut(20));    //添加高度为20的垂直框架
        b1.add(component);    //添加 map
        b1.add(Box.createHorizontalStrut(1));    //添加长度为140的水平框架 
       // b1.add(new JButton("东"));    //添加按钮2
        b1.add(Box.createHorizontalGlue());    //添加水平胶水
        
        b1.add(b2);    //添加嵌套的纵向Box容器
        //添加宽度为100，高度为20的固定区域  
        b2.add(Box.createRigidArea(new Dimension(100,20))); 
        b2.add(io);    //添加按钮3
        b2.add(Box.createVerticalGlue());    //添加垂直组件
        b2.add(ui);    //添加按钮4
        b2.add(Box.createVerticalStrut(40));    //添加长度为40的垂直框架
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setResizable(true);
		frame.setVisible(true);
        */
		///*
		BoardComponent component = new BoardComponent();
		frame.add(component,BorderLayout.CENTER);
		
		IO io = new IO();
		frame.add(io,BorderLayout.EAST);
		
		UserInput ui = new UserInput();
		frame.add(ui,BorderLayout.SOUTH);
		//*/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}

}
