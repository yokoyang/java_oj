package NIO;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.concurrent.*;

public class ChannelTest {
    private static String extract(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, read);
        }
        baos.flush();
        baos.close();
        return new String(baos.toByteArray(), "UTF-8");
    }

    static void fastwrite(File file, String word2048) throws IOException {
        //  必须采用RandomAccessFile，并且是rw模式
        RandomAccessFile acf = new RandomAccessFile(file, "rw");
        FileChannel fc = acf.getChannel();
        byte[] bs = word2048.getBytes();
        int len = bs.length * 1000;
        long offset = 0;
        int i = 2000000;
        while (i > 0) {
            MappedByteBuffer mbuf = fc.map(FileChannel.MapMode.READ_WRITE, offset, len);
            for (int j = 0; j < 1000; j++) {
                mbuf.put(bs);
            }
            offset = offset + len;
            i = i - 1000;
        }
        fc.close();

    }

    static void writeBuffer(File file, String word2048) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        int i = 1000000;
        while (i > 0) {
            //  word2048为字符串常量，刚好4800个字节
            writer.write(word2048);
            i--;
        }
        writer.close();
        fos.close();
    }

    static void writeBuffer2(File file, String word2048) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel fc = fos.getChannel();
        //  此数字可优化
        int times = 100;
        //  word2048为字符串常量，刚好4800个字节
        byte[] datas = word2048.getBytes();
        ByteBuffer bbuf = ByteBuffer.allocate(4800 * times);
        int i = 10000;
        while (i > 0) {
            for (int j = 0; j < times; j++) {
                bbuf.put(datas);
            }
            bbuf.flip();
            fc.write(bbuf);
            bbuf.clear();
            i--;
        }

    }

    public static void main(String[] args) throws Exception {
        File file = new File("E:\\project\\oj\\src\\main\\java\\NIO\\data2.txt");
        FileOutputStream outputStream = new FileOutputStream(file, true);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String string = "java nio\n";
        buffer.put(string.getBytes());
        buffer.flip();     //此处必须要调用buffer的flip方法
        channel.write(buffer);
        channel.close();
        outputStream.close();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        int size = 1024;
//        BigDecimal num = BigDecimal.valueOf(file.length()).divide(BigDecimal.valueOf(size), RoundingMode.UP);
//        InputStream inputStream = new FileInputStream(file);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        for (int i = 0; i != num.intValue(); i++) {
//            ReadFileTaks readFileTaks = new ReadFileTaks(inputStream, size, i * size);
//            Future<byte[]> future = executorService.submit(readFileTaks);
//            baos.write(future.get());
//        }
//        baos.flush();
//        String fileContent = new String(baos.toByteArray(), "UTF-8");
////        System.out.println(extract(inputStream));
//        inputStream.close();
//        System.out.println(fileContent);
    }

    static class ReadFileTaks implements Callable<byte[]> {
        private InputStream inputStream;
        private int size;
        private int start;

        public ReadFileTaks(InputStream inputStream, int size, int start) {
            this.inputStream = inputStream;
            this.size = size;
            this.start = start;
        }

        @Override
        public byte[] call() throws Exception {
            byte[] buffer = new byte[size];
            int read = inputStream.read(buffer, 0, buffer.length);
            return Arrays.copyOfRange(buffer, 0, read);
        }
    }
}
