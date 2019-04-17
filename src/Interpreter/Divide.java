package Interpreter;

public class Divide extends BinaryExpression
{
    public Divide(Expression left, Expression right)
    {
        super(left, right);
    }

    @Override
    public double calculate()
    {
        return left.calculate()/right.calculate();
    }
}
