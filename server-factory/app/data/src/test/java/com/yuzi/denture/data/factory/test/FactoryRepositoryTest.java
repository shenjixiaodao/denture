package com.yuzi.denture.data.factory.test;



import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.repository.FactoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FactoryDataApplicationTest.class)
public class FactoryRepositoryTest {

    @Autowired
    private FactoryRepository repository;

    @Test
    public void addDentureOrder(){
        /*Denture denture = new Denture(new Date().getTime()+"", 111L);
        denture.setId(new Date().getTime()+"");
        repository.add(denture);*/
    }
}
