import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.cart.dto.CartItemDto;
import com.fruitSalad_backend.cart.messaging.SetOrderItemsProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SetOrderItemsProducer.class)
@TestPropertySource(properties = {"spring.rabbitmq.template.exchange=orderItems_exchange", "spring.rabbitmq.template.routing-key=orderItems_routing_key"})
public class SetOrderItemsProducerTests {

    @Autowired
    private SetOrderItemsProducer producer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    @Test
    public void testSendMessage() throws JsonProcessingException {
        List<CartItemDto> cartItems = Arrays.asList(new CartItemDto("item1", 2, 2, 405),
                new CartItemDto("item2", 3, 1, 222));
        String message = "mocked-message";

        when(objectMapper.writeValueAsString(cartItems)).thenReturn(message);

        producer.sendMessage(cartItems);

        verify(rabbitTemplate, times(1)).convertAndSend("orderItems_exchange", "orderItems_routing_key", message);
    }

    @Test
    public void testSendMessageWithJsonProcessingException() throws JsonProcessingException {
        final List<CartItemDto> cartItems = Arrays.asList(new CartItemDto("item1", 2, 2, 405),
                new CartItemDto("item2", 3, 1, 222));

        when(objectMapper.writeValueAsString(cartItems)).thenThrow(JsonProcessingException.class);

        assertThrows(JsonProcessingException.class, new Executable() {
            public void execute() throws Throwable {
                producer.sendMessage(cartItems);
            }
        });
    }
}
