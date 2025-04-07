import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void updatePrice() {
        // Simulate price change between -5% to +5%
        double changePercent = (Math.random() - 0.5) * 0.10;
        price += price * changePercent;
        price = Math.round(price * 100.0) / 100.0;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double cash;

    Portfolio(double initialCash) {
        this.cash = initialCash;
    }

    void buyStock(String stockName, int quantity, double price) {
        double totalCost = price * quantity;
        if (cash >= totalCost) {
            cash -= totalCost;
            holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stockName);
        } else {
            System.out.println("Not enough cash to buy.");
        }
    }

    void sellStock(String stockName, int quantity, double price) {
        int owned = holdings.getOrDefault(stockName, 0);
        if (owned >= quantity) {
            holdings.put(stockName, owned - quantity);
            cash += price * quantity;
            System.out.println("Sold " + quantity + " shares of " + stockName);
        } else {
            System.out.println("You don’t own enough shares to sell.");
        }
    }

    void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n----- Portfolio -----");
        System.out.println("Cash: $" + Math.round(cash * 100.0) / 100.0);
        double total = cash;

        for (String stock : holdings.keySet()) {
            int qty = holdings.get(stock);
            double price = market.get(stock).price;
            double value = qty * price;
            System.out.println(stock + ": " + qty + " shares x $" + price + " = $" + Math.round(value * 100.0) / 100.0);
            total += value;
        }

        System.out.println("Total Portfolio Value: $" + Math.round(total * 100.0) / 100.0);
        System.out.println("---------------------\n");
    }
}

public class StockTradingApp {

    static Map<String, Stock> market = new HashMap<>();
    static Portfolio portfolio = new Portfolio(10000); // Start with $10,000
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initMarket();

        System.out.println("Welcome to the Simulated Stock Trading Platform!");
        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showMarket();
                    break;
                case "2":
                    buyStock();
                    break;
                case "3":
                    sellStock();
                    break;
                case "4":
                    portfolio.showPortfolio(market);
                    break;
                case "5":
                    updateMarketPrices();
                    System.out.println("Market prices updated.");
                    break;
                case "6":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void initMarket() {
        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("GOOGL", new Stock("GOOGL", 2700.0));
        market.put("TSLA", new Stock("TSLA", 700.0));
        market.put("AMZN", new Stock("AMZN", 3300.0));
    }

    static void showMenu() {
        System.out.println("\n1. View Market Data");
        System.out.println("2. Buy Stocks");
        System.out.println("3. Sell Stocks");
        System.out.println("4. View Portfolio");
        System.out.println("5. Simulate Market Changes");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    static void showMarket() {
        System.out.println("\n--- Market Data ---");
        for (String key : market.keySet()) {
            Stock s = market.get(key);
            System.out.println(s.name + ": $" + s.price);
        }
    }

    static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        portfolio.buyStock(symbol, qty, market.get(symbol).price);
    }

    static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        portfolio.sellStock(symbol, qty, market.get(symbol).price);
    }

    static void updateMarketPrices() {
        for (Stock stock : market.values()) {
            stock.updatePrice();
        }
    }
}
