import com.fasterxml.jackson.core.JsonProcessingException;
import com.fruitSalad_backend.payment.messaging.SetOrderItemsConsumer;
import com.fruitSalad_backend.payment.model.OrderItem;
import com.fruitSalad_backend.payment.service.IPaymentService;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.testng.annotations.Test;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SetOrderItemsConsumer.class)
public class SetOrderItemsConsumerTests {

    @Autowired
    private SetOrderItemsConsumer consumer;

    @MockBean
    private IPaymentService paymentService;

    @Test
    public void testConsume() throws JsonProcessingException {

        String message = "mocked-message";
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        Mockito.when(paymentService.getOrderItems()).thenReturn(orderItems);

        consumer.consume(message);

        Mockito.verify(paymentService, Mockito.times(1)).setOrderItems(orderItems);
    }

    @Test
    public void testConsumeWithJsonProcessingException() throws JsonProcessingException {
        final String invalidMessage = "{invalid_json}";

        assertThrows(JsonProcessingException.class, new Executable() {
            public void execute() throws Throwable {
                consumer.consume(invalidMessage);
            }
        });
    }
}
