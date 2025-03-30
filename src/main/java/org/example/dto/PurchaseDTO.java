package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.Employee;
import org.example.entities.Goods;
import org.example.entities.PurchaseType;
import org.example.entities.Shop;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@Setter
public class PurchaseDTO {
    @NotNull
    private Long goodsId;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long shopId;

    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    private Long purchaseTypeId;
}
