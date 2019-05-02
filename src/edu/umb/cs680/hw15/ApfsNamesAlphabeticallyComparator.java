package edu.umb.cs680.hw15;

import java.util.Comparator;

public class ApfsNamesAlphabeticallyComparator implements Comparator<ApfsFSElement> {
    public int compare(ApfsFSElement e1, ApfsFSElement e2) {
        return Comparator.<String>naturalOrder().compare(e1.getName(), e2.getName());
    }
}
