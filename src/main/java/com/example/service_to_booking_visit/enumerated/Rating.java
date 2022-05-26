package com.example.service_to_booking_visit.enumerated;


public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final Integer score;

    Rating(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
