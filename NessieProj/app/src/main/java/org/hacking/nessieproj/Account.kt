package org.hacking.nessieproj

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.google.gson.annotations.SerializedName
import org.hacking.nessieproj.R.id.*
import java.util.*

class Account : Observable {

    @SerializedName("type")
    var type: String = "Credit Card"
    @Bindable get
    set(value) {
        field = value
        PropertyChangeRegistry().notifyChange(this, BR.type)
    }

    @SerializedName("nickname")
    var nickname: String = ""
        @Bindable get
        set(value) {
            field = value
            PropertyChangeRegistry().notifyChange(this, BR.nickname)
        }

    @SerializedName("rewards")
    var rewards: Int = 0
        @Bindable get
        set(value) {
            field = value
            PropertyChangeRegistry().notifyChange(this, BR.rewards)
        }

    @SerializedName("balance")
    var balance: Int = 0
        @Bindable get
        set(value) {
            field = value
            PropertyChangeRegistry().notifyChange(this, BR.balance)
        }

    @SerializedName("account_number")
    var accountNumber: String = ""
        @Bindable get
        set(value) {
            field = value
            PropertyChangeRegistry().notifyChange(this, BR.accountNumber)
        }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        PropertyChangeRegistry().remove(callback)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        PropertyChangeRegistry().add(callback)
    }

}