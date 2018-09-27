package com.yuzi.denture.data.test;



import com.yuzi.denture.domain.DeliveryInfo;
import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.DentureOrder;
import com.yuzi.denture.domain.repository.FactoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplicationTest.class)
public class FactoryRepositoryTest {

    @Autowired
    private FactoryRepository repository;

    /*@Test
    public void addDenture(){
        Denture denture = new Denture(Denture.DentureType.Fixed, Denture.SpecType.ErYangHuaGao, 1L, "comment",
                1L, "a-2", "A2");
        denture.setId("456");
        repository.add(denture);
    }*/

    @Test
    public void findByDeliveryInfo() {
        Denture denture = repository.findDenture("123456", DeliveryInfo.Company.SF);
        System.out.println(denture);
    }

    @Test
    public void findByDentureId() {
        Denture denture = repository.findDenture("123456");
        System.out.println(denture);
    }

    @Test
    public void findDenturesByFactoryId() {
        List<Denture> dentures = repository.findDentures(1L);
        System.out.println(dentures.size());
    }

    @Test
    public void findOrdersByFactoryId() {
        List<DentureOrder> orders = repository.findOrders(1L);
        System.out.println(orders.size());
    }

    @Test
    public void findOrderById() {
        DentureOrder order = repository.findOrder("152802575036");
        System.out.println(order);
    }
}
