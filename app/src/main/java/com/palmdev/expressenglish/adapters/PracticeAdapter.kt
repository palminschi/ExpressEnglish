package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.ItemExerciseBinding
import com.palmdev.expressenglish.models.Exercise


class PracticeAdapter: RecyclerView.Adapter<PracticeAdapter.PracticeHolder>() {

    private val exercisesList = ArrayList<Exercise>()

    class PracticeHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = ItemExerciseBinding.bind(item)

        fun bind(exercise: Exercise) {
            val context = binding.root.context
            binding.title.text = exercise.title
            binding.subTitle.text = exercise.translation
            binding.textChoice1.text = exercise.choice1
            binding.textChoice2.text = exercise.choice2
            binding.textChoice3.text = exercise.choice3

            // if the exercise has only 2 choices
            if (exercise.choice3.isEmpty()){
                binding.choice3.visibility = View.GONE
            }

            // if the exercise hasn't translation
            if (exercise.translation.isEmpty()){
                binding.subTitle.visibility = View.GONE
            }
            binding.apply {
                choice1.isClickable = true
                choice2.isClickable = true
                choice3.isClickable = true
                tvA.visibility = View.VISIBLE
                tvB.visibility = View.VISIBLE
                tvC.visibility = View.VISIBLE
                imgTrue1.visibility = View.GONE
                imgTrue2.visibility = View.GONE
                imgTrue3.visibility = View.GONE
                imgFalse1.visibility = View.GONE
                imgFalse2.visibility = View.GONE
                imgFalse3.visibility = View.GONE
            }

            fun choice(view: View){
                view.setBackgroundColor(context.getColor(R.color.blue_light))
                binding.apply {
                    choice1.isClickable = false
                    choice2.isClickable = false
                    choice3.isClickable = false
                    tvA.visibility = View.GONE
                    tvB.visibility = View.GONE
                    tvC.visibility = View.GONE
                }

                when (exercise.correctAnswer) {
                    1 -> {
                        binding.apply {
                            imgTrue1.visibility = View.VISIBLE
                            imgFalse2.visibility = View.VISIBLE
                            imgFalse3.visibility = View.VISIBLE
                        }
                    }
                    2 -> {
                        binding.apply {
                            imgFalse1.visibility = View.VISIBLE
                            imgTrue2.visibility = View.VISIBLE
                            imgFalse3.visibility = View.VISIBLE
                        }
                    }
                    3 -> {
                        binding.apply {
                            imgFalse1.visibility = View.VISIBLE
                            imgFalse2.visibility = View.VISIBLE
                            imgTrue3.visibility = View.VISIBLE
                        }
                    }
                }
            }

            binding.choice1.setOnClickListener{
                choice(it)
                if (exercise.correctAnswer == 1) {
                    correctAnswers++
                }
            }
            binding.choice2.setOnClickListener{
                choice(it)
                if (exercise.correctAnswer == 2) {
                    correctAnswers++
                }
            }
            binding.choice3.setOnClickListener{
                choice(it)
                if (exercise.correctAnswer == 3) {
                    correctAnswers++
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return PracticeHolder(view)
    }

    override fun onBindViewHolder(holder: PracticeHolder, position: Int) {
        holder.bind(exercisesList[position])
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setExercises(data: ArrayList<Exercise>){
        exercisesList.addAll(data)
        notifyDataSetChanged()
    }

    fun getCorrectAnswers(): Int{
        return correctAnswers
    }

    fun resetCorrectAnswers(){
        correctAnswers = 0
    }

    companion object{
        var correctAnswers = 0
    }
}