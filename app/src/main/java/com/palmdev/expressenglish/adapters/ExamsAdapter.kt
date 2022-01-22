package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Tests
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.ItemExamContainerBinding
import com.palmdev.expressenglish.models.ExamContainer

class ExamsAdapter: RecyclerView.Adapter<ExamsAdapter.ExamsHolder>() {

    private val examContainers = ArrayList<ExamContainer>()

    class ExamsHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = ItemExamContainerBinding.bind(item)

        fun bind(examContainer: ExamContainer){
            val context = binding.root.context
            binding.title.text = examContainer.title
            val subTitleText =
                "${examContainer.numberOfQuestions} ${context.getString(R.string.questions)}"
            binding.subTitle.text = subTitleText
            binding.tvLevel.text = examContainer.level

            if (examContainer.passed) binding.imgPassed.visibility = View.VISIBLE
            else binding.imgPassed.visibility = View.GONE

            if (examContainer.premium) binding.imgPremium.visibility = View.VISIBLE
            else binding.imgPremium.visibility = View.GONE

            binding.btnExam.setOnClickListener {
                if (examContainer.premium) {
                    val premiumUser = User.getPremiumStatus(context)

                    if (premiumUser) {
                        MainActivity.navController.navigate(
                            R.id.testsFragment,
                            bundleOf(
                                Tests.EXAM_OR_QUICK_TEST to Tests.EXAM,
                                Tests.EXAM to examContainer.id,
                                Tests.LEVEL to examContainer.level
                            )
                        )
                    } else {
                        val dialog = Dialogs.dialogRestrictedContent(context)
                        dialog.show()
                        // TODO - if is Premium
                    }
                }else {
                    MainActivity.navController.navigate(
                        R.id.testsFragment,
                        bundleOf(
                            Tests.EXAM_OR_QUICK_TEST to Tests.EXAM,
                            Tests.EXAM to examContainer.id,
                            Tests.LEVEL to examContainer.level
                        )
                    )
                }
            }

            when (examContainer.level) {
                context.getString(R.string.A1Lvl) -> {
                    binding.tvLevel.setBackgroundColor(context.getColor(R.color.green_light))
                    binding.tvLevel.setTextColor(context.getColor(R.color.green))
                }
                context.getString(R.string.A2Lvl) -> {
                    binding.tvLevel.setBackgroundColor(context.getColor(R.color.blue_light))
                    binding.tvLevel.setTextColor(context.getColor(R.color.blue_main))
                }
                context.getString(R.string.B1Lvl) -> {
                    binding.tvLevel.setBackgroundColor(context.getColor(R.color.brown_light))
                    binding.tvLevel.setTextColor(context.getColor(R.color.brown))
                }
                context.getString(R.string.B2Lvl) -> {
                    binding.tvLevel.setBackgroundColor(context.getColor(R.color.violet_light))
                    binding.tvLevel.setTextColor(context.getColor(R.color.violet))
                }
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setExams(array: ArrayList<ExamContainer>){
        if (examContainers.isEmpty()) {
            examContainers.addAll(array)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamsHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_exam_container, parent, false)
        return ExamsHolder(view)
    }

    override fun onBindViewHolder(holder: ExamsHolder, position: Int) {
        holder.bind(examContainers[position])
    }

    override fun getItemCount(): Int {
        return examContainers.size
    }
}