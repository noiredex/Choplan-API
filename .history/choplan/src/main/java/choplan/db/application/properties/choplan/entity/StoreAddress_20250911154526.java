package choplan.db.application.properties.choplan.entity;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StoreAddress {
    private String roadAddress; //도로명 주소
    private String datailAddress; //상세주소
    private String postalCode; // 우편번호
}
s