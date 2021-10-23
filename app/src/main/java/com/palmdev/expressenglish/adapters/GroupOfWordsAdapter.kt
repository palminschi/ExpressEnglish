package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.ItemWordsGroupBinding
import com.palmdev.expressenglish.fragments.GroupOfWordsFragment
import com.palmdev.expressenglish.models.GroupOfWords

class GroupOfWordsAdapter: RecyclerView.Adapter<GroupOfWordsAdapter.GroupOfWordsHolder>() {

    private val groupOfWordsList = ArrayList<GroupOfWords>()

    class GroupOfWordsHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = ItemWordsGroupBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(groupOfWords: GroupOfWords) = with(binding){
            groupTitle.text =
                itemView.context.getString(R.string.groupOfWords) + " " + groupOfWords.groupID
            numberOfWordsInGroup.text = groupOfWords.numberOfWordsInGroup.toString()
            root.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_wordsFragment_to_groupOfWordsFragment,
                    bundleOf(
                        GroupOfWordsFragment.FIRST_INDEX to groupOfWords.firstWordIndex,
                        GroupOfWordsFragment.LAST_INDEX to groupOfWords.lastWordIndex
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupOfWordsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_words_group, parent, false)
        return GroupOfWordsHolder(view)
    }

    override fun onBindViewHolder(holder: GroupOfWordsHolder, position: Int) {
        holder.bind(groupOfWordsList[position])
    }

    override fun getItemCount(): Int {
        return groupOfWordsList.size
    }

    fun addGroup(group: GroupOfWords){
        groupOfWordsList.add(group)
        notifyDataSetChanged()
    }
}