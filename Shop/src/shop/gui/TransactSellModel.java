package shop.gui;

import shop.Shop;
import shop.documents.Sale;
import shop.documents.ServiceMethodDocument;
import shop.inforamation.ServiceMethodInformation;
import shop.reference.AutoParts;
import shop.reference.Client;
import shop.reference.ServiceMethodReference;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TransactSellModel extends AbstractTableModel {

    private Shop shop;

    private String [] columnNames;
    private Object [][] data;

    private ServiceMethodReference serviceMethodReference;

    public TransactSellModel(Shop shop) {
        columnNames = new String[]{"id", "date", "Client", "AutoParts", "qty", "cena"};

        this.shop = shop;

        serviceMethodReference   = new ServiceMethodReference(shop);

        readData();
    }

    private void readData() {

        List<Sale> sales = (List<Sale>) shop.getDb().getDataFromTable(Sale.class);

        data = new Object[sales.size()][getColumnCount()];

        int idx = 0;
        for (Sale sale : sales) {

            int idSale          = sale.getId();
            AutoParts autoParts = serviceMethodReference.getReferenceObjectById(sale.getAutoParts().getId(), AutoParts.class);
            String dateDoc      = sale.getDateToString();
            Client client       = serviceMethodReference.getReferenceObjectById(sale.getClient().getId(), Client.class);

            data[idx] = new Object[]{idSale, dateDoc, client.toString(), autoParts.toString(), new Integer(sale.getQty()), new Float(sale.getCena())};

            idx++;
        }

    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }


}
