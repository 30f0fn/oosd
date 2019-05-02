package edu.umb.cs680.hw03;

public class Singleton {

    private static Singleton theInstance;
    private String label;

    private Singleton(String label){
        this.label = label;
    }; // hide constructor from clients

    public static Singleton getInstance() {
        if (theInstance == null) {
            theInstance = new Singleton("I am it");
        }
        return theInstance;
    }

    private static String getLabel(){
        Singleton singl = Singleton.getInstance();
        return singl.label;
    }

    public static void main(String args[]) {
        Singleton singl = Singleton.getInstance();
        System.out.printf("Singleton: an instance of myself is labeled \"%s\"\n",
                          getLabel());
    }

}
