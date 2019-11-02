package tr.ozaytunctan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity<ID> implements Serializable {

	@Id
	@GeneratedValue(generator="idGenerator",strategy=GenerationType.SEQUENCE)
	private ID id;

	private Date createdDate = new Date();

	public BaseEntity() {
	}

	BaseEntity(ID id) {
		setId(id);
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
