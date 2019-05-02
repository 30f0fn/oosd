package edu.umb.cs680.hw15;

import java.util.Comparator;
import java.time.LocalDateTime;

public class ApfsTimestampComparator implements Comparator<ApfsFSElement> {
    public int compare(ApfsFSElement e1, ApfsFSElement e2) {
        return Comparator.<LocalDateTime>naturalOrder().compare(e1.getLastModified(),
                                                         e2.getLastModified());
    }
}
