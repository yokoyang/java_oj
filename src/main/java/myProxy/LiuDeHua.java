package myProxy;



public class LiuDeHua implements Star {
    @Override
    public String sing(String name) {
        System.out.println("给我一杯忘情水" + name);

        return "唱完";
    }

    @Override
    public String dance(String name) {
        System.out.println("开心的马骝" + name);
        return "跳完";
    }
}
