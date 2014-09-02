//package hu.okfonok.beans
//
//import hu.okfonok.entities.Address
//import hu.okfonok.entities.user.Certification
//import hu.okfonok.entities.user.Profile
//import hu.okfonok.services.UserService
//import hu.okfonok.utils.Config
//
//import javax.annotation.PostConstruct
//
//import org.apache.commons.io.FileUtils
//import org.primefaces.event.FileUploadEvent
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Scope
//
///**
//  2014.08.06 EZ CSAK AZÉRT VAN MEG, HOGY LEHESSEN A KÓDBÓL MÁSOLNI
// * @author Ács Ádám
// *
// */
//@org.springframework.stereotype.Component("profileBean")
//@Scope("view")
//class ProfileBean implements Serializable{
//	@Autowired
//	private UserService userService
//	@Autowired
//	UserBean userBean
//	
//	
//	@PostConstruct
//	void init() {
//		if (!userBean.user.address) {
//			userBean.user.address = new Address()
//		}
//		if (!userBean.user.profile) {
//			userBean.user.profile = new Profile()
//		}
//		if (!userBean.user.certification) {
//			userBean.user.certification = new Certification()
//		}
//	}
//
//	void save() {
//		if (!StringUtils.equalsIgnoreCase(userBean.user.profile.email, ServiceLocator.getBean(UserBean.class).user.profile.email)) {
//			emailChanged()
//		}
//
//		if (!StringUtils.equalsIgnoreCase(userBean.user.profile.phoneNumber, ServiceLocator.getBean(UserBean.class).user.profile.phoneNumber)) {
//			phoneChanged()
//		}
//
//		userBean.user = userService.save(userBean.user)
//	}
//
//	/**
//	 * Az e-mail cím alapértelmezésben a regisztráció során használt e-mail cím. 
//	 * Amennyiben módosításra kerül, akkor küldünk ki egy hitelesítő mail-t a megváltozott e-mail címre, 
//	 * ahol egy linkre klikkelve tudja megerősíteni a módosult e-mail címet. 
//	 * Amíg ez nincs meg, addig nem változtatjuk meg a címet. 
//	 * Ha visszaigazolta, onnantól él csak az új e-mail cím.
//	 */
//	private void emailChanged() {
//		emailChangeEmailSender.send() //TODO
//	}
//
//	/**
//	 * 	A telefonszám hitelesítését is biztosítani kell, egy sms kiküldésével.
//	 * Folyamata: a megadott telefonszám mellett elhelyezünk egy “Telefonszám hitelesítése” mezőt. 
//	 * Ezt megnyomva kiküldünk egy 4 karakteres (kis és nagybetűkből álló) kódot a megadott számra, 
//	 * amit a felületen a telefonszám mellett (akár a gomb helyén megjelenő) adatbeviteli mezőbe beírva és leokézva a telefonszám hitelesítésre kerül. 
//	 * Ezt egy zöld pipával jelöljük a szám mellett a továbbiakban. 
//	 * Telefonszám módosításakor a hitelesítettség megszűnik, az új számot a fenti folyamat szerint hitelesíteni kell. 
//	 */
//	private void phoneChanged() {
//		/* TODO */
//	}
//	
//	void idCardPictureUpload(FileUploadEvent event) {
//		FileUtils.writeByteArrayToFile(new File("${Config.userProfilePath}/${userBean.user.userName}/idcard"), event.file.contents)
//	}
//	
//	void addressCardPictureUpload(FileUploadEvent event) {
//		FileUtils.writeByteArrayToFile(new File("${Config.userProfilePath}/${userBean.user.userName}/addressCard"), event.file.contents)
//	} 
//	
//	void profilePictureUpload(FileUploadEvent event) {
//		String path = "${Config.userProfilePath}/${userBean.user.userName}/profile";
//		FileUtils.writeByteArrayToFile(new File(path), event.file.contents);
//		userBean.user.profile.profileImagePath = "${Config.userProfileContext}/${userBean.user.userName}/profile"
//		userBean.user = userService.save(userBean.user)
//	}
//	
//}
