package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by yefeiw on 7/2/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CardInfo {
    //Credit Card Information
    private String creditCardNumber;
    private String expirationDate;
    private String securityCode;

    @JsonCreator
    public CardInfo(@JsonProperty("number")String number,
                   @JsonProperty("exp") String expirationDate,
                   @JsonProperty("cvc") String cvc) {
        this.creditCardNumber = number;
        this.expirationDate = expirationDate;
        this.securityCode = cvc;
    }

    public boolean isValid() {
        if (creditCardNumber == null || expirationDate == null || securityCode == null) {
            return false;
        }
        return true;
    }

}
