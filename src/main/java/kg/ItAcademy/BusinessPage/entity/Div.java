package kg.ItAcademy.BusinessPage.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "final_div2")
public class Div implements Comparable<Div>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "type")
    Integer type;

    @Column(name = "address")
    String address;

    @ManyToOne
    @JoinColumn(name = "page_id")
    Page page;

    @Column(name = "address")
    String style;

    @Override
    public int compareTo(Div div) {
        int a = type.compareTo(div.getType());
        if(a == 0) return id.compareTo(div.getId());
        return a;
    }

    //    @OneToMany(mappedBy = "divText", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    List<InputText> inputTexts;
//
//    @OneToMany(mappedBy = "divClass", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    List<StyleClass> styleClasses;
}
