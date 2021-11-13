package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Lessons
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.ItemLessonBinding
import com.palmdev.expressenglish.fragments.SelectLessonFragment
import com.palmdev.expressenglish.models.Lesson

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonsHolder>() {

    private val lessonsList = ArrayList<Lesson>()

    class LessonsHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ItemLessonBinding.bind(item)

        fun bind(lesson: Lesson) {
            binding.titleOfLesson.text = lesson.title
            // Remove prefix from lesson number (1.01 -> 1)
            var lessonNumber = lesson.number.drop(2)
            if (lessonNumber.first() == '0'){
                lessonNumber = lesson.number.drop(3)
            }
            binding.lessonNumber.text = lessonNumber

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

            binding.root.setOnClickListener {
                // if the Lesson has already been learned
                if (lesson.status != Lessons.STATUS_LEARNED) {
                    // the Lesson status - read
                    SharedPref.put(lesson.number, Lessons.STATUS_READ)
                }
                // open needed lesson
                MainActivity.navController.navigate(
                    R.id.lessonFragment,
                    bundleOf(SelectLessonFragment.SELECTED_LESSON to lesson.number)
                )
            }

            if (lesson.forSearch){
                binding.lessonLevel.text = lesson.level
                binding.lessonLevel.visibility = View.VISIBLE
                binding.imgNotRead.visibility = View.GONE
                binding.imgLearned.visibility = View.GONE
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonsHolder(view)
    }

    override fun onBindViewHolder(holder: LessonsHolder, position: Int) {
        holder.bind(lessonsList[position])
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLessons(lessons: ArrayList<Lesson>) {
        lessonsList.clear()
        lessonsList.addAll(lessons)
        notifyDataSetChanged()
    }
}