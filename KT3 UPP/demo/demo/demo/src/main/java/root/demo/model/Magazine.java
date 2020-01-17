package root.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "magazine")
public class Magazine {
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

    public Long getIssn() {
        return issn;
    }

    public void setIssn(Long issn) {
        this.issn = issn;
    }

    public String getSciFields() {
        return sciFields;
    }

    public void setSciFields(String sciFields) {
        this.sciFields = sciFields;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(String chiefEditor) {
        this.chiefEditor = chiefEditor;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getResident1() {
        return resident1;
    }

    public void setResident1(String resident1) {
        this.resident1 = resident1;
    }

    public String getResident2() {
        return resident2;
    }

    public void setResident2(String resident2) {
        this.resident2 = resident2;
    }

    public String getEditor1() {
        return editor1;
    }

    public void setEditor1(String editor1) {
        this.editor1 = editor1;
    }

    public String getEditor2() {
        return editor2;
    }

    public void setEditor2(String editor2) {
        this.editor2 = editor2;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = false,nullable = false)
    String name;
    @Column(name = "issn", unique = false, nullable = false)
    Long issn;

    @Column(name = "sciFields", unique = false, nullable = false)
    String sciFields;

    @Column(name = "paymentMethod", unique = false, nullable = false)
    String paymentMethod;

    @Column(name = "chiefEditor", unique = false, nullable = false)
    String chiefEditor;

    @Column(name = "active", unique = false, nullable = false)
    String active;

    @Column(name = "resident1", unique = false, nullable = true)
    String resident1;

    @Column(name = "resident2", unique = false, nullable = true)
    String resident2;

    @Column(name = "editor1", unique = false, nullable = true)
    String editor1;

    @Column(name = "editor2", unique = false, nullable = true)
    String editor2;

    public Magazine(){}

    public Magazine(String name, Long issn, String sciFields, String paymentMethod)
    {
        this.name = name;
        this.issn = issn;
        this.sciFields = sciFields;
        this.paymentMethod = paymentMethod;
    }
}
