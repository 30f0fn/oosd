package edu.umb.cs681.hw14;

import edu.umb.cs681.utilities.RandomString;

class Product {
    private String name;

    public Product() {
        RandomString randy = new RandomString();
        this.name = randy.nextString();
    }

    public Product(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

}
