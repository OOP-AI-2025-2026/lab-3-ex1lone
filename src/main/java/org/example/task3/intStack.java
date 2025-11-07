package org.example.task3;

import java.util.NoSuchElementException;

public class intStack {
    private int[] data;
    private int size;

    public intStack() {
        this.data = new int[8];
        this.size = 0;
    }

    public void push(int value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return data[--size];  // повертаємо одразу, без зайвої змінної 'v'
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return data[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        size = 0;
    }

    private void ensureCapacity(int min) {
        if (min <= data.length) return;
        int newCap = Math.max(min, data.length * 2);
        int[] next = new int[newCap];
        System.arraycopy(data, 0, next, 0, size);
        data = next;
    }


    public static void main(String[] args) {
        intStack stack = new intStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Size: " + stack.size());
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop, size: " + stack.size());
        stack.clear(); // <- тепер метод clear() використовується
        System.out.println("After clear, isEmpty: " + stack.isEmpty());
    }
}
