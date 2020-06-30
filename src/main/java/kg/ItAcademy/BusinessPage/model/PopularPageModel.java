package kg.ItAcademy.BusinessPage.model;

import kg.ItAcademy.BusinessPage.entity.Page;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PopularPageModel implements Comparable<PopularPageModel>{
    String name;
    String address;
    Long count;

    @Override
    public int compareTo(PopularPageModel popularPageModel) {
        return -this.count.compareTo(popularPageModel.count);
    }
}
