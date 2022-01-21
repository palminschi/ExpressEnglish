package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.ItemExerciseN2Binding
import com.palmdev.expressenglish.models.ExerciseN2


class TestsAdapter: RecyclerView.Adapter<TestsAdapter.TestsHolder>() {

    private val exercisesList = ArrayList<ExerciseN2>()

    class TestsHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = ItemExerciseN2Binding.bind(item)

        fun bind(exercise: ExerciseN2) {
            val context = binding.root.context
            binding.title.text = exercise.title
            binding.textChoice1.text = exercise.choice1
            binding.textChoice2.text = exercise.choice2
            binding.textChoice3.text = exercise.choice3
            if (exercise.choice3.isEmpty()){
                binding.choice3.visibility = View.GONE
                binding.tvD.text = "C"
            }

            fun choice(view: View){
                binding.apply {
                    choice1.isClickable = false
                    choice2.isClickable = false
                    choice3.isClickable = false
                    choice4.isClickable = false
                }
                view.setBackgroundColor(context.getColor(R.color.blue_light))

            }
            binding.choice1.setOnClickListener{
                onAnswerSelected.onClick(it)
                choice(it)
                if (exercise.correctAnswer == 1) {
                    correctAnswers++
                } else{
                    incorrectAnswersArray.add(exercise.topic)
                }
            }
            binding.choice2.setOnClickListener{
                onAnswerSelected.onClick(it)
                choice(it)
                if (exercise.correctAnswer == 2) {
                    correctAnswers++
                }else{
                    incorrectAnswersArray.add(exercise.topic)
                }
            }
            binding.choice3.setOnClickListener{
                onAnswerSelected.onClick(it)
                choice(it)
                if (exercise.correctAnswer == 3) {
                    correctAnswers++
                }else{
                    incorrectAnswersArray.add(exercise.topic)
                }
            }
            binding.choice4.setOnClickListener{
                onAnswerSelected.onClick(it)
                choice(it)
                incorrectAnswersArray.add(exercise.topic)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_n2, parent, false)
        return TestsHolder(view)
    }

    override fun onBindViewHolder(holder: TestsHolder, position: Int) {
        holder.bind(exercisesList[position])
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setExercises(data: ArrayList<ExerciseN2>){
        exercisesList.addAll(data)
        notifyDataSetChanged()
    }

    fun getCorrectAnswers(): Int{
        return correctAnswers
    }

    fun resetCorrectAnswers(){
        correctAnswers = 0
    }

    fun getIncorrectAnswersArray(): ArrayList<String>{
        return incorrectAnswersArray
    }

    fun resetIncorrectAnswersArray(){
        incorrectAnswersArray.clear()
    }



    fun setOnAnswerSelectedListener(listener: View.OnClickListener){
        onAnswerSelected = listener
    }

    companion object{
        private lateinit var onAnswerSelected: View.OnClickListener

        var correctAnswers = 0
        // Topics that were answered incorrectly
        val incorrectAnswersArray = ArrayList<String>()
    }
}