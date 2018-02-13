package com.dao;

import com.BaseTest;
import com.builder.InventoryStateBuilder;
import com.builder.InvoiceItemBuilder;
import com.builder.ProductPersistentBuilder;
import com.entity.InventoryState;
import com.entity.InventoryStatePk;
import com.entity.InvoiceItem;
import com.entity.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class TestInventoryStateDaoImpl extends BaseTest {

    @Resource(name = "inventoryStateDaoImpl")
    private InventoryStateDao inventoryStateDao;

    @Resource(name = "productPersistentBuilder")
    private ProductPersistentBuilder productPersistentBuilder;

    private InventoryStateBuilder inventoryStateBuilder;
    private InvoiceItemBuilder invoiceItemBuilder;

    @Before
    public void init() {
        inventoryStateBuilder = new InventoryStateBuilder();
        invoiceItemBuilder = new InvoiceItemBuilder();
    }

    @Test
//    @Rollback(false)
    public void testAddAndGetInventoryState() {
        Product product = productPersistentBuilder.buildAndAddProduct();

        InventoryState inventoryState = inventoryStateBuilder.withProduct(product).build();
        inventoryStateDao.saveInventoryState(inventoryState);

        InvoiceItem invoiceItem = invoiceItemBuilder.withProduct(product).build();

        InventoryState actualInventoryState = inventoryStateDao.getInventoryStateByProductIdWhereMaxStateDate(invoiceItem);
        Assert.assertEquals("ActualInventoryState must be equal inventoryState", actualInventoryState, inventoryState);
    }

    @Test
    public void testGetInventoryStateByPK() {
        Product product = productPersistentBuilder.buildAndAddProduct();

        InventoryStatePk inventoryStatePk = new InventoryStatePk();
        inventoryStatePk.setProduct(product);
        inventoryStatePk.setStateDate(LocalDateTime.parse("2013-10-14T20:00:10.976"));

        InventoryState inventoryState = inventoryStateBuilder
                .withInventoryStatePK(inventoryStatePk)
                .build();

        inventoryStateDao.saveInventoryState(inventoryState);

        InventoryState actualInventoryState = inventoryStateDao
                .getInventoryStateByPk(inventoryStatePk);

        Assert.assertEquals("Actual result must be expected", actualInventoryState, inventoryState);
    }

    @Test
    public void testGetStateByDate() {
        List<InventoryState> inventoryStateList = inventoryStateDao.getActualInventoryStateByDate(LocalDateTime.now());
        System.out.println(inventoryStateList);
    }
}
