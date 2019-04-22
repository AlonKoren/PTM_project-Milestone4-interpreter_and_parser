package alon.flightsim.language.interpreter.calculator;

import alon.flightsim.Environment;

public class Number implements Expression
{
    private Environment environment;
    private String value;

    public Number(String value,Environment environment) {
        this.environment = environment;
        this.value = value;
    }

    public Double getValue()
    {
        try {
            return environment.getValue(this.value);
        }catch (RuntimeException ignore){
            return Double.parseDouble(value);
        }
    }

    public void setValue(double value)
    {
        this.value = value+"";
    }

    @Override
    public Double calculate()
    {
        return this.getValue();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
