package org.hacking.nessieproj

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class CustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        savedInstanceState ?: run {
            val addAccountsFragment = CustomerAccountButtonsFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_layout, addAccountsFragment)
            transaction.commit()
        }
    }
}
