package hello.core.order;

import org.springframework.stereotype.Component;

@Component
public interface OrderService {

    Order createdOrder(Long memberId, String itemName, int itemPrice);
}
