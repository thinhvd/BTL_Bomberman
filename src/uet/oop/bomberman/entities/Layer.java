package uet.oop.bomberman.entities;

import java.util.Comparator;

public class Layer implements Comparator<Entity> {
    /**
     * Quan ly cac Entities chong len nhau tai 1 o.
     * Su dung Comparator so sanh layer cua cac Object de sap xep thu tu trong mang.
     * Giup sap xep thu tu render phu hop.
     */
    @Override
    public int compare(Entity o1, Entity o2) {
        return Integer.compare(o2.getLayer(), o1.getLayer());
    }
}