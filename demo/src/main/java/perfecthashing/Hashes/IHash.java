package perfecthashing.Hashes;

import java.util.Arrays;
import java.util.Objects;

public interface IHash {

    int getSize();

    public boolean insert(String key);

    public boolean search(String key);

    public void batchInsert(String path);

    public void batchDelete(String path);

    public boolean delete(String key);

    public void display();

}
