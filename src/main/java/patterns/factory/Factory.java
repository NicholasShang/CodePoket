package patterns.factory;

/**
 * Created by SKF on 2018/4/9.
 * 形状工厂（通过该类相应的实例化对象）
 */
public class Factory {
    public <T> T getShape(Class<? extends  T> clazz){
        if (clazz == null ) {
            return null;
        }
        T obj = null;
        try {
            obj = (T) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
