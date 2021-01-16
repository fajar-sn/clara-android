package tech.fulbek_dev.clara_app.view.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import tech.fulbek_dev.clara_app.R

class AboutUsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)
        view.findViewById<TextView>(R.id.all_mentor_name).text = "Umi Saadah \n Desy Intan Permatasari \nAndhik Ampuh Yunanto \nMaulidan Bagus Afridian Rasyid\n" +
                "        Willy Achmat Fauzi \nVerent Flourencia Irene \nMayshella Ainun Wakhidah \n Andika Ahmad Ramadhan \n Fandi Ahmad\n" +
                "        Ardian Kristya Pratama \n Angga Pradipta Kurnia Putra \n Muhammad Alif Pradipta ADP \n Rafly Arief Kanza\n" +
                "        Ahmad Jarir At Thobari \n Ajie Dibyo Respati \n Achmad Zulkarnain \n Arie Affianto \n Tegar Imansyah"
        return view
    }

}