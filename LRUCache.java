//Reference: https://www.geeksforgeeks.org/design-a-data-structure-for-lru-cache/
import java.util.Map;
import java.util.HashMap;

public class LRUCache {

    private Map<Integer, Node> map;
    private int capacity;
    private Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.pre = null;
        tail.next = null;
        head.next = tail;
        tail.pre = head;
    }
    
    public static void main(String []args){
        LRUCache cache = new LRUCache(3);
        
        System.out.println("cache.get(1):" + cache.get(1));
        System.out.println("cache.get(2):" + cache.get(2));
        
        cache.set(1, 1);
        System.out.println("cache.get(1):" + cache.get(1));
        
        cache.set(3, 3);
        System.out.println("cache.get(3):" + cache.get(3));
        
        System.out.println("cache.get(1):" + cache.get(1));
        
        cache.set(2, 2);
        cache.set(4, 4);
        cache.set(5, 5);
        System.out.println("cache.get(1):" + cache.get(1));
        System.out.println("cache.get(2):" + cache.get(2));
        System.out.println("cache.get(4):" + cache.get(4));
        System.out.println("cache.get(5):" + cache.get(5));
    }
    
    public int get(int key) {
        
        if(map.get(key) != null) {
            Node node = map.get(key);
            //present in map
            //remove and move it to head
            remove(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        
        if(map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            //present in map
            //remove and move it to head
            remove(node);
            addToHead(node);
        } else {
            //not present in map
            //Add the key value in map
            //remove the last entry
            //add the new value to head
            Node node = new Node(key, value);
            if(map.size() < capacity) {
                map.put(key, node);
                addToHead(node);
                return;
            }
            map.remove(tail.pre.key);
            map.put(key, node);
            remove(tail.pre);
            addToHead(node);
        }
    }
    
    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    private void addToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }
    
    class Node {
        private int key;
        private int value;
        private Node pre, next;
        
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
