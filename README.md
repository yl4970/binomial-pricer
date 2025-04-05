**Binomial Pricer**

A simple Java implementation of the binomial options pricing model for European-style options. This project calculates option prices using user-defined parameters such as strike, spot, volatility, interest rate, and number of steps in the binomial tree. It’s designed for educational purposes and as a foundation for extending into American and exotic options.

**Usage**

Update parameters in EuOptions.java and run:

```bash
javac EuOptions.java
java main.java.mypackage.EuOptions
```

**Folder Structure**

```aiignore
binomial_pricer/
│── src/main/java/mypackage/
│       ├── main.java.mypackage.BinomialPricer.java
│       ├── OptionType.java
│       ├── main.java.mypackage.Main.java
│── pom.xml (if using Maven)
│── README.md
```
