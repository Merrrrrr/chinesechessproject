package chinesechess;

public class ChessPoint {
	int x, y;
	boolean flag;
	ChessPiece piece = null;
	ChessBoard board = null;

	public ChessPoint(int x, int y, boolean boo) {
		this.x = x;
		this.y = y;
		flag = boo;
	}

	public boolean isPiece() {
		return flag;
	}

	public void setFlag(boolean boo) {
		flag = boo;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPiece(ChessPiece piece, ChessBoard board) {
		this.board = board;
		this.piece = piece;
		board.add(piece);
		int w = (board.unitWidth);
		int h = (board.unitHeight);
		piece.setBounds(x - w / 2, y - h / 2, w, h);// 棋格的宽度，高度和X轴长，Y轴长
		flag = true;
		board.validate();// 表单内容的验证
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public void reMovePiece(ChessPiece piece, ChessBoard board) {
		this.board = board;
		this.piece = piece;
		board.remove(piece);
		board.validate();
		flag = false;
	}
}