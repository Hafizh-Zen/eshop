package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/debug")
    @ResponseBody
    public String debugTemplates() {
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL resource = classLoader.getResource("templates/productList.html");
        return (resource != null) ? "Template Found: " + resource.toString() : "Template NOT found!";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }
    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") int productId, Model model) {
        Product product = service.findid(productId);
        model.addAttribute("product", product);
        return "editProduct";
    }
}
