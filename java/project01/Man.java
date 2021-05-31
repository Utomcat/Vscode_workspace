/**
 * Man 类 继承 Persion 类
 */
public class Man extends Persion {

    private String brith;

    /**
     * @param brith the brith to set
     */
    public void setBrith(String brith) {
        this.brith = brith;
    }

    /**
     * @return the brith
     */
    public String getBrith() {
        return brith;
    }

    @Override
    public String toString() {
        return  "[Man:" + super.toString()+",brith="+brith+"]";
    }
}