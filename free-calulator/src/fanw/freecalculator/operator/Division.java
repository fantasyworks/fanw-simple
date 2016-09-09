package fanw.freecalculator.operator;
import java.math.BigDecimal;
import java.util.Stack;

public class Division implements Operator{

	@Override
	public void execute(Stack<BigDecimal> stackTemp) {
		BigDecimal b=stackTemp.pop();
		BigDecimal a=stackTemp.pop();
		a=new BigDecimal(a.doubleValue()/b.doubleValue());
		stackTemp.push(a);
	}
	
	@Override
	public int order() {
		return OperatorOrder.OPERATION_ORDER.DIVISION.order;
	}
}
