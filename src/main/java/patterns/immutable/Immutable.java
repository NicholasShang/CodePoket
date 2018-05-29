package patterns.immutable;

/**
 * Created by SKF on 2018/4/9.
 * 设计一个不可变类应该遵循以下几点：

 1、类的所有属性声明为private，去除掉所有的setter方法，防止外界直接对其进行修改
 2、类的声明采用final进行修饰，保证没有父类对其修改
 3、类的属性声明为final,如果对象类型为可变类型，应对其重新包装，重新new一个对象返回

 优点：

 1、因为是不可变的，所以不允许程序对其进行修改，避免了程序中修改数据带来的异常产生
 2、由于对象是不可变的，减少了线程同步带来的开销

 缺点：

 1、每次返回都创建新的对象，内存会有一定的开销，不容易被垃圾回收器回收，造成资源的浪费

 应用场景：

 1、不适合大对象、且创建频繁的场景，因为对象大且创建频繁会容易导致内存泄漏
 2、适合表示抽象数据类型（如数字、枚举类型或颜色）的值
 3、适合在多线程环境中进行同步而不需要考虑线程同步

 */
public final class Immutable {

    private final String name;
    private final int age;

    public Immutable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * 将年龄增加10岁
     * @param newAge
     * @return
     */
    public Immutable addAge(int newAge) {
        return new Immutable(this.getName(), this.getAge() + newAge);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        Immutable immutable = new Immutable("a", 12);
        System.out.println(immutable);
        Immutable newImmutable = immutable.addAge(10);
        System.out.println(newImmutable);
    }

}
