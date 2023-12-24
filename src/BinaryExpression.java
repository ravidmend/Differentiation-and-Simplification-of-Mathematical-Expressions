
import java.util.LinkedList;
import java.util.List;

/**
 * The type Binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression ex1;
    private Expression ex2;


    /**
     * Instantiates a new Binary expression.
     *
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public BinaryExpression(Expression ex1, Expression ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }


    /**
     * Gets variables.
     *
     * @return the variables
     */
    public List<String> getVariables() {
        //make a list of the variables in the first expression
        List<String> list1 = ex1.getVariables();
        //make a list of the variables in the second expression
        List<String> list2 = ex2.getVariables();
        //will be the union of the 2 list without repetitive variables
        List<String> finalList = new LinkedList();
        for (int i = 0; i < list1.size(); i++) {
            if (!(finalList.contains(list1.get(i)))) {
                finalList.add(list1.get(i));
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            if (!(finalList.contains(list2.get(i)))) {
                finalList.add(list2.get(i));
            }
        }
        return finalList;
    }


    /**
     * Gets ex 1.
     *
     * @return the ex1
     */
    public Expression getEx1() {
        return (this.ex1);
    }


    /**
     * Gets ex 2.
     *
     * @return the ex2
     */
    public Expression getEx2() {
        return (this.ex2);
    }
}
