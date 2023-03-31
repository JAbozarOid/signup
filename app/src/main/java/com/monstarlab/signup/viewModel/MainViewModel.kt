package com.monstarlab.signup.viewModel

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.monstarlab.signup.R
import com.monstarlab.signup.view.widget.simplelistwidget.SimpleListWidgetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    val settingItems = MutableLiveData<List<SimpleListWidgetModel>>()

    fun showSetting() {
        if (settingItems.value != null) {
            if (settingItems.value!!.isNotEmpty()) settingItems.postValue(settingItems.value)
            return
        }
        settingItems.postValue(generateSettingData())
    }

    private fun generateSettingData(): List<SimpleListWidgetModel> {
        val list = mutableListOf<SimpleListWidgetModel>()
        list.add(
            SimpleListWidgetModel(
                translator.getString(R.string.profile),
                translator.getDrawableByName("ic_person_outline") as Drawable,
                onClick = { }
            )
        )
        list.add(
            SimpleListWidgetModel(
                translator.getString(R.string.sign_in_report),
                translator.getDrawableByName("ic_sign_in_report") as Drawable,
                onClick = { }
            )
        )
        list.add(
            SimpleListWidgetModel(
                translator.getString(R.string.setting),
                translator.getDrawableByName("ic_setting_outline") as Drawable
            )
        )
        list.add(
            SimpleListWidgetModel(
                translator.getString(R.string.support),
                translator.getDrawableByName("ic_support_outline") as Drawable,
                onClick = { }
            )
        )
        list.add(
            SimpleListWidgetModel(
                translator.getString(R.string.logout),
                translator.getDrawableByName("ic_logout") as Drawable,
                onClick = { }
            )
        )
        return list
    }

}
