import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.cart.dto.CartItemDto;
import com.fruitSalad_backend.cart.messaging.SetOrderItemsProducer;
import com.fruitSalad_backend.payment.messaging.SetOrderItemsConsumer;
import com.fruitSalad_backend.payment.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootApplication
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@SpringBootTest(classes = Application.class)
@ExtendWith(OutputCaptureExtension.class)
@TestPropertySource(properties = {
        "spring.rabbitmq.host=localhost",
        "spring.rabbitmq.port=5672",
        "spring.rabbitmq.username=guest",
        "spring.rabbitmq.password=guest",
        "spring.rabbitmq.template.exchange=orderItems_exchange",
        "spring.rabbitmq.template.routing-key=orderItems_routing_key"
})
@Testcontainers
public class SetOrderItemsTests {

    @Autowired
    private SetOrderItemsProducer producer;

    @Autowired
    private SetOrderItemsConsumer consumer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Container
    public static GenericContainer<?> rabbitmqContainer = new GenericContainer<>("rabbitmq:3.12.2-management")
            .withExposedPorts(5672);

    @DynamicPropertySource
    static void rabbitmqProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", rabbitmqContainer::getContainerIpAddress);
        registry.add("spring.rabbitmq.port", rabbitmqContainer::getFirstMappedPort);
    }

    @BeforeEach
    void setUp() {
        // Mock external service calls or RabbitMQ interactions if needed
    }

    @Test
    public void testSendMessageAndConsume() throws JsonProcessingException {
        // Arrange
        List<CartItemDto> cartItems = Arrays.asList(new CartItemDto("item1", 2.5, 2, 1),
                new CartItemDto("item2", 3.5, 2, 2));
        String message = "mocked-message";
        ObjectMapper objectMapper = new ObjectMapper();

        when(rabbitTemplate.convertSendAndReceive(anyString(), anyString(), any(CartItemDto.class))).thenReturn(message);
        when(objectMapper.readValue(message, objectMapper.getTypeFactory().constructCollectionType(List.class, OrderItem.class)))
                .thenReturn(Arrays.asList(new OrderItem(1, "testTitle", 2.0, 1, 5), new OrderItem(2, "testTitle2", 6.0, 5, 4)));

        producer.sendMessage(cartItems);

        Mockito.verify(rabbitTemplate, times(1)).convertAndSend("orderItems_exchange", "orderItems_routing_key", message);
        Mockito.verify(consumer, times(1)).consume(message);

        // Add more assertions as needed
        List<OrderItem> processedOrderItems = consumer.getPaymentService().getOrderItems();
        assertEquals(2, processedOrderItems.size());
        assertEquals("item1", processedOrderItems.get(0).getTitle());
        assertEquals(2.0, processedOrderItems.get(0).getPrice());
        assertEquals("item2", processedOrderItems.get(1).getTitle());
        assertEquals(3.0, processedOrderItems.get(1).getPrice());
    }
}