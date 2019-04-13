Source: https://medium.com/outco/reversing-a-linked-list-easy-as-1-2-3-560fbffe2088

public void reverse(Node head) {
  Node previous = null;
  Node current = head;
  Node following = head;
  
  while (current != null) {
    following = following.next;
    current.next = previous;
    previous = current;
    current = following;
  }
  return previous:
}
