package oop;

import java.util.*;

public class LRUCache {
  /*
    Question: Design a data structure holding int key -> int value w/ the following functions
    - 1. constructor which takes in an int capacity
    - 2. get(int key) which returns the value of the key or -1 if DNE
    - 3. void put(int key, int value) update/add the k/v pair. If the number of keys
    exceeds capacity, remove the least recently used key.

    Script:
    - To clarify, are the keys and values of the map integers?
    - Correct my understanding - whenever a key is gotten, updated or added, it goes to the front of the cache.
    Are there any other ways a key can change positions in the cache?

    - Let me give a quick overview of the problem.
    - First let me note some properties we want this data structure to have. We want to be able to quickly
    move a key from the middle of the cache to the front. We want to be able to quickly remove a key from the
    end of the cache. This suggests to us to use a Doubly Linked List to represent the cache as nodes in a DLL
    can move themselves in the list without needing any other references.
    - We also want to keep track of keys and values and quickly retrieve them. For that we can use a HashMap
    mapping keys to their representative nodes.
    - Using these data structures we should be able to put and get in O(1) time.
    - If you agree with my analysis, I can start coding it up.
  */

  class DLinkedNode {
    int key;
    int value;
    DLinkedNode pre;
    DLinkedNode post;
  }

  // map representing the cache
  private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
  // current size of the cache
  private int count;
  // max capacity of the cache
  private int capacity;
  // dummy head/tail nodes for quick adding/removal
  private DLinkedNode head, tail;

  // add a node to the front of the list
  private void addNode(DLinkedNode node) {
    node.pre = head;
    node.post = head.post;

    head.post.pre = node;
    head.post = node;
  }

  // move a node to the front (updated)
  private void moveToHead(DLinkedNode node) {
    this.removeNode(node);
    this.addNode(node);
  }

  // remove the end of the cache
  private DLinkedNode popTail() {
    DLinkedNode res = tail.pre;
    this.removeNode(res);
    return res;
  }

  // initialize everything
  public LRUCache(int capacity) {
    this.count = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    head.pre = null;

    tail = new DLinkedNode();
    tail.post = null;

    head.post = tail;
    tail.pre = head;
  }

  // move the node to the head
  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if(node == null){
      return -1; // should raise exception here.
    }
  
    // move the accessed node to the head;
    this.moveToHead(node);
  
    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);
    // add the node
    if(node == null){
  
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;
  
      this.cache.put(key, newNode);
      this.addNode(newNode);
  
      count++;
  
      if(count > capacity){
        // pop the tail
        DLinkedNode tail = this.popTail();
        this.cache.remove(tail.key);
        --count;
      }
    } else {
      // update node
      node.value = value;
      this.moveToHead(node);
    }
  }
}
