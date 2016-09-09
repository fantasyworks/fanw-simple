package fanw.freecalculator.operator;
import java.math.BigDecimal;
import java.util.Stack;

public class BrackeyOpen implements Operator{

	@Override
	public void execute(Stack<BigDecimal> stackTemp) {
	}

	@Override
	public int order() {
		return OperatorOrder.OPERATION_ORDER.BRACKEY_OPEN.order;
	}
}
