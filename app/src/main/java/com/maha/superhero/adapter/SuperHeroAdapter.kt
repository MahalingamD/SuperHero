package com.maha.superhero.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.maha.superhero.R
import com.maha.superhero.data.model.SuperHeroResult
import com.maha.superhero.helper.loadFromUrlGlide
import com.maha.superhero.ui.main.MainActivity

class SuperHeroAdapter(val mContext: Context, var mSuperHeroList: ArrayList<SuperHeroResult>) : RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val aNameText: TextView = itemView.findViewById(R.id.name_tv)
        private val aFullNameText: TextView = itemView.findViewById(R.id.fullname_tv)
        private val aGenderText: TextView = itemView.findViewById(R.id.gender_tv)
        private val aHeightText: TextView = itemView.findViewById(R.id.height_tv)
        private val aWeightText: TextView = itemView.findViewById(R.id.weight_tv)
        private val aSuperHeroImg: AppCompatImageView = itemView.findViewById(R.id.superhero_img)
        private val aRootLayout: ConstraintLayout = itemView.findViewById(R.id.root_layout)

        lateinit var aIntent: Intent

        @SuppressLint("SetTextI18n")
        fun bindItems(position: Int) {
            try {
                val aSuperHero = mSuperHeroList[position]
                aNameText.text = aSuperHero.name
                aFullNameText.text = aSuperHero.biography?.fullname ?: "-"
                aGenderText.text = "Gender - ${aSuperHero.appearance?.gender ?: "-"}"
                aHeightText.text = "Height - ${aSuperHero.appearance?.height!![0]}"
                aWeightText.text = "Weight - ${aSuperHero.appearance?.weight!![0]}"

                if (aSuperHero.image != null) {
                    loadFromUrlGlide(aSuperHero.image!!.url ?: "", aSuperHeroImg, mContext)
                }

                aRootLayout.setOnClickListener {

                    val aIntent = Intent(mContext, MainActivity::class.java)
                    aIntent.putExtra("SuperHero", aSuperHero)
                    mContext.startActivity(aIntent)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.super_hero_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mSuperHeroList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(position)
    }

    fun update(aSuperHeroList: ArrayList<SuperHeroResult>) {
        mSuperHeroList.clear()
        mSuperHeroList.addAll(aSuperHeroList)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        mSuperHeroList.clear()
        notifyDataSetChanged()
    }

}