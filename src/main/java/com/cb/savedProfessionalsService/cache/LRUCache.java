package com.cb.savedProfessionalsService.cache;

import com.cb.savedProfessionalsService.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class LRUCache<T1, T2> {
    //create dummy nodes for head and tail
    Node<T1, T2> head;
    Node<T1, T2> tail;

    //declare a hashmap
    HashMap<T1, Node> map;

    //logging
    Logger logger = LoggerFactory.getLogger(Main.class);

    //declare a variable to store the capacity allowed
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap();
    }

    private void addNode(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public boolean contains(T1 key) {
        return map.containsKey(key);
    }

    public T2 get(T1 key) {
        Node ret = map.containsKey(key) ? map.get(key) : null;

        if(ret != null) {
            removeNode(ret);
            addNode(ret);
            return (T2) ret.val;
        }
        return null;
    }

    public void remove(T1 key) {
        Node rem =  map.containsKey(key) ? map.get(key) : null;

        if(rem != null) {
            removeNode(rem);
            map.remove(key);
        }
    }

    public void put(T1 key, T2 val) {
        Node ret = map.containsKey(key) ? map.get(key) : null;

        if(ret != null) {
            removeNode(ret);
            ret.val = val;
            map.put(key, ret);
            addNode(ret);
        }
        else {
            if(capacity == map.size()) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            Node node = new Node(key, val);
            addNode(node);
            map.put(key, node);
        }
    }
}

class Node<T1, T2> {
    T1 key;
    T2 val;
    Node<T1, T2> prev;
    Node<T1, T2> next;

    public Node() {

    }

    public Node(T1 key, T2 val) {
        this.key = key;
        this.val = val;
    }
}