package com.wallet.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserWalletDTO {

		private Long id;  // está tudo como Long pois está trafegando apenas o ID
		@NotNull(message = "Informe o ID do usuário")
		private Long users;
		@NotNull(message = "Informe o ID da carteira")
		private Long wallet;
}
