/**
 * Main 类
 */
public class TestMainWindows {

    /**
     * 主方法
     * 
     * @param args
     */
    public static void main(String[] args) {
        Man persion = new Man();
        persion.setName("name");
        persion.setSex("sex");
        persion.setBrith("brith");
        System.out.println(persion.toString());
    }
}