package chinesechess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class MakeChessManual extends JPanel implements ActionListener {
	JScrollPane scroll = null;
	ChessBoard board = null;
	ChessPoint[][] point;
	LinkedList<MoveStep> ChessManual = null;
	LinkedList<Object> EatPiece = null;
	JButton buttonUndo, buttonExit;
	int i = 0;

	public MakeChessManual(ChessBoard board, ChessPoint[][] point) {
		//创建对象
		this.board = board;
		this.point = point;
		scroll = new JScrollPane();
		ChessManual = new LinkedList<MoveStep>();
		EatPiece = new LinkedList<Object>();
		buttonUndo = new JButton("悔棋");
		buttonUndo.setFont(new Font("平体", Font.PLAIN, 20));
		buttonExit = new JButton("退出");
		buttonExit.setFont(new Font("平体", Font.PLAIN, 20));
		setLayout(new BorderLayout());
		add(buttonUndo, BorderLayout.NORTH);
		add(buttonExit, BorderLayout.SOUTH);
		buttonUndo.addActionListener(this);
		buttonExit.addActionListener(this);
	}

	public char numberToLetter(int n) {
		//棋盘坐标，为记录准备
		char c = '\0';
		switch (n) {
			case 1: c = 'A';break;
			case 2: c = 'B';break;
			case 3: c = 'C';break;
			case 4: c = 'D';break;
			case 5: c = 'E';break;
			case 6: c = 'F';break;
			case 7: c = 'G';break;
			case 8: c = 'H';break;
			case 9: c = 'I';break;
			case 10: c = 'J';break;
		}
		return c;
	}

	public void recordChessManual(ChessPiece piece, int startI, int startJ, int endI, int endJ) {
		//记录走棋内容
		Point pStart = new Point(startI, startJ);
		Point pEnd = new Point(endI, endJ);
		MoveStep step = new MoveStep(pStart, pEnd);
		ChessManual.add(step);
	}

	public void recordPieceEaten(Object object) {
		EatPiece.add(object);
	}

	public LinkedList<MoveStep> getChessManual() {
		return ChessManual;
	}

	public void actionPerformed(ActionEvent e) {

		if (ChessManual.size() > 0) {
			MoveStep lastStep = (MoveStep) ChessManual.getLast();
			ChessManual.removeLast();
			Object chesspiece = EatPiece.getLast();
			EatPiece.removeLast();
			String temp = chesspiece.toString();
			if (temp.equals("没吃棋子")) {
				int startI = lastStep.pStart.x;
				int startJ = lastStep.pStart.y;
				int endI = lastStep.pEnd.x;
				int endJ = lastStep.pEnd.y;
				ChessPiece piece = point[endI][endJ].getPiece();

				point[startI][startJ].setPiece(piece, board);
				(point[endI][endJ]).setFlag(false);

				if (piece.Category().equals(board.redteamcolor)) {
					board.redrun = true;
					board.blackrun = false;
				}
				if (piece.Category().equals(board.blackteamcolor)) {
					board.blackrun = true;
					board.redrun = false;
				}
			} else {
				ChessPiece removedPiece = (ChessPiece) chesspiece;
				int startI = lastStep.pStart.x;
				int startJ = lastStep.pStart.y;
				int endI = lastStep.pEnd.x;
				int endJ = lastStep.pEnd.y;
				ChessPiece piece = point[endI][endJ].getPiece();
				point[startI][startJ].setPiece(piece, board);
				point[endI][endJ].setPiece(removedPiece, board);
				(point[endI][endJ]).setFlag(true);

				if (piece.Category().equals(board.redteamcolor)) {
					board.redrun = true;
					board.blackrun = false;
				}
				if (piece.Category().equals(board.blackteamcolor)) {
					board.blackrun = true;
					board.redrun = false;
				}
			}
		}
		Object obj = e.getSource();
		if (obj == buttonExit) {
			System.exit(0);
		}
	}
}
