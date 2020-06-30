package kg.ItAcademy.BusinessPage.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "final_page_d")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "name")
    String name;

    @Column(name = "photo")
    String photo;

    @Column(name = "address")
    String address;

//    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    List<Div> divs;
}
