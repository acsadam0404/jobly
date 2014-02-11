package hu.jobly.beans;

import hu.jobly.entities.User
import hu.jobly.services.RoleService
import hu.jobly.services.UserService

import javax.inject.Inject
import javax.inject.Named

import org.apache.log4j.Logger
import org.springframework.context.annotation.Scope
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.authentication.encoding.Md5PasswordEncoder

/**
 * 
 * @author Ács Ádám
 *
 */
@Named("registrationBean")
@Scope("session")
class RegistrationBean implements Serializable {
	private static final Logger log = Logger.getLogger(RegistrationBean.class)

	@Inject
	private RoleService roleService
	@Inject
	private UserService userService
	@Inject
	private Md5PasswordEncoder passwordEncoder

	User user = new User()

	void register() {
		try {
			user.roles.add(roleService.getUserRole())
			user.password = passwordEncoder.encodePassword(user.password, "basicsalt")
			userService.merge(user)
			user = new User();
		} 
		catch (DataIntegrityViolationException divEx) {
			log.error("Sikertelen regisztráció!", divEx);
		}
		catch (Exception e) {
			log.fatal("Sikertelen regisztráció!", e);
		}
	}
	
	void reset() {
		user = new User()
	}
}