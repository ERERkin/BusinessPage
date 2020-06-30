package kg.ItAcademy.BusinessPage.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
//import java.sql.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "final_log_d")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "page_id")
    Page page;

    @Column(name = "title")
    Long views;

    @Column(name = "date_views")
    Date date;
}
