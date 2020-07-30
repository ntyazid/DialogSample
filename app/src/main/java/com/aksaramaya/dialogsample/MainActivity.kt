package com.aksaramaya.dialogsample

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_custom_view.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*add click listener in button*/
        btn_show_dialog.setOnClickListener {
            alertDialog()
        }
    }

    /*Standard alert dialog*/
    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Alert Dialog")
            setMessage("This is alert dialog sample")
            setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                /*you can put action in this block when choose yes option*/
            }
            setNegativeButton("No") { dialog: DialogInterface?, which: Int ->

            }
            show()
        }
    }

    /*Alert dialog with custom view*/
    private fun alertDialogCustomView() {
        val view = layoutInflater.inflate(R.layout.dialog_custom_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        btn_ok.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    /*Date picker dialog*/
    private fun datePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "$dayOfMonth-${month + 1}-$year", Toast.LENGTH_LONG).show()
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    /*Time picker dialog*/
    private fun timePickerDialog() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val tpd =
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                Toast.makeText(this, "$hourOfDay-$minute", Toast.LENGTH_LONG).show()
            }, hour, minute, true)
        tpd.show()
    }

    /*Bottom sheet dialog, you need to add
    * implementation 'com.google.android.material:material:1.1.0'
    * in your dependencies build gradle app*/
    private fun bottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    /*Bottom sheet dialog fragment.
    * you must create class from BottomSheetDialogFragment,
    * like this*/
    class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
        }
    }

    /*then to call this BottomSheetDialogFragment class*/
    private fun bottomSheetDialogFragment() {
        val bottomSheetFragment = CustomBottomSheetDialogFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}