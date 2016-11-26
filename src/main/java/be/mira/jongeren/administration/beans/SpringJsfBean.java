package be.mira.jongeren.administration.beans;


import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.Date;

/*@ManagedBean
@RequestScoped*/
@Component
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "home", pattern = "/", viewId = "/index.jsf")
})
public class SpringJsfBean {

    private String hello = "KJKJMMLMMMHello World";

    private Date date;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
