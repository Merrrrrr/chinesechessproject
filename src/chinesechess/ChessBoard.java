package chinesechess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {
	public ChessPoint point[][];
	public int unitWidth, unitHeight; // 点的水平距离和垂直距离
	int xaxislength, yaxislength; // 棋盘行与列数
	int x, y;
	boolean move = false;
	public String redteamcolor = "红色", blackteamcolor = "黑色";
	ChessPiece 红車1, 红車2, 红马1, 红马2, 红相1, 红相2, 红帅, 红仕1, 红仕2, 红兵1, 红兵2, 红兵3, 红兵4, 红兵5, 红炮1, 红炮2;
	ChessPiece 黑車1, 黑車2, 黑马1, 黑马2, 黑将, 黑士1, 黑士2, 黑卒1, 黑卒2, 黑卒3, 黑卒4, 黑卒5, 黑象1, 黑象2, 黑炮1, 黑炮2;

	int startX, startY;
	int startI, startJ;
	public boolean redrun = true, blackrun = false;
	ChessRule rule = null;
	public ChessDo record = null;

	public ChessBoard(int w, int h, int r, int c)// 棋格的宽度，高度和X轴长，Y轴长
	{
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);// 跟踪鼠标移动和鼠标拖动
		Color bc = getBackground();
		setBackground(Color.orange);
		unitWidth = w;
		unitHeight = h;
		xaxislength = r;
		yaxislength = c;
		point = new ChessPoint[r + 5][c + 5];
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				point[i][j] = new ChessPoint(i * unitWidth, j * unitHeight, false);
			}
		}
		rule = new ChessRule(this, point);
		record = new ChessDo(this, point);

		红車1 = new ChessPiece("車", Color.red, bc, w - 4, h - 4, this);
		红車1.setCategory(redteamcolor);
		红車2 = new ChessPiece("車", Color.red, bc, w - 4, h - 4, this);
		红車2.setCategory(redteamcolor);
		红马1 = new ChessPiece("马", Color.red, bc, w - 4, h - 4, this);
		红马1.setCategory(redteamcolor);
		红马2 = new ChessPiece("马", Color.red, bc, w - 4, h - 4, this);
		红马2.setCategory(redteamcolor);
		红炮1 = new ChessPiece("炮", Color.red, bc, w - 4, h - 4, this);
		红炮1.setCategory(redteamcolor);
		红炮2 = new ChessPiece("炮", Color.red, bc, w - 4, h - 4, this);
		红炮2.setCategory(redteamcolor);
		红相1 = new ChessPiece("相", Color.red, bc, w - 4, h - 4, this);
		红相1.setCategory(redteamcolor);
		红相2 = new ChessPiece("相", Color.red, bc, w - 4, h - 4, this);
		红相2.setCategory(redteamcolor);
		红仕1 = new ChessPiece("仕", Color.red, bc, w - 4, h - 4, this);
		红仕1.setCategory(redteamcolor);
		红仕2 = new ChessPiece("仕", Color.red, bc, w - 4, h - 4, this);
		红仕2.setCategory(redteamcolor);
		红帅 = new ChessPiece("帅", Color.red, bc, w - 4, h - 4, this);
		红帅.setCategory(redteamcolor);
		红兵1 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		红兵1.setCategory(redteamcolor);
		红兵2 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		红兵2.setCategory(redteamcolor);
		红兵3 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		红兵3.setCategory(redteamcolor);
		红兵4 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		红兵4.setCategory(redteamcolor);
		红兵5 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		红兵5.setCategory(redteamcolor);

		黑将 = new ChessPiece("将", Color.black, bc, w - 4, h - 4, this);
		黑将.setCategory(blackteamcolor);
		黑士1 = new ChessPiece("士", Color.black, bc, w - 4, h - 4, this);
		黑士1.setCategory(blackteamcolor);
		黑士2 = new ChessPiece("士", Color.black, bc, w - 4, h - 4, this);
		黑士2.setCategory(blackteamcolor);
		黑車1 = new ChessPiece("車", Color.black, bc, w - 4, h - 4, this);
		黑車1.setCategory(blackteamcolor);
		黑車2 = new ChessPiece("車", Color.black, bc, w - 4, h - 4, this);
		黑車2.setCategory(blackteamcolor);
		黑炮1 = new ChessPiece("炮", Color.black, bc, w - 4, h - 4, this);
		黑炮1.setCategory(blackteamcolor);
		黑炮2 = new ChessPiece("炮", Color.black, bc, w - 4, h - 4, this);
		黑炮2.setCategory(blackteamcolor);
		黑象1 = new ChessPiece("象", Color.black, bc, w - 4, h - 4, this);
		黑象1.setCategory(blackteamcolor);
		黑象2 = new ChessPiece("象", Color.black, bc, w - 4, h - 4, this);
		黑象2.setCategory(blackteamcolor);
		黑马1 = new ChessPiece("马", Color.black, bc, w - 4, h - 4, this);
		黑马1.setCategory(blackteamcolor);
		黑马2 = new ChessPiece("马", Color.black, bc, w - 4, h - 4, this);
		黑马2.setCategory(blackteamcolor);
		黑卒1 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		黑卒1.setCategory(blackteamcolor);
		黑卒2 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		黑卒2.setCategory(blackteamcolor);
		黑卒3 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		黑卒3.setCategory(blackteamcolor);
		黑卒4 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		黑卒4.setCategory(blackteamcolor);
		黑卒5 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		黑卒5.setCategory(blackteamcolor);
		point[1][10].setPiece(红車1, this);
		point[2][10].setPiece(红马1, this);
		point[3][10].setPiece(红相1, this);
		point[4][10].setPiece(红仕1, this);
		point[5][10].setPiece(红帅, this);
		point[6][10].setPiece(红仕2, this);
		point[7][10].setPiece(红相2, this);
		point[8][10].setPiece(红马2, this);
		point[9][10].setPiece(红車2, this);
		point[2][8].setPiece(红炮1, this);
		point[8][8].setPiece(红炮2, this);
		point[1][7].setPiece(红兵1, this);
		point[3][7].setPiece(红兵2, this);
		point[5][7].setPiece(红兵3, this);
		point[7][7].setPiece(红兵4, this);
		point[9][7].setPiece(红兵5, this);
		point[1][1].setPiece(黑車1, this);
		point[2][1].setPiece(黑马1, this);
		point[3][1].setPiece(黑象1, this);
		point[4][1].setPiece(黑士1, this);
		point[5][1].setPiece(黑将, this);
		point[6][1].setPiece(黑士2, this);
		point[7][1].setPiece(黑象2, this);
		point[8][1].setPiece(黑马2, this);
		point[9][1].setPiece(黑車2, this);
		point[2][3].setPiece(黑炮1, this);
		point[8][3].setPiece(黑炮2, this);
		point[1][4].setPiece(黑卒1, this);
		point[3][4].setPiece(黑卒2, this);
		point[5][4].setPiece(黑卒3, this);
		point[7][4].setPiece(黑卒4, this);
		point[9][4].setPiece(黑卒5, this);

	}

	public void paintComponent(Graphics g)// 画棋盘
	{
		super.paintComponent(g);
		for (int j = 1; j <= yaxislength; j++) {
			g.drawLine(point[1][j].x, point[1][j].y, point[xaxislength][j].x, point[xaxislength][j].y);// 直线的起点坐标，终点坐标
		}
		for (int i = 1; i <= xaxislength; i++) {
			if (i != 1 && i != xaxislength) {
				g.drawLine(point[i][1].x, point[i][1].y, point[i][yaxislength - 5].x, point[i][yaxislength - 5].y);
				g.drawLine(point[i][yaxislength - 4].x, point[i][yaxislength - 4].y, point[i][yaxislength].x,
						point[i][yaxislength].y);// 竖直画线，多减一个格子总体向上平移一行
			} else {
				g.drawLine(point[i][1].x, point[i][1].y, point[i][yaxislength].x, point[i][yaxislength].y);
			}
		}

		g.drawLine(point[4][1].x, point[4][1].y, point[6][3].x, point[6][3].y);
		g.drawLine(point[6][1].x, point[6][1].y, point[4][3].x, point[4][3].y);
		g.drawLine(point[4][8].x, point[4][8].y, point[6][yaxislength].x, point[6][yaxislength].y);
		g.drawLine(point[4][yaxislength].x, point[4][yaxislength].y, point[6][8].x, point[6][8].y);
		g.setFont(new Font("Serif", 6, 22));
		g.drawString("楚     河                     汉     界", point[2][5].x, point[2][6].y);

	}

	public void mousePressed(MouseEvent e)// 接口方法，负责鼠标拖动棋子走棋过程中的动作响应
	{
		ChessPiece piece = null;
		Rectangle rect = null;
		if (e.getSource() == this)// 如果得到的事件源是这个类的实例本身
			move = false;
		if (move == false)
			if (e.getSource() instanceof ChessPiece)// 如果事件源是ChessPiece类的实例
			{
				piece = (ChessPiece) e.getSource();
				startX = piece.getBounds().x;
				startY = piece.getBounds().y;

				rect = piece.getBounds();
				for (int i = 1; i <= xaxislength; i++) {
					for (int j = 1; j <= yaxislength; j++) {
						int x = point[i][j].getX();// 返回鼠标点击的X
						int y = point[i][j].getY();
						if (rect.contains(x, y))// 点击了
						{
							startI = i;
							startJ = j;
							break;
						}

					}
				}
			}
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

		ChessPiece piece = null;
		if (e.getSource() instanceof ChessPiece) {
			piece = (ChessPiece) e.getSource(); // 将变量piece赋值为 鼠标事件的发生源

			move = true;

			e = SwingUtilities.convertMouseEvent(piece, e, this);//// 将鼠标事件转化
		}

		if (e.getSource() == this) {
			if (move && piece != null) {
				x = e.getX();// 得到这个鼠标事件相对于产生它的组件的X坐标
				y = e.getY();
				if (redrun && ((piece.Category()).equals(redteamcolor))) {
					piece.setLocation(x - piece.getWidth() / 2, y - piece.getHeight() / 2);// piece组件移到新的位置
				}
				if (blackrun && (piece.Category().equals(blackteamcolor))) {
					piece.setLocation(x - piece.getWidth() / 2, y - piece.getHeight() / 2);
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		ChessPiece piece = null;
		move = false;
		Rectangle rect = null;
		if (e.getSource() instanceof ChessPiece) {
			piece = (ChessPiece) e.getSource();
			rect = piece.getBounds();

			e = SwingUtilities.convertMouseEvent(piece, e, this);
		}
		if (e.getSource() == this) {
			boolean containChessPoint = false;
			int x = 0, y = 0;
			int m = 0, n = 0;
			if (piece != null) {
				for (int i = 1; i <= xaxislength; i++) {
					for (int j = 1; j <= yaxislength; j++) {
						x = point[i][j].getX();
						y = point[i][j].getY();
						if (rect.contains(x, y)) {

							containChessPoint = true;
							m = i;
							n = j;
							break;
						}

					}
				}
			}
			if (piece != null && containChessPoint) {
				Color pieceColor = piece.getColor();
				if (point[m][n].isPiece()) {
					Color c = (point[m][n].getPiece()).getColor();
					if (pieceColor.getRGB() == c.getRGB()) {
						piece.setLocation(startX, startY);

						(point[startI][startJ]).setFlag(true);
					} else {
						boolean ok = rule.movePieceRule(piece, startI, startJ, m, n);
						if (ok) {
							ChessPiece pieceRemoved = point[m][n].getPiece();
							point[m][n].reMovePiece(pieceRemoved, this);
							point[m][n].setPiece(piece, this);
							(point[startI][startJ]).setFlag(false);
							record.recordChessManual(piece, startI, startJ, m, n);
							record.recordPieceEaten(pieceRemoved);
							if (piece.Category().equals(redteamcolor)) {
								redrun = false;
								blackrun = true;
							}
							if (piece.Category().equals(blackteamcolor)) {
								blackrun = false;
								redrun = true;
							}
							validate();
							repaint();
						} else {
							piece.setLocation(startX, startY);
							(point[startI][startJ]).setFlag(true);
						}
					}

				} else {

					boolean ok = rule.movePieceRule(piece, startI, startJ, m, n);
					if (ok) {
						point[m][n].setPiece(piece, this);
						(point[startI][startJ]).setFlag(false);
						record.recordChessManual(piece, startI, startJ, m, n);
						record.recordPieceEaten("没吃棋子");
						if (piece.Category().equals(redteamcolor)) {
							redrun = false;
							blackrun = true;
						}
						if (piece.Category().equals(blackteamcolor)) {
							blackrun = false;
							redrun = true;
						}
					} else {
						piece.setLocation(startX, startY);
						(point[startI][startJ]).setFlag(true);
					}
				}
			}
			if (piece != null && !containChessPoint) {
				piece.setLocation(startX, startY);
				(point[startI][startJ]).setFlag(true);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
}
