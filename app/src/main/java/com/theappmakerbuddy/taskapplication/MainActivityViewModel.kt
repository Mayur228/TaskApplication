package com.theappmakerbuddy.taskapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: MainActivityRepository): ViewModel() {
    val result = MutableLiveData<ResultVO>()
    val showToast  = MutableLiveData<String>()

    fun calculate(num: String, num2: String, num3: String) {

        val isValid = num.isNotEmpty() && num2.isNotEmpty() && num3.isNotEmpty()

        if(isValid) {
            val numList = repository.parseNumbers(num)
            val numList2 = repository.parseNumbers(num2)
            val numList3 = repository.parseNumbers(num3)

            val numLists = listOf(numList, numList2, numList3)

            val intersectionResult = repository.getIntersection(numLists)
            val unionResult = repository.getUnion(numLists)
            val maxNumberResult = repository.getMaxNumber(unionResult)

            result.value = ResultVO(
                intersectResult = intersectionResult.joinToString(", "),
                unionResult = unionResult.joinToString(", "),
                maxNumberResult = maxNumberResult ?: 0
            )
        }else {
            showToast.value = "Please Enter number in each box"
        }

    }
}