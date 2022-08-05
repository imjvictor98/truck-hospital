package br.com.truckhospital.modules.ui.order.description

import br.com.truckhospital.modules.core.model.Complaint

class DescriptionPresenter(override val view: DescriptionContract.View?) : DescriptionContract.Presenter {
    override fun getDescription(text: String) = Complaint(text)
}