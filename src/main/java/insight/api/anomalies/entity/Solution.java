package insight.api.anomalies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor  
@Table(name = "solutions")
<<<<<<< HEAD
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; 
    
    /*@ManyToMany(mappedBy = "solutions",cascade = CascadeType.DETACH)
    private List<Probleme> problemes;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
    
    
    
=======
public class Solution { 
	@Id													
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String titre;
														
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "probleme_id") 
	private Probleme probleme;*/ 
>>>>>>> parent of 06f4cee (add relations between product probleme and solutions)
}
