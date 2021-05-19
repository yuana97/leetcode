package design;

// Q: Design a stack where you can push, pop, peek, and get min value in O(1) time

// A linked list is a simple way to implement stacks/queues.
// Whenever we push, we want to update the min in O(1) time. This is simple.
// Hard part: when we pop, we need to update the min w/o searching the stack.
// Solve this by having each node keep track of the min at its level and below.
// e.g. put a 'min' field in each node.

class MinStack {
  private Node head;
  
  // push: push w/ updated min
  public void push(int x) {
      if(head == null) 
          head = new Node(x, x);
      else 
          head = new Node(x, Math.min(x, head.min), head);
  }

  public void pop() {
      head = head.next;
  }

  public int top() {
      return head.val;
  }

  public int getMin() {
      return head.min;
  }
  
  private class Node {
      int val;
      int min;
      Node next;
      
      private Node(int val, int min) {
          this(val, min, null);
      }
      
      private Node(int val, int min, Node next) {
          this.val = val;
          this.min = min;
          this.next = next;
      }
  }
}
