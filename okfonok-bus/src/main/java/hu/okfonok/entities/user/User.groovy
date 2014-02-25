package hu.okfonok.entities.user

import hu.okfonok.entities.BaseEntity
import hu.okfonok.entities.Role

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * 
 * @author Ács Ádám
 *
 */
@Entity
class User extends BaseEntity implements Serializable{
	
	@Column(unique = true)
	@NotNull
	@Size(min=4, max=20)
	String userName
	
	@NotNull
	@Size(min=4)
	String password
	
	Boolean enabled = false
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "User_Roles", joinColumns = [ @JoinColumn(name = "user_id", referencedColumnName="id") ], inverseJoinColumns = [ @JoinColumn(name = "role_id", referencedColumnName="id") ])
	Set<Role> roles = new HashSet<Role>()
	
	@Embedded
	Address address = new Address()
	
	@Embedded
	Certification certification = new Certification()
	
	@Embedded
	Profile profile = new Profile()
	
	String providerId
	
	@Embedded
	Paypal paypal

}
