package chinesechess;
import java.awt.Point;
//棋子的开始着点结束着点（序列化）
@SuppressWarnings("serial")
public class MoveStep implements java.io.Serializable {
	public Point pStart, pEnd;

	public MoveStep(Point p1, Point p2) {
		pStart = p1;
		pEnd = p2;
	}
}
