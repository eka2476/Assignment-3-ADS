import java.util.Random;

public class Main {

    public static void main(String[] args) {


        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random random = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                "Frank", "Grace", "Henry", "Ivy", "Jack"};

        // add 10000 random elements
        for (int i = 0; i < 10000; i++) {
            String name = names[random.nextInt(names.length)] + i;
            int id = random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(name, id);
            Student value = new Student("Student" + i, 18 + random.nextInt(10));
            table.put(key, value);
        }

        System.out.println("Total elements: " + table.getSize());
        System.out.println("\nElements in each bucket:");
        table.printBucketSizes();

        // test get
        MyTestingClass testKey = new MyTestingClass("TestKey", 999);
        table.put(testKey, new Student("TestStudent", 20));
        System.out.println("\nGet test: " + table.get(testKey));

        // test contains
        Student testStudent = new Student("TestStudent", 20);
        System.out.println("Contains TestStudent: " + table.contains(testStudent));

        // test remove
        table.remove(testKey);
        System.out.println("After remove, get test: " + table.get(testKey));


        BST<Integer, String> tree = new BST<>();

        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(9, "nine");

        System.out.println("Size: " + tree.size());

        System.out.println("\nIn-order traversal (should be sorted):");
        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        // test get
        System.out.println("\nGet key 4: " + tree.get(4));
        System.out.println("Get key 10: " + tree.get(10));

        // test delete
        System.out.println("\nDeleting key 3...");
        tree.delete(3);
        System.out.println("Size after delete: " + tree.size());

        System.out.println("In-order after delete:");
        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        // delete root
        System.out.println("\nDeleting root (5)...");
        tree.delete(5);
        System.out.println("In-order after root delete:");
        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
