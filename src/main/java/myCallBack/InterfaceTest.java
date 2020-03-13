package myCallBack;

import java.util.ArrayList;

interface Index {
    public void a();
}

class InterfaceTest {

    public static void main(String[] args) {
        Index i = new Index() {
            @Override
            public void a() {
                System.out.println("end back!!!");
            }
        };
        Index j = new Index() {
            @Override
            public void a() {
                System.out.println("end back!!!");
            }
        };
    }
}
