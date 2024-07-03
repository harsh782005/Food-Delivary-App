package com.harsh.fooddelivaryapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.harsh.fooddelivaryapp.databinding.CutomaddressBinding
import com.harsh.fooddelivaryapp.databinding.FragmentItemBinding
import com.harsh.fooddelivaryapp.databinding.UpdateItemCustomBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentItemBinding? = null
    var mainActivity: MainActivity? = null
    lateinit var adapterClass: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(layoutInflater)
        return binding?.root
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterClass = StudentAdapter(mainActivity?.studentarray ?: arrayListOf())
        binding?.listView?.adapter = adapterClass
        binding?.btnAddress?.setOnClickListener {
            val dialogBinding = CutomaddressBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                getWindow()?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            dialogBinding.btnclick.setOnClickListener {
                if (dialogBinding.etName.text.toString().isNullOrEmpty()) {
                    dialogBinding.etName.error = " enter your item"
                }
                if (dialogBinding.etCity.text.toString().isNullOrEmpty()) {
                    dialogBinding.etCity.error = " enter your quantity"
                } else {
                    mainActivity?.studentarray?.add(
                        StudentAdapterDataClass(
                            dialogBinding.etName.text.toString(),
                            dialogBinding.etCity.text.toString().toInt()
                        )
                    )
                    adapterClass.notifyDataSetChanged()
                    val bundle = Bundle()
                    bundle.putString("item", mainActivity?.studentarray?.toString())
                    dialog.dismiss()
                }
            }
        }
        binding?.listView?.setOnItemLongClickListener { adapterView, view, i, l ->
            var alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Do You Want To Delete")
            alertDialog.setPositiveButton("Yes") { _, _ ->
                mainActivity?.studentarray?.removeAt(i)
                adapterClass.notifyDataSetChanged()
            }
            alertDialog.setNegativeButton("No") { _, _ -> }
            alertDialog.show()
            return@setOnItemLongClickListener true
        }
        binding?.listView?.setOnItemClickListener { adapterview, view, i, l ->
            val dialogBinding = UpdateItemCustomBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                getWindow()?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            dialogBinding.update.setOnClickListener {
                if (dialogBinding.name.text.toString().trim().isNullOrEmpty()) {
                    dialogBinding.name.error = "enter your Item"
                } else if (dialogBinding.qnt.text.toString().trim().isNullOrEmpty()) {
                    dialogBinding.qnt.error = "enter your Quantity"
                } else {
                    mainActivity?.studentarray?.set(
                        i, StudentAdapterDataClass(
                            dialogBinding.name.text.toString(),
                            dialogBinding.qnt.text.toString().toInt()
                        )
                    )
                    adapterClass.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}