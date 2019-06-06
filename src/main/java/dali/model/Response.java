package dali.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import io.springlets.format.EntityFormat;

@Entity
@EntityFormat
/**
 * = Response
 TODO Auto-generated class documentation
 *
 */
public class Response {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
	
	@Column(name="client")
	String client;
	
	@Column(name="duration")
	long duration;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "run_id")
    private String id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "SS")
    private Date start;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "SS")
    private Date end;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "response")
    private List<Bacon> items = new ArrayList<Bacon>();
    
   

	public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

	/**
     * TODO Auto-generated method documentation
     * 
     * @param itemsToAdd
     */
    public void addToItems(Iterable<Bacon> itemsToAdd) {
        for (Bacon item : itemsToAdd) {
            this.items.add(item);
            item.setResponse(this);
        }
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param itemsToRemove
     */
    public void removeFromItems(Iterable<Bacon> itemsToRemove) {
        Assert.notNull(itemsToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Bacon item : itemsToRemove) {
            this.items.remove(item);
            item.setResponse(null);
        }
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    @Override
	public String toString() {
        return "Response {" + 
                "id='" + id + '\'' + 
                ", version='" + version + '\'' + 
                ", start='" + start + '\'' + 
                ", end='" + end + '\'' + 
                "}" + super.toString();
    }

	/**
     * This `equals` implementation is specific for JPA entities and uses 
     * the entity identifier for it, following the article in 
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     * 
     * @param obj
     * @return Boolean
     */
    @Override
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof Response)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Response) obj).getId());
    }

	/**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able 
     * to identify the entity in collections after a new id is assigned to the entity, following the article in 
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     * 
     * @return Integer
     */
    @Override
	public int hashCode() {
        return 31;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public String getId() {
        return this.id;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Integer
     */
    public Integer getVersion() {
        return this.version;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Date
     */
    public Date getStart() {
        return this.start;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param start
     */
    public void setStart(Date start) {
        this.start = start;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Date
     */
    public Date getEnd() {
        return this.end;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public List<Bacon> getItems() {
        return this.items;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param items
     */
    public void setItems(List<Bacon> items) {
        this.items = items;
    }

	public long getDuration() {
    	return this.duration;
    }

	public void setDuration(long duration) {
    	this.duration = duration;
    }

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
}
