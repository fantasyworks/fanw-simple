package fanw.freecalculator;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import fanw.freecalculator.operator.BrackeyClose;
import fanw.freecalculator.operator.BrackeyOpen;
import fanw.freecalculator.operator.Operator;

public class FreeCalculator {
	private static enum SORTING{NONE,NUMBER,OPERATPR,BRACKEY_CLOSE};
	private Queue<String> queueAfter;
	private Stack<BigDecimal> stackTemp;
	private List<String> listSplitFormula;
	private Stack<String> stackOp=new Stack<String>();
	private String formula;
	
	private Map<String,Operator> mapOperator;
	
	public FreeCalculator(Map<String,Operator> mapOperator){
		queueAfter=new ArrayDeque<String>();
		stackTemp=new Stack<BigDecimal>();
		listSplitFormula=new LinkedList<String>();
		stackOp=new Stack<String>();
		this.mapOperator=mapOperator;
		
		mapOperator.put("(", new BrackeyOpen());
		mapOperator.put(")", new BrackeyClose());
	}
	
	public FreeCalculator(Map<String,Operator> mapOperator,String formula){
		this(mapOperator);
		setFormula(formula);
	}
	
	public void setFormula(String formula){
		this.formula=formula;
	}
	
	public BigDecimal calculate() throws Exception{
		queueAfter.clear();
		stackTemp.clear();
		listSplitFormula.clear();
		stackOp.clear();
		
		splitFormula();
		loadAterCalcuate();
		return calculateAterOperator();
	}
	
	private BigDecimal calculateAterOperator() throws Exception{
		String value=null;
		do{
			value=queueAfter.poll();
			if(value==null) break;
			
			Operator op=mapOperator.get(value);
			if(op==null){
				stackTemp.push(new BigDecimal(value));
			}else{
				op.execute(stackTemp);
			}
		}while(true);
		return stackTemp.pop();
	}
	
	private void loadAterCalcuate() throws Exception{		
		SORTING preSorting=SORTING.NONE;
		for(String value : listSplitFormula){
			Operator opOrder=mapOperator.get(value);
			
			if(opOrder==null){
				if(preSorting==SORTING.BRACKEY_CLOSE)
					pushOperator("*");
				queueAfter.offer(value);
				preSorting=SORTING.NUMBER;
			}else{
				if(opOrder instanceof BrackeyOpen){
					if(preSorting==SORTING.NUMBER || preSorting==SORTING.BRACKEY_CLOSE){
						pushOperator("*");
					}
				}
				
				pushOperator(value);
				
				if(opOrder instanceof BrackeyClose){
					preSorting=SORTING.BRACKEY_CLOSE;
				}else{
					preSorting=SORTING.OPERATPR;
				}
			}
		}
		
		while(stackOp.isEmpty()==false){
			queueAfter.offer(stackOp.pop());
		}
	}
	
	private void pushOperator(String op) throws Exception{
		String prevOp=null;
		if(")".equals(op)){
			do{
				prevOp=stackOp.pop();
				if("(".equals(prevOp))break;
				queueAfter.offer(prevOp);
			}while(true);
		}else if("(".equals(op)){
			stackOp.push(op);
		}else{
			String tmpOp=op;
			while(stackOp.isEmpty()==false){
				prevOp=stackOp.pop();
				
				if(mapOperator.get(tmpOp).order() <=mapOperator.get(prevOp).order()){
					queueAfter.offer(prevOp);
				}else{
					stackOp.push(prevOp);
					break;
				}
			}
			stackOp.push(op);
		}
	}
	
	private void splitFormula() throws Exception{
		if(formula==null)
			throw new Exception("式がありません。");
		
		char[] cFormula=formula.toCharArray();
		StringBuffer sb=new StringBuffer();
		
		SORTING preSorting=SORTING.NONE;
		SORTING currentSorting;

		for(char c : cFormula){
			currentSorting=isNumber(c) ? SORTING.NUMBER : SORTING.OPERATPR;
			
			if((currentSorting!=preSorting || currentSorting==SORTING.OPERATPR) && preSorting!=SORTING.NONE){
				listSplitFormula.add(sb.toString());
				sb.delete(0, sb.length());
			}
			sb.append(c);
			preSorting=currentSorting;
		}
		listSplitFormula.add(sb.toString());
	}
	
	private boolean isNumber(char c){
		if((c>='0' && c<='9') || c=='.')
			return true;
		else
			return false;
	}
}