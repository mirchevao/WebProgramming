package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.Product;
import web.programming.aud.wpaud.model.ShoppingCart;
import web.programming.aud.wpaud.model.enumerations.ShoppingCartStatus;
import web.programming.aud.wpaud.model.exceptions.ProductAlreadyInShoppingCartException;
import web.programming.aud.wpaud.model.exceptions.ProductNotFoundException;
import web.programming.aud.wpaud.model.exceptions.ShoppingCartNotFoundException;
import web.programming.aud.wpaud.model.exceptions.UserNotFoundException;
import web.programming.aud.wpaud.repository.impl.InMemoryShoppingCartRepository;
import web.programming.aud.wpaud.repository.impl.InMemoryUserRepository;
import web.programming.aud.wpaud.service.ProductService;
import web.programming.aud.wpaud.service.ShoppingCartService;

import web.programming.aud.wpaud.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository, InMemoryUserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }


    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent()) {
            throw new ShoppingCartNotFoundException(cartId);
        }

        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findUserByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        if(shoppingCart.getProducts()
                .stream()
                .filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0) {

            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
