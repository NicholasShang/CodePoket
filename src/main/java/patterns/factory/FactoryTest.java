package patterns.factory;

/**
 * Created by SKF on 2018/4/9.
 */
public class FactoryTest {
    public static void main(String[] args) {
        Factory shapeFactory = new Factory();
        Shape circle = shapeFactory.getShape(Circle.class);
        circle.describe();

        Shape rectangle = shapeFactory.getShape(Rectangle.class);
        rectangle.describe();
    }
}
