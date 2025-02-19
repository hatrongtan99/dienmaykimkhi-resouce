package com.hatrongtan99.app.messageBroker.consumer;

import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.exception.NotFoundException;
import com.hatrongtan99.app.messageBroker.dto.ProductChangeStatusInStock;
import com.hatrongtan99.app.repository.ProductRepository;
import com.hatrongtan99.saga.constants.QueueName;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductInventoryEventHandler {
    private final ProductRepository productRepository;

    @RabbitListener(queues = QueueName.CHANGE_STATUS_IN_STOCK_QUEUE_NAME)
    @Transactional
    public void handlerChangeStatusInStock(ProductChangeStatusInStock message) {
        ProductEntity mainProduct = this.productRepository.findById(message.productId())
                .orElseThrow(() -> new NotFoundException("not found"));
        mainProduct.setAvailInStock(message.isInStock());
    }
}
