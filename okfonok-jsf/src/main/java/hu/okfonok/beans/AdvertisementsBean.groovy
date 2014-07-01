package hu.okfonok.beans;

import hu.okfonok.entities.Advertisement
import hu.okfonok.entities.JobCategory
import hu.okfonok.services.AdvertisementService
import hu.okfonok.services.JobCategoryService
import hu.okfonok.utils.ServiceLocator

import javax.faces.model.SelectItem
import javax.faces.model.SelectItemGroup

import org.primefaces.context.RequestContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope

@org.springframework.stereotype.Component("adsBean")
@Scope("session")
class AdvertisementsBean implements Serializable {
	@Autowired
	private AdvertisementService service
	@Autowired
	private JobCategoryService jcService

	private List<Advertisement> ads
	List<Advertisement> filteredAds
	Advertisement selected
	Advertisement adToView

	List<Advertisement> getAds() {
		if (!ads) {
			ads = service.findAll()
					//FIXME testadat
			ads << new Advertisement(description: 'teszt leírás', jobTime: new Date(), expiration :new Date())
			ads << new Advertisement(description: 'teszt leírás 2', jobTime: new Date(), expiration :new Date())
			//FIXME vége
		}

		return ads
	}

	void viewAd(Advertisement ad) {
		adToView = ad
		Map options = [
			modal: true
			,draggable: false
			,resizable: false
			,contentHeight: 470
			,contentWidth: 620
			,width: 650
			,height: 500
		]
		RequestContext.getCurrentInstance().openDialog("fragments/index/viewAdDialog", options, null);
	}
	
	
	List<SelectItem> getCategories() {
		JobCategoryService service = ServiceLocator.getBean(JobCategoryService)
		List<SelectItem> categories = []
		service.findAllMain().each { JobCategory maincat ->
			SelectItemGroup g = new SelectItemGroup(maincat.name);
			List<SelectItem> items = []
			maincat.subCategories?.each { JobCategory subcat ->
				items << new SelectItem(subcat, subcat.name)
			}
			g.setSelectItems(items as SelectItem[])
			categories.add(g)
		}

		return categories
	}
}
