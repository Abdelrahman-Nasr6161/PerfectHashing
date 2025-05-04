package perfecthashing.Hashes;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class QuadraticHash {
    private static final Random rand = new Random();
    private int a, b, p, m;
    private String[] table;
    private String[] keys;
    private int size = 0;        // tracks number of inserted keys
    private int n;
    public QuadraticHash() {
        this.n = 1000;           // initial estimate
        this.m = n * n;
        this.p = getNextPrime(100_003);
        rehashWithNewFunction(new String[0]);  // empty table initially
    }
    public QuadraticHash(String[] keys) {
        this.keys = keys;
        int n = keys.length;
        this.m = n * n;
        this.p = getNextPrime(100_003); // A prime larger than any reasonable hashCode()

        while (true) {
            System.out.println("here");
            a = rand.nextInt(p - 1) + 1; // a ∈ [1, p-1]
            b = rand.nextInt(p); // b ∈ [0, p)
            table = new String[m];

            if (tryHashing(this.keys)) {
                break;
            }
        }
    }

    private boolean tryHashing(String[] keys) {
        Arrays.fill(table, null);
        for (String key : keys) {
            int index = hash(key);
            if (table[index] != null) {
                return false; // collision occurred
            }
            table[index] = key;
            size++;
        }
        size = 0;
        Arrays.fill(table, null);
        return true;
    }

    public boolean insert(String word) {
        if (contains(word)) {
            System.out.println("Item already inserted in Hash Table");
            return false;
        }
    
        if (size + 1 > n) { // Resize and rehash when size exceeds threshold
            resize();
        }
    
        int index = hash(word);
        while (table[index] != null) {
            index = (index + 1) % m; // simple linear probing fallback (or retry hash if strict)
        }
    
        table[index] = word;
        size++;
        System.out.println("Successfully inserted");
        return true;
    }
    private void resize() {
        n *= 2;  // Double the value of n
        m = n * n;  // m = n^2
    
        if (m <= 1) {
            m = 2;  // Ensure that m is at least 2
        }
    
        p = getNextPrime(m);  // Get the next prime greater than or equal to m
    
        // Rehash existing keys with the new table size
        String[] existingKeys = Arrays.stream(table)
                                      .filter(Objects::nonNull)
                                      .toArray(String[]::new);
        rehashWithNewFunction(existingKeys);
    }
    private void rehashWithNewFunction(String[] keys) {
        while (true) {
            a = rand.nextInt(p - 1) + 1; // a ∈ [1, p-1]
            b = rand.nextInt(p); // b ∈ [0, p)
            table = new String[m];  // Reset table size to new m value
    
            if (tryHashing(keys)) {
                size = keys.length; // Set the size to the number of existing keys
                break;
            }
        }
    }
    public boolean delete(String word) {
        int index = hash(word);
        if (table[index] == null || !table[index].equals(word)) {
            System.out.println("Item not present in Hash Table");
            return false;
        }
        table[index] = null;
        size--;
        System.out.println("Successfully deleted");
        return true;
    }
    public boolean search(String word) {
        int index = hash(word);
        if (table[index] == null || !table[index].equals(word)) {
            System.out.println("Item not present in Hash Table");
            return false;
        }
        System.out.println("Item Found");
        return true;
    }

    public void batchInsert(String path) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            insert(word);
        }
        scanner.close();

    }

    public void batchDelete(String path) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            delete(word);
        }
        scanner.close();
    }

    private int hash(String key) {
        if (p == 0) {
            throw new IllegalStateException("Prime number 'p' is zero!");
        }
    
        // Debugging statements to verify values of m and p
        System.out.println("Hashing key: " + key);
        System.out.println("Prime p: " + p);
        System.out.println("Table size m: " + m);
        System.out.println("Size is  : "+size);
        long x = key.hashCode() & 0xFFFFFFFFL; // Treat as unsigned
        long hash = ((a * x + b) % p) % m;
        return (int) hash;
    }

    public boolean contains(String key) {
        int index = hash(key);
        return table[index] != null && table[index].equals(key);
    }

    private int getNextPrime(int min) {
        while (true) {
            if (isPrime(min)) {
                return min;
            }
            min++;
        }
    }

    private boolean isPrime(int num) {
        if (num < 2)
            return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}
