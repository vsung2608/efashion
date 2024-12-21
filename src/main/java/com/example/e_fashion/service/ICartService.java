package com.example.e_fashion.service;

import com.example.e_fashion.dto.response.CartResponse;
import com.example.e_fashion.entity.Order;
import com.example.e_fashion.entity.User;

import java.util.List;

public interface ICartService {

    List<CartResponse> getAllItemByUserId(String userId);

    void deleteAllByUserId(String userId);

    Order payCart(User user);

    void addProductInCart(String email, String productId, int quantity);

    void deleteByCartId(String cartId);
}
