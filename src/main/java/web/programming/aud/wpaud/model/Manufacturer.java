package web.programming.aud.wpaud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Manufacturer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "manufacturerAddress")
    private String address;

    public Manufacturer(String name, String address) {
        this.id = (long) Math.random()*1000;
        this.name = name;
        this.address = address;
    }


    public Manufacturer() {

    }
}
