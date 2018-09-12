package org.hacking.nessieproj.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.gson.annotations.SerializedName
import org.hacking.nessieproj.BR

class ObservableAccount : BaseObservable() {

    @SerializedName("type")
    var type: String = "Credit Card"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.type)
        }

    @SerializedName("nickname")
    var nickname: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.nickname)
        }

    @SerializedName("rewards")
    var rewards: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.rewards)
        }

    @SerializedName("balance")
    var balance: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.balance)
        }

    @SerializedName("account_number")
    var accountNumber: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.accountNumber)
        }
}