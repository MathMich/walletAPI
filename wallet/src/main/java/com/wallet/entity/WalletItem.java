package com.wallet.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.wallet.util.enums.TypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallet_items") // pra saber olha dentro do src/main/resources/db/migration/postgreesql/V1__init.sql
@Data
// Como foi criado um construtor com parâmetros lá no Test
@NoArgsConstructor // aqui cria um construtor sem parâmetros
@AllArgsConstructor // aqui cria um construtor com todos parâmetros
public class WalletItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "wallet",referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Wallet wallet;
	@NotNull
	private Date date;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	@NotNull
	private String description;
	@NotNull
	private BigDecimal value;
	
	
	
}
