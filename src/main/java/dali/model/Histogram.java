package dali.model;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.NumberFormat;

import io.springlets.format.EntityFormat;

@Entity
@Table(name = "histogram")
@EntityFormat
/**
 * = Histogram
 TODO Auto-generated class documentation
 *
 */
public class Histogram {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

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
    @Column(name = "word")
    private String word;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "frequency")
    @NumberFormat
    private int frequency;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    @Override
	public String toString() {
        return "Histogram {" + 
                "id='" + id + '\'' + 
                ", version='" + version + '\'' + 
                ", word='" + word + '\'' + 
                ", frequency='" + frequency + '\'' + 
                ", ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE + '\'' + 
                ", ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE + '\'' + "}" + super.toString();
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
        if (!(obj instanceof Histogram)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Histogram) obj).getId());
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
    public Long getId() {
        return this.id;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     */
    public void setId(Long id) {
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
     * @return String
     */
    public String getWord() {
        return this.word;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Integer
     */
    public int getFrequency() {
        return this.frequency;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";
}
