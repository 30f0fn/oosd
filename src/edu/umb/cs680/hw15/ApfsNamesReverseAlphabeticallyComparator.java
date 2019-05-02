package edu.umb.cs680.hw15;

import java.util.Comparator;

public class ApfsNamesReverseAlphabeticallyComparator implements Comparator<ApfsFSElement> {
    public int compare(ApfsFSElement e1, ApfsFSElement e2) {
        Comparator<String> nameComp = Comparator.<String>naturalOrder().reversed();
        int alphaCompRet = nameComp.compare(e1.getName(), e2.getName());
        return alphaCompRet;
    }
}
