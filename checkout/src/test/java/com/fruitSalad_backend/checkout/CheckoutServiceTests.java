package com.fruitSalad_backend.checkout;

import com.fruitSalad_backend.checkout.messaging.CheckoutProducer;
import com.fruitSalad_backend.checkout.model.Address;
import com.fruitSalad_backend.checkout.repository.CheckoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class CheckoutServiceTests {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private CheckoutRepository checkoutRepository;

        @Test
        public void testAddAddress() {
            // Create an instance of the Address entity
            Address address = new Address();
            address.setCustomer("John Doe");
            address.setName("Home");
            address.setAddress("123 Main St");
            address.setAddressEtc("Apt 4B");
            address.setCity("New York");
            address.setCountry("USA");
            address.setZipCode(10001);

            // Save the address to the in-memory database
            Address savedAddress = entityManager.persistAndFlush(address);

            // Retrieve the address from the repository
            Address retrievedAddress = checkoutRepository.findById(savedAddress.getId());

            assertThat(retrievedAddress).isNotNull();
            assertThat(retrievedAddress.getId()).isEqualTo(savedAddress.getId());
            assertThat(retrievedAddress.getCustomer()).isEqualTo("John Doe");
            assertThat(retrievedAddress.getName()).isEqualTo("Home");
            assertThat(retrievedAddress.getAddress()).isEqualTo("123 Main St");
            assertThat(retrievedAddress.getAddressEtc()).isEqualTo("Apt 4B");
            assertThat(retrievedAddress.getCity()).isEqualTo("New York");
            assertThat(retrievedAddress.getCountry()).isEqualTo("USA");
            assertThat(retrievedAddress.getZipCode()).isEqualTo(10001);
        }
}
