package com.minhas.midias.entidade;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "midia")  // Define o nome da tabela no banco de dados
public class Midia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerenciamento automático da chave primária
    @Column(name = "id")  // Define o nome da coluna no banco de dados
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)  // Define o nome da coluna e a restrição de não nulo
    private String titulo;
    
    @Column(name = "tipo", nullable = false, length = 255)  // Define o nome da coluna e a restrição de não nulo
    private String tipo;

    @Column(name = "descricao", columnDefinition = "TEXT")  // Define a coluna como TEXT para mais espaço
    private String descricao;

    @Column(name = "visto", nullable = false)  // Define a coluna 'visto' como não nula
    private boolean visto;

    @Column(name = "capa_path", length = 255)  // Define o tamanho da coluna 'capa_path'
    private String capaPath;
    
    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public String getCapaPath() {
        return capaPath;
    }

    public void setCapaPath(String capaPath) {
        this.capaPath = capaPath;
    }

    // hashCode and equals
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Midia other = (Midia) obj;
        return Objects.equals(id, other.id);
    }
}