package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
        name = "getMyAllFollows",
        query = "SELECT f FROM Follow AS f WHERE f.followor_employee = :followor_employee ORDER BY f.id DESC"
    ),
    @NamedQuery(
        name = "getMyFollowsCount",
        query = "SELECT COUNT(f) FROM Follow AS f WHERE f.followor_employee = :followor_employee"
    ),
    @NamedQuery(
        name = "follow_employeeDuplicateCheckFlag",
        query = "SELECT COUNT (f) FROM Follow AS f "
                + "WHERE f.follow_employee = :follow_employee and f.followor_employee = :followor_employee"
        )
})

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    private Employee follow_employee;

    @ManyToOne
    @JoinColumn(name = "followor_id",nullable = false)
    private Employee followor_employee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFollow_employee() {
        return follow_employee;
    }

    public void setFollow_employee(Employee follow_employee) {
        this.follow_employee = follow_employee;
    }

    public Employee getFollowor_employee() {
        return followor_employee;
    }

    public void setFollowor_employee(Employee followor_employee) {
        this.followor_employee = followor_employee;
    }



}
