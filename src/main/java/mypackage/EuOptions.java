package main.java.mypackage;

import java.lang.Math;

public class EuOptions {
    public static double priceOption(
            double S, double K, double r, double sigma, double T, int steps, boolean isCall
    ) {
        double dt = T / steps;
        double u = Math.exp(sigma * Math.sqrt(dt));
        double d = 1 / u;
        double p = (Math.exp(r * dt) - d) / (u - d);
        double q = 1 - p;

        double[] prices = new double[steps + 1];
        double[] values = new double[steps + 1];

        // Compute final step values
        for (int i = 0; i <= steps; i++) {
            prices[i] = S * Math.pow(u, steps - i) * Math.pow(d, i);
            values[i] = isCall ? Math.max(0, prices[i] - K) : Math.max(0, K - prices[i]);
        }

        // Work backward
        for (int j = steps - 1; j >= 0; j--) {
            for (int i = 0; i <= j; i++) {
                values[i] = Math.exp(-r * dt) * (p * values[i] + q * values[i + 1]);
            }
        }

        return values[0];
    }

    public static void main(String[] args) {
        double S = 100, K = 100, r = 0.05, sigma = 0.2, T = 1;
        int steps = 100;
        boolean isCall = true;

        double price = priceOption(S, K, r, sigma, T, steps, isCall);
        System.out.println("Option Price: " + price);
    }
}