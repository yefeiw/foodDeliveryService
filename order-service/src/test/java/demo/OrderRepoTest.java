package demo;

import demo.domain.Order;
import demo.domain.OrderItem;
import demo.domain.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yefeiw on 6/27/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderServiceApplication.class)
@WebAppConfiguration
public class OrderRepoTest {
    @Autowired
    OrderRepository repository;

    @Test
    public void saveOrder() {

        Order cand;
        cand = new Order("11223344","name","1234","Nothing special","123 Main street",new ArrayList<OrderItem>());
        this.repository.save(cand);
        assertThat(this.repository.findById("1234").getCustomerName()).isEqualTo("name");
    }

    @Test
    public void crudTest() {
        //Create;
        final String id1 = "11223344";
        final String id2 = "55667788";
        Order firstOrder = new Order(id1,"name","1234","Nothing special","123 Main street",new ArrayList<OrderItem>());
        Order secondOrder= new Order(id2,"name","1234","Nothing special","123 Main street",new ArrayList<OrderItem>());
        this.repository.save(firstOrder);
        this.repository.save(secondOrder);
        //assert- create
        assertThat(repository.findAll().size()).isEqualTo(2);
        assertThat(repository.findById(id1).getId()).isEqualTo(id1);
        assertThat(repository.findById(id2).getId()).isEqualTo(id2);
        //delete
        this.repository.deleteAll();
        //assert -delete
        assertThat(repository.findAll().size()).isZero();


    }
}
