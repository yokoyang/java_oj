package myCallBack;

interface CallBack {
    void beWakeUp();
}

class Customer implements CallBack {
    //顾客同时持有酒店类的对象
    Hotel hotel;
    Customer(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void beWakeUp() {
        System.out.println("被叫醒了");
    }

    public void bookWeakService() {
        this.hotel.wakeService(this);
    }
}

class Hotel {
    void wakeService(CallBack callBack) {
        System.out.println("预定叫醒服务");
        try {
            Thread.sleep(1000);
            System.out.println("天亮了");
            callBack.beWakeUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class CallBackerTest {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Customer customer = new Customer(hotel);
        customer.bookWeakService();
    }
}