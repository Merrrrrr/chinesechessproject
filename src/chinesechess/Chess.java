package chinesechess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Chess extends JFrame {
	JScrollPane scroll = null;
	ChessBoard board = null;
	Container con = null;
	ChessDo record = null;
	ChessPoint[][] point;
	LinkedList<MoveStep> ChessManual = null;
	LinkedList<Object> EatPiece = null;
	JButton buttonStart;
	int i = 0;
	public void setTitle(String title) {
		super.setTitle("�й����壺Ĭ�Ϻ�������");
	}
	public Chess() {
		board = new ChessBoard(45, 45, 9, 10);
		record = board.record;
		setTitle("�й����壺Ĭ�Ϻ�������");
		con = getContentPane();
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, board, record);
		split.setDividerSize(5);
		split.setDividerLocation(460);
		con.add(split, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		setBounds(60, 20, 670, 540);
		con.validate();
		validate();
	}
	
	public static void main(String args[]) {
		new Chess();
	}
}
