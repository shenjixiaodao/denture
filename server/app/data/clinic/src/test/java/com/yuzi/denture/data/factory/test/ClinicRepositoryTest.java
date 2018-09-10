package com.yuzi.denture.data.factory.test;

import com.yuzi.denture.domain.clinic.DentureOrder;
import com.yuzi.denture.domain.clinic.repository.ClinicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicDataApplicationTest.class)
public class ClinicRepositoryTest {

    @Autowired
    private ClinicRepository repository;

    @Test
    public void addDentureOrder(){
        DentureOrder order = new DentureOrder(111L, 111L);
        order.setId(new Date().getTime()+"");
        repository.add(order);
    }
}
