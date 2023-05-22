package com.fruitSalad_backend.Backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.fruitSalad_backend.Backend.model.CustomerImpl;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repo;

    @Test
    public void registerCustomerTest() {
        CustomerImpl customer = new CustomerImpl();
        customer.setEmail("willk@gmail.com");
        customer.setPassword("abc");
        customer.setFirstName("Will");
        customer.setLastName("Kupec");

        CustomerImpl savedCustomer = repo.save(customer);

        CustomerImpl existCustomer = entityManager.find(CustomerImpl.class, savedCustomer.getId());

        assertThat(customer.getEmail()).isEqualTo(existCustomer.getEmail());

    }
}
