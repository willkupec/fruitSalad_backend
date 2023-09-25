package com.fruitSalad_backend.checkout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.checkout.controller.CheckoutController;
import com.fruitSalad_backend.checkout.messaging.CheckoutProducer;
import com.fruitSalad_backend.checkout.model.Address;
import com.fruitSalad_backend.checkout.repository.CheckoutRepository;
import com.fruitSalad_backend.checkout.service.ICheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutRepository checkoutRepository;

    @MockBean
    private ICheckoutService checkoutService;

    @MockBean
    private CheckoutProducer checkoutProducer;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testAddAddress() throws Exception {
        Address address = new Address();
        address.setCustomer("Test Customer");
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Mockito.when(checkoutService.addAddress(Mockito.any())).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/checkout")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Added " + address));
    }

    @Test
    void testRemoveAddress() throws Exception {
        int addressId = 1;

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/checkout/{id}", addressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Removed address with id: " + addressId));
    }

    @Test
    void testRemoveAddressDoesNotExist() throws Exception {

        int addressId = 1;

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/checkout/{id}", addressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Removed address with id: " + addressId));
    }

    @Test
    void testUpdateAddress() throws Exception {
        int addressId = 1;
        Address updatedAddress = new Address();
        // Set updated values for the address

        Mockito.when(checkoutService.getAddressById(addressId)).thenReturn(updatedAddress);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/checkout/{id}", addressId)
                .content(asJsonString(updatedAddress))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(updatedAddress)));
    }

    @Test
    void testListAllAddresses() throws Exception {
        List<Address> addresses = new ArrayList<>();
        // Add multiple addresses to the list as needed

        Mockito.when(checkoutService.getAllAddresses()).thenReturn(addresses);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(addresses)));
    }

    @Test
    void testGetAddressById() throws Exception {
        int addressId = 1;
        Address address = new Address();
        // Set address details as needed

        Mockito.when(checkoutService.getAddressById(addressId)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout/{id}", addressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(address)));
    }

    @Test
    void testGetAddressByCustomer() throws Exception {
        String customer = "John Doe";
        Address address = new Address();
        // Set address details as needed

        Mockito.when(checkoutService.getAddressByCustomer(customer)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout/customer/{customer}", customer))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(address)));
    }

    // Add more test cases for error scenarios, validation, etc., as needed

    // Helper method to convert an object to JSON
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}