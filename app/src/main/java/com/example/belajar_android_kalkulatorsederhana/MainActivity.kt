package com.example.belajar_android_kalkulatorsederhana

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getBtn : Array<Button> = arrayOf(
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btn0),
            findViewById(R.id.btn00),
            findViewById(R.id.btn000),
            findViewById(R.id.btnPlus),
            findViewById(R.id.btnMin),
            findViewById(R.id.btnMultiple),
            findViewById(R.id.btnDivide),
            findViewById(R.id.btnResult),
            findViewById(R.id.btnDelete)
        )
        var etnumber = findViewById<EditText>(R.id.etNumber)
        getBtn[0].setOnClickListener {
            inputBtn(etnumber,1,"1")
        }
        getBtn[1].setOnClickListener {
            inputBtn(etnumber,1,"2")
        }
        getBtn[2].setOnClickListener {
            inputBtn(etnumber,1,"3")
        }
        getBtn[3].setOnClickListener {
            inputBtn(etnumber,1,"4")
        }
        getBtn[4].setOnClickListener {
            inputBtn(etnumber,1,"5")
        }
        getBtn[5].setOnClickListener {
            inputBtn(etnumber,1,"6")
        }
        getBtn[6].setOnClickListener {
            inputBtn(etnumber,1,"7")
        }
        getBtn[7].setOnClickListener {
            inputBtn(etnumber,1,"8")
        }
        getBtn[8].setOnClickListener {
            inputBtn(etnumber,1,"9")
        }
        getBtn[9].setOnClickListener {
            inputBtn(etnumber,1,"0")
        }
        getBtn[10].setOnClickListener {
            inputBtn(etnumber,2,"00")
        }
        getBtn[11].setOnClickListener {
            inputBtn(etnumber,3,"000")
        }
        getBtn[12].setOnClickListener {
            if(!(etnumber.selectionStart-1 < 0))
                inputBtn(etnumber,1,"+")
        }
        getBtn[13].setOnClickListener {
            if(!(etnumber.selectionStart-1 < 0))
                inputBtn(etnumber,1,"-")
        }
        getBtn[14].setOnClickListener {
            if(!(etnumber.selectionStart-1 < 0))
                inputBtn(etnumber,1,"x")
        }
        getBtn[15].setOnClickListener {
            if(!(etnumber.selectionStart-1 < 0))
                inputBtn(etnumber,1,"/")

        }
        getBtn[16].setOnClickListener {
            val sep: Array<String> = arrayOf("+","-","x","/")
            var hasil = 0
            var sepTxt: MutableList<String> = mutableListOf()
            for(text in etnumber.text.toString()){
                when (text){
                    '+' -> sepTxt.add("+") && sepTxt.add("")
                    '-' -> sepTxt.add("-") && sepTxt.add("")
                    'x' -> sepTxt.add("x") && sepTxt.add("")
                    '/' -> sepTxt.add("/") && sepTxt.add("")
                    else -> {
                        if (!(sepTxt.size == 0))
                            sepTxt[sepTxt.size-1] = sepTxt[sepTxt.size-1] + text
                        else
                            sepTxt.add("$text")
                    }
                }
            }
            while (true){
                if(sepTxt.contains("+")||sepTxt.contains("-")||sepTxt.contains("x")||sepTxt.contains("/")){
                    if(sepTxt.contains("x")){
                        sepTxt[sepTxt.indexOf("x")-1] = (sepTxt[sepTxt.indexOf("x")-1].toInt() * sepTxt[sepTxt.indexOf("x")+1].toInt()).toString()
                        sepTxt.removeAt(sepTxt.indexOf("x")+1)
                        sepTxt.removeAt(sepTxt.indexOf("x"))
                    }else if(sepTxt.contains("/")){
                        sepTxt[sepTxt.indexOf("/")-1] = (sepTxt[sepTxt.indexOf("/")-1].toInt() / sepTxt[sepTxt.indexOf("/")+1].toInt()).toString()
                        sepTxt.removeAt(sepTxt.indexOf("/")+1)
                        sepTxt.removeAt(sepTxt.indexOf("/"))
                    }else{
                        if(sepTxt.contains("+")){
                            sepTxt[sepTxt.indexOf("+")-1] = (sepTxt[sepTxt.indexOf("+")-1].toInt() + sepTxt[sepTxt.indexOf("+")+1].toInt()).toString()
                            sepTxt.removeAt(sepTxt.indexOf("+")+1)
                            sepTxt.removeAt(sepTxt.indexOf("+"))
                        }else if(sepTxt.contains("-")){
                            sepTxt[sepTxt.indexOf("-")-1] = (sepTxt[sepTxt.indexOf("-")-1].toInt() - sepTxt[sepTxt.indexOf("-")+1].toInt()).toString()
                            sepTxt.removeAt(sepTxt.indexOf("-")+1)
                            sepTxt.removeAt(sepTxt.indexOf("-"))
                        }
                    }
                }else{
                    hasil = sepTxt[0].toInt()
                    break
                }
            }
            etnumber.setText("$hasil")
            etnumber.setSelection(etnumber.length())
            Log.d("ResultCalc","$sepTxt")

        }
        getBtn[getBtn.size-1].setOnClickListener {
            if(etnumber.selectionStart-1 < 0){
            }else if(etnumber.selectionStart == etnumber.length()){
                etnumber.setText("${etnumber.text.toString().subSequence(0,etnumber.selectionStart-1)}")
                etnumber.setSelection(etnumber.length())
            }
            else{
                val textpos = etnumber.selectionStart-1
                val textEnd = etnumber.text.toString().subSequence(etnumber.selectionStart,etnumber.length())
                val textStart = etnumber.text.toString().subSequence(0,etnumber.selectionStart-1)
                val textFinal = "$textStart$textEnd"
                etnumber.setText("$textFinal")
                Log.d("textEnd","$textEnd")
                etnumber.setSelection(textpos)
            }

        }
        etnumber.setOnClickListener {
            hideKeyboard()
        }
    }
    fun inputBtn(any: EditText, length: Int,text: String){
        if (any.selectionStart == any.length()){
            any.setText("${any.text.toString() + text}")
            any.setSelection(any.length())
        }else{
            val textpos = any.selectionStart+length
            val textEnd = any.text.toString().subSequence(any.selectionStart,any.length())
            val textStart = any.text.toString().subSequence(0,any.selectionStart)
            val textBtn = text
            val textFinal = "$textStart$textBtn$textEnd"
            any.setText("$textFinal")
            Log.d("textEnd","$textEnd")
            any.setSelection(textpos)
        }
    }
    fun findIndex(arr: Array<Int>, item: Int): Int {
        return arr.indexOf(item)
    }
    fun hideKeyboard(){
        val view = currentFocus
        if(view!=null){
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}