package shell;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Cat {
    public static void main(String[] args){
        List<String> resultStringList = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        String dirName = "E:\\project\\oj\\doc\\网易2019";
        String pattern = ".md";
        String regex = "表示";
        File path = new File(dirName);
        if (path.exists() && path.isDirectory()){
            for(File file : path.listFiles()){
                if(file.getName().endsWith(pattern)){
                    Task task = new Task(file, regex);
                    Future<List<String>> result = executorService.submit(task);
                    try{
                        resultStringList.addAll(result.get());
                    } catch(InterruptedException | ExecutionException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        executorService.shutdown();
        System.out.println(resultStringList);
    }
    static class Task implements Callable<List<String>> {
        private File file;
        private String regex;
        private Task(File file, String regex){
            this.file = file;
            this.regex = regex;
        }
        @Override
        public List<String> call() throws IOException {
            List<String> callList = new ArrayList<>();
//            BufferedReader br = new BufferedReader(new FileReader(file));
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String str;
            while((str=br.readLine()) != null){
                if (str.contains(regex)){
                    callList.add(str);
                }
            }
            return callList;
        }
    }
}