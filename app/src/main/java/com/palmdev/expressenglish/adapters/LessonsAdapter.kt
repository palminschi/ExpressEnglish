package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.Ads
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Lessons
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.ItemLessonBinding
import com.palmdev.expressenglish.fragments.grammar.LessonFragment
import com.palmdev.expressenglish.fragments.grammar.SelectLessonFragment
import com.palmdev.expressenglish.models.Lesson
import com.palmdev.expressenglish.utils.Network

private const val AD_TYPE = 0
private const val CONTENT_TYPE = 1
private const val AD_CONDITION = 4

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonsHolder>() {

    private val lessonsList = ArrayList<Lesson>()
    private var isPremiumUser = true
    private var isNetworkActive = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsHolder {
        isPremiumUser = User.getPremiumStatus(parent.context)
        isNetworkActive = Network.isNetworkAvailable(parent.context)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonsHolder(view)
    }

    override fun onBindViewHolder(holder: LessonsHolder, position: Int) {
        if (isPremiumUser || !isNetworkActive) {
            holder.bind(lessonsList[position])
            return
        }

        if (getItemViewType(position) == AD_TYPE) {
            Ads.loadNativeAd(holder.itemView.context, holder.itemView.rootView as FrameLayout)
        } else {
            if (position > AD_CONDITION) {
                holder.bind(lessonsList[position - 1])
            } else {
                holder.bind(lessonsList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isPremiumUser || lessonsList.size > AD_CONDITION || !isNetworkActive) lessonsList.size
        else lessonsList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPremiumUser || !isNetworkActive) CONTENT_TYPE
        else {
            when (position) {
                AD_CONDITION -> AD_TYPE
                else -> CONTENT_TYPE
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLessons(lessons: ArrayList<Lesson>) {
        lessonsList.clear()
        lessonsList.addAll(lessons)
        notifyDataSetChanged()
    }

    class LessonsHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ItemLessonBinding.bind(item)

        fun bind(lesson: Lesson) {
            binding.titleOfLesson.text = lesson.title
            // Remove prefix from lesson number (1.01 -> 1)
            var lessonNumber = lesson.number.drop(2)
            if (lessonNumber.first() == '0') {
                lessonNumber = lesson.number.drop(3)
            }
            binding.lessonNumber.text = lessonNumber

            // Lesson Status (Learned, Read or not read)
            when (lesson.status) {
                Lessons.STATUS_NOT_READ -> {
                    // The Lesson has not been read
                    binding.imgLearned.visibility = View.GONE
                    binding.imgNotRead.visibility = View.VISIBLE
                }
                Lessons.STATUS_LEARNED -> {
                    // The Lesson has been read and learned
                    binding.imgLearned.visibility = View.VISIBLE
                    binding.imgNotRead.visibility = View.GONE
                }
                Lessons.STATUS_READ -> {
                    // The Lesson has been read
                    binding.imgLearned.visibility = View.GONE
                    binding.imgNotRead.visibility = View.GONE
                }
            }

            // Item Click
            binding.root.setOnClickListener {
                // if the Lesson has already been learned
                if (lesson.status != Lessons.STATUS_LEARNED) {
                    // the Lesson status - read
                    SharedPref.put(lesson.number, Lessons.STATUS_READ)
                }
                // open needed lesson
                MainActivity.navController.navigate(
                    R.id.lessonFragment,
                    bundleOf(
                        SelectLessonFragment.SELECTED_LESSON to lesson.number,
                        LessonFragment.WITH_PRACTICE to lesson.practice
                    )
                )
            }

            // Lesson with practice or not
            if (!lesson.practice) binding.tvPractice.visibility = View.GONE

            // For Searching
            if (lesson.forSearch) {
                binding.lessonLevel.text = lesson.level
                binding.lessonLevel.visibility = View.VISIBLE
                binding.imgNotRead.visibility = View.GONE
                binding.imgLearned.visibility = View.GONE
            }
        }
    }
}