package edu.umb.cs680.hw11;

public class Glass {

    private final float capacity;
    private float filledTo;
    
    protected void observePour(Pour pour) {
        filledTo += pour.getVolume();
        check();
    }

    protected void observeSip (Sip sip) {
        filledTo -= sip.getVolume();
        check();
    }

    public Glass(Float volume) {
        this.capacity = volume;
        this.filledTo = 0;
    }

    public float measureBeer() {
        return this.filledTo;
    }

    public void dump() {
        this.filledTo = 0;
    }

    private void check() {
        if(filledTo > capacity) {
            throw new RuntimeException("oops, you spilled some beer.");
        }
        if(filledTo < 0) {
            throw new RuntimeException("the quantity of beer in your glass is negative.  you must have drunk too much.");
        }
    }

    public static void main(String[] args) {
        ObservableEnhanced<Pour> bartender = new ObservableEnhanced<Pour>();
        ObservableEnhanced<Sip> drinker = new ObservableEnhanced<Sip>();
        Glass glass = new Glass(100f);
        bartender.addObserver(glass::observePour);
        drinker.addObserver(glass::observeSip);
        Pour pour = new Pour(80f);
        bartender.notifyObservers(pour);
        Sip sip = new Sip(15f);
        drinker.notifyObservers(sip);
        System.out.printf("Glass: after pouring %f and sipping %f, there is %f of beer left\n",
                          pour.getVolume(),
                          sip.getVolume(),
                          glass.measureBeer());
    }

}
