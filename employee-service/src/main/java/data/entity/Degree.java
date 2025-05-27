package data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id", nullable = false)
    private Integer id;

    @Column(name = "degree_name", nullable = false, length = 30)
    private String degreeName;

    @ColumnDefault("0")
    @Column(name = "base_salary_factor", nullable = false)
    private Double baseSalaryFactor;

    @OneToMany(mappedBy = "degree")
    private Set<Employee> employees = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Double getBaseSalaryFactor() {
        return baseSalaryFactor;
    }

    public void setBaseSalaryFactor(Double baseSalaryFactor) {
        this.baseSalaryFactor = baseSalaryFactor;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}