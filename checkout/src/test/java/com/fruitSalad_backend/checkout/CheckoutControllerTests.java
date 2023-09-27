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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

        Address address = new Address();
        address.setCustomer("Test Customer");
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Mockito.when(checkoutService.getAddressById(addressId)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/checkout/{id}", addressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Removed address with id: " + addressId));
    }

    @Test
    void testRemoveAddressNotFound() throws Exception {
        int addressId = 1;

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
                .delete("/checkout/{id}", addressId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateAddress() throws Exception {
        int addressId = 1;
        Address updatedAddress = new Address();
        updatedAddress.setCustomer("Test Customer");
        updatedAddress.setName("Test Name");
        updatedAddress.setAddress("123 Main St");
        updatedAddress.setAddressEtc("Apt 4B");
        updatedAddress.setCity("New York");
        updatedAddress.setCountry("USA");
        updatedAddress.setZipCode(10001);

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
    void testUpdateAddressNotFound() throws Exception {
        int addressId = 1;
        Address updatedAddress = new Address();
        updatedAddress.setCustomer("Test Customer");
        updatedAddress.setName("Test Name");
        updatedAddress.setAddress("123 Main St");
        updatedAddress.setAddressEtc("Apt 4B");
        updatedAddress.setCity("New York");
        updatedAddress.setCountry("USA");
        updatedAddress.setZipCode(10001);

        Mockito.when(checkoutService.getAddressById(addressId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/checkout/{id}", addressId)
                .content(asJsonString(updatedAddress))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testListAllAddresses() throws Exception {
        List<Address> addresses = new ArrayList<>();

        Address address = new Address();
        address.setCustomer("Test Customer");
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Address address2 = new Address();
        address2.setCustomer("Test Customer2");
        address2.setName("Test Name2");
        address2.setAddress("124 South St");
        address2.setAddressEtc("Floor 3");
        address2.setCity("Berlin");
        address2.setCountry("DE");
        address2.setZipCode(14581);

        addresses.add(address);
        addresses.add(address2);

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
        address.setCustomer("Test Customer");
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Mockito.when(checkoutService.getAddressById(addressId)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout/{id}", addressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(address)));
    }

    @Test
    void testGetAddressByIdNotFound() throws Exception {
        int addressId = 1;
        Address address = new Address();
        address.setCustomer("Test Customer");
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Mockito.when(checkoutService.getAddressById(addressId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout/{id}", addressId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAddressByCustomer() throws Exception {
        String customer = "custTest";
        Address address = new Address();
        address.setCustomer(customer);
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Mockito.when(checkoutService.getAddressByCustomer(customer)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout/customer/{customer}", customer))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(address)));
    }

    @Test
    void testGetAddressByCustomerNotFound() throws Exception {
        String customer = "custTest";
        Address address = new Address();
        address.setCustomer(customer);
        address.setName("Test Name");
        address.setAddress("123 Main St");
        address.setAddressEtc("Apt 4B");
        address.setCity("New York");
        address.setCountry("USA");
        address.setZipCode(10001);

        Mockito.when(checkoutService.getAddressByCustomer(customer)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/checkout/customer/{customer}", customer)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

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