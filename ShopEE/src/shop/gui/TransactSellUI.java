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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TransactSellUI {

    private Shop        shop;

    JFrame              frame;
    JPanel              pSale;
    JPanel              pTransactionSell;

    JPanel              sellingPanel;
    JTextField          tfCustomer;
    JLabel              lCustomer;
    JRadioButton        rbutton;
    JFormattedTextField iQty;
    ButtonGroup         buttonGroup;
    int                 idxProduct;
    float               discount = 0.0f;

    JLabel              lDiscount;
    JLabel              lCena;

    ArrayList<String>   dataForComboBox;
    JComboBox           comboBoxClient;

//    private ServiceMethodReference serviceMethodReference;
//    private ServiceMethodDocument serviceMethodDocument;
//    private ServiceMethodInformation serviceMethodInformation;

    public TransactSellUI(Shop shop) {
        this.shop = shop;
        frame = new JFrame("Transactions of sell");
        //frame.setMinimumSize(new Dimension(800,400));
        frame.setSize(new Dimension(800,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

//        serviceMethodReference   = new ServiceMethodReference(shop);
//        serviceMethodDocument    = new ServiceMethodDocument(shop);
//        serviceMethodInformation = new ServiceMethodInformation(shop);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuAddSale= new JMenu("Sales");
        menuBar.add(menuAddSale);
        JMenuItem menuItemNewSale   = new JMenuItem("Buy Autoparts");
        menuAddSale.add(menuItemNewSale);
        menuItemNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SellingUI sellingUI = new SellingUI(getShop());
                showSellingForm();
            }
        });

        frame.setJMenuBar(menuBar);

        pSale               = creatingSellingPanel();
        pTransactionSell    = creatingTrabsactionPanel();

        frame.getContentPane().add(pTransactionSell);
        frame.pack();
        frame.setVisible(true);

    }

    //Creating Jpanel for sell
    private JPanel creatingSellingPanel(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        lCustomer = new JLabel("Select Customer: ");
        //JTextField tfCustomer = new JTextField("", 30);
        //tfCustomer = new JTextField();
        //tfCustomer.setColumns(30);

        dataForComboBox = new ArrayList<String>();

        List<Client> clients = (List<Client>) shop.getDb().getDataFromTable(Client.class);

        for (Client client : clients) {
            dataForComboBox.add(client.getName());
        }
        DefaultComboBoxModel model  = new DefaultComboBoxModel(dataForComboBox.toArray());
        comboBoxClient              = new JComboBox(model);
        comboBoxClient.setEditable(true);

        panel.add(lCustomer, new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        //panel.add(tfCustomer, new GridBagConstraints(1,0,2,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        panel.add(comboBoxClient, new GridBagConstraints(1, 0, 3, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 50, 0));

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
//            rbutton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    idxProduct = Integer.parseInt(e.getActionCommand());
//                    System.out.println(idxProduct);
//                }
//            });
            rbutton.setActionCommand(String.valueOf(autoParts.getId()));

            if (k == 0) {
                //rbutton.setSelected(true);
                k++;
            }

            buttonGroup.add(rbutton);

            panelGoods.add(rbutton);
        }

        panel.add(lGoods, new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));
        panel.add(panelGoods, new GridBagConstraints(1,1,3,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0),0,0));

        Format general = NumberFormat.getNumberInstance();
        JLabel lQty = new JLabel("Input qty: ");

        iQty = new JFormattedTextField(general);
        iQty.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                updateForm();
            }
        });
        iQty.setColumns(4);
        Font font = new Font("SansSerif", Font.BOLD, 16);
        iQty.setFont(font);
        iQty.setValue(1);

        panel.add(lQty, new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));
        panel.add(iQty, new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));

        lCena = new JLabel();
        lCena.setText("Price: ");
        panel.add(lCena, new GridBagConstraints(2,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));

        lDiscount = new JLabel();
        lDiscount.setText("Discount: ");
        panel.add(lDiscount, new GridBagConstraints(3,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));

        JButton buttonBuy = new JButton("Buy");
        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Sale      documSale;
                AutoParts autoParts;
                Client    client;
                int       idxAutoParts;

                rbutton = getSelection(buttonGroup);

                client = shop.getServiceMethodReference().getReferenceObjectByName(((String) comboBoxClient.getSelectedItem()).trim(), Client.class);
                //client = new Client(shop).getObjectByName(((String) comboBoxClient.getSelectedItem()).trim());

                if (client == null){
                    System.err.println("Not client in DataBase");
                    return;
                }

                autoParts = shop.getServiceMethodReference().getReferenceObjectByName(rbutton.getText().trim(), AutoParts.class);

                float cenaOfSale = shop.getServiceMethodInformation().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA)
                                   - discount/Byte.valueOf(iQty.getText());

                documSale = shop.getServiceMethodDocument().newSale(client, autoParts, Byte.valueOf(iQty.getText()), cenaOfSale, true);

