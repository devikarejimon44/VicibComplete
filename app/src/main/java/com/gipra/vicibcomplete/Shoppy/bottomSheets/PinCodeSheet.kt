package com.gipra.vicibshoppy.bottomSheets

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gipra.vicibcomplete.R
import com.gipra.vicibshoppy.utlis.showToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.shoppy_picode_sheet.*
import kotlinx.android.synthetic.main.shoppy_picode_sheet.view.*

class PinCodeSheet : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.shoppy_picode_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pincodeValue = pinCode.text

        pinCode.addTextChangedListener(object  : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length < 6 || s.toString().length >6 || s.toString().isEmpty())
                    pinCode.setError("PinCode length must be 6")
            }

        })
        submitPincode.setOnClickListener {

            //(acc.isEmpty() || acc.length() <= 11 ||acc.length()>=18)
            //val content = it.text.toString()
            //  it.error = if (content.length >= 6) null else "message"
//            String pvalue=pinCode.getText.tostring();
//            if (pvalue.isEmpty() || pvalue.length()!=10){
//                activity!!.showToast("Error")
//            }
            var pvalue=pinCode.text.toString();
            if (pvalue.isEmpty()|| pvalue.length!=6){
                activity!!.showToast("Invalid Pincode")
            }
            else{
                savePincode(pincodeValue.toString())
            }

        }

    }
    private fun savePincode(pincodeValue: String?) {
        val sp =
            activity!!.getSharedPreferences("User", Context.MODE_PRIVATE).edit()
        sp.clear()
        sp.putString("pinCode", pincodeValue)
        sp.commit()

        activity!!.showToast("PinCode added successfully")
        dismiss()

//        if (!sp.equals(null)){
//
//
//
//      //    dismiss()
//        }
    }
}