package Interpreter;

public class Multiply extends BinaryExpression
{
    public Multiply(Expression left, Expression right)
    {
        super(left, right);
    }

    @Override
    public double calculate()
    {
        return left.calculate()*right.calculate();
    }
}
