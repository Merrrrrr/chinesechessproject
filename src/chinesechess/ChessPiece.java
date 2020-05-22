package chinesechess;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial") // 当实现了序列化接口的类上缺少serialVersionUID属性的定义时，会出现黄色警告。可以使用@SuppressWarnings将警告关闭
public class ChessPiece extends JLabel {
	String name;
	Color backColor = null, foreColor;// backColor指窗体的背景色，即程序运行时你看到的窗体颜色
	String colorcatrgory = null;
	ChessBoard board = null;
	int width, height;

	public ChessPiece(String name, Color fc, Color bc, int width, int height, ChessBoard board) {
		this.name = name;
		this.board = board;
		this.width = width;
		this.height = height;
		foreColor = fc;
		backColor = bc;
		setSize(width, height);
		setBackground(bc);
		addMouseMotionListener(board);
		addMouseListener(board);
	}

	public void paint(Graphics g) {
		g.setColor(foreColor);
		g.fillOval(2, 2, width - 2, height - 2);// 填充卵形左右，上下，宽，高
		g.setColor(Color.white);// 象棋字体颜色
		g.setFont(new Font("隶书", Font.PLAIN, 27)); // Font(字体名或逻辑字体名，字型，字号)
		g.drawString(name, 7, height - 8);
		// g.drawOval(2,2,width-2,height-2);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return foreColor;
	}

	public void setCategory(String category) {
		colorcatrgory = category;
	}

	public String Category() {
		return colorcatrgory;
	}
}
