package myspi.imp;

import myspi.api.Search;

import java.util.List;

public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 "+keyword);
        return null;
    }
}
