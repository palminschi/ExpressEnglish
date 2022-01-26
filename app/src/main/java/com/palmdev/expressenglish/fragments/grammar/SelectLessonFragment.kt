package com.palmdev.expressenglish.fragments.grammar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.LessonsAdapter
import com.palmdev.expressenglish.data.Lessons
import com.palmdev.expressenglish.databinding.FragmentSelectLessonBinding


class SelectLessonFragment : Fragment(R.layout.fragment_select_lesson) {
    private lateinit var binding: FragmentSelectLessonBinding
    private lateinit var mSelectedLevel: String
    private val mAdapter = LessonsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectLessonBinding.bind(view)

        binding.recView.layoutManager = LinearLayoutManager(view.context)
        binding.recView.setItemViewCacheSize(20)
        binding.recView.adapter = mAdapter

        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

    }

    override fun onResume() {
        super.onResume()

        mSelectedLevel = requireArguments().getString(GrammarFragment.SELECTED_LEVEL).toString()
        when(mSelectedLevel){
            // A1
            GrammarFragment.A1_SELECTED -> {
                // Set Title
                binding.title.text = getString(R.string.btnA1Title)
                // Init Recycler View
                val lessons = Lessons.getLessonsA1(requireContext())
                mAdapter.setLessons(lessons)
            }
            // A2
            GrammarFragment.A2_SELECTED -> {
                // Set Title
                binding.title.text = getString(R.string.btnA2Title)
                // Init Recycler View
                val lessons = Lessons.getLessonsA2(requireContext())
                mAdapter.setLessons(lessons)
            }
            // B1
            GrammarFragment.B1_SELECTED -> {
                // Set Title
                binding.title.text = getString(R.string.btnB1Title)
                // Init Recycler View
                val lessons = Lessons.getLessonsB1(requireContext())
                mAdapter.setLessons(lessons)
            }
        }

        // Firebase Event
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,
            SelectLessonFragment().javaClass.simpleName + mSelectedLevel)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,
            SelectLessonFragment().javaClass.simpleName + mSelectedLevel)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    companion object {
        const val SELECTED_LESSON = "SELECTED_LESSON"
    }
}