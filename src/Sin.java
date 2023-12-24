
import java.util.List;
import java.util.Map;

/**
 * The type Sin.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Sin.
     *
     * @param ex1 the ex 1
     */
    public Sin(Expression ex1) {
        super(ex1);
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
        List<String> myList = this.getVariables();
        for (int i = 0; i < myList.size(); i++) {
            if (!(assignment.containsKey(myList.get(i)))) {
                throw new Exception();
            }
        }
        return (Math.sin(this.getEx1().evaluate(assignment) * Math.PI / 180));
    }
    /**
     * A convenience method. Like the `evaluate(assignment)` method above,but uses an empty assignment.
     * @return the value
     */
    public double evaluate() throws Exception {
        return (Math.sin(this.getEx1().evaluate() * Math.PI / 180));
    }
    /**
     * Returns a list of the variables in the expression.
     * @return the list
     */
    public List<String> getVariables() {
        return (super.getVariables());
    }
    /**
     * Returns a nice string representation of the expression.
     * @return the String
     */
    public String toString() {
        return ("sin(" + this.getEx1().toString() + ")");
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
        Expression e1;
        if (this.getEx1().toString().contains(var)) {
            e1 = this.getEx1().assign(var, expression);
        } else {
            e1 = this.getEx1();
        }
        return (new Sin(e1));
    }
    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     * @param var the variable we differentiate by
     * @return the new Expression
     */
    public Expression differentiate(String var) {
        Expression ex1 = this.getEx1();
        Expression dEx1 = this.getEx1().differentiate(var);
        return (new Mult(new Cos(ex1), dEx1));
    }
    /**
     * Simplify the expression.
     *
     * @return the new Expression
     */
    public Expression simplify() {
        Expression ex1 = this.getEx1();
        if (this.getVariables().size() == 0) {
            try {
                return (new Num(this.evaluate()));
            } catch (Exception exception) {
                //
            }
        }
        return (new Sin(ex1.simplify()));
    }
}
