import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.payment.messaging.SetOrderItemsConsumer;
import com.fruitSalad_backend.payment.service.IPaymentService;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.GenericMessage;

@Configuration
@EnableRabbit
public class IntegrationTestConfig {

        @Autowired
        private ObjectMapper objectMapper;

        @Bean
        public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
            factory.setConcurrentConsumers(1);
            factory.setMaxConcurrentConsumers(1);
            return factory;
        }

        @RabbitListener(queues = "orderItems")
        public void handleMessage(GenericMessage<String> message) throws JsonProcessingException {
            // Simulate RabbitMQ message consumption
            SetOrderItemsConsumer consumer = new SetOrderItemsConsumer();
            consumer.setPaymentService(Mockito.mock(IPaymentService.class));
            consumer.consume(message.getPayload());
        }
}
