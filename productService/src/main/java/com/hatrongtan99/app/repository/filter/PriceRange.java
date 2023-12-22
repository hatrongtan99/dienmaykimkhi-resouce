package com.hatrongtan99.app.repository.filter;

import lombok.Getter;

@Getter
public class PriceRange {
    private double startRange;
    private double endRange;

    public PriceRange(double startRange, double endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

}
