package com.maha.superhero.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.maha.superhero.R
import com.maha.superhero.data.model.SuperHeroResult
import com.maha.superhero.helper.loadFromUrlGlide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.inflate_apperance_layout.*
import kotlinx.android.synthetic.main.inflate_biography_layout.*
import kotlinx.android.synthetic.main.inflate_connection_layout.*
import kotlinx.android.synthetic.main.inflate_power_layout.*
import kotlinx.android.synthetic.main.inflate_work_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    lateinit var chart: PieChart

    var mSeperHeroResult: SuperHeroResult? = null

    var mBioBool = false
    var mAppearBool = false
    var mPowerBool = false
    var mConnectionBool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)

        getBundleValue()

        mSeperHeroResult?.apply { setSuperHeroDetail(this) }


    }

    private fun getBundleValue() {
        if (intent.extras != null) {
            mSeperHeroResult = intent.extras!!.getSerializable("SuperHero") as SuperHeroResult
        }
    }

    private fun setSuperHeroDetail(aSuperHeroResult: SuperHeroResult) {

        txt_super_hero_name.text = aSuperHeroResult.name
        loadFromUrlGlide(aSuperHeroResult.image?.url ?: "", img_superhero, this)


        setBioGraphyDetail(aSuperHeroResult)
        setAppearanceDetail(aSuperHeroResult)
        setPowerDetail(aSuperHeroResult)
        setConnectionDetail(aSuperHeroResult)
        setWorkDetail(aSuperHeroResult)
    }


    private fun setBioGraphyDetail(aSuperHeroResult: SuperHeroResult) {
        try {
            bio_cardview.setOnClickListener {

                if (!mBioBool) {
                    mBioBool = true
                    bio_detail_layout.visibility = View.VISIBLE
                } else {
                    mBioBool = false
                    bio_detail_layout.visibility = View.GONE
                }
            }

            fullname_txt.text = aSuperHeroResult.biography!!.fullname ?: ""
            alter_txt.text = aSuperHeroResult.biography!!.alteregos ?: ""
            place_txt.text = aSuperHeroResult.biography!!.placeofbirth ?: ""
            first_app_txt.text = aSuperHeroResult.biography!!.firstappearance ?: ""
            publish_txt.text = aSuperHeroResult.biography!!.publisher ?: ""
            allign_txt.text = aSuperHeroResult.biography!!.alignment ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setAppearanceDetail(aSuperHeroResult: SuperHeroResult) {
        try {
            appear_cardview.setOnClickListener {

                if (!mAppearBool) {
                    mAppearBool = true
                    appearance_detail_layout.visibility = View.VISIBLE
                } else {
                    mAppearBool = false
                    appearance_detail_layout.visibility = View.GONE
                }
            }

            gender_txt.text = aSuperHeroResult.appearance!!.gender ?: ""
            race_txt.text = aSuperHeroResult.appearance!!.race ?: ""
            height_txt.text = aSuperHeroResult.appearance!!.height[0]
            weight_txt.text = aSuperHeroResult.appearance!!.weight[0]
            eye_color_txt.text = aSuperHeroResult.appearance!!.eyecolor ?: ""
            hair_txt.text = aSuperHeroResult.appearance!!.haircolor ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setPowerDetail(aSuperHeroResult: SuperHeroResult) {
        try {
            power_cardview.setOnClickListener {

                if (!mPowerBool) {
                    mPowerBool = true
                    power_detail_layout.visibility = View.VISIBLE
                } else {
                    mPowerBool = false
                    power_detail_layout.visibility = View.GONE
                }
            }

            if (aSuperHeroResult.powerstats != null) {
                val aIntelligence = aSuperHeroResult.powerstats?.intelligence ?: "0"
                val aStrength = aSuperHeroResult.powerstats?.strength ?: "0"
                val aSpeed = aSuperHeroResult.powerstats?.speed ?: "0"
                val aDurability = aSuperHeroResult.powerstats?.durability ?: "0"
                val aPower = aSuperHeroResult.powerstats?.power ?: "0"
                val aCombat = aSuperHeroResult.powerstats?.combat ?: "0"

                setChartData(aIntelligence, power_range_title, "1")
                setChartData(aStrength, power_2_range_title, "2")
                setChartData(aSpeed, power_3_range_title, "3")
                setChartData(aDurability, power_4_range_title, "4")
                setChartData(aPower, power_5_range_title, "5")
                setChartData(aCombat, power_6_range_title, "6")
            }
            // setData2(0f, 100f)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setConnectionDetail(aSuperHeroResult: SuperHeroResult) {

        try {
            connection_cardview.setOnClickListener {

                if (!mConnectionBool) {
                    mConnectionBool = true
                    connection_detail_layout.visibility = View.VISIBLE
                } else {
                    mConnectionBool = false
                    connection_detail_layout.visibility = View.GONE
                }
            }

            group_txt.text = aSuperHeroResult.connections!!.groupaff ?: ""
            relative_txt.text = aSuperHeroResult.connections!!.relatives ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setWorkDetail(aSuperHeroResult: SuperHeroResult) {
        try {
            work_cardview.setOnClickListener {

                if (!mConnectionBool) {
                    mConnectionBool = true
                    work_detail_layout.visibility = View.VISIBLE
                } else {
                    mConnectionBool = false
                    work_detail_layout.visibility = View.GONE
                }
            }

            occupation_txt.text = aSuperHeroResult.work!!.occupation ?: ""
            base_txt.text = aSuperHeroResult.work!!.base ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun setChartData(
        aIntelligence: String,
        aTextView: AppCompatTextView,
        aType: String
    ) {
        try {
            var aFirst = 0f
            var aSecond = 0f
            if (aIntelligence.isNotEmpty() && aIntelligence != "null") {
                aFirst = aIntelligence.toFloat()
                aSecond = 100f - aFirst
                aTextView.text = aIntelligence
            } else {
                aFirst = 0f
                aSecond = 100f
                aTextView.text = "0"
            }

            when (aType) {
                "1" -> setData(aFirst, aSecond, chart1, aType)
                "2" -> setData(aFirst, aSecond, chart2, aType)
                "3" -> setData(aFirst, aSecond, chart3, aType)
                "4" -> setData(aFirst, aSecond, chart4, aType)
                "5" -> setData(aFirst, aSecond, chart5, aType)
                "6" -> setData(aFirst, aSecond, chart6, aType)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setData(
        fl: Float,
        fl1: Float,
        chart1: PieChart,
        aType: String
    ) {
        try {


            chart1.setUsePercentValues(true)
            chart1.description.isEnabled = false
            chart1.setExtraOffsets(5f, 10f, 5f, 5f)

            chart1.dragDecelerationFrictionCoef = 0.95f

            val entries = ArrayList<PieEntry>()

            entries.add(PieEntry(fl, ""))
            entries.add(PieEntry(fl1, ""))

            val dataSet = PieDataSet(entries, "")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f


            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter(chart1))
            data.setValueTextSize(11f)

            // add a lot of colors
            val colors = ArrayList<Int>()

            when (aType) {
                "1" -> {
                    colors.add(ContextCompat.getColor(this, R.color.black))
                    colors.add(ColorTemplate.getHoloBlue())
                    data.setValueTextColor(Color.WHITE)
                }
                "2" -> {
                    colors.add(ContextCompat.getColor(this, R.color.purple_500))
                    colors.add(ContextCompat.getColor(this, R.color.teal_200))
                    data.setValueTextColor(Color.WHITE)
                }
                "3" -> {
                    colors.add(ContextCompat.getColor(this, R.color.green))
                    colors.add(ContextCompat.getColor(this, R.color.light_green))
                    data.setValueTextColor(Color.WHITE)
                }
                "4" -> {
                    colors.add(ContextCompat.getColor(this, R.color.violet))
                    colors.add(ContextCompat.getColor(this, R.color.light_violet))
                    data.setValueTextColor(Color.WHITE)
                }
                "5" -> {
                    colors.add(ContextCompat.getColor(this, R.color.red))
                    colors.add(ContextCompat.getColor(this, R.color.light_red))
                    data.setValueTextColor(Color.WHITE)
                }
                "6" -> {
                    colors.add(ContextCompat.getColor(this, R.color.blue))
                    colors.add(ContextCompat.getColor(this, R.color.light_blue))
                    data.setValueTextColor(Color.WHITE)
                }
            }
            dataSet.colors = colors
            chart1.data = data

            // undo all highlights
            chart1.highlightValues(null)
            chart1.invalidate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}