package perfecthashing.Hashes;

import static org.junit.Assert.fail;

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
    private int rehashes;
    private boolean knownCorpus;
    public QuadraticHash() {
        this.n = 1;           // initial estimate
        this.rehashes = 0;
        this.size = 0;
        this.m = n * n;
        this.p = getPrime(15_000_000);
        a = rand.nextInt(p-1) + 1;
        b = rand.nextInt(p);
        table = new String[m];
        knownCorpus = false;
    }
    public QuadraticHash(String[] keys) {
        this.keys = keys;
        knownCorpus = true;
        this.n = keys.length;
        this.m = n*n;
        this.size = 0;
        this.rehashes = 0;
        this.p = getPrime(15_000_000);
        rehash(keys);
    }
    public int getRehashes()
    {
        return rehashes;
    }
    public int getSize()
    {
        return size;
    }
    private void rehash(String [] keys)
    {
        while (true) {
            System.out.println("n is currently : " + n);
            System.out.println("m is currently : " + m);
            System.out.println("a is currently : " + a);
            System.out.println("b is currently : " + b);
            System.out.println("p is currently : " + p);
            boolean exit = false;
            for (int i = 0 ; i < 10 ; i ++ )
            {
                a = rand.nextInt(p-1) + 1;
                b = rand.nextInt(p);
                table = new String[m];
                if (tryHashing(keys))
                {
                    exit = true;
                    break;
                }
            }
            if(exit)
            {
                break;
            }
            n++;
            m = n*n;
            if(m>1e7)
            {
                System.out.println("Maximum Table Size reached");
                System.exit(-1);
            }
        }
    }
    private boolean tryHashing(String[] keys)
    {
        Arrays.fill(table, null);
        for (String key : keys)
        {
            int index = hash(key);
            if (table[index] != null)
            {
                return false;
            }
            table[index] = key;
            size++;
        }
        Arrays.fill(table, null);
        size = 0;
        return true;
    }
    public boolean insert(String key) {
        int index = hash(key);
        if (size + 1 > n) {
            resize();
            rehashes++;
            index = hash(key); // Recalculate index after resize
        }
    
        if (table[index] != null) {
            if (!knownCorpus) {
                String[] existing_keys = Arrays.stream(table).filter(Objects::nonNull).toArray(String[]::new);
                rehash(existing_keys);
                for (String word : existing_keys) {
                    int idx = hash(word);
                    table[idx] = word;
                    size++;
                }
                index = hash(key); // Recalculate index after rehash
            } else {
                return false;
            }
        }
    
        table[index] = key;
        size++;
        System.out.println("Item inserted");
        return true;
    }
    public boolean delete(String key)
    {
        int index = hash(key);
        if (table[index]==null || !table[index].equals(key))
        {
            System.out.println("Item not in Hashmap");
            return false;
        }
        size--;
        table[index] = null;
        System.out.println("Item Deleted");
        return true;
    }
    public void display()
    {
        String[] existing_keys = Arrays.stream(table).filter(Objects::nonNull).toArray(String[]::new);
        for (String key : existing_keys)
        {
            System.out.print(key+ " ");
        }
        System.out.println();
    }
    private void resize()
    {
        n = 2*n;
        m = n*n;
        String[] existingKeys = Arrays.stream(table)
                                  .filter(Objects::nonNull)
                                  .toArray(String[]::new);
        table = new String[m];
        rehash(existingKeys);
        for(String key : existingKeys)
        {
            this.insert(key);
        }
    }
    private int getPrime(int min)
    {
        while (true) {
            if (isPrime(min)) return min;
            min++;
        }
    }
    private boolean isPrime(int num)
    {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    private int hash(String key) {
        long x = Math.abs((long) key.hashCode());
        long hash = ((a * x + b) % p) % m;
        return (int) hash;
    }
}
