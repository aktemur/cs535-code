class Chopstick {
    private int id;
    private boolean isTaken;

    public Chopstick(int id) {
        this.id = id;
        this.isTaken = false;
    }

    public void take() {
        isTaken = true;
    }

    public void put() {
        isTaken = false;
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
            think();
            leftStick.take();
            System.out.println(this + " acquired " + leftStick + ".");
            rightStick.take();
            System.out.println(this + " acquired " + rightStick + ".");
            eat();
            leftStick.put();
            System.out.println(this + " released " + leftStick + ".");
            rightStick.put();
            System.out.println(this + " released " + rightStick + ".");
        }
    }

    private void think() {
        System.out.println(this + " is thinking...");
    }

    private void eat() {
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
