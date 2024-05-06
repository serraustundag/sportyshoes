package ex.controller;

import ex.entity.Login;
import ex.entity.Orders;
import ex.entity.Product;
import ex.repository.LoginRepository;
import ex.service.LoginService;
import ex.service.OrdersService;
import ex.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminDashboardController {

    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    LoginService loginService;


    @RequestMapping(value = "admindashboard",method = RequestMethod.GET)
    public String open(Model model, Product product) {

        String name="Store Product";

        List<Product> listOfProduct = productService.findAllProducts();
        List<Object[]> orderdetails = productService.orderDetails();
        List<Login> loggedInUsers = loginService.findAllLoggedInUsers();

        model.addAttribute("products", listOfProduct);
        model.addAttribute("buttonValue", name);
        model.addAttribute("product", product);
        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("loggedInUsers", loggedInUsers);

        System.out.println(listOfProduct);
        return "admindashboard";
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProductDetails(Model model, Product product, HttpServletRequest req) {
        String b1 = req.getParameter("b1");
        String result="";
        String name="";
        if(b1.equals("Store Product")) {
            result = productService.storeProduct(product);
        }else {
            result = productService.updateProduct(product);
        }
        name = "Store Product";
        product.setPid(0);
        product.setPname("");
        product.setPrice(0);
        product.setCategory("");
        model.addAttribute("product", product);
        List<Product> listOfProduct = productService.findAllProducts();
        List<Object[]> orderdetails = productService.orderDetails();
        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("products", listOfProduct);
        model.addAttribute("msg", result);
        model.addAttribute("buttonValue", name);
        return "admindashboard";
    }

    @RequestMapping(value = "/deleteProduct",method = RequestMethod.GET)
    public String deleteProductById(Model model, Product product,HttpServletRequest req) {
        int pid = Integer.parseInt(req.getParameter("pid"));
        System.out.println("pid is "+pid);
        String name = "Store Product";
        String result = productService.deleteProduct(pid);
        List<Product> listOfProduct = productService.findAllProducts();
        model.addAttribute("products", listOfProduct);
        model.addAttribute("product", product);
        model.addAttribute("msg", result);
        model.addAttribute("buttonValue", name);
        List<Object[]> orderdetails = productService.orderDetails();
        model.addAttribute("orderdetails", orderdetails);
        return "admindashboard";
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.GET)
    public String searchProductById(Model model, HttpServletRequest req) {
        int pid = Integer.parseInt(req.getParameter("pid"));
        String name="Update Product";
        Product product = productService.searchProductById(pid);
        List<Product> listOfProduct = productService.findAllProducts();
        model.addAttribute("products", listOfProduct);
        model.addAttribute("product", product);
        model.addAttribute("buttonValue", name);
        List<Object[]> orderdetails = productService.orderDetails();
        model.addAttribute("orderdetails", orderdetails);
        //model.addAttribute("msg", result);

        return "admindashboard";
    }
    /*
    @RequestMapping(value="/loggedInUsers", method = RequestMethod.GET)
        public String loggedInUsers(Model model, HttpServletRequest req) {
        List <Login> loggedInUsers = loginService.findAllLoggedInUsers();
        model.addAttribute("loggedInUsers", loggedInUsers);
        return "admindashboard";     }

     */
    @RequestMapping(value="/loggedInUsers", method = RequestMethod.GET)
    public String loggedInUsers(Model model) {
        List<Login> loggedInUsers = loginService.findAllLoggedInUsers();
        model.addAttribute("loggedInUsers", loggedInUsers);
        return "admindashboard";
    }
}