package com.yuzi.denture.data.test;




import com.yuzi.denture.data.hj.CustomerMapper;
import com.yuzi.denture.data.hj.OrderMapper;
import com.yuzi.denture.domain.hj.Customer;
import com.yuzi.denture.domain.hj.HJRepository;
import com.yuzi.denture.domain.hj.Order;
import com.yuzi.denture.domain.hj.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplicationTest.class)
public class HJRepositoryTest {

    @Autowired
    private HJRepository repository;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void parse() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try(BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/orders.csv"))) {
            String line = reader.readLine();
            while ((line=reader.readLine())!=null) {
                if(StringUtils.isEmpty(line))
                    continue;
                String[] cells = line.split(",");
                // 1 company name
                Customer customer = new Customer(cells[1].trim());
                repository.store(customer);

                Order order = new Order();
                customer = customerMapper.findCustomerByName(cells[1].trim());
                order.setCustomer_id(customer.getId());
                order.setCustomer_name(cells[1].trim());
                String createdDateStr = cells[2].trim();
                Date createdDate = parseDate(createdDateStr);
                if (createdDate != null) {
                    order.setCreated_date(dateFormat.format(createdDate));
                }
                String completedDate = cells[3].trim();
                order.setDeadline(completedDate);
                order.setSalesman(cells[4].trim());
                repository.store(order);
                //
                OrderDetail detail = new OrderDetail();
                Map<String, String> paras = new HashMap<>();
                paras.put("created_date", dateFormat.format(createdDate));
                paras.put("customer_name", customer.getName());
                order = orderMapper.findOrderByNameDate(paras);
                detail.setOrder_id(order.getId());
                detail.setProduct_name(cells[5].trim());
                detail.setSpecification(cells[6].trim());
                detail.setUnit(cells[7].trim());
                if(!StringUtils.isEmpty(cells[8])) {
                    detail.setNumber(Double.parseDouble(cells[8].trim()));
                }
                detail.setUnit_price(new BigDecimal(cells[9].split("/")[0].trim()));
                repository.store(detail);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Date parseDate(String str) throws ParseException {
        if(StringUtils.isEmpty(str))
            return null;
        Date date;
        if (str.matches("201[7-9]/([1-9]|1[0-2])/([1-9]|[1-3][0-9])")) {
            date = new SimpleDateFormat("yyyy/M/d").parse(str);
        } else if (str.matches("201[7-9]-([1-9]|1[0-2])-([1-9]|[1-3][0-9])")) {
            date = new SimpleDateFormat("yyyy-M-d").parse(str);
        } else {
            date = new SimpleDateFormat("mm/dd/yy").parse(str);
        }
        return date;
    }

    public static void main(String[] strs) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = "2018/2/2";
        System.out.println(str.matches("201[7-9]/([1-9]|1[0-2])/([1-9]|[1-3][0-9])"));
        Date date = new SimpleDateFormat("yyyy/M/d").parse(str);
        System.out.println(dateFormat.format(date));
        str = "1/24/18";
        System.out.println(str.matches("201[7-9]/([1-9]|1[0-2])/([1-9]|[1-3][0-9])"));
        date = new SimpleDateFormat("mm/dd/yy").parse(str);
        System.out.println(dateFormat.format(date));
    }

}