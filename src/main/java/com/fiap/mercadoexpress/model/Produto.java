package com.fiap.mercadoexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "TDS_TB_MERCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mercado_seq")
    @SequenceGenerator(name = "mercado_seq", sequenceName = "TDS_SEQ_MERCADO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @NotBlank(message = "Setor é obrigatório")
    @Column(name = "SETOR", nullable = false, length = 50)
    private String setor;

    @NotBlank(message = "Tamanho é obrigatório")
    @Column(name = "TAMANHO", nullable = false, length = 20)
    private String tamanho;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

}
