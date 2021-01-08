package ar.ungs.shop.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ArticleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private Long barcode;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	private Integer stock;
	
	private Double price;
	
	private String provider;
	
	private boolean discontinued;

	public ArticleEntity() {
		setId(null);
		setBarcode(null);
		setDescription(null);
		setRegistrationDate(null);
		setPrice(null);
		setProvider(null);
		setStock(null);
		this.setDiscontinued(false);
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
	
	@JsonIgnore
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (discontinued ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}

	@JsonIgnore
	@Override
	public boolean equals(Object obj) {
		ArticleEntity other = (ArticleEntity) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discontinued != other.discontinued)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	@JsonIgnore
	public static ArticleEntity makeTestEntity() {
		ArticleEntity ret = new ArticleEntity();
		ret.setBarcode((long) 312313);
		ret.setDescription("desc");
		ret.setProvider("prov");
		ret.setPrice(12.0);
		ret.setRegistrationDate(new Date());
		ret.setStock(20);
		ret.setDiscontinued(false);
		return ret;
	}

	@JsonIgnore
	@Override
	public String toString() {
		return "ArticleEntity [id=" + id + ", barcode=" + barcode + ", description=" + description
				+ ", registrationDate=" + registrationDate + ", stock=" + stock + ", price=" + price + ", provider="
				+ provider + ", discontinued=" + discontinued + "]";
	}
}
