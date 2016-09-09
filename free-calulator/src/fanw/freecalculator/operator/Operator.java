package fanw.freecalculator.operator;
import java.math.BigDecimal;
import java.util.Stack;

public interface Operator {
	public void execute(Stack<BigDecimal> stackTemp);
	public int order();
}
