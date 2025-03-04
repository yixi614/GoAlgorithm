package SystemDesign;

public class Singleton {
    // The private static instance variable
    private static volatile Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Prevent reflection instantiation
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the instance.");
        }
    }

    // Public static method to get the instance
    public static Singleton getInstance() {
        // Double-checked locking
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method
    public void doSomething() {
        System.out.println("Singleton is doing something");
    }
}