package cn.edu.sicnu.fundiary.ui.DairyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.edu.sicnu.fundiary.Diary
import cn.edu.sicnu.fundiary.DiaryList

class DairyListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is DairyList Fragment"
    }
    val text: LiveData<String> = _text

    private val _diarylistModel = MutableLiveData<MutableList<Diary>>().apply {
        this.value= DiaryList
    }

    val connlistModel: LiveData<MutableList<Diary>> = _diarylistModel
}