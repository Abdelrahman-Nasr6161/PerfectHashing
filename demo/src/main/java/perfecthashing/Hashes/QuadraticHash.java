package perfecthashing.Hashes;

import java.util.*;

import lombok.Getter;

public class QuadraticHash {
    private static final Random rand = new Random();
    private int a, b, p;
    @Getter
    private int m;
    @Getter
    private String[] table;
    private String[] keys;
    @Getter
    private int size = 0;        // tracks number of inserted keys
    @Getter
    private int n;
    @Getter
    private int rehashes;
    public QuadraticHash() {
        this.n = 64;           // initial estimate
        this.rehashes = 0;
        this.size = 0;
        this.m = n * n;
        this.p = getPrime(15_000_000);
        a = rand.nextInt(p-1) + 1;
        b = rand.nextInt(p);
        table = new String[m];
    }
    public QuadraticHash(String[] keys) {
        this.keys = keys;
        this.n = keys.length;
        this.m = n*n;
        this.size = 0;
        this.rehashes = 0;
        this.p = getPrime(15_000_000);
        rehash(this.keys);
    }
    private void rehash(String[] keys , String newWord)
    {
        while (true) {
            boolean exit = false;
            for (int i = 0 ; i < 100 ; i ++ )
            {
                a = rand.nextInt(p-1) + 1;
                b = rand.nextInt(p);
                table = new String[m];
                if (tryHashing(keys,newWord))
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
    private void rehash(String [] keys)
    {
        while (true) {
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
    private boolean tryHashing(String[] keys, String word)
    {
        Arrays.fill(table, null);
        int newIdx = hash(word);
        for (String key : keys)
        {
            int index = hash(key);
            if(index == newIdx)
            {
                return false;
            }
            if (table[index] != null)
            {
                return false;
            }
            table[index] = key;
        }
        Arrays.fill(table, null);
        return true;
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
        }
        Arrays.fill(table, null);
        return true;
    }
    public boolean insert(String key) {
        int index = hash(key);
        if(table[index]!= null && table[index].equals(key))
        {
            System.out.println("Already in table");
            return false;
        }
        while (size + 1 > n || table[index] != null) {
            resize();
            rehashes++;
            index = hash(key); // Recalculate index after resize
        }
        table[index] = key;
        size++;
        // System.out.println("Item inserted");
        return true;
    }
    public boolean contains(String key)
    {
        int index = hash(key);
        if (table[index]!=null && table[index].equals(key))
        {
            System.out.println("Found");
            return true;
        }
        System.out.println("Not found");
        return false;
    }
    public void batchInsert(String path) {
        try (Scanner scanner = new Scanner(new java.io.File(path))) {
            int i = 0;
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim();
                if (!key.isEmpty()) {
                    insert(key);
                }
                i++;
            }
            System.out.println(i+" lines");
        } catch (java.io.IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void batchDelete(String path) {
        try (Scanner scanner = new Scanner(new java.io.File(path))) {
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim();
                if (!key.isEmpty()) {
                    delete(key);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
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
        System.out.println(existing_keys.length+"this is table length");
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
        size = existingKeys.length;
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
