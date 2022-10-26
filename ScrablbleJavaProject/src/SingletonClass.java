public class SingletonClass {
private static Object instance;
private String name;

    public Object SingletonClass(String  name) {
        if ((boolean) SingletonClass.instance) {
            return SingletonClass.instance;
        }

        SingletonClass.instance = this;

        this.name = name;

        return this;
    }

    public String getName() {
        return this.name;
    }
}
