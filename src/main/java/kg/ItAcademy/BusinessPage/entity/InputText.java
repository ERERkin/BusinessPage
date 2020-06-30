package kg.ItAcademy.BusinessPage.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "final_input_text_d")
public class InputText {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "input_name")
    String inputName;

    @Column(name = "input_text")
    String inputText;

    @ManyToOne
    @JoinColumn(name = "div_id")
    Div divText;

//    @Column(name = "input_text")
//    String style;
}
