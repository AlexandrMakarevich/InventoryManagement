package com.dao;

import com.entity.InventoryState;
import com.entity.InventoryStatePk;
import com.entity.InvoiceItem;
import java.time.LocalDateTime;
import java.util.List;

public interface InventoryStateDao {

  void saveInventoryState(InventoryState inventoryState);

  InventoryState getInventoryStateByPk(InventoryStatePk inventoryStatePk);

  InventoryState getInventoryStateByProductIdWhereMaxStateDate(InvoiceItem invoiceItem);

  List<InventoryState> getInventoryStates(List<Integer> strings);

  List<InventoryState> getActualInventoryStateByDate(LocalDateTime date);
}
