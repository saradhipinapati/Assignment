package apps.saa.assignment.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import apps.saa.assignment.R
import apps.saa.networking.model.Uidata

class UIHelper(val context: Context) {

    fun getUIElement(uiData: Uidata): View {
        lateinit var view: View;
        when(uiData.uitype) {
            "label" -> {
                view = LayoutInflater.from(context).inflate(R.layout.ui_textview, null) as TextView
                view.text = uiData.value
            }

            "edittext" -> {
                view = LayoutInflater.from(context).inflate(R.layout.ui_edittext, null) as EditText
                view.hint = uiData.hint
            }

            "button" -> {
                view = LayoutInflater.from(context).inflate(R.layout.ui_button, null) as Button
                view.text = uiData.value
            }
        }

        return view;
    }
}