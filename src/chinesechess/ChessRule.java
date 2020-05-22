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

	public boolean movePieceRule(ChessPiece piece, int startI, int startJ, int endI, int endJ) {// �ƶ����ӵĹ���
		this.piece = piece;
		this.startI = startI;
		this.startJ = startJ;
		this.endI = endI;
		this.endJ = endJ;
		int minI = Math.min(startI, endI);
		int maxI = Math.max(startI, endI);
		int minJ = Math.min(startJ, endJ);
		int maxJ = Math.max(startJ, endJ);
		boolean Flag = false;// �ж��Ƿ��������
		if (piece.getName().equals("܇")) {
			//�����ߡ����߾��ɣ�������������ߣ������ж��Ƿ�������������
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
		} else if (piece.getName().equals("��")) {
			//�����գ����Ⱥ��Ż�ֱ����һ��Ȼ����б����һ���Խ��ߣ����н��ķ��������赲�����н���
			int xAxle = Math.abs(startI - endI);//x��
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
		} else if (piece.getName().equals("��")) {
			//��������ܹ��ӣ�������м����Ӳ����ߣ�
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
		} else if (piece.getName().equals("��")) {
			//ͬ����
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
		} else if (piece.getName().equals("��")) {
			//�����ߣ�ͬ����������Ҫ���ӵ�ʱ������С��š�
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
		} else if (piece.getName().equals("��")) {
			//�ڼ���ֻ��ǰ���������Ӻ��ڵз�����ǰ�����ҡ���һ����һ����
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
		} else if (piece.getName().equals("��")) {
			//ͬ������
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
		} else if (piece.getName().equals("ʿ") || (piece.getName().equals("��"))) {
			//ֻ���߼����Ź��е�б��
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4 && xAxle == 1 && yAxle == 1) {
				Flag = true;
			} else {
				Flag = false;
			}
		} else if ((piece.getName().equals("˧")) || (piece.getName().equals("��"))) {
			//ֻ���߼����Ź��е�ֱ��
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
