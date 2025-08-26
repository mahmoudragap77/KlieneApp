package com.example.klieneapp.dialouge

import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.klieneapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun Fragment.setUpBottomSheetDialog(
    onSendClick: (String) -> Unit
){
    val dialog = BottomSheetDialog(requireContext())
    val    view = layoutInflater.inflate(R.layout.bottom_sheet_dialouge, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edEmail = view.findViewById<EditText>(R.id.ed_email)
    val btnCancel = view.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_cancel)
    val btnSend = view.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_send)

    btnSend.setOnClickListener {
        val email = edEmail.text.toString().trim()
        if(email.isNotEmpty()){
            onSendClick(email)
        }
        dialog.dismiss()
    }
    btnCancel.setOnClickListener {
        dialog.dismiss()

    }

}