package hu.progmasters.kingfishair.domain;

import hu.progmasters.kingfishair.dto.incoming.NewReservationCommand;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String identificationNumber;
    @Column
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @NotNull
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ExtraAddon.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "extra_addon")
    @Column(name = "extra_addon")
    private List<ExtraAddon> extraAddonList;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column
    private Long seatNumber;


    public Seat(Long id, String identificationNumber, String name, Gender gender, List<ExtraAddon> extraAddonList, Reservation reservation, Long seatNumber) {
        this.id = id;
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.gender = gender;
        this.extraAddonList = extraAddonList;
        this.reservation = reservation;
        this.seatNumber = seatNumber;
    }
    public Seat(NewReservationCommand newReservationCommand) {
        this.identificationNumber = newReservationCommand.getIdentificationNumber();
        this.name = newReservationCommand.getName();
        this.gender = Gender.valueOf(newReservationCommand.getGender());
        this.extraAddonList = newReservationCommand.getExtraAddons().stream().map(ExtraAddon::valueOf).collect(Collectors.toList());
        this.reservation= reservation;
        this.seatNumber= newReservationCommand.getSeatNumber();
    }

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<ExtraAddon> getExtraAddonList() {
        return extraAddonList;
    }

    public void setExtraAddonList(List<ExtraAddon> extraAddonList) {
        this.extraAddonList = extraAddonList;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }
}
