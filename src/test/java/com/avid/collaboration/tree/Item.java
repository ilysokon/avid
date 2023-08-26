package com.avid.collaboration.tree;

public class Item implements Comparable<Item> {
    private final Number number;

    public Item(Number number) {
        this.number = number;
    }

    public int compareTo(Item item) {
        return Integer.compare(number.intValue(), item.number.intValue());
    }
}
