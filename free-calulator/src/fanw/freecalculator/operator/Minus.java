package fanw.freecalculator.operator;
import java.math.BigDecimal;
import java.util.Stack;

public class Minus implements Operator{

	@Override
	public void execute(Stack<BigDecimal> stackTemp) {
		BigDecimal a=stackTemp.pop();
		a=a.multiply(new BigDecimal(-1));
		stackTemp.push(a);
	}
	
	@Override
	public int order() {
		return OperatorOrder.OPERATION_ORDER.MINUS.order;
	}
}
