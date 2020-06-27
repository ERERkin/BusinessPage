package kg.ItAcademy.BusinessPage.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageModel {
    Long id;
    String title;
    String name;
}
