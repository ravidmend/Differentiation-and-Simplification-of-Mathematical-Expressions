
import java.util.LinkedList;
import java.util.List;

/**
 * The type Unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression ex1;

    /**
     * Instantiates a new Unary expression.
     *
     * @param ex1 the ex 1
     */
    public UnaryExpression(Expression ex1) {
        this.ex1 = ex1;
    }

    /**
     * Gets variables.
     *
     * @return the variables
     */
    public List<String> getVariables() {
        List<String> list1 = ex1.getVariables();
        List<String> finalList = new LinkedList();
        for (int i = 0; i < list1.size(); i++) {
            if (!(finalList.contains(list1.get(i)))) {
                finalList.add(list1.get(i));
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
}
