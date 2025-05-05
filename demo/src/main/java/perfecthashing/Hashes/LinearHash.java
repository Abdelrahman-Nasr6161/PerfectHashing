package perfecthashing.Hashes;

import java.util.*;

public class LinearHash implements IHash {
    private QuadraticHash[] secondLevel;
    private String[] firstLevel;
    private int flSize;
    private int totalRehashes = 0;
    private int a, b;
    Random rand = new Random();

    public LinearHash() {
        this(16);
    }

    public LinearHash(int initialSize) {
        this.flSize = initialSize;
        this.firstLevel = new String[flSize];
        this.secondLevel = new QuadraticHash[flSize];
        this.totalRehashes = 0;
        this.a = rand.nextInt(Integer.MAX_VALUE);
        this.b = rand.nextInt(Integer.MAX_VALUE);
    }

    private int firstLevelHash(String key) {
        return Math.abs((a * key.hashCode() + b)) % flSize;
    }

    public boolean insert(String key) {
        if (key == null) return false;
        
        int index = firstLevelHash(key);
        
        // Check first level
        if (firstLevel[index] == null) {
            firstLevel[index] = key;
            return true;
        } 
        // Exists in first level
        else if (firstLevel[index].equals(key)) {
            return false;
        }
        // Handle collision
        else {
            String existingKey = firstLevel[index];
            firstLevel[index] = null;
            
            if (secondLevel[index] == null) {
                secondLevel[index] = new QuadraticHash();
                secondLevel[index].insert(existingKey);
            }
            
            boolean result = secondLevel[index].insert(key);
            totalRehashes += secondLevel[index].getRehashes();
            return result;
        }
    }

    public boolean delete(String key) {
        if (key == null) return false;
        int index = firstLevelHash(key);
        
        // Check first level
        if (firstLevel[index] != null && firstLevel[index].equals(key)) {
            firstLevel[index] = null;
            return true;
        }
        // Check second level
        if (secondLevel[index] != null) {
            return secondLevel[index].delete(key);
        }
        return false;
    }

    public boolean search(String key) {
        if (key == null) return false;
        int index = firstLevelHash(key);
        
        // Check first level
        if (firstLevel[index] != null && firstLevel[index].equals(key)) {
            return true;
        }
        // Check second level
        if (secondLevel[index] != null) {
            return secondLevel[index].search(key);
        }
        return false;
    }

    public void batchInsert(String filename) {
        try (Scanner scanner = new Scanner(new java.io.File(filename))) {
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
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void batchDelete(String filename) {
        try (Scanner scanner = new Scanner(new java.io.File(filename))) {
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

    public int getSize() {
        int count = 0;
        // first level
        for (String key : firstLevel) {
            if (key != null) count++;
        }
        // second level
        for (QuadraticHash qh : secondLevel) {
            if (qh != null) count += qh.getSize();
        }
        return count;
    }

    public void display() {
        int totalElements = getSize();
        // First level keys
        Arrays.stream(firstLevel)
              .filter(Objects::nonNull)
              .forEach(key -> System.out.print(key + " "));
        
        // Second level keys
        Arrays.stream(secondLevel)
              .filter(Objects::nonNull)
              .flatMap(qh -> Arrays.stream(qh.getTable()).filter(Objects::nonNull))
              .forEach(key -> System.out.print(key + " "));
        
        System.out.println();
        System.out.println("This is table length: " + totalElements);
    }
}