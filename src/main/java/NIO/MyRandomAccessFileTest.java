package NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class MyRandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\project\\oj\\src\\main\\java\\NIO\\data3.txt", "rw");
        randomAccessFile.setLength(1024 * 1014);
        randomAccessFile.close();

    }

    static class FileWriteThread implements Callable {
        private int skip;
        private byte[] content;

        public FileWriteThread(int skip, byte[] content) {
            this.skip = skip;
            this.content = content;
        }

        @Override
        public Object call() throws Exception {
            return null;
        }
    }
}


