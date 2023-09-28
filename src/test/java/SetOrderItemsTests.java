import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.cart.dto.CartItemDto;
import com.fruitSalad_backend.payment.model.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.rabbitmq.template.exchange=orderItems_exchange",
        "spring.rabbitmq.template.routing-key=orderItems_routing_key"
})
public class SetOrderItemsTests {

    @Autowired
    private SetOrderItemsProducer producer;

    @Autowired
    private SetOrderItemsConsumer consumer;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessageAndConsume() throws JsonProcessingException {
        // Arrange
        List<CartItemDto> cartItems = Arrays.asList(new CartItemDto("item1", 2), new CartItemDto("item2", 3));
        String message = "mocked-message";
        ObjectMapper objectMapper = new ObjectMapper();

        when(rabbitTemplate.convertSendAndReceive(anyString(), anyString(), any())).thenReturn(message);
        when(objectMapper.readValue(message, objectMapper.getTypeFactory().constructCollectionType(List.class, OrderItem.class)))
                .thenReturn(Arrays.asList(new OrderItem("item1", 2.0), new OrderItem("item2", 3.0)));

        // Act
        producer.sendMessage(cartItems);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend("orderItems_exchange", "orderItems_routing_key", message);
        verify(consumer, times(1)).consume(message);
    }

    @Test
    public void testSendMessageWithRabbitTemplateFailure() throws JsonProcessingException {
        // Arrange
        List<CartItemDto> cartItems = Arrays.asList(new CartItemDto("item1", 2), new CartItemDto("item2", 3));

        when(rabbitTemplate.convertAndSend(anyString(), anyString(), any())).thenThrow(RuntimeException.class);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> producer.sendMessage(cartItems));
        verify(consumer, never()).consume(any());
    }

    @Test
    public void testConsumeWithJsonProcessingException() throws JsonProcessingException {
        // Arrange
        String invalidMessage = "{invalid_json}";

        // Act and Assert
        assertThrows(JsonProcessingException.class, () -> consumer.consume(invalidMessage));
    }

    @Test
    public void testConsumeWithServiceFailure() throws JsonProcessingException {
        // Arrange
        String message = "mocked-message";
        when(consumer.paymentService.setOrderItems(any())).thenThrow(RuntimeException.class);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> consumer.consume(message));
    }
}
