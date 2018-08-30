import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.hacking.nessieproj.CustomerAccount
import org.hacking.nessieproj.R
import org.hacking.nessieproj.databinding.CustomerAccountBinding

@SuppressLint("IncorrectAdapter")
class CustomAdapter(private val context: Context, private val dataList: List<CustomerAccount>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(var binding: CustomerAccountBinding) : RecyclerView.ViewHolder(binding.root) {
//        var typeTextView: TextView = view.findViewById(R.id.type)
//        var nicknameTextView: TextView = view.findViewById(R.id.nickname)
//        var rewardsTextView: TextView = view.findViewById(R.id.rewards)
//        var balanceTextView: TextView = view.findViewById(R.id.balance)
//        var accountNumberTextView: TextView = view.findViewById(R.id.accountNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var binding : CustomerAccountBinding = DataBindingUtil.inflate(layoutInflater, R.layout.customer_account, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        holder.binding.type = dataList[position].type
//        holder.typeTextView.text  = dataList[position].type
//        holder.nicknameTextView.text  = dataList[position].nickname
//        holder.rewardsTextView.text  = dataList[position].rewards.toString()
//        holder.balanceTextView.text  = dataList[position].balance.toString()
//        holder.accountNumberTextView.text  = dataList[position].accountNumber


//        val builder = Picasso.Builder(context)
//        builder.downloader(OkHttp3Downloader(context))
//        builder.build().load(dataList[position].getThumbnailUrl())
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.coverImage)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}