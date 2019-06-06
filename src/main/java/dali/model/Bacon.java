package dali.model;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import io.springlets.format.EntityFormat;

@Entity
@EntityFormat
/**
 * = Bacon
 TODO Auto-generated class documentation
 *
 */

public class Bacon implements Serializable {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
    //store it in db to not compute it over and over
    @Column(name="duration")
    long duration;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date start;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date end;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String data;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "response_run_id")
    @EntityFormat
    private Response response;

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
        if (!(obj instanceof Bacon)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Bacon) obj).getId());
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
     * @return String
     */
    @Override
	public String toString() {
        return "Bacon {" + 
                "id='" + id + '\'' + 
                ", version='" + version + '\'' + 
                ", start='" + start + '\'' + 
                ", end='" + end + '\'' + 
                ", data='" + data + '\'' + 
                "}" + super.toString();
    }


	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
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
     * @return Calendar
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
     * @return String
     */
    public String getData() {
        return this.data;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Response
     */
    public Response getResponse() {
        return this.response;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
}
