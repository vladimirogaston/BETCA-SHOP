package ar.ungs.shop.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import ar.ungs.shop.entities.ArticleEntity;

public class ArticleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Long barcode;

	@NotBlank
	private String description;
	
	private Date registrationDate;
	
	private Double price;
	
	private String provider;
	
	private Integer stock;

	private boolean discontinued;
	
	public ArticleDto() {}
	
	public ArticleDto(ArticleEntity entity) {
		setId(entity.getId());
		setBarcode(entity.getBarcode());
		setDescription(entity.getDescription());
		setRegistrationDate(entity.getRegistrationDate());
		setPrice(entity.getPrice());
		setProvider(entity.getProvider());
		setStock(entity.getStock());
		setDiscontinued(entity.isDiscontinued());
	}

	public ArticleEntity toEntity() {
		ArticleEntity entity = new ArticleEntity();
		entity.setId(id);
		entity.setBarcode(barcode);
		entity.setDescription(description);
		entity.setPrice(price);
		entity.setProvider(provider);
		entity.setStock(stock);
		entity.setRegistrationDate(registrationDate);
		entity.setDiscontinued(discontinued);
		return entity;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getBarcode() {
		return barcode;
	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
}