//                documSale = new Sale(shop);
//                autoParts = new AutoParts(shop).getObjectByName(rbutton.getText().trim());
//                idxAutoParts = idxProduct;
//                documSale.setClient(client);
//                documSale.setIdClient(client.getId());
//                documSale.setAutoParts(autoParts);
//                documSale.setIdAutoParts(autoParts.getId());
//
////                float cenaOfSale = new Prices(shop).getPriceByGoods(autoParts, CategoryPrice.ROZNITSA)
////                                    - discount/Byte.valueOf(iQty.getText());
//
//                documSale.setCena(cenaOfSale);
//                documSale.setQty(Byte.valueOf(iQty.getText()));
//                documSale.setDate(shop.getCurrentDate());
//                documSale.save();

                System.out.println(client.getName() + "    " + autoParts.getName() + "    " +
                        documSale.getQty() + "    " + documSale.getCena());

                showTransactionSellForm();

            }
        });

        panel.add(buttonBuy, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton buttonCancel = new JButton("Cancel");
        ActionListener bCancelListener = new CancelListener();
        buttonCancel.addActionListener(bCancelListener);
        panel.add(buttonCancel, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        return panel;
    }

    /**
     * Update data of JPanel
     */
    private void updateForm(){

        rbutton = getSelection(buttonGroup);

        if (rbutton == null) {
            return;
        } else if (iQty.getText().trim().isEmpty()){
            return;
        }

        //AutoParts autoParts = new AutoParts(shop).getObjectByName(rbutton.getText().trim());

        AutoParts autoParts = shop.getServiceMethodReference().getReferenceObjectByName(rbutton.getText().trim(), AutoParts.class);

        float cenaOnPrice = shop.getServiceMethodInformation().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
        Integer qtySale = Integer.valueOf(iQty.getText().trim());

        float sumSale = cenaOnPrice * qtySale;

        String percentDiscount = "(0%) ";

        discount = (float) shop.getServiceMethodDocument().getDiscountSummaForSummaSale(sumSale);
        percentDiscount = "(" + Float.toString(shop.getServiceMethodDocument().getDiscountPercentForSummaSale(sumSale)) + "%) ";

//        if (sumSale >= 1000f) {
//            discount = sumSale * 0.1f;
//            percentDiscount = "(10%) ";
//        } else if (sumSale >= 500f){
//            discount = sumSale * 0.05f;
//            percentDiscount = "(5%) ";
//        } else {
//            discount = 0.0f;
//        }

        lDiscount.setText(" Discount " + percentDiscount + ": " + discount);

        lCena.setText("Price: " + cenaOnPrice);

    }

    //For ButtonGroup search selection buttton
    public static JRadioButton getSelection(ButtonGroup group) {
        for (Enumeration e = group.getElements(); e.hasMoreElements();) {
            JRadioButton b = (JRadioButton) e.nextElement();
            if (b.getModel() == group.getSelection()) {
                return b;
            }
        }
        return null;
    }

    //Listener for RadiButton. Update idx current product
    private class RBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            idxProduct = Integer.parseInt(e.getActionCommand());
            String nameOfProduct = ((JRadioButton) e.getSource()).getText();
            //AutoParts autoParts = new AutoParts(shop).getObjectByName(nameOfProduct);
            AutoParts autoParts = shop.getServiceMethodReference().getReferenceObjectByName(nameOfProduct, AutoParts.class);

            updateForm();

        }
    }

    //Listener for RadiButton Cancel.
    private class CancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            showTransactionSellForm();
        }
    }

    //Creating Jpanel for transaction sell
    private JPanel creatingTrabsactionPanel(){
        JPanel pTransaction = new JPanel();

        TransactSellModel transactSellModel   = new TransactSellModel(shop);
        JTable              transactTable       = new JTable(transactSellModel);
        JScrollPane         trabsactScrollPane  = new JScrollPane(transactTable);
        trabsactScrollPane.setPreferredSize(new Dimension(800,400));

        transactTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        transactTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        transactTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        transactTable.getColumnModel().getColumn(3).setPreferredWidth(220);
        transactTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        transactTable.getColumnModel().getColumn(5).setPreferredWidth(50);

        pTransaction.add(trabsactScrollPane, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

        return pTransaction;
    }

    //Show selling panel
    private void showSellingForm(){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pSale);

        frame.pack();
        frame.repaint();
    }

    //Show transaction panel
    private void showTransactionSellForm(){
        frame.getContentPane().removeAll();
        pTransactionSell = creatingTrabsactionPanel();
        frame.add(pTransactionSell);

        frame.pack();
        frame.setVisible(true);
    }

    public Shop getShop(){
        return this.shop;
    }

}
