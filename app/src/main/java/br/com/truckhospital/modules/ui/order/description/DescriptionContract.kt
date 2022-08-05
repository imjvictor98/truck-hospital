package br.com.truckhospital.modules.ui.order.description

import br.com.truckhospital.modules.core.model.Complaint
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

class DescriptionContract {
    interface View: BaseFragmentContract.BaseView {}

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
        fun getDescription(text: String): Complaint
    }
}