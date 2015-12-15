package shop.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WorkWithDerby implements DB,QueryToDB {
    @Override
    public void inicialisation() throws SQLException {

    }

    @Override
    public <T> List<?> getDataFromTable(Class<T> cls) {
        return null;
    }

    @Override
    public Integer addNewRecord_(Object object) {
        return null;
    }

    @Override
    public void updateRecord(Object object) {

    }

    @Override
    public void deleteRecord(Object object) {

    }

    @Override
    public <T> T getObjectById(int id, Class<T> cls) {
        return null;
    }

    @Override
    public <T> T getObjectByName(String name, Class<T> cls) {
        return null;
    }

    @Override
    public <T> T getResourceByObject(Object[] object, Class<T> clsTypeResourceReturn) {
        return null;
    }

    @Override
    public int getNewId(Object object) {
        return 0;
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
