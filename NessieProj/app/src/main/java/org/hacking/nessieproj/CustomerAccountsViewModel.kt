package org.hacking.nessieproj

import android.databinding.BaseObservable

data class CustomerAccountsViewModel(
        var type: String,
        var nickname: String,
        var rewards: Int,
        var balance: Int,
        var accountNumber: String) : BaseObservable()