package hu.progmasters.kingfishair.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotNull
    private String registrationNumber;

    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private Long numberOfSeats;


   @OneToMany(mappedBy = "plane")
    private List<Image> imageList;


    public Plane(Long id, String registrationNumber, String name, Long numberOfSeats, List<Image> imageList) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.imageList = imageList;
    }

    public Plane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
