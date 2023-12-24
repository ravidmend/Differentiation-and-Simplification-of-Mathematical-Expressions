# Differentiation and Simplification of Mathematical Expressions

This project implements a system for representing, evaluating, differentiating, and simplifying mathematical expressions. The expressions can include variables, unary and binary operations, and constants. The system is designed to work within a recursive framework, utilizing polymorphism, inheritance, and class hierarchies.


## Part 1: Mathematical Expressions

### Expression Interface

The project begins with an interface called `Expression`, which supports the following operations:

- **evaluate:** Evaluate the expression using variable values provided in the assignment.
- **getVariables:** Retrieve a list of variables in the expression.
- **toString:** Get a readable string representation of the expression.
- **assign:** Return a new expression with variable occurrences replaced by the provided expression.

#### Implementing Classes

The following classes are implemented, each corresponding to an atomic expression:

- `Num`, `Var`: Representing numbers and variables.
- Unary Expressions: `Sin`, `Cos`, `Neg`.
- Binary Expressions: `Plus`, `Minus`, `Mult`, `Div`, `Pow`, `Log`.

example:

```java
Expression e = new Sin(
    new Pow(
        new Mul(
            new Plus(
                new Mul(new Num(2), new Var("x")),
                new Var("y")),
            new Num(4)),
        new Var("x")));
```
        
      
        
The tree is given below:

![img](https://github.com/ravidmend/Differentiation-and-Simplification-of-Mathematical-Expressions/blob/master/.idea/pictures/tree.png)

#### Class Hierarchy

An abstract class hierarchy is established with `BaseExpression`, `UnaryExpression`, and `BinaryExpression`. Shared code is placed in base classes to promote code reusability.
![img](https://github.com/ravidmend/Differentiation-and-Simplification-of-Mathematical-Expressions/blob/master/.idea/pictures/Class%20Hierarchy.png)
## Part 2: Automatic Differentiation

A `differentiate` method is added to the `Expression` interface, allowing the differentiation of expressions with respect to a given variable.

### Example Usage

```java
Expression e = new Pow(new Var("x"), new Num(4));

Expression de = e.differentiate("x");
```
System.out.println(de); // Outputs: 4*(x^3)


## Part 3: Simplification

A `simplify` method is added to the `Expression` interface for simplifying expressions.

### Supported Simplifications

- x * 1 = x
- x * 0 = 0
- x + 0 = x
- x / x = 1
- x / 1 = x
- x - 0 = x
- 0 - x = -x
- x - x = 0
- log(x, x) = 1
- An expression without variables evaluates to its result.

### Example Usage
```java
Expression e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));

System.out.println(e.differentiate("x"));
// (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
```
```java
System.out.println(e.differentiate("x").simplify());
// (((x + y) ^ 2.0) * (2.0 / (x + y)))
```
```java
e = new Pow(new Var("e"), new Var("x"));
System.out.println(e.differentiate("x"));
// ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
```
```java
System.out.println(e.differentiate("x").simplify());
// (e ^ x)
```
