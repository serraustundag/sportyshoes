package ex.controller;

import ex.entity.Login;
import ex.entity.Orders;
import ex.entity.Product;
import ex.repository.LoginRepository;
import ex.service.OrdersService;
import ex.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class UserDashboardController {
    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;
    @Autowired
    LoginRepository loginRepository;

    @RequestMapping(value = "userdashboard",method = RequestMethod.GET)
    public String open(Model model, Product product) {

        String name="Store Product";

        List<Product> listOfProduct = productService.findAllProducts();
        List<Object[]> orderdetails = productService.orderDetails();

        model.addAttribute("products", listOfProduct);
        model.addAttribute("buttonValue", name);
        model.addAttribute("product", product);
        model.addAttribute("orderdetails", orderdetails);

        return "userdashboard";
    }
    @RequestMapping(value = "/orderPlace",method = RequestMethod.GET)
    public String placeOrder(Model model, HttpServletRequest req, Orders order, Product product) {
        int pid = Integer.parseInt(req.getParameter("pid"));
        order.setPid(pid);
        int userId = getUserIdFromSession();
        Login user = new Login();
        user.setId(userId); // Veya kullanıcının kimliğini içeren uygun bir alanı ayarlayın.
        order.setLogin(user);
        String name="Store Product";
        String result = ordersService.placeOrder(order);
        List<Product> listOfProduct = productService.findAllProducts();
        model.addAttribute("products", listOfProduct);
        model.addAttribute("product", product);
        model.addAttribute("msg", result);
        model.addAttribute("buttonValue", name);
        //model.addAttribute("msg", result);
        List<Object[]> orderdetails = productService.orderDetails();
        model.addAttribute("orderdetails", orderdetails);
        return "userdashboard";
    }
    private int getUserIdFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Login> userOptional = loginRepository.findByUsername(username);
        Login user = userOptional.get();
        int userId = user.getId();
        return userId;
    }
}