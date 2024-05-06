package ex.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Scope("prototype")
public class Product {

    @Id
    private int pid;
    private String pname;
    private float price;
    private String category; // Kategori alanı

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pid")
    private List <Orders> listOfOrders;
    private List<Orders> getListOfOrders()
    {
        return listOfOrders;
    }
    public void setListOfOrders(List<Orders> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
