package main.java.mypackage;

public class AmOptions {
    private final double spotPrice;
    private final double strikePrice;
    private final double riskFreeRate;
    private final double volatility;
    private final double timeToMaturity;
    private final int steps;
    private final OptionType optionType;

    public AmOptions(double spotPrice, double strikePrice, double riskFreeRate, double volatility,
                          double timeToMaturity, int steps, OptionType optionType) {
        this.spotPrice = spotPrice;
        this.strikePrice = strikePrice;
        this.riskFreeRate = riskFreeRate;
        this.volatility = volatility;
        this.timeToMaturity = timeToMaturity;
        this.steps = steps;
        this.optionType = optionType;
    }

    public double price() {
        double dt = timeToMaturity / steps;
        double u = Math.exp(volatility * Math.sqrt(dt));
        double d = 1 / u;
        double discount = Math.exp(-riskFreeRate * dt);
        double p = (Math.exp(riskFreeRate * dt) - d) / (u - d);

        // Store option values at each node
        double[] optionValues = new double[steps + 1];

        // Compute option payoff at final step
        for (int i = 0; i <= steps; i++) {
            double stockPrice = spotPrice * Math.pow(u, steps - i) * Math.pow(d, i);
            optionValues[i] = Math.max((optionType == OptionType.CALL) ? (stockPrice - strikePrice) : (strikePrice - stockPrice), 0);
        }

        // Backward induction for American option
        for (int j = steps - 1; j >= 0; j--) {
            for (int i = 0; i <= j; i++) {
                double stockPrice = spotPrice * Math.pow(u, j - i) * Math.pow(d, i);
                double intrinsicValue = Math.max((optionType == OptionType.CALL) ? (stockPrice - strikePrice) : (strikePrice - stockPrice), 0);
                optionValues[i] = Math.max(intrinsicValue, discount * (p * optionValues[i] + (1 - p) * optionValues[i + 1]));
            }
        }

        return optionValues[0];
    }
}