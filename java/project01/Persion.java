/**
 * Persion 类 父类
 */
public class Persion {

    private String name;
    private String sex;

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "[Persion:name=" + name + ",sex=" + sex + "]";
    }

}