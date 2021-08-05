package br.com.gft.realestate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="TB_PROPERTY")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Endereço é obrigatório")
    private String address;

    @NotNull(message = "O Negócio é obrigatório")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="ID_BUSINESS")
    private Business business;

    @NotNull(message = "A Categoria é obrigatória")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="ID_CATEGORY")
    private Category category;

    @NotNull(message = "O Bairro é obrigatório")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="ID_NEIGHBORHOOD")
    private Neighborhood neighborhood;

    @NotNull(message = "A quantidade de Quarto é obrigatória")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="ID_ROOM")
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
