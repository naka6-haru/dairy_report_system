package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "feedback")
@NamedQueries({
    @NamedQuery(
        name = "getMyAllFeedback",
        query = "SELECT f FROM Feedback AS f WHERE f.report_id = :report_id ORDER BY f.id DESC"
        ),
    @NamedQuery(
        name = "getMyFeedbackCount",
        query = "SELECT COUNT (f) FROM Feedback AS f WHERE f.report_id = :report_id"
        )
})
@Entity
public class Feedback {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "report_id",nullable = false)
    private Report report_id;

    @ManyToOne
    @JoinColumn(name = "commment_employee_id",nullable = false)
    private Employee comment_employee;

    @Lob
    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "created_at",nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at",nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Report getReport_id() {
        return report_id;
    }

    public void setReport_id(Report report_id) {
        this.report_id = report_id;
    }

    public Employee getComment_employee() {
        return comment_employee;
    }

    public void setComment_employee(Employee comment_employee) {
        this.comment_employee = comment_employee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
