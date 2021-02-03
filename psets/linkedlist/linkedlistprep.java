package psets.linkedlist;

public class linkedlistprep {
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  // Merge two lists
  // Q: Given two linked lists representing sorted lists of numbers, merge the two lists
  // S: Pointer at both nodes. Take the smaller node and attach it to the tail. Repeat until finished
  // Key insight: add the smaller node
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while (l1 != null || l2 != null) {
      // if l2 is smaller take l2
      if (l1 == null || (l2 != null && l1.val >= l2.val)) {
        cur.next = l2;
        cur = l2;
        l2 = l2.next;
      } else {
        cur.next = l1;
        cur = l1;
        l1 = l1.next;
      }
    }
    return dummy.next;
  }

  // Merge Two lists without duplicates
  // Q: Given two linked lists representing sorted lists of numbers, merge the two lists, but the resulting
  // list can't have duplicates
  // S: Do the same thing as above but skip over blocks of duplicates
  // Key insight: in sorted lists, duplicates come in blocks
  public ListNode mergeTwoListsWithoutDuplicates(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while (l1 != null || l2 != null) {
      // if l2 is smaller take l2
      if (l1 == null || (l2 != null && l1.val >= l2.val)) {
        int val = l2.val;
        cur.next = l2;
        cur = l2;
        while (l2 != null && l2.val == val) {
          l2 = l2.next;
        }
        // in case values are equal
        while (l1 != null && l1.val == val) {
          l1 = l1.next;
        }
      } else {
        int val = l1.val;
        cur.next = l1;
        cur = l1;
        while (l1 != null && l1.val == val) {
          l1 = l1.next;
        }
      }
    }
    return dummy.next;
  }

  // Two sum on a sorted list
  // Q: Given a target number and a sorted list of integers return the indices
  // of a pair of numbers adding up to the target
  // S: two pointer
  // key insight: it's sorted so lets two pointer
  public int[] twoSum2(int[] arr, int target) throws IllegalArgumentException {
    if (arr == null) {
      throw new IllegalArgumentException("array must not be null");
    }

    int[] result = new int[2];
    if (arr.length < 2) {
      return result;
    }
    int left = 0, right = arr.length - 1;
    while (left < right) {
      int sum = arr[left] + arr[right];
      if (sum == target) {
        result[0] = left;
        result[1] = right;
        break;
      } else if (sum > target) {
        right--;
      } else {
        left++;
      }
    }
    return result;
  }

  // Cycle detection
  // Given a linked list return true if it contains a cycle and false otherwise
  // S: Use the tortoise-and-hare method (one slow and one fast pointer)

  public boolean hasCycle(ListNode head) {
    ListNode tortoise = head;
    ListNode hare = head;
    while (hare != null && hare.next != null) {
      tortoise = tortoise.next;
      hare = hare.next.next;
      if (tortoise == hare) {
        return true;
      }
    }
    return false;
  }

  // Reverse nodes in k-group
  // Q: Given a list of nodes and a positive integer k, reverse each group of k consecutive nodes
  // and return the resulting list. If the last group is smaller than length k, do not modify it.
  // S: This question is straightforward you just need to break it down. Write a helper function
  // to reverse a k-group. Then iteratively apply this helper.

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = head;
    ListNode prev = dummy;
    while (cur != null) {
      ListNode start = cur;
      // attempt to go forward k steps
      int counter = k;
      while (counter > 0 && cur != null) {
        cur = cur.next;
        counter--;
      }
      // if we're at the end of the list, just break out
      if (counter != 0) {
        prev.next = start;
        break;
      }

      // reverse the k-group
      ListNode last = reverse(start, k);
      // reconnect the previous group to the new starting node
      prev.next = last;
      // set prev to the new end of the list
      prev = start;
    }
    return dummy.next;
  }

  private ListNode reverse(ListNode head, int k) {
    ListNode prev = null;
    while (head != null && k > 0) {
      // point the current node to the previous and move current/prev forward
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
      k--;
    }
    return prev;
  }
}
