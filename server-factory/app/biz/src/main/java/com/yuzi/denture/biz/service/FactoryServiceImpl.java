package com.yuzi.denture.biz.service;

import com.yuzi.denture.biz.util.IdGenerator;
import com.yuzi.denture.domain.*;
import com.yuzi.denture.domain.exception.CodeException;
import com.yuzi.denture.domain.repository.ClinicRepository;
import com.yuzi.denture.domain.repository.FactoryRepository;
import com.yuzi.denture.domain.response.ResponseCode;
import com.yuzi.denture.domain.service.FactoryService;
import com.yuzi.denture.domain.type.*;
import com.yuzi.denture.domain.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryRepository repository;
    @Autowired
    private ClinicRepository clinicRepository;

    private final long DayLong = 24*60*60*1000L;

    @Transactional
    @Override
    public Denture createOrderAndDenture(Long clinicId, Long dentistId, Long factoryId, String comment,
                                         String positions, Denture.DentureType type, String specification, Integer number,
                                         String colorNo, FieldType fieldType, BiteLevel biteLevel,
                                         BorderType borderType, NeckType neckType, InnerCrownType innerCrowType,
                                         PaddingType paddingType, OuterCrownType outerCrowType, String requirement,
                                         String patientName, Long salesmanId, String salesman, String stage,
                                         Date receivedDate, Long creatorId, String creator, String dentist) {
        //1, create denture
        Denture denture = new Denture(type, specification, clinicId, comment,
                factoryId, positions, number, colorNo);
        denture.setId(IdGenerator.generate(factoryId));
        denture.setFieldType(fieldType);
        denture.setBiteLevel(biteLevel);
        denture.setBorderType(borderType);
        denture.setNeckType(neckType);
        denture.setInnerCrownType(innerCrowType);
        denture.setPaddingType(paddingType);
        denture.setOuterCrownType(outerCrowType);
        denture.setRequirement(requirement);
        denture.setPatientName(patientName);
        denture.setSalesman(salesman);
        denture.setSalesmanId(salesmanId);
        denture.setStage(stage+"阶段");
        denture.setStatus(Denture.Status.Created.name());
        denture.setReceivedDate(receivedDate);
        denture.setEstimatedDuration(new Date(receivedDate.getTime()+Integer.parseInt(stage)*DayLong));
        denture.setCreator(creator);
        denture.setCreatorId(creatorId);
        denture.setDentistId(dentistId);
        denture.setDentist(dentist);
        //初始创建denture时，生成加工所需要的所有工序
        List<ProcedureGroup> groups = denture.generateProcedureGroups();
        repository.batchAddProcedureGroups(groups);
        repository.add(denture);
        //2, create order
        DentureOrder order = new DentureOrder(denture.getId(), clinicId, factoryId, dentistId, comment);
        order.setId(IdGenerator.generate(clinicId));
        repository.add(order);
        return denture;
    }

    @Override
    @Transactional
    public Denture inspectReviewAndStart(String dentureId, Date estimatedDuration, String basePrice,
                                         String factoryPrice, String requirement, Long inspectorId,
                                         ReviewResult reviewResult) {
        Denture denture = repository.findDenture(dentureId);
        denture.setStartDate(new Date());
        denture.setModelInspectionDate(new Date());
        denture.setModelInspector(new FactoryUser(inspectorId));
        denture.setEstimatedDuration(estimatedDuration);
        denture.setBasePrice(basePrice);
        denture.setFactoryPrice(factoryPrice);
        denture.setRequirement(requirement);
        //审核合并执行
        denture.setProReview(reviewResult);
        denture.setProReviewDate(new Date());
        denture.setQuaReview(reviewResult);
        denture.setQuaReviewDate(new Date());
        if(reviewResult == ReviewResult.Accept) {
            denture.setStatus(Denture.Status.Producing.name());
        } else {
            denture.setStatus(Denture.Status.Invalid.name());
        }
        repository.update(denture);
        //order
        DentureOrder order = repository.findOrderByDentureId(dentureId);
        order.setReceivedDate(new Date());
        if(reviewResult == ReviewResult.Accept) {
            order.setStatus(DentureOrder.Status.Accepted);
        } else {
            order.setStatus(DentureOrder.Status.Rejected);
        }
        repository.update(order);
        return denture;
    }

    @Override
    public Procedure completeProcedure(Long pgId, Long operatorId, String procedureName, String comment) {
        Procedure procedure = new Procedure(pgId, procedureName, operatorId);
        repository.add(procedure);
        return procedure;
    }

    @Transactional
    @Override
    public void addFactoryUser(FactoryUser user) {
        repository.add(user);
        List<FactoryRole> roles = user.getRoles();
        for(FactoryRole role:roles) {
            role.setUid(user.getId());
        }
        repository.deleteRole(user.getId());
        repository.addRole(roles);
    }

    @Override
    @Transactional
    public void modifyUser(FactoryUser user) {
        repository.update(user);
        if(user.getRoles() != null) {
            repository.deleteRole(user.getId());
            repository.addRole(user.getRoles());
        }
    }

    @Override
    public FactoryUser login(String contact, String encryptPWD) {
        FactoryUser user = repository.findUser(contact);
        if(user == null)
            throw new IllegalArgumentException("不存在用户");
        String hashPwd = user.decryptAndHashPwd(encryptPWD);
        if(!user.checkPWD(hashPwd))
            throw new IllegalArgumentException("密码错误");
        return user;
    }

    @Override
    public void modifyPwd(Long uid, String srcPwd, String dstPwd) {
        FactoryUser user = repository.findUser(uid);
        if(user == null)
            throw new IllegalArgumentException("用户为空");
        String hashPwd = user.decryptAndHashPwd(srcPwd);
        if(!user.checkPWD(hashPwd))
            throw new IllegalArgumentException("旧密码验证错误");
        hashPwd = user.decryptAndHashPwd(dstPwd);
        user.setPassword(hashPwd);
        repository.update(user);
    }

    @Override
    public void inviteCustomer(Long factoryId, Long clinicId, Long salesmanId) {
        FactoryCustomer customer = new FactoryCustomer(factoryId, clinicId, salesmanId);
        repository.addCustomer(customer);
    }

    @Transactional
    @Override
    public void addCustomer(Long factoryId, Long salesmanId, String name, String contact, String region,
                               String address, String dentistName) {
        Clinic clinic = new Clinic(name, region, address, contact);
        clinicRepository.add(clinic);
        ClinicUser user = new ClinicUser(contact, dentistName);
        user.setClinicId(clinic.getId());
        clinicRepository.addUser(user);
        FactoryCustomer customer = new FactoryCustomer(factoryId, salesmanId);
        customer.setClinic(clinic);
        repository.addCustomer(customer);
    }

    @Override
    public void modifyCustomer(Long customerId, Long clinicId, Long salesmanId) {
        FactoryCustomer customer = repository.findCustomer(customerId);
        if(clinicId!=null) {
            customer.setClinic(new Clinic(clinicId));
        }
        if(salesmanId != null) {
            customer.setSalesmanId(salesmanId);
        }
        repository.updateCustomer(customer);
    }

    @Override
    @Transactional
    public void addClinicUser(ClinicUser user) {
        clinicRepository.addUser(user);
    }

    @Override
    public void newIngredient(String name, Long factoryId, String type, String equalityRateRange) {
        Ingredient ingredient = new Ingredient(name, factoryId);
        ingredient.setType(type);
        ingredient.setEqualityRateRange(equalityRateRange);
        repository.newIngredient(ingredient);
    }

    @Transactional
    @Override
    public void newIngredientPurchaseRecord(String billNo, String price, Long ingredientId,
                                            Long supplierId, Double number) {
        Ingredient ingredient = repository.findIngredient(ingredientId);
        if(ingredient == null) {
            throw new IllegalArgumentException("未知物料");
        }
        ingredient.addBalance(number);
        repository.updateIngredient(ingredient);
        IngredientPurchaseRecord record = new IngredientPurchaseRecord(billNo, price, ingredientId, supplierId, number);
        repository.recordIngredientPurchase(record);
    }

    @Override
    @Transactional
    public void applyIngredient(Long uid, String dentureId, Long ingredientId, Double number,
                                String comment, Long factoryId) {
        Ingredient ingredient = repository.findIngredient(ingredientId);
        if(ingredient == null) {
            throw new IllegalArgumentException("未知物料");
        }
        ingredient.subBalance(number);
        repository.updateIngredient(ingredient);
        AppliedIngredient appliedIngredient = new AppliedIngredient(ingredientId, dentureId, number, uid, comment);
        appliedIngredient.setFactoryId(factoryId);
        repository.applyIngredient(appliedIngredient);
    }

    @Override
    public void addProductType(ProductType type) {
        try {
            repository.add(type);
        } catch (DuplicateKeyException dke) {
            throw new CodeException(ResponseCode.Duplicate_Code);
        }
    }

    @Override
    @Transactional
    public void addPriceSheet(PriceSheet price) {
        try {
            repository.add(price);
        } catch (DuplicateKeyException dke) {
            throw new CodeException(ResponseCode.Duplicate_PriceSheet);
        }
    }

    @Override
    public void addDeliveryInfo(String dentureId, String deliveryDate, String deliveryPerson) {
        Denture denture = new Denture();
        denture.setId(dentureId);
        denture.setDeliveryDate(deliveryDate);
        denture.setDeliveryPerson(deliveryPerson);
        denture.setDeliveryId(CommonUtil.dateToLongFormat(new Date()));
        denture.setStatus(Denture.Status.Delivered.name());
        repository.update(denture);
    }

    @Override
    public void addInspectionReport(InspectionReport inspection) {
        repository.add(inspection);
    }
}
