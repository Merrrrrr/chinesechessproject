package chinesechess;
import java.awt.Point;
//���ӵĿ�ʼ�ŵ�����ŵ㣨���л���
@SuppressWarnings("serial")
public class MoveStep implements java.io.Serializable {
	public Point pStart, pEnd;

	public MoveStep(Point p1, Point p2) {
		pStart = p1;
		pEnd = p2;
	}
}
