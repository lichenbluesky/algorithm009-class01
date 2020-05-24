import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {

    public static void main(String[] args) {

        Deque<String> queue = new LinkedList<>();

        queue.addLast("a");
        queue.addLast("b");
        queue.addLast("c");
        System.out.println(queue);

        String str = queue.peek();
        System.out.println(str);
        System.out.println(queue);

        while (queue.size() > 0) {
            System.out.println(queue.pop());
        }
    }
}
