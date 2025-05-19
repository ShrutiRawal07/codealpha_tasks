import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Holding {
    String symbol;
    int quantity;
    double avgBuyPrice;

    Holding(String symbol, int quantity, double avgBuyPrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.avgBuyPrice = avgBuyPrice;
    }
}

public class StockTradingPlatform {
    private static final Map<String, Stock> market = new HashMap<>();
    private static final Map<String, Holding> portfolio = new HashMap<>();
    private static double balance = 10000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeMarket();

        int choice;
        do {
            System.out.println("\n=== Stock Trading Platform ===");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewMarket();
                case 2 -> buyStock(scanner);
                case 3 -> sellStock(scanner);
                case 4 -> viewPortfolio();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void initializeMarket() {
        market.put("AAPL", new Stock("AAPL", 175.50));
        market.put("GOOGL", new Stock("GOOGL", 142.30));
        market.put("TSLA", new Stock("TSLA", 192.70));
        market.put("AMZN", new Stock("AMZN", 120.20));
    }

    private static void viewMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock stock : market.values()) {
            System.out.printf("%s: $%.2f\n", stock.symbol, stock.price);
        }
    }

    private static void buyStock(Scanner scanner) {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.next().toUpperCase();

        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int qty = scanner.nextInt();

        Stock stock = market.get(symbol);
        double totalCost = stock.price * qty;

        if (totalCost > balance) {
            System.out.println("Not enough balance.");
            return;
        }

        balance -= totalCost;

        if (portfolio.containsKey(symbol)) {
            Holding h = portfolio.get(symbol);
            double totalShares = h.quantity + qty;
            h.avgBuyPrice = ((h.avgBuyPrice * h.quantity) + totalCost) / totalShares;
            h.quantity += qty;
        } else {
            portfolio.put(symbol, new Holding(symbol, qty, stock.price));
        }

        System.out.printf("Bought %d shares of %s for $%.2f. Remaining balance: $%.2f\n",
                qty, symbol, totalCost, balance);
    }

    private static void sellStock(Scanner scanner) {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.next().toUpperCase();

        if (!portfolio.containsKey(symbol)) {
            System.out.println("You don't own this stock.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = scanner.nextInt();

        Holding h = portfolio.get(symbol);
        if (qty > h.quantity) {
            System.out.println("You don't have enough shares.");
            return;
        }

        Stock stock = market.get(symbol);
        double totalGain = stock.price * qty;
        h.quantity -= qty;
        balance += totalGain;

        if (h.quantity == 0) {
            portfolio.remove(symbol);
        }

        System.out.printf("Sold %d shares of %s for $%.2f. New balance: $%.2f\n",
                qty, symbol, totalGain, balance);
    }

    private static void viewPortfolio() {
        System.out.println("\n--- Portfolio ---");
        if (portfolio.isEmpty()) {
            System.out.println("No holdings.");
        } else {
            for (Holding h : portfolio.values()) {
                Stock marketPrice = market.get(h.symbol);
                double currentValue = marketPrice.price * h.quantity;
                double cost = h.avgBuyPrice * h.quantity;
                double gain = currentValue - cost;

                System.out.printf("%s - Qty: %d, Avg Buy: $%.2f, Current: $%.2f, Value: $%.2f, Gain/Loss: $%.2f\n",
                        h.symbol, h.quantity, h.avgBuyPrice, marketPrice.price, currentValue, gain);
            }
        }

        System.out.printf("Available Balance: $%.2f\n", balance);
    }
}
