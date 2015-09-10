import java.util.*;

class Account {
    private int balance = 0;

    public /* synchronized */ void deposit(int d) {
        balance += d;
    }

    public int balance() {
        return balance;
    }
}

class Customer extends Thread {
    private Account account;
    
    public Customer(Account account) {
        this.account = account;
    }
    
    public void run() {
        for(int i = 0; i < 1000; i++) {
            account.deposit(1);
        }
    }
}

public class Bank {
    private static final int NUM_THREADS = 2;
    
    public static void main(String[] args) throws InterruptedException {
        List<Customer> customers = new ArrayList<Customer>();
        Account account = new Account();
        
        for (int i = 0; i < NUM_THREADS; i++) {
            customers.add(new Customer(account));            
        }

        for (Customer customer : customers) {
            customer.start();
        }
        for (Customer customer : customers) {
            customer.join();
        }

        System.out.println("Final balance is " + account.balance());
    }
}

