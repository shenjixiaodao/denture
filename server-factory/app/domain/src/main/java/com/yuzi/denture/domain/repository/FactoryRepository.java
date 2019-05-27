package com.yuzi.denture.domain.repository;

import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.criteria.CustomerCriteria;
import com.yuzi.denture.domain.criteria.DentureCriteria;

import java.util.List;

public interface FactoryRepository {
    //denture order, 业务经理通过传统线下方式录单
    void add(DentureOrder order);
    void update(DentureOrder order);
    DentureOrder findOrderByDentureId(String dentureId);
    //denture
    void add(Denture denture);
    void update(Denture denture);

    //procedure
    void batchAddProcedureGroups(List<ProcedureGroup> groups);
    void add(Procedure procedure);

    List<Procedure> findProcedures(Long pgId);
    List<ProcedureGroup> findProcedureGroups(String dentureId);

    //user
    void add(FactoryUser user);
    void addRole(List<FactoryRole> roles);
    void deleteRole(Long uid);
    void addFunction(Long uid, Integer functionId);
    void deleteFunctions(Long uid);
    void update(FactoryUser user);
    void addCustomer(FactoryCustomer customer);
    void updateCustomer(FactoryCustomer customer);
    FactoryCustomer findCustomer(Long id);
    FactoryCustomer findCustomerDetail(Long id);
    List<FactoryCustomer> findCustomersByUid(Long uid);
    List<FactoryCustomer> findCustomers(CustomerCriteria criteria);
    Integer countCustomers(CustomerCriteria criteria);
    FactoryUser findUser(String contact);
    FactoryUser findUser(Long uid);
    List<FactoryUser> findUsers(Long factoryId);
    /**
     * 牙摸初始入厂，工厂根据物流单号查询 Denture信息
     */
    Denture findDenture(String deliveryId, DeliveryInfo.Company company);
    /**
     * 牙模进入工厂后，Denture都是通过{@param dentureId}来获取
     */
    Denture findDenture(String dentureId);
    /**
     * 综合管理员根据工厂ID，查询所有Denture信息
     */
    List<Denture> findDentures(Long factoryId);
    /**
     * 查询
     * 等待审核, 正在制作，已完成
     * 的义齿
     */
    Integer countDentures(DentureCriteria criteria);
    List<Denture> findWaitingDentures(DentureCriteria criteria);
    List<Denture> findDoingDentures(DentureCriteria criteria);
    List<Denture> findDoneDentures(DentureCriteria criteria);
    List<Denture> findDentures(DentureCriteria criteria);

    /**
     * 综合管理人员可以通过{@param factoryId} 查询所有工厂的订单
     */
    List<DentureOrder> findOrders(Long factoryId);

    /**
     * 业务经理查询看自己名下诊所下的订单
     */
    List<DentureOrder> findOrdersByUid(Long uid);
    DentureOrder findOrder(String dentureId);

    // ingredients
    void newIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
    void recordIngredientPurchase(IngredientPurchaseRecord record);
    List<Ingredient> findIngredients(Long factoryId);
    Ingredient findIngredient(Long id);
    void applyIngredient(AppliedIngredient ingredient);
    void useIngredient(UsedIngredient usedIngredient);

    void newSupplier(Supplier supplier);
    List<Supplier> findSuppliers(Long factoryId);

    void add(ProductType type);
    void deleteProductType(Long id);

    void add(PriceSheet price);

    void add(InspectionReport report);
    void add(InspectionItem item);

    void add(SettlementType settlementType);
    List<SettlementType> findSettlementTypes(Long factoryId);

    void add(Certification certification);
    List<Certification> findCertifications(Long factoryId);

    void modifyFactory(Factory factory);
    Factory findFactoryById(Long factoryId);
}
