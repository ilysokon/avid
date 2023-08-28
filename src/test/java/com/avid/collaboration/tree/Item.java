package com.avid.collaboration.tree;

import java.util.Objects;

class Item implements Comparable<Item> {
    private final Number number;

    Item(final Number number) {
        this.number = number;
    }

    public int compareTo(Item item) {
        return Integer.compare(number.intValue(), item.number.intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(number, item.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
