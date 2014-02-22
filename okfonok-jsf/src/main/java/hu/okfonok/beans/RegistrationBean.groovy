package hu.okfonok.beans;

import hu.okfonok.entities.user.User
import hu.okfonok.mail.RegistrationMailSender
import hu.okfonok.services.RoleService
import hu.okfonok.services.UserService

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
	private transient RoleService roleService
	@Inject
	private transient UserService userService
	@Inject
	private transient Md5PasswordEncoder passwordEncoder
	@Inject
	private transient RegistrationMailSender regMailSender

	User user = new User()

	
	void register(User user) {
		try {
			userService.persist(user)
			user.roles.add(roleService.getUserRole())
			user.password = passwordEncoder.encodePassword(user.password, "basicsalt")
			userService.merge(user)
			if (!user.enabled) {
				regMailSender.send();
			}
			log.info("Sikeres regisztráció: $user.userName")
			user = new User();
		}
		catch (DataIntegrityViolationException divEx) {
			log.error("Sikertelen regisztráció!", divEx);
		}
		catch (Exception e) {
			log.fatal("Sikertelen regisztráció!", e);
		}
	}
	
	void register() {
		register(user)
	}

	void reset() {
		user = new User()
	}

}