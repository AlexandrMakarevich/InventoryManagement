package com.invoice_components.panel;

import com.invoice_components.combo_box.ProductComboBox;
import com.invoice_components.listener.AddButtonListener;
import com.invoice_components.listener.DeleteButtonListener;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.combo_box.ProductComboBox.PRODUCT_COMBO_BOX;
import static com.invoice_components.listener.AddButtonListener.ADD_BUTTON_LISTENER_BEAN;
import static com.invoice_components.listener.DeleteButtonListener.DELETE_BUTTON_LISTENER_BEAN;

public class InputAndButtonPanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private ProductComboBox productComboBox;
    private JTextField textField = new JTextField("", 5);
    private Button addProductButton = new Button("Add");
    private Button deleteRowButton = new Button("Delete");
    private AddButtonListener addButtonListener;
    private DeleteButtonListener deleteButtonListener;

    public InputAndButtonPanel(ApplicationContext app) {
        initPanel(app);
    }

    public void initPanel(ApplicationContext app) {
        setLayout(flowLayout);
        productComboBox = (ProductComboBox) app.getBean(PRODUCT_COMBO_BOX);
        add(productComboBox);
        add(textField);
        add(addProductButton);
        add(deleteRowButton);
        addButtonListener = (AddButtonListener) app.getBean(ADD_BUTTON_LISTENER_BEAN);
        addButtonListener.setProductComboBox(productComboBox);
        addButtonListener.setQuantityField(textField);
        addProductButton.addActionListener(addButtonListener);
        deleteButtonListener = (DeleteButtonListener) app.getBean(DELETE_BUTTON_LISTENER_BEAN);
        deleteRowButton.addActionListener(deleteButtonListener);
    }

    public DeleteButtonListener getDeleteButtonListener() {
        return deleteButtonListener;
    }

    public AddButtonListener getAddButtonListener() {
        return addButtonListener;
    }
}
