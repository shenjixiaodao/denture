package com.yuzi.denture.data.repository;



import com.yuzi.denture.data.mapper.*;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import com.yuzi.denture.domain.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FactoryRepositoryImpl implements FactoryRepository {

    @Autowired
    private DentureMapper dentureMapper;
    @Autowired
    private DentureOrderMapper dentureOrderMapper;
    @Autowired
    private ProcedureMapper procedureMapper;
    @Autowired
    private ProcedureGroupMapper procedureGroupMapper;
    @Autowired
    private FactoryUserMapper userMapper;
    @Autowired
    private ClinicMapper clinicMapper;
    @Autowired
    private IngredientMapper ingredientMapper;

    @Override
    public void add(DentureOrder order) {
        dentureOrderMapper.save(order);
    }

    @Override
    public void update(DentureOrder order) {
        dentureOrderMapper.update(order);
    }

    @Override
    public DentureOrder findOrderByDentureId(String dentureId) {
        return dentureOrderMapper.findOrderByDentureId(dentureId);
    }

    @Override
    public void add(Denture denture) {
        dentureMapper.save(denture);
    }

    @Override
    public void update(Denture denture) {
        dentureMapper.update(denture);
    }

    @Override
    public void batchAddProcedureGroups(List<ProcedureGroup> groups) {
        procedureGroupMapper.batchSave(groups);
    }

    @Override
    public void add(Procedure procedure) {
        procedureMapper.save(procedure);
    }

    @Override
    public List<Procedure> findProcedures(Long pgId) {
        List<Procedure> procedures = procedureMapper.findProceduresByPgId(pgId);
        return procedures;
    }

    @Override
    public List<ProcedureGroup> findProcedureGroups(String dentureId) {
        return null;
    }

    @Override
    public void add(FactoryUser user) {
        userMapper.save(user);
    }

    @Override
    public void addRole(List<FactoryRole> roles) {
        userMapper.addRole(roles);
    }

    @Override
    public void deleteRole(Long uid) {
        userMapper.deleteRole(uid);
    }

    @Override
    public void update(FactoryUser user) {
        userMapper.update(user);
    }

    @Override
    public void addCustomer(FactoryCustomer customer) {
        Long clinicId = customer.getClinic().getId();
        if(clinicMapper.findById(clinicId) == null) {
            throw new IllegalArgumentException("编号：【"+clinicId+"】不存在");
        }
        userMapper.addCustomer(customer);
    }

    @Override
    public void updateCustomer(FactoryCustomer customer) {
        userMapper.updateCustomer(customer);
    }

    @Override
    public FactoryCustomer findCustomer(Long id) {
        return userMapper.findCustomerById(id);
    }

    @Override
    public FactoryCustomer findCustomerDetail(Long id) {
        return userMapper.findCustomerDetailById(id);
    }

    @Override
    public List<FactoryCustomer> findCustomersByUid(Long uid) {
        return userMapper.findCustomersByUid(uid);
    }

    @Override
    public List<FactoryCustomer> findCustomersByFactoryId(Long factoryId) {
        return userMapper.findCustomersByFactoryId(factoryId);
    }

    @Override
    public FactoryUser findUser(String contact) {
        return userMapper.findUserByContact(contact);
    }

    @Override
    public FactoryUser findUser(Long uid) {
        return userMapper.findUserById(uid);
    }

    @Override
    public List<FactoryUser> findUsers(Long factoryId) {
        return userMapper.findUsersByFactoryId(factoryId);
    }

    @Override
    public Denture findDenture(String deliveryId, DeliveryInfo.Company company) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("deliveryId", deliveryId);
        paras.put("company", company);
        return dentureMapper.findByDeliveryInfo(paras);
    }

    @Override
    public Denture findDenture(String dentureId) {
        Denture denture = dentureMapper.findByDentureId(dentureId);
        return denture;
    }

    @Override
    public List<Denture> findDentures(Long factoryId) {
        return dentureMapper.findDenturesByFactoryId(factoryId);
    }

    @Override
    public List<Denture> findWaitingDentures(DentureCriteria criteria) {
        List<Denture> dentures = dentureMapper.findWaitingDentures(criteria);
        return dentures;
    }

    @Override
    public List<Denture> findDoingDentures(DentureCriteria criteria) {
        List<Denture> dentures = dentureMapper.findDoingDentures(criteria);
        return dentures;
    }

    @Override
    public List<Denture> findDoneDentures(DentureCriteria criteria) {
        List<Denture> dentures = dentureMapper.findDoneDentures(criteria);
        return dentures;
    }

    @Override
    public List<DentureOrder> findOrders(Long factoryId) {
        return dentureOrderMapper.findOrdersByFactoryId(factoryId);
    }

    @Override
    public List<DentureOrder> findOrdersByUid(Long uid) {
        return dentureOrderMapper.findOrdersByUid(uid);
    }

    @Override
    public DentureOrder findOrder(String dentureId) {
        return dentureOrderMapper.findOrderByDentureId(dentureId);
    }

    @Override
    public void newIngredient(Ingredient ingredient) {
        ingredientMapper.save(ingredient);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientMapper.update(ingredient);
    }

    @Override
    public void recordIngredientPurchase(IngredientPurchaseRecord record) {
        ingredientMapper.saveIngredientPurchaseRecord(record);
    }

    @Override
    public List<Ingredient> findIngredients(Long factoryId) {
        List<Ingredient> ingredients = ingredientMapper.findIngredients(factoryId);
        return ingredients;
    }

    @Override
    public Ingredient findIngredient(Long id) {
        Ingredient ingredient = ingredientMapper.findIngredientById(id);
        return ingredient;
    }

    @Override
    public void applyIngredient(AppliedIngredient ingredient) {
        ingredientMapper.saveAppliedIngredient(ingredient);
    }

    @Override
    public void useIngredient(UsedIngredient usedIngredient) {
        ingredientMapper.saveUsedIngredient(usedIngredient);
    }

    @Override
    public void newSupplier(Supplier supplier) {
        ingredientMapper.saveSupplier(supplier);
    }

    @Override
    public List<Supplier> findSuppliers(Long factoryId) {
        return ingredientMapper.findSuppliers(factoryId);
    }
}
