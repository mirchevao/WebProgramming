package web.programming.aud.wpaud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.model.Product;
import web.programming.aud.wpaud.service.CategoryService;
import web.programming.aud.wpaud.service.ManufacturerService;
import web.programming.aud.wpaud.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if(this.productService.findById(id).isPresent()){
            Product product = this.productService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            return "add-product";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        return "add-product";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer){
        this.productService.save(name, price, quantity, category, manufacturer);
        return "redirect:/products";
    }
}
