
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type Var.
 */
public class Var implements Expression {
    private String variant;

    /**
     * Instantiates a new Var.
     *
     * @param variant the variant
     */
    public Var(String variant) {
        this.variant = variant;
    }
    /**
     * Evaluate.
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment the number
     * @return the value
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!(assignment.containsKey(this.variant))) {
            throw new Exception();
        }
        return assignment.get(variant);
    }
    /**
     * A convenience method. Like the `evaluate(assignment)` method above,but uses an empty assignment.
     * @return the value
     */
    public double evaluate() throws Exception {
        throw new Exception();
    }
    /**
     * Returns a list of the variables in the expression.
     * @return the list
     */
    public List<String> getVariables() {
        List<String> myList = new LinkedList();
        myList.add(this.variant);
        return (myList);
    }
    /**
     * Returns a nice string representation of the expression.
     * @return the String
     */
    public String toString() {
        return (this.variant);
    }
    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param expression the expression.
     * @param var the variant assign to.
     * @return the Expression
     */
    public Expression assign(String var, Expression expression) {
        Expression e = new Var(this.variant);
        if (var.equals(this.variant)) {
            return expression;
        }
        return this;
    }
    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     * @param var the variable we differentiate by
     * @return the new Expression
     */
    public Expression differentiate(String var) {
        if (var.equals(this.variant)) {
            return (new Num(1));
        }
        return (new Num(0));
    }
    /**
     * Simplify the expression.
     *
     * @return the new Expression
     */
    public Expression simplify() {
        return (this);
    }
}
