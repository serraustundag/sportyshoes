package ex.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)		// pk with auto_increment
    private int oid;
    private Integer pid;				// FK can hold null value but int can't in java side
    private LocalDateTime ldt;
    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public LocalDateTime getLdt() {
        return ldt;
    }
    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Login login;
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Orders [oid=" + oid + ", pid=" + pid + ", ldt=" + ldt + "]";
    }


}