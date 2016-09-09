package fanw.freecalculator.operator;

public interface OperatorOrder {
	public static enum OPERATION_ORDER{
		BRACKEY_OPEN(0)
		,BRACKEY_CLOSE(0)
		,MINUS(91)
		,PLUS(21)
		,MULTIPLY(21)
		,DIVISION(21)
		,POWER(21);
		
		public final int order;
	    private OPERATION_ORDER(int order) {
	        this.order = order;
	    }
	};
}
