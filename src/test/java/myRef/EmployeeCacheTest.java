package myRef;

import org.junit.Test;

public class EmployeeCacheTest {
    @Test
    public void testRefCache() {
        EmployeeCache instance = EmployeeCache.getInstance();
        Employee e1 = instance.getEmployee("1");
        Employee e2 = instance.getEmployee("2");
        Employee e3 = instance.getEmployee("3");
        instance.getEmployee("3");
        instance.getEmployee("1");
        System.out.println("a");
    }
}
