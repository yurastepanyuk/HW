package shop.gui;

import shop.Shop;
import shop.documents.Sale;
import shop.documents.ServiceMethodDocument;
import shop.enums.CategoryPrice;
import shop.inforamation.Prices;
import shop.inforamation.ServiceMethodInformation;
import shop.reference.AutoParts;
import shop.reference.Client;
import shop.reference.ServiceMethodReference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.List;

public class SellingUI {

    private Shop shop;

    private ServiceMethodReference serviceMethodReference;
    private ServiceMethodDocument serviceMethodDocument;
    private ServiceMethodInformation serviceMethodInformation;

    JTextField          tfCustomer;
    JLabel              lCustomer;
    JRadioButton        rbutton;
    JFormattedTextField iQty;
    ButtonGroup         buttonGroup;
    int                 idxProduct;
    float               discount = 0.0f;

    public SellingUI(Shop shop) {
        this.shop = shop;

        serviceMethodReference   = new ServiceMethodReference(shop);
        serviceMethodDocument    = new ServiceMethodDocument(shop);
        serviceMethodInformation = new ServiceMethodInformation(shop);

        JFrame frame = new JFrame("Sale");
        //frame.setMinimumSize(new Dimension(600, 300));
        frame.setSize(new Dimension(200, 300));
        frame.setLocation(300,100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(creatingSellingPanel());

        frame.pack();
        frame.setVisible(true);

    }

    public static JRadioButton getSelection(ButtonGroup group) {
        for (Enumeration e = group.getElements(); e.hasMoreElements();) {
            JRadioButton b = (JRadioButton) e.nextElement();
            if (b.getModel() == group.getSelection()) {
                return b;
            }
        }
        return null;
    }

    private JPanel creatingSellingPanel(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        lCustomer = new JLabel("Customer name: ");
        //JTextField tfCustomer = new JTextField("", 30);
        tfCustomer = new JTextField();
        tfCustomer.setColumns(30);

        panel.add(lCustomer, new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        panel.add(tfCustomer, new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

        JLabel lGoods = new JLabel("Select good: ");
        buttonGroup = new ButtonGroup();
        List<AutoParts> products = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);
        ActionListener rbListener = new RBListener();

        JPanel panelGoods = new JPanel();
        panelGoods.setLayout(new GridLayout(products.size(), 0));
        panelGoods.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        int k = 0;
        for (AutoParts autoParts : products) {

            rbutton = new JRadioButton(autoParts.getName());

            rbutton.addActionListener(rbListener);
            rbutton.setActionCommand(String.valueOf(autoParts.getId()));

            if (k == 0) {
                rbutton.setSelected(true);
                k++;
            }

            buttonGroup.add(rbutton);

            panelGoods.add(rbutton);
        }

        panel.add(lGoods, new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));
        panel.add(panelGoods, new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0),0,0));

        Format general = NumberFormat.getNumberInstance();
        JLabel lQty = new JLabel("Input qty: ");
        iQty = new JFormattedTextField(general);
        iQty.setColumns(5);
        Font font = new Font("SansSerif", Font.BOLD, 16);
        iQty.setFont(font);
        iQty.setValue(1);

        panel.add(lQty, new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));
        panel.add(iQty, new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));

        JButton buttonBuy = new JButton("Buy");
        buttonBuy.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            Sale documSale;
                                            AutoParts autoParts;
                                            Client client;
                                            int idxAutoParts;

                                            rbutton = getSelection(buttonGroup);

                                            //client    = new Client().getObjectByName(tfCustomer.getText().trim());
                                            client = serviceMethodReference.getReferenceObjectByName(tfCustomer.getText().trim(), Client.class);

                                            if (client == null){
                                                System.err.println("Not client in DataBase");
                                                return;
                                            }

                                            autoParts = serviceMethodReference.getReferenceObjectByName(rbutton.getText().trim(), AutoParts.class);

                                            float cenaOfSale = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA)
                                                    - discount/Byte.valueOf(iQty.getText());

                                            documSale = serviceMethodDocument.newSale(client, autoParts, Byte.valueOf(iQty.getText()), cenaOfSale);

//                                            documSale = new Sale(shop);
//                                            autoParts = new AutoParts().getObjectByName(rbutton.getText().trim());
//                                            idxAutoParts = idxProduct;
//                                            documSale.setClient(client);
//                                            documSale.setIdClient(client.getId());
//                                            documSale.setAutoParts(autoParts);
//                                            documSale.setIdAutoParts(autoParts.getId());
//                                            documSale.setCena(new Prices(shop).getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
//                                            documSale.setQty(Byte.valueOf(iQty.getText()));
//                                            documSale.setDate(shop.getCurrentDate());
//                                            documSale.save();
//
                                            System.out.println(client.getName() + "    " + autoParts.getName() + "    " +
                                                    documSale.getQty() + "    " + documSale.getCena());

                                        }
                                    });

        panel.add(buttonBuy, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        return panel;
    }

    private class RBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            idxProduct = Integer.parseInt(e.getActionCommand());
        }
    }
}
