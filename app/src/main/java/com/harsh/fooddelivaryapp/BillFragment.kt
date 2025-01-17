package com.harsh.fooddelivaryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.harsh.fooddelivaryapp.databinding.FragmentBillBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentBillBinding? = null
    var mainActivity: MainActivity? = null

    private lateinit var arrayAdapter: ArrayAdapter<StudentAdapterDataClass>
    var selectedItem = StudentAdapterDataClass()
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
        binding = FragmentBillBinding.inflate(layoutInflater)
        return binding?.root
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            mainActivity?.studentarray ?: arrayListOf()
        )
        binding?.item1?.adapter = arrayAdapter
        binding?.item1?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                     selectedItem = binding?.item1?.selectedItem as StudentAdapterDataClass
                    binding?.etQnt?.setText(selectedItem.etQnty.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


        binding?.btnAdd?.setOnClickListener {
            if ((binding?.etQnt?.text?.toString()?.trim()?.toIntOrNull()
                    ?: 0) > selectedItem.etQnty.toString().toInt()
            ) {
                binding?.etQnt?.error = "First Choose the Item "
            } else {
                var number = binding?.etQnt?.text.toString().toInt()
                number += 1
                binding?.etQnt?.setText(number.toString())
            }
        }
        binding?.btnSub?.setOnClickListener {
            if ((binding?.etQnt?.text?.toString()?.trim()?.toIntOrNull() ?: 0) <= 1) {
                binding?.etQnt?.error = "First Choose the Item "
            } else {
                var number = binding?.etQnt?.text.toString().toInt()
                number -= 1
                binding?.etQnt?.setText(number.toString())
            }
        }
        binding?.btnOrder?.setOnClickListener {
            if (binding?.etQnt?.text?.toString().isNullOrEmpty()) {
                Toast.makeText(requireContext(), "enter quantity", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "order placed sucsessfuly", Toast.LENGTH_LONG)
                    .show()
                binding?.etQnt?.setText("")
              //  binding?.item1?.setSelection(0)
               // binding?.item1?.adapter?.isEmpty
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
         * @return A new instance of fragment BillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BillFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}