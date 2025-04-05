package main.java.mypackage;

public class Main {
    public static void main(String[] args) {
        AmOptions pricer = new AmOptions(
                100.0,  // Spot price
                100.0,  // Strike price
                0.05,   // Risk-free rate (5%)
                0.2,    // Volatility (20%)
                1.0,    // Time to maturity (1 year)
                100,    // Number of steps
                OptionType.CALL  // Call option
        );

        double price = pricer.price();
        System.out.println("American Call Option Price: " + price);
    }
}