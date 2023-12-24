
import java.util.List;
import java.util.Map;

/**
 * The type Pow.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Pow.
     *
     * @param ex1 the ex 1
     * @param ex2 the ex 2
     */
    public Pow(Expression ex1, Expression ex2) {
        super(ex1, ex2);
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
        return (Math.pow(this.getEx1().evaluate(assignment), this.getEx2().evaluate(assignment)));
    }
    /**
     * A convenience method. Like the `evaluate(assignment)` method above,but uses an empty assignment.
     * @return the value
     */
    public double evaluateOld() throws Exception {
        if ((this.getEx1().simplify().evaluate() < 0) && (Math.abs(this.getEx2().simplify().evaluate()) < 1)) {
            throw new Exception();
        }
        if ((this.getEx1().simplify().evaluate() == 0) && (Math.abs(this.getEx2().simplify().evaluate()) < 0)) {
            throw new Exception();
        }
        return (Math.pow(this.getEx1().evaluate(), this.getEx2().evaluate()));
    }
    /**
     * A convenience method. Like the `evaluate(assignment)` method above,but uses an empty assignment.
     * @return the value
     */
    public double evaluate() throws Exception {
        if ((this.getEx1().simplify().evaluate() == 0) && (this.getEx2().simplify().evaluate()) <= 0) {
            throw new Exception();
        }
        if (((this.getEx1().simplify().evaluate() < 0) && (Math.abs(this.getEx2().simplify().evaluate()) < 1))
                && (this.getEx2().simplify().evaluate()) != 0) {
            throw new Exception();
        }
        return (Math.pow(this.getEx1().evaluate(), this.getEx2().evaluate()));
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
        return ("(" + this.getEx1().toString() + "^" + this.getEx2().toString() + ")");
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
        Expression e = new Pow(this.getEx1(), this.getEx2());
        Expression e1;
        Expression e2;
        if (this.getEx1().toString().contains(var)) {
            e1 = this.getEx1().assign(var, expression);
        } else {
            e1 = this.getEx1();
        }
        if (this.getEx2().toString().contains(var)) {
            e2 = this.getEx2().assign(var, expression);
        } else {
            e2 = this.getEx2();
        }
        return (new Pow(e1, e2));
    }
    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     * @param var the variable we differentiate by
     * @return the new Expression
     */
    public Expression differentiate(String var) {
        double e = Math.E;
        Expression ex1 = this.getEx1();
        Expression ex2 = this.getEx2();
        Expression dEx1 = this.getEx1().differentiate(var);
        Expression dEx2 = this.getEx2().differentiate(var);
        Expression left = new Pow(ex1, ex2);
        Expression right1 = new Mult(dEx1, new Div(ex2, ex1));
        Expression right2 = new Mult(dEx2, new Log(new Var("e"), ex1));
        return (new Mult(left, new Plus(right1, right2)));
    }
    /**
     * Simplify the expression.
     *
     * @return the new Expression
     */
    public Expression simplify() {
        Expression ex1 = this.getEx1();
        Expression ex2 = this.getEx2();
        if (this.getVariables().size() == 0) {
            try {
                return (new Num(this.evaluate()));
            } catch (Exception exception) {
                //
            }
        }
        return (new Pow(ex1.simplify(), ex2.simplify()));
    }
}
