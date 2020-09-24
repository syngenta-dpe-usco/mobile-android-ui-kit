package com.syngenta.uikit.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syngenta.uikit.MainActivity
import com.syngenta.uikit.R
import com.syngenta.uikit.adapters.StyledItemCallback
import com.syngenta.uikit.adapters.StyledListAdapter
import com.syngenta.uikit.adapters.StyledListItem
import kotlinx.android.synthetic.main.fragment_list_view.*

/**
 * A simple [Fragment] subclass.
 */
class ListViewFragment : Fragment(), StyledItemCallback {
    private var adapter: StyledListAdapter<ListItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setTitle(getString(R.string.list_view))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = StyledListAdapter(
            context!!,
            initialData(),
            recycler_view_generic,
            this
        )
    }

    private fun initialData(): Array<ListItem> {
        itemList.clear()
        counter = 0
        addItem()
        return itemList.toTypedArray()
    }

    override fun onClick(item: StyledListItem) {
        addItem()
        adapter?.setItems(itemList.toTypedArray())
    }

    private fun addItem() {
        itemList.add(
            ListItem(
                image = R.drawable.ic_launcher_foreground,
                main = "Main $counter",
                sub1 = "Sub 1",
                sub2 = "Sub 2"
            )
        )
        counter++
    }


    companion object {
        val itemList = mutableListOf<ListItem>()
        var counter: Int = 0
    }

}

class ListItem(
    val image: Int? = null,
    val main: String? = null,
    val sub1: String? = null,
    val sub2: String? = null
) : StyledListItem {
    override fun getImageUrl() = image
    override fun getMainText() = main
    override fun getSubText1() = sub1
    override fun getSubText2() = sub2
}