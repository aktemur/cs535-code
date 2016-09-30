class Chopstick {
    private int id;
    private boolean isTaken;
    
    public Chopstick(int id) {
        this.id = id;
        this.isTaken = false;
    }

    public void take(Philosopher taker) {
        isTaken = true;
        System.out.println(taker + " took " + this + ".");
    }

    public void put(Philosopher taker) {
        isTaken = false;
        System.out.println(taker + " put " + this + ".");
    }

    public boolean isTaken() {
        return isTaken;
    }

    public String toString() {
        return "Chopstick " + id;
    }
}

class Philosopher extends Thread {
    private int id;
    private Chopstick leftStick, rightStick;

    public Philosopher(int id, Chopstick leftStick, Chopstick rightStick) {
        this.id = id;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }

    public void run() {
        while(true) {
            try{
                think();
                leftStick.take(this);
                rightStick.take(this);

                eat();

                leftStick.put(this);
                rightStick.put(this);
            } catch(InterruptedException e) {
                return;
            }
        }
    }

    private void think() throws InterruptedException {
        sleep(500);
        System.out.println(this + " is thinking...");
    }

    private void eat() throws InterruptedException {
        sleep(200);
        System.out.println(this + " is eating.");
    }

    public String toString() {
        return "Philosopher " + id;
    }
}
    
public class Philosophers {
    public static final int NUM_PHILOSOPHERS = 5;
    
    public static void main(String[] args) throws InterruptedException {
        Chopstick[] sticks = new Chopstick[NUM_PHILOSOPHERS];
        for (int i = 0; i < sticks.length; i++) {
            sticks[i] = new Chopstick(i);
        }
        
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i, sticks[i], sticks[(i + 1) % NUM_PHILOSOPHERS]);
        }

        for (Philosopher phil : philosophers) {
            phil.start();
        }

        for (Philosopher phil : philosophers) {
            phil.join();
        }
    }   
}
