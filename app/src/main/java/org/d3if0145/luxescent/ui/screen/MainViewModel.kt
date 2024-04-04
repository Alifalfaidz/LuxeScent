package org.d3if0145.luxescent.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0145.luxescent.model.parfum

class MainViewModel : ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<parfum>{
        val data = arrayListOf<parfum>(
            parfum(

                "CHRISTIAN DIOR",
                "Sauvage Man",
                2_550_000
            ),
            parfum(

                "PACO RABANNE",
                "One Million Man",
                1_225_000
            ),
            parfum(

                "BVLGARI",
                "Aqva man",
                1_285_000
            ),
            parfum(

                "JAGUAR",
                "Classic Black Man",
                750_000
            )
        )
        return data
    }
}