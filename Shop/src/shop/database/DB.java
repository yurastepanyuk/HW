package shop.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DB {

    public void inicialisation() throws SQLException;

    public <T> List<?> getDataFromTable(Class<T> cls);

    public void addNewRecord(Object object);

    public void updateRecord(Object object);

    public void deleteRecord(Object object);

    public <T> T getObjectById(int id, Class<T> cls);

    public <T> T getObjectByName(String name, Class<T> cls);

	public <T> T getResourceByObject(Object[] object, Class<T> clsTypeResourceReturn);

    public int getNewId(Object object);

    public Connection getConnection();

}
