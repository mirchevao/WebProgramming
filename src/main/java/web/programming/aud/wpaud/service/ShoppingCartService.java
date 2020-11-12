package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Product;
import web.programming.aud.wpaud.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
