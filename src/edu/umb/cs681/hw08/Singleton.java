public class Singleton {

    private Singleton(){};

    private static Singleton instance = null;

    public static Singleton getInstance(){
        // not threadsafe, because one thread may call and return getInstance while another one is in the middle of executing it, so that instance gets assigned twice
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
