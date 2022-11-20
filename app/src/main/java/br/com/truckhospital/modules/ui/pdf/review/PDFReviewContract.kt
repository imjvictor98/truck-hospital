package br.com.truckhospital.modules.ui.pdf.review

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

interface PDFReviewContract {
    interface View: BaseActivityContract.BaseView {

    }

    interface Presenter: BaseActivityContract.BasePresenter<View> {

    }
}