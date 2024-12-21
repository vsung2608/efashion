package com.example.e_fashion.service.impl;

import com.example.e_fashion.dto.response.CartResponse;
import com.example.e_fashion.entity.*;
import com.example.e_fashion.mapper.BrandMapper;
import com.example.e_fashion.mapper.CartMapper;
import com.example.e_fashion.mapper.ProductMapper;
import com.example.e_fashion.repository.CartRepository;
import com.example.e_fashion.repository.ProductRepository;
import com.example.e_fashion.repository.UserRepository;
import com.example.e_fashion.service.ICartService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final ProductMapper productMapper;
    private final CartMapper cartMapper;
    private final BrandMapper brandMapper;

    @Override
    public List<CartResponse> getAllItemByUserId(String userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(c -> {
                    var cartItem = cartMapper.toResponse(c);

                    var pres = productMapper.toProductResponse(c.getProduct());
                    pres.setBrand(brandMapper.toBrandResponse(c.getProduct().getBrand()));
                    BigDecimal price = pres.getPrice();
                    BigDecimal discount = BigDecimal.valueOf(pres.getDiscount());
                    BigDecimal discountedPrice = price
                            .multiply(BigDecimal.valueOf(100).subtract(discount))
                            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                    pres.setPromotionalPrice(discountedPrice);

                    cartItem.setProduct(pres);
                    return cartItem;
                }).toList();
    }

    @Override
    public void deleteAllByUserId(String userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Override
    public Order payCart(User user) {
        List<Cart> carts = cartRepository.findByUserId(user.getId());
        List<OrderItem> orders = new ArrayList<>();

        carts.forEach(c -> {
            BigDecimal price = c.getProduct().getPrice();
            BigDecimal discount = BigDecimal.valueOf(c.getProduct().getDiscount());
            BigDecimal discountedPrice = price
                    .multiply(BigDecimal.valueOf(100).subtract(discount))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            OrderItem o = OrderItem.builder()
                    .price(discountedPrice.multiply(new BigDecimal(c.getQuantity())))
                    .quantity(c.getQuantity())
                    .product(c.getProduct())
                    .build();
            orders.add(o);
        });

        BigDecimal total_price = BigDecimal.ZERO;
        int total_amount = 0;
        for (var o : orders){
            total_price = total_price.add(o.getPrice());
            total_amount += o.getQuantity();
        }

        Payment payment = Payment.builder()
                .paymentMethod("Trực tiếp")
                .paymentStatus("Chưa thanh toán")
                .amount(total_price)
                .build();

        Order order = Order.builder()
                .payment(payment)
                .orderItems(orders)
                .shippingAddress(user.getDeliveryAddress())
                .status(OrderStatus.PENDING)
                .totalAmount(total_amount)
                .user(user)
                .build();

        payment.setOrder(order);
        orders.forEach(o -> o.setOrder(order));

        return order;
    }

    @Override
    public void addProductInCart(String email, String productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = Cart.builder()
                .product(product)
                .user(user)
                .quantity(quantity)
                .build();
        cartRepository.save(cart);
    }

    @Override
    public void deleteByCartId(String cartId) {
        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartRepository.delete(cart);
    }
}
