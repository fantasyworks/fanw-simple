package fanw.freecalculator.operator;
import java.math.BigDecimal;
import java.util.Stack;

public class Power implements Operator{

	@Override
	public void execute(Stack<BigDecimal> stackTemp) {
		BigDecimal b=stackTemp.pop();
		BigDecimal a=stackTemp.pop();
		a=a.pow(b.intValue());
		stackTemp.push(a);
	}
	
	@Override
	public int order() {
		return OperatorOrder.OPERATION_ORDER.POWER.order;
	}
}
