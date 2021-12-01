package anticordev.group.anticoruption.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.model.send_models.Area

/**
 * @author Zokirjon
 * @date 11/16/2021
 */
class CustomDropDown(context: Context, resource: Int, var items: List<Area>) :
    ArrayAdapter<Area>(context, resource, items) {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.simple_spinner_drop_down, parent, false)
        }
        (view?.findViewById(R.id.text1) as TextView).text = getItem(position)!!.name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.simple_spinner_drop_down, parent, false)
        }
        (view?.findViewById(android.R.id.text1) as TextView).text = getItem(position)!!.name
        return view
    }
}