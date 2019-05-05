package com.yuzi.denture.biz.test;




import com.yuzi.denture.domain.ReviewResult;
import com.yuzi.denture.domain.service.FactoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BizApplicationTest.class)
public class FactoryServiceTest {

    @Autowired
    private FactoryService service;

    @Test
    public void inspectReviewAndStart() {
        //service.inspectReviewAndStart("123456",1L, ReviewResult.Accept);
    }

    @Test
    public void completeProcedure() {
        // service.completeProcedure(1L, 1L, "烤漆", null);
    }
}
