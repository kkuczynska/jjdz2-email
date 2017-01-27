package com.jbd.DBA;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQuery(name = "Form.findByName", query = "select p FROM Form p WHERE p.name = :name ")
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_form")
    private Long id;
    @Column(nullable = false)
    private String name;
    private LocalDateTime creationTime;
    @OneToMany(mappedBy = "form" )
   // @JoinColumn(name = "form_id", referencedColumnName = "id_form")
    private List<Form_Details> details;

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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
