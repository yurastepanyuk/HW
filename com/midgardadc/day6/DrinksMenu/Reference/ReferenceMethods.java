package day6.DrinksMenu.Reference;

/**
 * Created by stepanyuk on 24.06.2015.
 */
public interface ReferenceMethods {

    void save(Reference r);

    Reference[] getAllData();

    Reference getObjectById();

    int getId();
    void setId(int id);

    String getName();
    void setName(String name);



}
