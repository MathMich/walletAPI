package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletItemsDTO {
	
	@NotNull(message="Informe o ID da carteira")
	private Long id;
	
	@NotNull(message = "Informe a Data")
	private Long Wallet;
	
	private Date date;
	
	@NotNull(message = "Informe um tipo")
	@Pattern(regexp="^(ENTRADA|SAÍDA)$",message = "Para o tipo somente são aceitos os valores ENTRADA ou SAÍDA")
	private String type;
    
	@NotNull(message="Informe uma descrição")
	@Length(min = 5, message="A descriçao deve conter no mínimo 5 caracteres")
	private String description;
    

    @NotNull(message="Informe um valor")
    private BigDecimal value;
}
