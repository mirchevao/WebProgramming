package web.programming.aud.wpaud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.programming.aud.wpaud.model.ShoppingCart;
import web.programming.aud.wpaud.service.ShoppingCartService;
import web.programming.aud.wpaud.model.User;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoopingCartPage(@RequestParam (required = false) String error, HttpServletRequest request, Model model) {

        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError",true);
            model.addAttribute("error", error);
        }

        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        return "shopping-cart";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            ShoppingCart shoppingCart = this.shoppingCartService.addProductToShoppingCart(user.getUsername(),id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}
