package shop.reference;

public interface ServiceMethodRef {

    public void save(Reference object);

    public <T> T getReferenceObjectByName(String name, Class<T> cls);

    public <T> T getReferenceObjectById(int id, Class<T> cls);

    public void getResourses(Reference object);

}
