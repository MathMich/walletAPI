package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletItemsDTO {
	
	private Long id;
	@NotNull(message="Informe o ID da carteira")
	private Long Wallet;
	@NotNull(message = "Informe a Data")
	private Date date;
	@NotNull(message = "Informe um tipo")
	private String type;
    @NotNull(message="Informe uma descrição")
    @Length(min = 5, message="A descriçao deve conter no mínimo 5 caracteres")
    private String description;
    @NotNull(message="Informe um valor")
    private BigDecimal value;

}
