package chinesechess;
public class ChessRule {
	ChessBoard board = null;
	ChessPiece piece = null;
	ChessPoint point[][];
	int startI, startJ, endI, endJ;

	public ChessRule(ChessBoard board, ChessPoint point[][]) {
		this.board = board;
		this.point = point;
	}

	public boolean movePieceRule(ChessPiece piece, int startI, int startJ, int endI, int endJ) {// 移动棋子的规则
		this.piece = piece;
		this.startI = startI;
		this.startJ = startJ;
		this.endI = endI;
		this.endJ = endJ;
		int minI = Math.min(startI, endI);
		int maxI = Math.max(startI, endI);
		int minJ = Math.min(startJ, endJ);
		int maxJ = Math.max(startJ, endJ);
		boolean Flag = false;// 判断是否可以走棋
		if (piece.getName().equals("車")) {
			//（横线、竖线均可）无子阻拦便可走（以下判断是否有子阻拦）。
			if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						Flag = false;
						break;
					}
				}
				if (j == maxJ) {
					Flag = true;
				}
			} else if (startJ == endJ) {
				int i = 0;
				for (i = minI + 1; i <= maxI - 1; i++) {
					if (point[i][startJ].isPiece()) {
						Flag = false;
						break;
					}
				}
				if (i == maxI) {
					Flag = true;
				}
			} else {
				Flag = false;
			}
		} else if (piece.getName().equals("马")) {
			//马走日（即先横着或直着走一格，然后再斜着走一个对角线）若行进的方向有子阻挡则不能行进。
			int xAxle = Math.abs(startI - endI);//x轴
			int yAxle = Math.abs(startJ - endJ);

			if (xAxle == 2 && yAxle == 1) {
				if (endI > startI) {
					if (point[startI + 1][startJ].isPiece()) {
						Flag = false;
					} else {
						Flag = true;
					}
				}
				if (endI < startI) {
					if (point[startI - 1][startJ].isPiece()) {
						Flag = false;
					} else {
						Flag = true;
					}
				}

			} else if (xAxle == 1 && yAxle == 2) {
				if (endJ > startJ) {
					if (point[startI][startJ + 1].isPiece()) {
						Flag = false;
					} else {
						Flag = true;
					}
				}
				if (endJ < startJ) {
					if (point[startI][startJ - 1].isPiece()) {
						Flag = false;
					} else {
						Flag = true;
					}
				}

			} else {
				Flag = false;
			}
		} else if (piece.getName().equals("象")) {
			//象走田（不能过河，“田”字中间有子不能走）
			int centerI = (startI + endI) / 2;
			int centerJ = (startJ + endJ) / 2;
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (xAxle == 2 && yAxle == 2 && endJ <= 5) {
				if (point[centerI][centerJ].isPiece()) {
					Flag = false;
				} else {
					Flag = true;
				}
			} else {
				Flag = false;
			}
		} else if (piece.getName().equals("相")) {
			//同“象”
			int centerI = (startI + endI) / 2;
			int centerJ = (startJ + endJ) / 2;
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (xAxle == 2 && yAxle == 2 && endJ >= 6) {
				if (point[centerI][centerJ].isPiece()) {
					Flag = false;
				} else {
					Flag = true;
				}
			} else {
				Flag = false;
			}
		} else if (piece.getName().equals("炮")) {
			//随意走（同“车”），要吃子的时候必须有“桥”
			int number = 0;
			if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						number++;
					}
				}
				if (number > 1) {
					Flag = false;
				} else if (number == 1) {
					if (point[endI][endJ].isPiece()) {
						Flag = true;
					}
				} else if (number == 0 && !point[endI][endJ].isPiece()) {
					Flag = true;
				}
			} else if (startJ == endJ) {
				int i = 0;
				for (i = minI + 1; i <= maxI - 1; i++) {
					if (point[i][startJ].isPiece()) {
						number++;
					}
				}
				if (number > 1) {
					Flag = false;
				} else if (number == 1) {
					if (point[endI][endJ].isPiece()) {
						Flag = true;
					}
				} else if (number == 0 && !point[endI][endJ].isPiece()) {
					Flag = true;
				}
			} else {
				Flag = false;
			}
		} else if (piece.getName().equals("兵")) {
			//在己方只能前进，（过河后）在敌方可走前、左、右。（一次走一步）
			int xAxle = Math.abs(startI - endI);
			if (endJ >= 6) {
				if (startJ - endJ == 1 && xAxle == 0) {
					Flag = true;
				} else {
					Flag = false;
				}
			} else if (endJ <= 5) {
				if ((startJ - endJ == 1) && (xAxle == 0)) {
					Flag = true;
				} else if ((endJ - startJ == 0) && (xAxle == 1)) {
					Flag = true;
				} else {
					Flag = false;
				}
			}
		} else if (piece.getName().equals("卒")) {
			//同“兵”
			int xAxle = Math.abs(startI - endI);
			if (endJ <= 5) {
				if (endJ - startJ == 1 && xAxle == 0) {
					Flag = true;
				} else {
					Flag = false;
				}
			} else if (endJ >= 6) {
				if ((endJ - startJ == 1) && (xAxle == 0)) {
					Flag = true;
				} else if ((endJ - startJ == 0) && (xAxle == 1)) {
					Flag = true;
				} else {
					Flag = false;
				}
			}
		} else if (piece.getName().equals("士") || (piece.getName().equals("仕"))) {
			//只能走己方九宫中的斜线
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4 && xAxle == 1 && yAxle == 1) {
				Flag = true;
			} else {
				Flag = false;
			}
		} else if ((piece.getName().equals("帅")) || (piece.getName().equals("将"))) {
			//只能走己方九宫中的直线
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4) {
				if ((xAxle == 1 && yAxle == 0) || (xAxle == 0 && yAxle == 1)) {
					Flag = true;
				} else {
					Flag = false;
				}
			} else {
				Flag = false;
			}
		}
		return Flag;
	}
}
