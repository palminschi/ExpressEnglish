package com.palmdev.expressenglish.fragments.grammar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.ExamsAdapter
import com.palmdev.expressenglish.data.Tests
import com.palmdev.expressenglish.databinding.FragmentSelectExamBinding

class SelectExamFragment: Fragment(R.layout.fragment_select_exam) {

    private lateinit var binding: FragmentSelectExamBinding
    private val mAdapterA1 = ExamsAdapter()
    private val mAdapterA2 = ExamsAdapter()
    private val mAdapterB1 = ExamsAdapter()
    private val mAdapterB2 = ExamsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectExamBinding.bind(view)

        initRecyclerViews()

        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
    }


    private fun initRecyclerViews(){
        // RecView 1 - A1
        binding.recViewA1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recViewA1.adapter = mAdapterA1
        mAdapterA1.setExams(Tests.getExamContainers(requireContext(), getString(R.string.A1Lvl)))

        // RecView 2 - A2
        binding.recViewA2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recViewA2.adapter = mAdapterA2
        mAdapterA2.setExams(Tests.getExamContainers(requireContext(), getString(R.string.A2Lvl)))

        // RecView 3 - B1
        binding.recViewB1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recViewB1.adapter = mAdapterB1
        mAdapterB1.setExams(Tests.getExamContainers(requireContext(), getString(R.string.B1Lvl)))

        // RecView 4 - B2
        binding.recViewB2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recViewB2.adapter = mAdapterB2
        mAdapterB2.setExams(Tests.getExamContainers(requireContext(), getString(R.string.B2Lvl)))

    }
}