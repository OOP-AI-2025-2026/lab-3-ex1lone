package org.example.task2;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Cart {
    private final Item[] items;
    private int size;

    public Cart(Item[] storage) {
        if (storage == null || storage.length == 0)
            throw new IllegalArgumentException("initial storage must be non-empty");
        this.items = storage;
        this.size = 0;
    }

    public void add(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (isFull()) throw new IllegalStateException("cart is full");
        this.items[this.size++] = item;
    }

    public void removeById(long id) {
        if (this.size == 0) return;
        int idx = indexOfId(id);
        if (idx == -1) return;
        for (int i = idx; i < this.size - 1; i++) items[i] = items[i + 1];
        items[--size] = null;
    }

    private int indexOfId(long id) {
        for (int i = 0; i < this.size; i++) if (items[i].getId() == id) return i;
        return -1;
    }

    public boolean isFull()  { return this.size == items.length; }
    public boolean isEmpty() { return this.size == 0; }
    public int size()        { return this.size; }

    public Item[] toArray() { return Arrays.copyOf(items, size); }

    public double total() {
        double s = 0;
        for (int i = 0; i < size; i++) s += items[i].getPrice();
        return s;
    }

    public Item peekLast() {
        if (size == 0) throw new NoSuchElementException("cart is empty");
        return items[size - 1];
    }

    @Override
    public String toString() {
        return "Cart{contents=" + Arrays.toString(toArray()) + "}\n";
    }

    // ✅ Додаємо main() для перевірки (і щоб методи вважались використаними)
    public static void main(String[] args) {
        Item[] storage = new Item[5];
        Cart cart = new Cart(storage);

        cart.add(new Item(1, "Apple", 15.5));
        cart.add(new Item(2, "Banana", 10.0));

        System.out.println(cart); // друк усіх елементів
        System.out.println("Cart is empty: " + cart.isEmpty());
        System.out.println("Cart size: " + cart.size());
        System.out.println("Last added item: " + cart.peekLast());
        System.out.println("Total price: " + cart.total());

        cart.removeById(1);
        System.out.println("After removing item with id=1:");
        System.out.println(cart);
    }
}
