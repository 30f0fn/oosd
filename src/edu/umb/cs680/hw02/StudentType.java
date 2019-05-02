package edu.umb.cs680.hw02;

public enum StudentType {
    INTERNATIONAL {
        @Override
            public float getTuitionFactor() {
            return 2;
        }
            
    },

    INSTATE {
        @Override
            public float getTuitionFactor() {
            return .5f;
        }
    },

    OUTOFSTATE {
        @Override
            public float getTuitionFactor() {
            return 1;
        }
    };

    public abstract float getTuitionFactor();

}
